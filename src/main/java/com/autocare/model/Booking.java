package com.autocare.model;

public class Booking {
	private int bookingId;
    private int userId;
    private int vehicleId;
    private String serviceType;
    private String bookingDate;
    private String status;

    // from view table
    private String customerName;
    private String plateNumber;
    private String brand;
    
	public int getBookingId() {
		return bookingId;
	}
	public int getUserId() {
		return userId;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public String getServiceType() {
		return serviceType;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public String getStatus() {
		return status;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public String getBrand() {
		return brand;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
}