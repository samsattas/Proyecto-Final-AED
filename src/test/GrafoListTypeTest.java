package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import datastructures.GrafoListType;
import exceptions.InvalidActionInSimpleGraphException;

class GrafoListTypeTest {
	private String setUpSceneAddEdgesDirectedNoMultiple() throws InvalidActionInSimpleGraphException  {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(5, true, false);
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
		}
	}
	
	private String setUpSceneAddEdgesDirectedMultiple() throws InvalidActionInSimpleGraphException  {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(5, true, true);
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
		}
	}
	private String setUpSceneAddEdgesNoDirectedNoMultiple() throws InvalidActionInSimpleGraphException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(5, false, false);
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
		}
	}
	private String setUpSceneAddEdgesNoDirectedMultiple() throws InvalidActionInSimpleGraphException  {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(5, false, true);
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
		}
	}
	///////////////////Test Exceptions//////////////////
	private String setUpSceneAddEdgesDirectedNoMultipleE() throws InvalidActionInSimpleGraphException  {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(5, true, false);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		graph.addEdges(2, 0, 44);
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			for (int j = 0; j < graph.getAdjacentList()[i].size(); j++) {
				asd = (int[]) graph.getAdjacentList()[i].get(j);
				data += "Origen:"+ i  + " "+ "Destino:" + asd[0]+ " " + "Peso" + asd[1] + "\n";
			}
		}
		System.out.println(data);
		return data;
	}
	@Test
	void testAddEdgesDirectedE() {
		try {
			setUpSceneAddEdgesDirectedNoMultipleE();
			fail("Expected Exception");
		} catch (InvalidActionInSimpleGraphException e) {
			System.out.println("Succesful test");
		}
	}
	private String setUpSceneAddEdgesNoDirectedNoMultipleE() throws InvalidActionInSimpleGraphException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(5, false, false);
		graph.addEdges(0, 1, 2);
		graph.addEdges(0, 1, 7);
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
	void testAddEdgesNoDirectedNoMultipleE() {
		try {
			setUpSceneAddEdgesNoDirectedNoMultipleE();
			fail("Expected Exception");
		} catch (InvalidActionInSimpleGraphException e) {
			System.out.println("Succesful test");
		}
	}
	private String setUpScenegetEdges() throws InvalidActionInSimpleGraphException {
		ArrayList<int[]> edges;
		String data = "";
		GrafoListType graph = new GrafoListType(5, false, true);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		edges = graph.getEdges(4, 3);
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
		}
	}
	private String setUpSceneDeleteEdgeNoDirected() throws InvalidActionInSimpleGraphException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(5, false, true);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		graph.deleteEdge(4, 3);
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
		}
	}
	private String setUpSceneDeleteEdgeDirected() throws InvalidActionInSimpleGraphException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(5, true, true);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		graph.deleteEdge(4, 3);
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
		}
	}
	private String setUpSceneDeleteVertex() throws InvalidActionInSimpleGraphException {
		int[] asd = new int[2];
		String data = "";
		GrafoListType graph = new GrafoListType(5, false, true);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		graph.deleteVertex(4);
		for (int i = 0; i < graph.getAdjacentList().length-1; i++) {
			for (int j = 0; j < graph.getAdjacentList()[i].size(); j++) {
				asd = (int[]) graph.getAdjacentList()[i].get(j);
				data += "Origen:"+ i  + " "+ "Destino:" + asd[0]+ " " + "Peso:" + asd[1] + "\n";	
			}
		}
		return data;
	}
	@Test
	void testDeleteVertex() {
		try {
			System.out.println(setUpSceneDeleteVertex());
		} catch (InvalidActionInSimpleGraphException e) {
			fail("Unexpected Exception");
		}
	}
	
}