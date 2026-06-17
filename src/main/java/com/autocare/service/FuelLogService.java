package com.autocare.service;

import com.autocare.model.FuelLog;
import com.autocare.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuelLogService {

    // Create Fuel Log
    public boolean createFuelLog(FuelLog fuelLog) {
        String query = "INSERT INTO fuel_logs (vehicle_id, staff_id, fuel_type, liters) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, fuelLog.getVehicleId());
            stmt.setInt(2, fuelLog.getStaffId());
            stmt.setString(3, fuelLog.getFuelType());
            stmt.setDouble(4, fuelLog.getLiters());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Fuel Log by ID
    public FuelLog getFuelLog(int id) {
        String query = "SELECT * FROM fuel_log_view WHERE log_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                FuelLog log = new FuelLog();
                log.setLogId(rs.getInt("log_id"));
                log.setPlateNumber(rs.getString("plate_number"));
                log.setBrand(rs.getString("brand"));
                log.setFilledBy(rs.getString("filled_by"));
                log.setFuelType(rs.getString("fuel_type"));
                log.setLiters(rs.getDouble("liters"));
                log.setFilledAt(rs.getString("filled_at"));
                return log;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get All Fuel Logs
    public List<FuelLog> getAllFuelLogs() {
        List<FuelLog> logs = new ArrayList<>();
        String query = "SELECT * FROM fuel_log_view ORDER BY log_id DESC";
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                FuelLog log = new FuelLog();
                log.setLogId(rs.getInt("log_id"));
                log.setPlateNumber(rs.getString("plate_number"));
                log.setBrand(rs.getString("brand"));
                log.setFilledBy(rs.getString("filled_by"));
                log.setFuelType(rs.getString("fuel_type"));
                log.setLiters(rs.getDouble("liters"));
                log.setFilledAt(rs.getString("filled_at"));
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    // Update Fuel Log
    public boolean updateFuelLog(FuelLog fuelLog) {
        String query = "UPDATE fuel_logs SET vehicle_id = ?, staff_id = ?, fuel_type = ?, liters = ? WHERE log_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, fuelLog.getVehicleId());
            stmt.setInt(2, fuelLog.getStaffId());
            stmt.setString(3, fuelLog.getFuelType());
            stmt.setDouble(4, fuelLog.getLiters());
            stmt.setInt(5, fuelLog.getLogId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Fuel Log
    public boolean deleteFuelLog(int id) {
        String query = "DELETE FROM fuel_logs WHERE log_id = ?";
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