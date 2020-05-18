package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import datastructures.GraphMatrix;
import exceptions.MaximumRangeExceededException;
import exceptions.UnavaiableBoatsException;


public class ShippingApp implements Serializable {
	private String name;
	private GraphMatrix<Country> countrys;
	private ArrayList<ShippmentReport> reports;
	public static String FLATCOUNTRYS = "./data/countrys.dat";
	public ShippingApp(String name) {
		countrys =   new GraphMatrix<Country>(false, true);
		reports = new ArrayList<>();
		this.name = name;
		load(); 
		if(countrys.consultWeight()<=0) {
			addBoats();
		}
	}
	public GraphMatrix<Country> getCountrys() {
		return countrys;
	}
	public void setCountrys(GraphMatrix<Country> countrys) {
		this.countrys = countrys;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*
	public void load() {
        try{
            FileInputStream file=new FileInputStream(FLATCOUNTRYS);
            ObjectInputStream creator=new ObjectInputStream(file);
            this.countrys=(GraphMatrix<Country>)creator.readObject();
            creator.close();
        }
        catch (IOException e) {save();} 
        catch (ClassNotFoundException e) {}
    } 
    public void save() {
        try {
        	System.out.println("save");
            FileOutputStream file=new FileOutputStream(FLATCOUNTRYS);
            ObjectOutputStream creator=new ObjectOutputStream(file);
            creator.writeObject(countrys);
            creator.close();
        }
        catch (IOException e) {}
    }
    */
	public void save() {
		try {
			File fl = new File(FLATCOUNTRYS);
			ObjectOutputStream duct = new ObjectOutputStream(new FileOutputStream(fl));
			duct.writeObject(countrys);
			duct.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public void load() {
		File file = new File(FLATCOUNTRYS);
		GraphMatrix<Country> temporalGraph;
		//Fidelization temporalFidelization;
		try {
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream co = new ObjectInputStream(fi);
			temporalGraph = (GraphMatrix<Country>) co.readObject();
			countrys = temporalGraph; 
			co.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    public int findCountryIndex(Country c) {
    	int aux = -1;
    	for (int i = 0; i < countrys.getValues().size(); i++) {
			if(c.getName().equalsIgnoreCase(countrys.getValues().get(i).getName())) {
				aux = i;
			}
		}
    	
    	return aux;
    }
    public ShippmentReport makeShipment(String originCountry, String destinyCountry,int totalLoadSize) throws UnavaiableBoatsException, MaximumRangeExceededException {
    	double deliveryTime = deliveryTime(originCountry, destinyCountry);
    	Country originCountryT = getCountryValue(originCountry);
    	Country destinyCountryT = getCountryValue(destinyCountry);
    	removeAndAdd(originCountryT,destinyCountryT);
    	ShippmentReport report = new ShippmentReport(originCountry, destinyCountry, totalLoadSize, deliveryTime);
    	reports.add(report);
    	return report;
    }
    public Country getCountryValue(String countryName) {
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
    	double deliveryTime = originCountryT.aproximateDeliverTime(countrys.dijkstra(originCountryT, destinyCountryT) /*DIJSKTRA MODIFICADO AQUI*/                          );
    	return deliveryTime;
    }
    private void removeAndAdd(Country originCountry, Country destinyCountry) throws UnavaiableBoatsException, MaximumRangeExceededException {
    	Boat auxBoat = originCountry.boatsToRemove(countrys.dijkstra(originCountry, destinyCountry));
    	destinyCountry.addBoatO(auxBoat);
    }
   
    public void addBoats() {
		Country china = new Country("china", 002123);
		Country usa = new Country("usa", 002123);
		Country jamaica = new Country("jamaica", 002123);
		Country brasil = new Country("brasil", 002123);
		Country rusia = new Country("rusia", 002123);
		Country southcorea = new Country("southcorea", 002123);
		Country australia = new Country("australia", 002123);
		china.addBoat("Titanic ll","2003", 12000, 38);
		china.addBoat("Zombies Cant swim","2012",12300,32.3);
		china.addBoat("Breaking Bass","2018",12700,33);
		usa.addBoat("Codfather","1999",14000,38.3);
		usa.addBoat("Kobe Boat","2020",13000,42.3);
		usa.addBoat("Pug Boat","2017",16000,31.3);
		jamaica.addBoat("Usain Boat","1986",7400,65);
		jamaica.addBoat("Error 404 fish not found","1969",7400,35);
		jamaica.addBoat("The wet dream","2004",7400,63);
		brasil.addBoat("Vitamin Sea","1972",10000,45.3);
		brasil.addBoat("Kayot","1990",10000,47.3);
		brasil.addBoat("Favorite Mistake","2000",10000,47.3);
		rusia.addBoat("Liquid Asset","1991",13900,42.3);
		rusia.addBoat("Vesper","1991",13900,39.3);
		rusia.addBoat("Unsinkable ll","1991",13900,41.3);
		southcorea.addBoat("Nayeon ","1995",25500,50);
		southcorea.addBoat("Twice","2015",40500,43.3);
		southcorea.addBoat("Dahyun","1998",25500,42);
		australia.addBoat("Villa Cubito Boat","2020",21300,41.3);
		australia.addBoat("Golder of the sea","2020",21300,39.3);
		australia.addBoat("Samsattas","2020",21300,60.3);
		countrys.addVertex(china);
		countrys.addVertex(usa);
		countrys.addVertex(jamaica);
		countrys.addVertex(brasil);
		countrys.addVertex(rusia);
		countrys.addVertex(southcorea);
		countrys.addVertex(australia);
		countrys.addEdge(china, usa, 10394);
		countrys.addEdge(china, jamaica, 18321);
		countrys.addEdge(china, brasil, 21443);
		countrys.addEdge(china, rusia, 2065);
		countrys.addEdge(china, southcorea, 780);
		countrys.addEdge(china, australia, 6923);
		countrys.addEdge(usa, rusia, 8513);
		countrys.addEdge(usa, jamaica, 7514);
		countrys.addEdge(usa, brasil, 10781);
		countrys.addEdge(usa, southcorea, 9682);
		countrys.addEdge(usa, australia, 11243);
		countrys.addEdge(jamaica, brasil, 3724);
		countrys.addEdge(jamaica, rusia, 16189);
		countrys.addEdge(jamaica, southcorea, 17766);
		countrys.addEdge(jamaica, australia, 16457);
		countrys.addEdge(brasil, rusia, 22782);
		countrys.addEdge(brasil, southcorea, 21147);
		countrys.addEdge(brasil, australia, 19534);
		countrys.addEdge(rusia, southcorea, 1378);
		countrys.addEdge(rusia, australia, 8545);
		countrys.addEdge(southcorea, australia, 7325);
	}
}
