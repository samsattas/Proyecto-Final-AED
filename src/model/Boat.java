package model;

public class Boat {
	private String name;
	private String manufacturingDate;
	private int maxCapacity;
	private double maxRange;
	private double maxSpeed;
	public Boat(String name, String manufacturingDate, int maxCapacity, double maxRange, double maxSpeed) {
		super();
		this.name = name;
		this.manufacturingDate = manufacturingDate;
		this.maxCapacity = maxCapacity;
		this.maxRange = maxRange;
		this.maxSpeed = maxSpeed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(String manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	public int getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public double getMaxRange() {
		return maxRange;
	}
	public void setMaxRange(double maxRange) {
		this.maxRange = maxRange;
	}
	public double getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	
}
