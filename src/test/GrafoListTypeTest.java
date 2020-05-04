package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import datastructures.GrafoListType;
import exceptions.InvalidActionInSimpleGraphException;
import exceptions.RepeatedVertexException;
import model.Country;

class GrafoListTypeTest {
	Country colombia = new Country("Colombia", 0);
	Country china = new Country("china", 1);
	Country rusia = new Country("rusia", 4);
	Country villacubito = new Country("villacubito", 3);
	Country canada = new Country("canada", 2);
	Country peru = new Country("peru", 5);
	private String setUpSceneAddEdgesDirectedNoMultiple() throws InvalidActionInSimpleGraphException, RepeatedVertexException  {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,true, false);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			for (int j = 0; j < graph.getAdjacentList()[i].size(); j++) {
				asd = (int[]) graph.getAdjacentList()[i].get(j);
				data += "Origen:"+ i  + " "+ "Destino:" + asd[0]+ " " + "Peso" + asd[1] + "//";
			}
		}
		return data;
	}
	@Test
	void testAddEdgesDirected() {
		try {
			assertEquals("Origen:0 Destino:1 Peso2//Origen:1 Destino:4 Peso2//Origen:2 Destino:0 Peso2//Origen:3 Destino:2 Peso2//Origen:4 Destino:3 Peso2//", setUpSceneAddEdgesDirectedNoMultiple());
		} catch (InvalidActionInSimpleGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	
	private String setUpSceneAddEdgesDirectedMultiple() throws InvalidActionInSimpleGraphException, RepeatedVertexException  {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,true, true);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.addEdges(0, 1, 2);
		graph.addEdges(0, 1, 12);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(4, 3, 35);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			for (int j = 0; j < graph.getAdjacentList()[i].size(); j++) {
				asd = (int[]) graph.getAdjacentList()[i].get(j);
				data += "Origen:"+ i  + " "+ "Destino:" + asd[0]+ " " + "Peso:" + asd[1] + "//";	
			}
		}
		return data;
	}
	@Test
	void testAddEdgesDirectedMultiple() {
		try {
			assertEquals("Origen:0 Destino:1 Peso:2//Origen:0 Destino:1 Peso:12//Origen:1 Destino:4 Peso:2//Origen:2 Destino:0 Peso:2//Origen:3 Destino:2 Peso:2//Origen:4 Destino:3 Peso:2//Origen:4 Destino:3 Peso:35//Origen:4 Destino:3 Peso:2//", setUpSceneAddEdgesDirectedMultiple());
		} catch (InvalidActionInSimpleGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	private String setUpSceneAddEdgesNoDirectedNoMultiple() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,false, false);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			for (int j = 0; j < graph.getAdjacentList()[i].size(); j++) {
				asd = (int[]) graph.getAdjacentList()[i].get(j);
				data += "Origen:"+ i  + " "+ "Destino:" + asd[0]+ " " + "Peso:" + asd[1] + "//";	
			}
		}
		return data;
	}
	@Test
	void testAddEdgesNoDirectedNoMultiple() {
		try {
			assertEquals("Origen:0 Destino:1 Peso:2//Origen:0 Destino:2 Peso:2//Origen:1 Destino:0 Peso:2//Origen:1 Destino:4 Peso:2//Origen:2 Destino:3 Peso:2//Origen:2 Destino:0 Peso:2//Origen:3 Destino:4 Peso:2//Origen:3 Destino:2 Peso:2//Origen:4 Destino:1 Peso:2//Origen:4 Destino:3 Peso:2//",setUpSceneAddEdgesNoDirectedNoMultiple());
		} catch (InvalidActionInSimpleGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	private String setUpSceneAddEdgesNoDirectedMultiple() throws InvalidActionInSimpleGraphException, RepeatedVertexException  {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,false, true);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.addEdges(0, 1, 2);
		graph.addEdges(0, 1, 12);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(4, 3, 35);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			for (int j = 0; j < graph.getAdjacentList()[i].size(); j++) {
				asd = (int[]) graph.getAdjacentList()[i].get(j);
				data += "Origen:"+ i  + " "+ "Destino:" + asd[0]+ " " + "Peso:" + asd[1] + "//";	
			}
		}
		return data;
	}
	@Test
	void testAddEdgesNoDirectedMultiple() {
		try {
			assertEquals("Origen:0 Destino:1 Peso:2//Origen:0 Destino:1 Peso:12//Origen:0 Destino:2 Peso:2//Origen:1 Destino:0 Peso:2//Origen:1 Destino:0 Peso:12//Origen:1 Destino:4 Peso:2//Origen:2 Destino:3 Peso:2//Origen:2 Destino:0 Peso:2//Origen:3 Destino:4 Peso:2//Origen:3 Destino:4 Peso:35//Origen:3 Destino:4 Peso:2//Origen:3 Destino:2 Peso:2//Origen:4 Destino:1 Peso:2//Origen:4 Destino:3 Peso:2//Origen:4 Destino:3 Peso:35//Origen:4 Destino:3 Peso:2//", setUpSceneAddEdgesNoDirectedMultiple());
		} catch (InvalidActionInSimpleGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	///////////////////Test Exceptions//////////////////
	private void setUpSceneAddEdgesDirectedNoMultipleE() throws InvalidActionInSimpleGraphException, RepeatedVertexException  {
		GrafoListType graph = new GrafoListType(colombia,true, false);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		graph.addEdges(2, 0, 44);
	}
	@Test
	void testAddEdgesDirectedE() {
		try {
			setUpSceneAddEdgesDirectedNoMultipleE();
			fail("Expected Exception");
		} catch (InvalidActionInSimpleGraphException e) {
			System.out.println("Succesful test");
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	private void setUpSceneAddEdgesNoDirectedNoMultipleE() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		GrafoListType graph = new GrafoListType(colombia,false, false);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.addEdges(0, 1, 2);
		graph.addEdges(0, 0, 7);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
	}
	@Test
	void testAddEdgesNoDirectedNoMultipleE() {
		try {
			setUpSceneAddEdgesNoDirectedNoMultipleE();
			fail("Expected Exception");
		} catch (InvalidActionInSimpleGraphException e) {
			System.out.println("Succesful test");
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	private String setUpScenegetEdges() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		ArrayList<int[]> edges;
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,false, true);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(4, 3, 12);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		edges = graph.getEdges(4, 3, 2);
		for (int i = 0; i < edges.size(); i++) {
			data +="Origen:" + 4 + " " + "Destino:" +  edges.get(i)[0] + "//";
		}
		return data;
	}
	@Test
	void testScenegetEdges() {
		try {
			assertEquals("Origen:4 Destino:3//", setUpScenegetEdges());
		} catch (InvalidActionInSimpleGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	private String setUpSceneDeleteEdgeNoDirected() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,false, true);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		graph.deleteEdge(4, 3, 2);
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			for (int j = 0; j < graph.getAdjacentList()[i].size(); j++) {
				asd = (int[]) graph.getAdjacentList()[i].get(j);
				data += "Origen:"+ i  + " "+ "Destino:" + asd[0]+ " " + "Peso:" + asd[1] + "//";	
			}
		}
		return data;
	}
	@Test
	void testDeleteEdgeNoDirected() {
		try {
			assertEquals("Origen:0 Destino:1 Peso:2//Origen:0 Destino:2 Peso:2//Origen:1 Destino:0 Peso:2//Origen:1 Destino:4 Peso:2//Origen:2 Destino:3 Peso:2//Origen:2 Destino:0 Peso:2//Origen:3 Destino:2 Peso:2//Origen:4 Destino:1 Peso:2//",setUpSceneDeleteEdgeNoDirected());
		} catch (InvalidActionInSimpleGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	private String setUpSceneDeleteEdgeDirected() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,true, true);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		graph.deleteEdge(4, 3, 2);
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			for (int j = 0; j < graph.getAdjacentList()[i].size(); j++) {
				asd = (int[]) graph.getAdjacentList()[i].get(j);
				data += "Origen:"+ i  + " "+ "Destino:" + asd[0]+ " " + "Peso:" + asd[1] + "//";	
			}
		}
		return data;
	}
	@Test
	void testDeleteEdgeDirected() {
		try {
			assertEquals("Origen:0 Destino:1 Peso:2//Origen:1 Destino:4 Peso:2//Origen:2 Destino:0 Peso:2//Origen:3 Destino:2 Peso:2//",setUpSceneDeleteEdgeDirected());		
		} catch (InvalidActionInSimpleGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	private String setUpSceneDeleteEdgesOfADeletedVertex() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		int[] asd = new int[2];
		int[] asdAux = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,false, true);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.addEdges(0, 1, 1);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 3);
		graph.addEdges(3, 2, 4);
		graph.addEdges(2, 0, 5);
		graph.deleteVertexValue(villacubito);///graph.deleteVertexValue(villacubito); //4
		///graph.deleteVertexValue(rusia); //3
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			for (int j = 0; j < graph.getAdjacentList()[i].size(); j++) {
				asd = (int[]) graph.getAdjacentList()[i].get(j);
				data += "Destino:" + asd[0]+ " " + "Peso:" + asd[1] + "//";	
			}
		}
		/*
		for (int i = 0; i < graph.getValues().size(); i++) {
			Country country = (Country) graph.getValues().get(i);
			//System.out.println(country.getName());
		}
		for (int i = 0; i < graph.getAdjacentList()[2].size(); i++) {
			asdAux = (int[]) graph.getAdjacentList()[2].get(i);
			//System.out.println(asdAux[0]);
			System.out.println(asdAux[1]);
		}
		*/
		return data;
	}
	@Test
	void testDeleteVertex() {
		try {
			assertEquals("Destino:1 Peso:1//Destino:2 Peso:5//Destino:0 Peso:1//Destino:4 Peso:2//Destino:0 Peso:5//Destino:1 Peso:2//",setUpSceneDeleteEdgesOfADeletedVertex());
		} catch (InvalidActionInSimpleGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	private String setUpSceneDeleteVertexValue() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,false, true);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.deleteVertexValue(rusia);
		graph.deleteVertexValue(villacubito);
		//graph.deleteVertexValue(villacubito);
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			Country country = (Country) graph.getValues().get(i);
			data += country.getName() + " // ";
		} 
		return data;
	}
	@Test
	void testDeleteVertexValue() {
		try {
			assertEquals("Colombia // china // canada // ",setUpSceneDeleteVertexValue());
		} catch (InvalidActionInSimpleGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	/////////
	private void setUpSceneDeleteVertexDoesnotExist() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,false, true);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.deleteVertexValue(4);
		graph.deleteVertexValue(3);
		graph.deleteVertexValue(10);
	}
	@Test
	void testDeleteVertexValueDoesnotExist() {
		try {
			setUpSceneDeleteVertexDoesnotExist();
			fail("Exception expected");
		} catch (InvalidActionInSimpleGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Succesful test");
		}
	}
	/////////
	private GrafoListType setUpSceneDeleteVertexValueSize() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,false, true);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.deleteVertexValue(rusia);
		graph.deleteVertexValue(villacubito);
		return graph;
	}
	@Test
	void testDeleteVertexValueSize() {
		try {
			assertEquals(setUpSceneDeleteVertexValueSize().getAdjacentList().length,3);
			assertEquals(setUpSceneDeleteVertexValueSize().getValues().size(),3);
		} catch (InvalidActionInSimpleGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	private String setUpSceneAddVertex() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,false, false);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.addVertexValue(peru);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(3, 5, 2);
		graph.addEdges(2, 0, 2);
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			for (int j = 0; j < graph.getAdjacentList()[i].size(); j++) {
				asd = (int[]) graph.getAdjacentList()[i].get(j);
				data += "Origen:"+ i  + " "+ "Destino:" + asd[0]+ " " + "Peso:" + asd[1] + "//";	
			}
		}
		return data;
	}
	@Test
	void testAddVertex() {
		try {
			assertEquals("Origen:0 Destino:1 Peso:2//Origen:0 Destino:2 Peso:2//Origen:1 Destino:0 Peso:2//Origen:1 Destino:4 Peso:2//Origen:2 Destino:3 Peso:2//Origen:2 Destino:0 Peso:2//Origen:3 Destino:4 Peso:2//Origen:3 Destino:2 Peso:2//Origen:3 Destino:5 Peso:2//Origen:4 Destino:1 Peso:2//Origen:4 Destino:3 Peso:2//Origen:5 Destino:3 Peso:2//",setUpSceneAddVertex());
		} catch (InvalidActionInSimpleGraphException e) {
			fail();
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	private String setUpSceneAddVertexValue() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,false, false);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			Country country = (Country) graph.getValues().get(i);
			data += country.getName() + " // ";
		}
		return data;
	}
	@Test
	void testAddVertexValue() {
		try {
			assertEquals("Colombia // china // rusia // villacubito // canada // ",setUpSceneAddVertexValue());
		} catch (InvalidActionInSimpleGraphException e) {
			fail();
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	private GrafoListType setUpSceneAddVertexValueSize() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,false, false);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		return graph;
	}
	@Test
	void testAddVertexValueSize() {
		try {
			assertEquals(setUpSceneAddVertexValueSize().getAdjacentList().length, 5);
			assertEquals(setUpSceneAddVertexValueSize().getValues().size(), 5);
		} catch (InvalidActionInSimpleGraphException e) {
			fail();
		} catch (RepeatedVertexException e) {
			fail();
		}
	}
	private void setUpSceneAddVertexValueRepeated() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(colombia,false, false);
		graph.addVertexValue(china);
		graph.addVertexValue(rusia);
		graph.addVertexValue(villacubito);
		graph.addVertexValue(canada);
		graph.addVertexValue(canada);
	}
	@Test
	void testAddVertexValueRepeated() {
		try {
			setUpSceneAddVertexValueRepeated();
			fail("Exception expected");
		} catch (InvalidActionInSimpleGraphException e) {
			fail();
		} catch (RepeatedVertexException e) {
			System.out.println("Succesful test");
		}
	}
}
