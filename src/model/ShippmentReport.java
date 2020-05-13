package model;

import java.util.ArrayList;

public class ShippmentReport {
	private ArrayList<String> countrys;
	private int totalLoadSize;
	private String originCountry;
	private double aproximateDeliveryTime;
	public ShippmentReport(ArrayList<String> countrys, int totalLoadSize, String originCountry,
			double aproximateDeliveryTime) {
		super();
		this.countrys = countrys;
		this.totalLoadSize = totalLoadSize;
		this.originCountry = originCountry;
		this.aproximateDeliveryTime = aproximateDeliveryTime;
	}
	public ArrayList<String> getCountrys() {
		return countrys;
	}
	public void setCountrys(ArrayList<String> countrys) {
		this.countrys = countrys;
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
