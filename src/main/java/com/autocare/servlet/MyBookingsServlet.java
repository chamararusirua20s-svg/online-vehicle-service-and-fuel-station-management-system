package com.autocare.servlet;

import com.autocare.model.Booking;
import com.autocare.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/client/my-bookings")
public class MyBookingsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        bookingService = new BookingService();
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
            if ("cancel".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Booking booking = bookingService.getBooking(id);

                if (booking != null && booking.getBookingId() > 0) {
                    // Only allow if user owns the booking
                    if (bookingService.getBooking(id).getCustomerName().equals((String) request.getSession().getAttribute("name"))) {
                        boolean deleted = bookingService.deleteBooking(id);
                        request.getSession().setAttribute(deleted ? "success" : "error",
                                deleted ? "Booking canceled successfully." : "Failed to cancel booking.");
                    } else {
                        request.getSession().setAttribute("error", "Unauthorized to cancel this booking.");
                    }
                } else {
                    request.getSession().setAttribute("error", "Booking not found.");
                }

                response.sendRedirect(request.getContextPath() + "/client/my-bookings");

            } else {
                List<Booking> bookings = bookingService.getBookingsByUserId(userId);
                request.setAttribute("bookings", bookings);
                request.getRequestDispatcher("/client/bookings/index.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request.");
        }
    }
}