-- ========================
-- DATABASE: autocare_db
-- ========================
CREATE DATABASE IF NOT EXISTS autocare_db;
USE autocare_db;

-- ========================
-- TABLE: users (Customers)
-- ========================
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (full_name, email, password, phone) VALUES
('Nuwan Perera', 'nuwan@example.com', 'pass123', '0712345678'),
('Iresha Silva', 'iresha@example.com', 'iresha456', '0779988776');

-- ========================
-- TABLE: staff
-- ========================
CREATE TABLE staff (
    staff_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'Mechanic', 'Fuel Staff') NOT NULL
);

INSERT INTO staff (name, email, password, role) VALUES
('Kamal Fernando', 'kamal@autocare.com', 'kamalpass', 'Admin'),
('Sanjaya Gamage', 'sanjaya@autocare.com', 'mech123', 'Mechanic'),
('Amal Dissanayake', 'amal@autocare.com', 'fuelpass', 'Fuel Staff');

-- ========================
-- TABLE: vehicles
-- ========================
CREATE TABLE vehicles (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    plate_number VARCHAR(20) NOT NULL,
    model VARCHAR(100),
    brand VARCHAR(100),
    year INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

INSERT INTO vehicles (user_id, plate_number, model, brand, year) VALUES
(1, 'CBA-1234', 'Vitz', 'Toyota', 2015),
(2, 'DGH-5678', 'Civic', 'Honda', 2018);

-- ========================
-- TABLE: service_bookings
-- ========================
CREATE TABLE service_bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    vehicle_id INT,
    service_type VARCHAR(100),
    booking_date DATE,
    status ENUM('Pending', 'In Progress', 'Completed') DEFAULT 'Pending',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) ON DELETE CASCADE
);

INSERT INTO service_bookings (user_id, vehicle_id, service_type, booking_date, status) VALUES
(1, 1, 'Oil Change', '2025-05-08', 'Pending'),
(2, 2, 'Engine Tune-up', '2025-05-09', 'In Progress');

-- ========================
-- TABLE: fuel_logs
-- ========================
CREATE TABLE fuel_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    staff_id INT,
    fuel_type ENUM('Petrol', 'Diesel') NOT NULL,
    liters DECIMAL(6,2),
    filled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) ON DELETE CASCADE,
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id) ON DELETE SET NULL
);

INSERT INTO fuel_logs (vehicle_id, staff_id, fuel_type, liters) VALUES
(1, 3, 'Petrol', 25.5),
(2, 3, 'Diesel', 30.0);

-- ========================
-- VIEW: vehicle_view
-- ========================
CREATE VIEW vehicle_view AS
SELECT 
    v.vehicle_id,
    v.plate_number,
    v.model,
    v.brand,
    v.year,
    u.full_name AS owner_name,
    u.email AS owner_email
FROM vehicles v
JOIN users u ON v.user_id = u.user_id;

-- ================================
-- VIEW: service_booking_view
-- ================================
CREATE VIEW service_booking_view AS
SELECT 
    sb.booking_id,
    sb.user_id,
    u.full_name AS customer_name,
    v.plate_number,
    v.brand,
    sb.service_type,
    sb.booking_date,
    sb.status
FROM service_bookings sb
JOIN users u ON sb.user_id = u.user_id
JOIN vehicles v ON sb.vehicle_id = v.vehicle_id;

-- ========================
-- VIEW: fuel_log_view
-- ========================
CREATE VIEW fuel_log_view AS
SELECT 
    f.log_id,
    v.plate_number,
    v.brand,
    s.name AS filled_by,
    f.fuel_type,
    f.liters,
    f.filled_at
FROM fuel_logs f
JOIN vehicles v ON f.vehicle_id = v.vehicle_id
LEFT JOIN staff s ON f.staff_id = s.staff_id;