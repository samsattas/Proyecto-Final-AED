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
	public void addBoat(String name,String manufacturingDate,int maxCapacity,double maxRange,double maxSpeed) {
		Boat boat = new Boat(name, manufacturingDate, maxCapacity, maxRange, maxSpeed);
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
		int boatsdistance = 0;
		for (int i = 0; i < boats.size(); i++) {
			boatsdistance += boats.get(i).getMaxRange();
		}
		if(distance<boatsdistance) {
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
	public void sortBoatsLessToMaxRange() {
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
	public ArrayList<Boat> boatsToRemove(int sizeOfLoad) throws MaximumCapacityExceededException, UnavaiableBoatsException {
		validateFleetLoad(sizeOfLoad);
		sortBoatsMaxToLessLoad();
		ArrayList<Boat> boatsAux = new ArrayList<>();
		int maxLoad = 0;
		for (int i = 0; i < boats.size(); i++) {
			maxLoad += boats.get(i).getMaxCapacity();
			if(maxLoad<sizeOfLoad) {
				boatsAux.add(boats.get(i));
				boats.remove(i);
			}
		}
		return boatsAux;
	}
	public double aproximateDeliverTime(double totalDistance, int loadSize) throws MaximumCapacityExceededException, UnavaiableBoatsException {
		validateFleetLoad(loadSize);
		sortBoatsMaxToLessLoad();
		int maxLoad = 0;
		int maxSpeed = 0;
		double aproximateDeliverTime = 0;
		for (int i = 0; i < boats.size(); i++) {
			maxLoad += boats.get(i).getMaxCapacity();
			if(maxLoad<loadSize) {
				maxSpeed += boats.get(i).getMaxSpeed();
			}
		}
		aproximateDeliverTime = totalDistance/maxSpeed;
		return aproximateDeliverTime;
	}
}
