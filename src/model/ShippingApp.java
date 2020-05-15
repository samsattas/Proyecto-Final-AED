package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import datastructures.Grafov2;
import exceptions.MaximumRangeExceededException;
import exceptions.UnavaiableBoatsException;

public class ShippingApp {
	private String name;
	Grafov2<Country> countrys = new Grafov2<Country>(false, true);
	//private ArrayList<Country> countrys;
	public static String FLATCOUNTRYS = "data//Contrys.txt";
	public ShippingApp(String name) {
		super();
		this.name = name;
		load();
		if(countrys.consultWeight()<=0) {
			addBoats();
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void load() {
        try{
            FileInputStream file=new FileInputStream(FLATCOUNTRYS);
            ObjectInputStream creator=new ObjectInputStream(file);
            this.countrys=(Grafov2<Country>)creator.readObject();
            creator.close();
        }
        catch (IOException e) {save();} 
        catch (ClassNotFoundException e) {}
    }
    public void save() {
        try {
            FileOutputStream file=new FileOutputStream(FLATCOUNTRYS);
            ObjectOutputStream creator=new ObjectOutputStream(file);
            creator.writeObject(countrys);
            creator.close();
        }
        catch (IOException e) {}
    }
   /*
    public double aproximateDeliverTime(double totalDistance, String countryName, int loadSize) throws MaximumCapacityExceededException, UnavaiableBoatsException {
		double time = 0;
		for (int i = 0; i < countrys.size(); i++) {
			if(countrys.get(i).getName().equals(countryName)) {
				time = countrys.get(i).aproximateDeliverTime(totalDistance, loadSize);
			}
		}
		return time;
	}
    */
    
    public ShippmentReport makeShipment(String originCountry, String destinyCountry,int totalLoadSize) throws UnavaiableBoatsException, MaximumRangeExceededException {
    	double deliveryTime = deliveryTime(originCountry, destinyCountry);
    	Country originCountryT = getCountryValue(originCountry);
    	Country destinyCountryT = getCountryValue(destinyCountry);
    	removeAndAdd(originCountryT,destinyCountryT);
    	ShippmentReport report = new ShippmentReport(originCountry, destinyCountry, totalLoadSize, deliveryTime);
    	return report;
    }
    private Country getCountryValue(String countryName) {
    	Country country = null;
    	for (int i = 0; i < countrys.getValues().size(); i++) {
			if(countrys.getValues().get(i).getName().equals(countryName)) {
				country = countrys.getValues().get(i);
			}
		}
    	return country;
    }
    public double deliveryTime(String originCountry, String destinyCountry) throws UnavaiableBoatsException, MaximumRangeExceededException {
    	Country originCountryT = getCountryValue(originCountry);
    	Country destinyCountryT = getCountryValue(destinyCountry);
    	double deliveryTime = originCountryT.aproximateDeliverTime(countrys.dijkstra(originCountryT, destinyCountryT), /*DIJSKTRA MODIFICADO AQUI*/      0                    );
    	return deliveryTime;
    }
    private void removeAndAdd(Country originCountry, Country destinyCountry) throws UnavaiableBoatsException, MaximumRangeExceededException {
    	Boat auxBoat = originCountry.boatsToRemove(countrys.dijkstra(originCountry, destinyCountry),/*DIJSKTRA MODIFICADO AQUI*/      0                     );
    	destinyCountry.addBoatO(auxBoat);
    }
    public String[] saveTheWorld(){
    	String[] countrysName = new String[countrys.getValues().size()];
    	double kruskalWeight = 0;
    	Grafov2<Country> tmpGraph = countrys;
    	tmpGraph.kruskal();
    	/*
    	 * AQUI VA ALGORITMO QUE SUMA EL VALOR DE TODAS LAS ARISTAS 
    	 */
    	for (int i = 0; i < countrys.getValues().size(); i++) {
    		countrysName[i] = countrys.getValues().get(i).saveTheWorld(kruskalWeight);
		}
    	return countrysName; 
    }
    public void addBoats() {
		Country china = new Country("china", 002123);
		Country usa = new Country("usa", 002123);
		Country jamaica = new Country("jamaica", 002123);
		Country brasil = new Country("brasil", 002123);
		Country rusia = new Country("rusia", 002123);
		Country southcorea = new Country("southcorea", 002123);
		Country australia = new Country("australia", 002123);
		
		china.addBoat("Titanic ll","2003", 12, 38);
		china.addBoat("Zombies Cant swim","2012",12.3,32.3);
		china.addBoat("Breaking Bass","2018",12.7,33);
		usa.addBoat("Codfather","1999",14,38.3);
		usa.addBoat("Kobe Boat","2020",13,42.3);
		usa.addBoat("Pug Boat","2017",16,31.3);
		jamaica.addBoat("Usain Boat","1986",7.4,65);
		jamaica.addBoat("Error 404 fish not found","1969",7.4,35);
		jamaica.addBoat("The wet dream","2004",7.4,63);
		brasil.addBoat("Vitamin Sea","1972",10,45.3);
		brasil.addBoat("Kayot","1990",10,47.3);
		brasil.addBoat("Favorite Mistake","2000",10,47.3);
		rusia.addBoat("Liquid Asset","1991",13.9,42.3);
		rusia.addBoat("Vesper","1991",13.9,39.3);
		rusia.addBoat("Unsinkable ll","1991",13.9,41.3);
		southcorea.addBoat("Nayeon ","1995",25.5,50);
		southcorea.addBoat("Twice","2015",40.5,43.3);
		southcorea.addBoat("Dahyun","1998",25.5,42);
		australia.addBoat("Villa Cubito Boat","2020",21.3,41.3);
		australia.addBoat("Golder of the sea","2020",21.3,39.3);
		australia.addBoat("Samsattas","2020",21.3,60.3);
		
		countrys.addVertex(china);
		countrys.addVertex(usa);
		countrys.addVertex(jamaica);
		countrys.addVertex(brasil);
		countrys.addVertex(rusia);
		countrys.addVertex(southcorea);
		countrys.addVertex(australia);
	}

	
}
