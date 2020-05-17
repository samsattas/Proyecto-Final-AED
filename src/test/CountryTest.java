package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.MaximumRangeExceededException;
import exceptions.UnavaiableBoatsException;
import model.Boat;
import model.Country;

class CountryTest {
	
	public Country setUpSceneAddBoat() {
		Country country = new Country("Mordor",0);
		country.addBoat("Breaking Bass","2018",12700,33);
		country.addBoat("Titanic ll","2003", 12000, 38);
		country.addBoat("Zombies Cant swim","2012",12300,32.3);
		return country;
		
	}
	public Country setUpSceneSaveTheWorldNot() {
		Country country = new Country("Mordor",0);
		country.addBoat("Breaking Bass","2018",8700,33);
		country.addBoat("Titanic ll","2003", 8000, 38);
		country.addBoat("Zombies Cant swim","2012",8300,32.3);
		return country;
		
	}
	@Test
	void testAddBoat() {
		ArrayList<Boat> boats = setUpSceneAddBoat().getBoats();
		assertEquals("Boat [name=Breaking Bass, manufacturingDate=2018, maxRange=12700.0, maxSpeed=33.0]",boats.get(0).toString());
		assertEquals("Boat [name=Titanic ll, manufacturingDate=2003, maxRange=12000.0, maxSpeed=38.0]",boats.get(1).toString());
		assertEquals("Boat [name=Zombies Cant swim, manufacturingDate=2012, maxRange=12300.0, maxSpeed=32.3]",boats.get(2).toString());
		assertEquals(3,boats.size());
	}
	public Country setUpSceneVerifyBoatAvailability() {
		Country country = new Country("Mordor",0);
		return country;
	}
	@Test
	void testVerifyBoatAvailabilityNone() {
		Country country = setUpSceneVerifyBoatAvailability();
		try {
			country.verifyBoatAvailability();
			fail("Exception expected");
		} catch (UnavaiableBoatsException e) {
			System.out.println("Pass!");
		}
	}
	@Test
	void testVerifyBoatAvailabilityFull() {
		Country country = setUpSceneAddBoat();
		try {
			country.verifyBoatAvailability();
			System.out.println("Pass!");
		} catch (UnavaiableBoatsException e) {
			fail("Exception expected");
		}
	}
	@Test
	void testValidateFleetRangeYep() {
		Country country = setUpSceneAddBoat();
		try {
			country.validateFleetRange(12.4);
			System.out.println("Pass!");
		} catch (MaximumRangeExceededException e) {
			fail("Exception unexpected");
		} catch (UnavaiableBoatsException e) {
			fail("Exception unexpected");
		}
	}
	@Test
	void testValidateFleetRangeNope() {
		Country country = setUpSceneAddBoat();
		try {
			country.validateFleetRange(14400);
			fail("Exception expected");
		} catch (MaximumRangeExceededException e) {
			System.out.println("Pass!");
		} catch (UnavaiableBoatsException e) {
			fail("Exception unexpected");
		}
	}
	@Test
	void testSortBoats() {
		Country country = setUpSceneAddBoat();
		country.sortBoatsLessToMaxRange();
		ArrayList<Boat> boats = country.getBoats();
		assertEquals("Boat [name=Titanic ll, manufacturingDate=2003, maxRange=12000.0, maxSpeed=38.0]",boats.get(0).toString());
		assertEquals("Boat [name=Zombies Cant swim, manufacturingDate=2012, maxRange=12300.0, maxSpeed=32.3]",boats.get(1).toString());
		assertEquals("Boat [name=Breaking Bass, manufacturingDate=2018, maxRange=12700.0, maxSpeed=33.0]",boats.get(2).toString());	
	}
	
	@Test
	void testBoatsToRemove() {
		Country country = setUpSceneAddBoat();
		country.sortBoatsLessToMaxRange();
		ArrayList<Boat> boats = country.getBoats();
		try {
			country.boatsToRemove(12200);
		} catch (UnavaiableBoatsException e) {
		} catch (MaximumRangeExceededException e) {	
		}
		assertEquals("Boat [name=Titanic ll, manufacturingDate=2003, maxRange=12000.0, maxSpeed=38.0]",boats.get(0).toString());
		assertEquals("Boat [name=Breaking Bass, manufacturingDate=2018, maxRange=12700.0, maxSpeed=33.0]",boats.get(1).toString());
		assertEquals(2,boats.size());
	}
	@Test
	void testAproximateDeliverTime() {
		Country country = setUpSceneAddBoat();
		try {
			country.aproximateDeliverTime(14000,12.2);
			assertEquals(15,country.aproximateDeliverTime(14000,12.2));
		} catch (UnavaiableBoatsException e) {
		} catch (MaximumRangeExceededException e) {	
		}
	}
	@Test
	void testSaveTheWorldYes() {
		Country country = setUpSceneAddBoat();
		assertEquals("MordorDisponible",country.saveTheWorld(10000));
	}
	@Test
	void testSaveTheWorldNo() {
		Country country = setUpSceneSaveTheWorldNot();
		assertEquals("MordorNo disponible",country.saveTheWorld(10000));
	}
}
