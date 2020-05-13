package model;

import java.util.ArrayList;

public class ShippmentReport {
	private String country;
	private int totalLoadSize;
	private String originCountry;
	private double aproximateDeliveryTime;
	public ShippmentReport(String originCountry, String destinyCountry,int totalLoadSize, double aproximateDeliveryTime) {
		super();
		this.country = country;
		this.totalLoadSize = totalLoadSize;
		this.originCountry = originCountry;
		this.aproximateDeliveryTime = aproximateDeliveryTime;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String countrys) {
		this.country = countrys;
	}
	public int getTotalLoadSize() {
		return totalLoadSize;
	}
	public void setTotalLoadSize(int totalLoadSize) {
		this.totalLoadSize = totalLoadSize;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public double getAproximateDeliveryTime() {
		return aproximateDeliveryTime;
	}
	public void setAproximateDeliveryTime(double aproximateDeliveryTime) {
		this.aproximateDeliveryTime = aproximateDeliveryTime;
	}
}
