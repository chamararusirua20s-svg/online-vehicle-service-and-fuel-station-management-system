package com.autocare.servlet;

import com.autocare.model.User;
import com.autocare.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/client/profile")
public class UserProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserService();

    private Integer validateSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null || session.getAttribute("user") == null) {
            if (session != null) session.invalidate();
            response.sendRedirect(request.getContextPath() + "/client/login");
            return null;
        }
        return (Integer) session.getAttribute("userId");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = validateSession(request, response);
        if (userId == null) return;

        User user = userService.getUser(userId);
        request.setAttribute("userProfile", user);
        request.getRequestDispatcher("/client/profile/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = validateSession(request, response);
        if (userId == null) return;

        String fullName = request.getParameter("full_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        // Password validation (server-side)
        if (password.length() < 8 ||
            !password.matches(".*[A-Z].*") ||
            !password.matches(".*[a-z].*") ||
            !password.matches(".*\\d.*")) {
            request.setAttribute("error", "Password must be at least 8 characters long and contain uppercase, lowercase, and a number.");
            request.setAttribute("userProfile", userService.getUser(userId));
            request.getRequestDispatcher("/client/profile/edit.jsp").forward(request, response);
            return;
        }

        // Create updated user object
        User user = new User();
        user.setUserId(userId);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);

        boolean updated = userService.updateUser(user);

        if (updated) {
            request.setAttribute("success", "Profile updated successfully.");
        } else {
            request.setAttribute("error", "Failed to update profile. Please try again.");
        }

        request.setAttribute("userProfile", userService.getUser(userId));
        request.getRequestDispatcher("/client/profile/edit.jsp").forward(request, response);
    }
}