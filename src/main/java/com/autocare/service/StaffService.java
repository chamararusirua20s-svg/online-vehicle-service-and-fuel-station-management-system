package com.autocare.service;

import com.autocare.model.Staff;
import com.autocare.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffService {

    // Create Staff
    public boolean createStaff(Staff staff) {
        String query = "INSERT INTO staff (name, email, password, role) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getEmail());
            stmt.setString(3, staff.getPassword());
            stmt.setString(4, staff.getRole());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Staff by ID
    public Staff getStaff(int id) {
        String query = "SELECT * FROM staff WHERE staff_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getInt("staff_id"));
                staff.setName(rs.getString("name"));
                staff.setEmail(rs.getString("email"));
                staff.setPassword(rs.getString("password"));
                staff.setRole(rs.getString("role"));
                return staff;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get All Staff
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM staff ORDER BY staff_id";
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getInt("staff_id"));
                staff.setName(rs.getString("name"));
                staff.setEmail(rs.getString("email"));
                staff.setPassword(rs.getString("password"));
                staff.setRole(rs.getString("role"));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    // Update Staff
    public boolean updateStaff(Staff staff) {
        String query = "UPDATE staff SET name = ?, email = ?, password = ?, role = ? WHERE staff_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getEmail());
            stmt.setString(3, staff.getPassword());
            stmt.setString(4, staff.getRole());
            stmt.setInt(5, staff.getStaffId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Staff
    public boolean deleteStaff(int id) {
        String query = "DELETE FROM staff WHERE staff_id = ?";
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