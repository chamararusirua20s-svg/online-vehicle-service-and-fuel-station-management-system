package com.autocare.servlet;

import com.autocare.model.User;
import com.autocare.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/client/register")
public class UserRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("full_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        // Server-side password validation
        if (password.length() < 8 ||
            !password.matches(".*[A-Z].*") ||
            !password.matches(".*[a-z].*") ||
            !password.matches(".*\\d.*")) {
            request.setAttribute("error", "Password must be at least 8 characters long and contain uppercase, lowercase, and a number.");
            request.getRequestDispatcher("/client/signup.jsp").forward(request, response);
            return;
        }

        // Check if email already exists
        if (userService.isEmailTaken(email)) {
            request.setAttribute("error", "Registration failed. Email may already be in use.");
            request.getRequestDispatcher("/client/signup.jsp").forward(request, response);
            return;
        }

        // Create user object
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);

        // Create user
        boolean created = userService.createUser(user);

        if (created) {
            request.setAttribute("success", "Registration successful! Redirecting to login...");
            request.getRequestDispatcher("/client/signup.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("/client/signup.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/client/dashboard");
        } else {
            request.getRequestDispatcher("/client/signup.jsp").forward(request, response);
        }
    }
}