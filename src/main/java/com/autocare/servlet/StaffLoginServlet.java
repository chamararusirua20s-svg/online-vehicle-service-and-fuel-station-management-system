package com.autocare.servlet;

import com.autocare.model.Staff;
import com.autocare.service.StaffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/staff/login")
public class StaffLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final StaffService staffService = new StaffService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("staff") != null) {
            response.sendRedirect(request.getContextPath() + "/staff/booking");
            return;
        }

        // Authenticate staff
        Staff staff = authenticateStaff(email, password);

        if (staff != null) {
            session = request.getSession(true);
            session.setAttribute("staff", staff);
            session.setAttribute("staffId", staff.getStaffId());
            session.setAttribute("name", staff.getName());
            session.setAttribute("email", staff.getEmail());
            session.setAttribute("role", staff.getRole());
            session.setMaxInactiveInterval(30 * 60); // 30 minutes

            response.sendRedirect(request.getContextPath() + "/staff/booking");
        } else {
            request.setAttribute("error", "Incorrect email or password!");
            request.getRequestDispatcher("/staff/login.jsp").forward(request, response);
        }
    }

    private Staff authenticateStaff(String email, String password) {
        List<Staff> staffList = staffService.getAllStaff();
        for (Staff staff : staffList) {
            if (staff.getEmail().equalsIgnoreCase(email) && staff.getPassword().equals(password)) {
                return staff;
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("staff") != null) {
            response.sendRedirect(request.getContextPath() + "/staff/booking");
        } else {
            request.getRequestDispatcher("/staff/login.jsp").forward(request, response);
        }
    }
}