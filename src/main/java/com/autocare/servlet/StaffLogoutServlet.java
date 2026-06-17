package com.autocare.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/staff/logout")
public class StaffLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // get existing session, do not create new one
        if (session != null) {
            session.invalidate(); // destroy session
        }
        response.sendRedirect(request.getContextPath() + "/staff/login"); // redirect to staff login page
    }
}