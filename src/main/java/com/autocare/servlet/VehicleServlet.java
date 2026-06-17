package com.autocare.servlet;

import com.autocare.model.Vehicle;
import com.autocare.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/staff/vehicle")
public class VehicleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        vehicleService = new VehicleService();
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
                // List all vehicles
                List<Vehicle> vehicles = vehicleService.getAllVehicles();
                request.setAttribute("vehicles", vehicles);
                request.getRequestDispatcher("/staff/vehicles/index.jsp").forward(request, response);

            } else if ("create".equals(action)) {
                request.getRequestDispatcher("/staff/vehicles/create.jsp").forward(request, response);

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Vehicle vehicle = vehicleService.getVehicle(id);
                if (vehicle != null) {
                    request.setAttribute("vehicle", vehicle);
                    request.getRequestDispatcher("/staff/vehicles/update.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Vehicle not found.");
                }

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                vehicleService.deleteVehicle(id);
                response.sendRedirect(request.getContextPath() + "/staff/vehicle");

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
                Vehicle vehicle = new Vehicle();
                vehicle.setUserId(Integer.parseInt(request.getParameter("user_id")));
                vehicle.setPlateNumber(request.getParameter("plate_number"));
                vehicle.setModel(request.getParameter("model"));
                vehicle.setBrand(request.getParameter("brand"));
                vehicle.setYear(Integer.parseInt(request.getParameter("year")));

                if (vehicleService.createVehicle(vehicle)) {
                    response.sendRedirect(request.getContextPath() + "/staff/vehicle");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create vehicle.");
                }

            } else if ("update".equals(action)) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(Integer.parseInt(request.getParameter("id")));
                vehicle.setUserId(Integer.parseInt(request.getParameter("user_id")));
                vehicle.setPlateNumber(request.getParameter("plate_number"));
                vehicle.setModel(request.getParameter("model"));
                vehicle.setBrand(request.getParameter("brand"));
                vehicle.setYear(Integer.parseInt(request.getParameter("year")));

                if (vehicleService.updateVehicle(vehicle)) {
                    response.sendRedirect(request.getContextPath() + "/staff/vehicle");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update vehicle.");
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