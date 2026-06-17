package com.autocare.servlet;

import com.autocare.model.Booking;
import com.autocare.model.Vehicle;
import com.autocare.service.BookingService;
import com.autocare.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/client/book-service")
public class BookServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private BookingService bookingService;
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        bookingService = new BookingService();
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

        try {
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            Vehicle vehicle = vehicleService.getVehicle(vehicleId);
            if (vehicle != null && vehicle.getUserId() == userId) {
                request.setAttribute("vehicle", vehicle);
                request.getRequestDispatcher("/client/bookings/book.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid vehicle access.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = validateSession(request, response);
        if (userId == null) return;

        try {
            int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));
            String serviceType = request.getParameter("service_type");
            String bookingDate = request.getParameter("booking_date");

            Booking booking = new Booking();
            booking.setUserId(userId);
            booking.setVehicleId(vehicleId);
            booking.setServiceType(serviceType);
            booking.setBookingDate(bookingDate);
            booking.setStatus("Pending");

            HttpSession session = request.getSession();
            if (bookingService.createBooking(booking)) {
                session.setAttribute("success", "Booking created successfully.");
            } else {
                session.setAttribute("error", "Failed to create booking. Try again.");
            }
            response.sendRedirect(request.getContextPath() + "/client/my-bookings");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "An error occurred while booking.");
            response.sendRedirect(request.getContextPath() + "/client/my-bookings");
        }
    }
}