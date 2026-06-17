package com.autocare.servlet;

import com.autocare.model.User;
import com.autocare.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/client/login")
public class UserLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/client/vehicle");
            return;
        }

        // Authenticate user
        User user = authenticateUser(email, password);

        if (user != null) {
            session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("name", user.getFullName());
            session.setAttribute("email", user.getEmail());
            session.setMaxInactiveInterval(30 * 60); // 30 minutes

            response.sendRedirect(request.getContextPath() + "/client/vehicle");
        } else {
            request.setAttribute("error", "Incorrect email or password!");
            request.getRequestDispatcher("/client/login.jsp").forward(request, response);
        }
    }

    private User authenticateUser(String email, String password) {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/client/vehicle");
        } else {
            request.getRequestDispatcher("/client/login.jsp").forward(request, response);
        }
    }
}