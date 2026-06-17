package com.autocare.servlet;

import com.autocare.model.Vehicle;
import com.autocare.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/client/vehicle")
public class MyVehicleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        vehicleService = new VehicleService();
    }

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

        String action = request.getParameter("action");

        try {
            if (action == null) {
                List<Vehicle> vehicles = vehicleService.getVehiclesByUserId(userId);
                request.setAttribute("vehicles", vehicles);
                request.getRequestDispatcher("/client/vehicles/index.jsp").forward(request, response);

            } else if ("create".equals(action)) {
                request.getRequestDispatcher("/client/vehicles/create.jsp").forward(request, response);

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Vehicle vehicle = vehicleService.getVehicle(id);
                if (vehicle != null && vehicle.getUserId() == userId) {
                    request.setAttribute("vehicle", vehicle);
                    request.getRequestDispatcher("/client/vehicles/update.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
                }

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Vehicle vehicle = vehicleService.getVehicle(id);
                if (vehicle != null && vehicle.getUserId() == userId) {
                    vehicleService.deleteVehicle(id);
                    response.sendRedirect(request.getContextPath() + "/client/vehicle");
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
                }

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = validateSession(request, response);
        if (userId == null) return;

        String action = request.getParameter("action");

        try {
            if ("create".equals(action)) {
                Vehicle vehicle = new Vehicle();
                vehicle.setUserId(userId);
                vehicle.setPlateNumber(request.getParameter("plate_number"));
                vehicle.setModel(request.getParameter("model"));
                vehicle.setBrand(request.getParameter("brand"));
                vehicle.setYear(Integer.parseInt(request.getParameter("year")));

                if (vehicleService.createVehicle(vehicle)) {
                    response.sendRedirect(request.getContextPath() + "/client/vehicle");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create vehicle.");
                }

            } else if ("update".equals(action)) {
                int vehicleId = Integer.parseInt(request.getParameter("id"));
                Vehicle vehicle = vehicleService.getVehicle(vehicleId);

                if (vehicle != null && vehicle.getUserId() == userId) {
                    vehicle.setPlateNumber(request.getParameter("plate_number"));
                    vehicle.setModel(request.getParameter("model"));
                    vehicle.setBrand(request.getParameter("brand"));
                    vehicle.setYear(Integer.parseInt(request.getParameter("year")));

                    if (vehicleService.updateVehicle(vehicle)) {
                        response.sendRedirect(request.getContextPath() + "/client/vehicle");
                    } else {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update vehicle.");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
                }

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
        }
    }
}