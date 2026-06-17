package com.autocare.service;

import com.autocare.model.Booking;
import com.autocare.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    // Create Booking
    public boolean createBooking(Booking booking) {
        String query = "INSERT INTO service_bookings (user_id, vehicle_id, service_type, booking_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getVehicleId());
            stmt.setString(3, booking.getServiceType());
            stmt.setString(4, booking.getBookingDate());
            stmt.setString(5, booking.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Get bookings by user ID
    public List<Booking> getBookingsByUserId(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM service_booking_view WHERE user_id = ? ORDER BY booking_id DESC";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setCustomerName(rs.getString("customer_name"));
                booking.setPlateNumber(rs.getString("plate_number"));
                booking.setBrand(rs.getString("brand"));
                booking.setServiceType(rs.getString("service_type"));
                booking.setBookingDate(rs.getString("booking_date"));
                booking.setStatus(rs.getString("status"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    
    // Update only status of Booking
    public boolean updateBookingStatus(int bookingId, String status) {
        String query = "UPDATE service_bookings SET status = ? WHERE booking_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, bookingId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Booking by ID
    public Booking getBooking(int id) {
        String query = "SELECT * FROM service_booking_view WHERE booking_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setCustomerName(rs.getString("customer_name"));
                booking.setPlateNumber(rs.getString("plate_number"));
                booking.setBrand(rs.getString("brand"));
                booking.setServiceType(rs.getString("service_type"));
                booking.setBookingDate(rs.getString("booking_date"));
                booking.setStatus(rs.getString("status"));
                return booking;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get All Bookings
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM service_booking_view ORDER BY booking_id DESC";
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setCustomerName(rs.getString("customer_name"));
                booking.setPlateNumber(rs.getString("plate_number"));
                booking.setBrand(rs.getString("brand"));
                booking.setServiceType(rs.getString("service_type"));
                booking.setBookingDate(rs.getString("booking_date"));
                booking.setStatus(rs.getString("status"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Update Booking
    public boolean updateBooking(Booking booking) {
        String query = "UPDATE service_bookings SET user_id = ?, vehicle_id = ?, service_type = ?, booking_date = ?, status = ? WHERE booking_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getVehicleId());
            stmt.setString(3, booking.getServiceType());
            stmt.setString(4, booking.getBookingDate());
            stmt.setString(5, booking.getStatus());
            stmt.setInt(6, booking.getBookingId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Booking
    public boolean deleteBooking(int id) {
        String query = "DELETE FROM service_bookings WHERE booking_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}