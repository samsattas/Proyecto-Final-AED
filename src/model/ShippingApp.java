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
import exceptions.MaximumCapacityExceededException;
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
	public ArrayList<Country> createData() {
			ArrayList<Country> c = new ArrayList<>();
			try {
				File	file = new File(FLATCOUNTRYS);
				FileReader	frReader = new FileReader(file);
				BufferedReader	bufferRead = new BufferedReader(frReader);
				String saveString;
					while((saveString = bufferRead.readLine())!= null){
						String[] parts = saveString.split(",");
						String part1 = parts[0];
						String part2 = parts[1];
						c.add(new Country(part1, Integer.parseInt(part2)));
				}
				bufferRead.close();
				frReader.close();
			}
			catch(Exception e){
				System.out.println("Ayyyy que man tan de malas");
				e.printStackTrace();
			}
			return c;
	}
	public void addBoats() {
		Country china = new Country("china", 002123);
		Country usa = new Country("usa", 002123);
		Country jamaica = new Country("jamaica", 002123);
		Country brasil = new Country("brasil", 002123);
		Country rusia = new Country("rusia", 002123);
		Country southcorea = new Country("southcorea", 002123);
		Country australia = new Country("australia", 002123);
		
		china.addBoat("Titanic ll","2003",13420, 12, 38);
		china.addBoat("Zombies Cant swim","2012",14000,12.3,32.3);
		china.addBoat("Breaking Bass","2018",15000,12.7,33);
		usa.addBoat("Codfather","1999",14360,14,38.3);
		usa.addBoat("Kobe Boat","2020",12450,13,42.3);
		usa.addBoat("Pug Boat","2017",16330,16,31.3);
		jamaica.addBoat("Usain Boat","1986",7892,7.4,65);
		jamaica.addBoat("Error 404 fish not found","1969",1500,7.4,35);
		jamaica.addBoat("The wet dream","2004",7777,7.4,63);
		brasil.addBoat("Vitamin Sea","1972",4400,10,45.3);
		brasil.addBoat("Kayot","1990",5500,10,47.3);
		brasil.addBoat("Favorite Mistake","2000",3000,10,47.3);
		rusia.addBoat("Liquid Asset","1991",14500,13.9,42.3);
		rusia.addBoat("Vesper","1991",14300,13.9,39.3);
		rusia.addBoat("Unsinkable ll","1991",14350,13.9,41.3);
		southcorea.addBoat("Nayeon ","1995",17000,25.5,50);
		southcorea.addBoat("Twice","2015",20130,40.5,43.3);
		southcorea.addBoat("Dahyun","1998",14000,25.5,42);
		australia.addBoat("Villa Cubito Boat","2020",16450,21.3,41.3);
		australia.addBoat("Golder of the sea","2020",15211,21.3,39.3);
		australia.addBoat("Samsattas","2020",8754,21.3,60.3);
		
		countrys.addVertex(china);
		countrys.addVertex(usa);
		countrys.addVertex(jamaica);
		countrys.addVertex(brasil);
		countrys.addVertex(rusia);
		countrys.addVertex(southcorea);
		countrys.addVertex(australia);
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
    public void makeShipment(String originCountry, String destinyCountry, ArrayList<String> countrysS, int loadSize) throws MaximumCapacityExceededException, UnavaiableBoatsException {
    	double deliveryTime = deliveryTime(originCountry, destinyCountry, loadSize);
    	Country originCountryT = getCountryValue(originCountry);
    	Country destinyCountryT = getCountryValue(destinyCountry);
    	removeAndAdd(originCountryT,destinyCountryT, loadSize);
    	originCountryT.b
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
    public double deliveryTime(String originCountry, String destinyCountry, int loadSize) throws MaximumCapacityExceededException, UnavaiableBoatsException {
    	Country originCountryT = getCountryValue(originCountry);
    	Country destinyCountryT = getCountryValue(destinyCountry);
    	double deliveryTime = originCountryT.aproximateDeliverTime(countrys.dijkstra(originCountryT, destinyCountryT), loadSize);
    	return deliveryTime;
    }
    private void removeAndAdd(Country originCountry, Country destinyCountry, int loadSize) throws MaximumCapacityExceededException, UnavaiableBoatsException {
    	ArrayList<Boat> boats = originCountry.boatsToRemove(loadSize);
    	for (int i = 0; i < boats.size(); i++) {
    		destinyCountry.addBoatO(boats.get(i));
		}
    }

	
}
