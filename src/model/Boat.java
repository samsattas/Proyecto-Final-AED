package model;

import java.io.Serializable;

public class Boat implements Comparable<Boat>, Serializable{
	private String name;
	private String manufacturingDate;
	private double maxRange;
	private double maxSpeed;
	public Boat(String name, String manufacturingDate, double maxRange, double maxSpeed) {
		super();
		this.name = name;
		this.manufacturingDate = manufacturingDate;
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
	
	@Override
	public String toString() {
		return "Boat [name=" + name + ", manufacturingDate=" + manufacturingDate + ", maxRange=" + maxRange
				+ ", maxSpeed=" + maxSpeed + "]";
	}
	@Override
	public int compareTo(Boat b) {
		if(maxRange <= b.getMaxRange()) {
			return -1;
		}
		if(maxRange > b.getMaxRange()) {
			return 1;
		}
		return 0;
	}
	
	
}
