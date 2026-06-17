package com.autocare.service;

import com.autocare.model.Vehicle;
import com.autocare.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleService {

    // Create Vehicle
    public boolean createVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (user_id, plate_number, model, brand, year) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicle.getUserId());
            stmt.setString(2, vehicle.getPlateNumber());
            stmt.setString(3, vehicle.getModel());
            stmt.setString(4, vehicle.getBrand());
            stmt.setInt(5, vehicle.getYear());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Use the original table to get user_id for validation
    public Vehicle getVehicle(int id) {
        String query = "SELECT * FROM vehicles WHERE vehicle_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt("vehicle_id"));
                vehicle.setUserId(rs.getInt("user_id")); // this is the key field!
                vehicle.setPlateNumber(rs.getString("plate_number"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setYear(rs.getInt("year"));
                return vehicle;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Vehicle> getVehiclesByUserId(int userId) throws Exception {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt("vehicle_id"));
                vehicle.setUserId(rs.getInt("user_id"));
                vehicle.setPlateNumber(rs.getString("plate_number"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setYear(rs.getInt("year"));
                list.add(vehicle);
            }
        }
        return list;
    }

    // Get All Vehicles
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicle_view ORDER BY vehicle_id DESC";
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt("vehicle_id"));
                vehicle.setPlateNumber(rs.getString("plate_number"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setOwnerName(rs.getString("owner_name"));
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Update Vehicle
    public boolean updateVehicle(Vehicle vehicle) {
        String query = "UPDATE vehicles SET user_id = ?, plate_number = ?, model = ?, brand = ?, year = ? WHERE vehicle_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicle.getUserId());
            stmt.setString(2, vehicle.getPlateNumber());
            stmt.setString(3, vehicle.getModel());
            stmt.setString(4, vehicle.getBrand());
            stmt.setInt(5, vehicle.getYear());
            stmt.setInt(6, vehicle.getVehicleId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Vehicle
    public boolean deleteVehicle(int id) {
        String query = "DELETE FROM vehicles WHERE vehicle_id = ?";
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