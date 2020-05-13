package model;

import java.util.ArrayList;

import exceptions.MaximumCapacityExceededException;
import exceptions.MaximumRangeExceededException;
import exceptions.UnavaiableBoatsException;


public class Country {
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
			if(boats.get(i).getMaxRange()<=distance) {
				boatAvaiable = true;
			}
		}
		if(boatAvaiable == false) {
			throw new MaximumRangeExceededException("Max");
		}
	}
	/*
	public void validateFleetLoad(int load) throws MaximumCapacityExceededException, UnavaiableBoatsException {
		verifyBoatAvailability();
		int totalLoad = 0;
		for (int i = 0; i < boats.size(); i++) {
			totalLoad += boats.get(i).getMaxCapacity();
		}
		if(load<totalLoad) {
			throw new MaximumCapacityExceededException("Max");
		}
	}
	*/
	/*
	public void sortBoatsMaxToLessLoad() {
		for (int i = 1; i<boats.size(); i++){
			for(int j = i; j>0  ; j--){
				if(boats.get(j-1).getMaxCapacity() >= boats.get(j).getMaxCapacity()) {
					Boat tmp = boats.get(j);
					boats.set(j, boats.get(j-1));
					boats.set(j-1, tmp);
				}else if(boats.get(j-1).getMaxCapacity() < boats.get(j).getMaxCapacity()) {
					Boat tmp = boats.get(j-1);
					boats.set(j, tmp);
					boats.set(j-1, boats.get(j));
				}	
			}	
		}
	}
	*/
	public void sortBoatsLessToMaxRange() {
		for (int i = 1; i<boats.size(); i++){
			for(int j = i; j>0  ; j--){
				if(boats.get(j-1).getMaxRange() <= boats.get(j).getMaxRange()) {
					Boat tmp = boats.get(j);
					boats.set(j, boats.get(j-1));
					boats.set(j-1, tmp);
				}else if(boats.get(j-1).getMaxRange() > boats.get(j).getMaxRange()) {
					Boat tmp = boats.get(j-1);
					boats.set(j, tmp);
					boats.set(j-1, boats.get(j));
				}	
			}	
		}
	}
	public Boat boatsToRemove(double range, double partialRange) throws  UnavaiableBoatsException, MaximumRangeExceededException {
		validateFleetRange(range);
		sortBoatsLessToMaxRange();
		Boat boatAux = null;
		for (int i = 0; i < boats.size() && boatAux!=null; i++) {
			if(boats.get(i).getMaxRange()>=range) {
				boatAux = boats.get(i);
				boats.remove(i);
			}
		}
		return boatAux;
	}
	public double aproximateDeliverTime(double totalDistance, double partialDistance) throws  UnavaiableBoatsException, MaximumRangeExceededException {
		validateFleetRange(partialDistance);
		sortBoatsLessToMaxRange();
		double maxSpeed = 0;
		Boat boatAux = null;
		double aproximateDeliverTime = 0;
		for (int i = 0; i < boats.size() && boatAux!=null; i++) {
			if(boats.get(i).getMaxRange()>=totalDistance) {
				boatAux = boats.get(i);
				maxSpeed = boats.get(i).getMaxSpeed();
			}
		}
		aproximateDeliverTime = totalDistance/maxSpeed;
		return aproximateDeliverTime;
	}
}
