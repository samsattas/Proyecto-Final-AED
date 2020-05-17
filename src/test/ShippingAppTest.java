package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.MaximumRangeExceededException;
import exceptions.UnavaiableBoatsException;
import model.ShippingApp;

class ShippingAppTest {
	public ShippingApp setUpSceneGetCountryValue() {
		ShippingApp shipping = new ShippingApp("ASSOITASSHIPPINGINTERNATIONAL");
		return shipping;
	}
	@Test
	void testGetCountryValue() {
		ShippingApp shipping = setUpSceneGetCountryValue();
		assertEquals("Country [name=china, id=1107, boats=[Boat [name=Titanic ll, manufacturingDate=2003, maxRange=12000.0, maxSpeed=38.0], Boat [name=Zombies Cant swim, manufacturingDate=2012, maxRange=12300.0, maxSpeed=32.3], Boat [name=Breaking Bass, manufacturingDate=2018, maxRange=12700.0, maxSpeed=33.0]]]",shipping.getCountryValue("china").toString());
	}
	@Test
	void testDeliveryTime() {
		ShippingApp shipping = setUpSceneGetCountryValue();
		try {
			assertEquals(2,shipping.deliveryTime("china", "rusia"));
		} catch (UnavaiableBoatsException e) {

		} catch (MaximumRangeExceededException e) {
			
		}
	}
	@Test
	void testMakeShipment() {
		ShippingApp shipping = setUpSceneGetCountryValue();
		try {
			assertEquals("ShippmentReport [destinyCountry=rusia, totalLoadSize=14555, originCountry=china, aproximateDeliveryTime=2.0]",shipping.makeShipment("china", "rusia", 14555).toString());
			assertEquals("Country [name=china, id=1107, boats=[Boat [name=Zombies Cant swim, manufacturingDate=2012, maxRange=12300.0, maxSpeed=32.3], Boat [name=Breaking Bass, manufacturingDate=2018, maxRange=12700.0, maxSpeed=33.0]]]",shipping.getCountryValue("china").toString());
			assertEquals("Country [name=rusia, id=1107, boats=[Boat [name=Liquid Asset, manufacturingDate=1991, maxRange=13900.0, maxSpeed=42.3], Boat [name=Vesper, manufacturingDate=1991, maxRange=13900.0, maxSpeed=39.3], Boat [name=Unsinkable ll, manufacturingDate=1991, maxRange=13900.0, maxSpeed=41.3], Boat [name=Titanic ll, manufacturingDate=2003, maxRange=12000.0, maxSpeed=38.0]]]",shipping.getCountryValue("rusia").toString());
		} catch (UnavaiableBoatsException e) {
			
		} catch (MaximumRangeExceededException e) {
			
		}
	}
	@Test
	void testCovidMode() {
		ShippingApp shipping = setUpSceneGetCountryValue();
		try {
			assertEquals(7,shipping.deliveryTime("australia", "usa"));
//			shipping.covidMode();
			assertEquals(17,shipping.deliveryTime("australia", "usa"));
			
		} catch (UnavaiableBoatsException e) {
			
		} catch (MaximumRangeExceededException e) {
		
		}
	}
	

}
