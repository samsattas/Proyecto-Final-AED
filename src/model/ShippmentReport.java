package model;

import java.io.Serializable;

public class ShippmentReport implements Serializable{
	private String destinyCountry;
	private int totalLoadSize;
	private String originCountry;
	private double aproximateDeliveryTime;
	public ShippmentReport(String originCountry, String destinyCountry,int totalLoadSize, double aproximateDeliveryTime) {
		super();
		this.originCountry = originCountry;
		this.totalLoadSize = totalLoadSize;
		this.destinyCountry = destinyCountry;
		this.aproximateDeliveryTime = aproximateDeliveryTime;
	}
	public String getDestityCountry() {
		return destinyCountry;
	}
	public void setCountry(String countrys) {
		this.destinyCountry = countrys;
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
	@Override
	public String toString() {
		return "ShippmentReport [destinyCountry=" + destinyCountry + ", totalLoadSize=" + totalLoadSize
				+ ", originCountry=" + originCountry + ", aproximateDeliveryTime=" + aproximateDeliveryTime + "]";
	}
	
	
}
