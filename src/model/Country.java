package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import exceptions.MaximumRangeExceededException;
import exceptions.UnavaiableBoatsException;


public class Country implements Serializable{
	private String name;
	private int id;
	private ArrayList<Boat> boats = new ArrayList<>();
	public Country(String name, int id) {
		this.name = name;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void addBoat(String name,String manufacturingDate,double maxRange,double maxSpeed) {
		Boat boat = new Boat(name, manufacturingDate, maxRange, maxSpeed);
		boats.add(boat);
	}
	public void addBoatO(Boat boat) {
		boats.add(boat);
	}
	public void verifyBoatAvailability() throws UnavaiableBoatsException {
		if(boats.size()==0) {
			throw new UnavaiableBoatsException("None");
		}
	}
	public void validateFleetRange(double distance) throws MaximumRangeExceededException, UnavaiableBoatsException {
		verifyBoatAvailability();
		boolean boatAvaiable = false;
		for (int i = 0; i < boats.size(); i++) {
			if(boats.get(i).getMaxRange()>=distance) {
				boatAvaiable = true;
			}
		}
		if(boatAvaiable == false) {
			throw new MaximumRangeExceededException("Max");
		}
	}
	public void sortBoatsLessToMaxRange() {
		Collections.sort(boats);
	}
	public Boat boatsToRemove(/*double range, */double partialRange) throws  UnavaiableBoatsException, MaximumRangeExceededException {
		validateFleetRange(partialRange);
		sortBoatsLessToMaxRange();
		Boat boatAux = null;
		for (int i = 0; i < boats.size() && boatAux==null; i++) {
			if(boats.get(i).getMaxRange()>=partialRange) {
				boatAux = boats.get(i);
				boats.remove(i);
			}
		}
		return boatAux;
	}
	public int aproximateDeliverTime(double totalDistance) throws  UnavaiableBoatsException, MaximumRangeExceededException {
		validateFleetRange(totalDistance);
		sortBoatsLessToMaxRange();
		double maxSpeed = 0;
		Boat boatAux = null;
		double aproximateDeliverTime = 0;
		for (int i = 0; i < boats.size() && boatAux==null; i++) {
			if(boats.get(i).getMaxRange()>=totalDistance) {
				boatAux = boats.get(i);
				maxSpeed = boats.get(i).getMaxSpeed();
			}
		}
		aproximateDeliverTime = (totalDistance)/maxSpeed;
		return (int) (aproximateDeliverTime/24);
	}
	public ArrayList<Boat> getBoats() {
		return boats;
	}
	public void setBoats(ArrayList<Boat> boats) {
		this.boats = boats;
	}
	@Override
	public String toString() {
		return name;
	}
	public String toStringB() {
		return "Country [name=" + name + ", id=" + id + ", boats=" + boats + "]";
	}
	
	
	
}
