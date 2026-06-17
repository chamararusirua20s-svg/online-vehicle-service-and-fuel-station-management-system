package com.autocare.model;

public class Vehicle {
	private int vehicleId;
    private int userId;
    private String plateNumber;
    private String model;
    private String brand;
    private int year;

    // from view table
    private String ownerName;

	public int getVehicleId() {
		return vehicleId;
	}

	public int getUserId() {
		return userId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public String getModel() {
		return model;
	}

	public String getBrand() {
		return brand;
	}

	public int getYear() {
		return year;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}