package com.autocare.model;

public class FuelLog {
	private int logId;
    private int vehicleId;
    private int staffId;
    private String fuelType;
    private double liters;
    private String filledAt;

    // from view table
    private String plateNumber;
    private String brand;
    private String filledBy;
    
	public int getLogId() {
		return logId;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public int getStaffId() {
		return staffId;
	}
	public String getFuelType() {
		return fuelType;
	}
	public double getLiters() {
		return liters;
	}
	public String getFilledAt() {
		return filledAt;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public String getBrand() {
		return brand;
	}
	public String getFilledBy() {
		return filledBy;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public void setLiters(double liters) {
		this.liters = liters;
	}
	public void setFilledAt(String filledAt) {
		this.filledAt = filledAt;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setFilledBy(String filledBy) {
		this.filledBy = filledBy;
	}
}