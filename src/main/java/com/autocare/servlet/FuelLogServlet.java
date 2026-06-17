package com.autocare.servlet;

import com.autocare.model.FuelLog;
import com.autocare.model.Vehicle;
import com.autocare.service.FuelLogService;
import com.autocare.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/staff/fuel-log")
public class FuelLogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FuelLogService fuelLogService;
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        fuelLogService = new FuelLogService();
        vehicleService = new VehicleService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("staff") == null) {
            response.sendRedirect(request.getContextPath() + "/staff/login");
            return;
        }

        String action = request.getParameter("action");

        try {
            if (action == null) {
                // FIXED: Set correct attribute name
                request.setAttribute("fuelLogs", fuelLogService.getAllFuelLogs());
                request.getRequestDispatcher("/staff/fuel-logs/index.jsp").forward(request, response);

            } else if ("create".equals(action)) {
                List<Vehicle> vehicles = vehicleService.getAllVehicles();
                request.setAttribute("vehicles", vehicles);
                request.getRequestDispatcher("/staff/fuel-logs/create.jsp").forward(request, response);

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                FuelLog log = fuelLogService.getFuelLog(id);
                if (log != null) {
                    request.setAttribute("log", log);
                    request.setAttribute("vehicles", vehicleService.getAllVehicles());
                    request.getRequestDispatcher("/staff/fuel-logs/update.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Fuel log not found.");
                }

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                fuelLogService.deleteFuelLog(id);
                response.sendRedirect(request.getContextPath() + "/staff/fuel-log");

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
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("staffId") == null) {
            response.sendRedirect(request.getContextPath() + "/staff/login");
            return;
        }

        String action = request.getParameter("action");

        try {
            int staffId = (int) session.getAttribute("staffId");

            if ("create".equals(action)) {
                FuelLog log = new FuelLog();
                log.setVehicleId(Integer.parseInt(request.getParameter("vehicleId")));
                log.setStaffId(staffId);
                log.setFuelType(request.getParameter("fuelType"));
                log.setLiters(Double.parseDouble(request.getParameter("liters")));

                if (fuelLogService.createFuelLog(log)) {
                    response.sendRedirect(request.getContextPath() + "/staff/fuel-log");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create fuel log.");
                }

            } else if ("update".equals(action)) {
                FuelLog log = new FuelLog();
                log.setLogId(Integer.parseInt(request.getParameter("id")));
                log.setVehicleId(Integer.parseInt(request.getParameter("vehicleId")));
                log.setStaffId(staffId);
                log.setFuelType(request.getParameter("fuelType"));
                log.setLiters(Double.parseDouble(request.getParameter("liters")));

                if (fuelLogService.updateFuelLog(log)) {
                    response.sendRedirect(request.getContextPath() + "/staff/fuel-log");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update fuel log.");
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