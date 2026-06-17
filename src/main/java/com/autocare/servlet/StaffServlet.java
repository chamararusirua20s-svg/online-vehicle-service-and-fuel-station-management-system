package com.autocare.servlet;

import com.autocare.model.Staff;
import com.autocare.service.StaffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/staff/staff")
public class StaffServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StaffService staffService;

    @Override
    public void init() throws ServletException {
        staffService = new StaffService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Session check
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("staff") == null) {
            response.sendRedirect(request.getContextPath() + "/staff/login");
            return;
        }

        String action = request.getParameter("action");

        try {
            if (action == null) {
                // List all staff
                request.setAttribute("staffList", staffService.getAllStaff());
                request.getRequestDispatcher("/staff/staffs/index.jsp").forward(request, response);

            } else if ("create".equals(action)) {
                request.getRequestDispatcher("/staff/staffs/create.jsp").forward(request, response);

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Staff staff = staffService.getStaff(id);
                if (staff != null) {
                    request.setAttribute("staff", staff);
                    request.getRequestDispatcher("/staff/staffs/update.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Staff member not found.");
                }

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                staffService.deleteStaff(id);
                response.sendRedirect(request.getContextPath() + "/staff/staff");

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Session check
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("staff") == null) {
            response.sendRedirect(request.getContextPath() + "/staff/login");
            return;
        }

        String action = request.getParameter("action");

        try {
            if ("create".equals(action)) {
                Staff staff = new Staff();
                staff.setName(request.getParameter("name"));
                staff.setEmail(request.getParameter("email"));
                staff.setPassword(request.getParameter("password"));
                staff.setRole(request.getParameter("role"));

                if (staffService.createStaff(staff)) {
                    response.sendRedirect(request.getContextPath() + "/staff/staff");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create staff.");
                }

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Staff staff = new Staff();
                staff.setStaffId(id);
                staff.setName(request.getParameter("name"));
                staff.setEmail(request.getParameter("email"));
                staff.setPassword(request.getParameter("password"));
                staff.setRole(request.getParameter("role"));

                if (staffService.updateStaff(staff)) {
                    response.sendRedirect(request.getContextPath() + "/staff/staff");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update staff.");
                }

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }
}