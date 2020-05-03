package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import datastructures.Grafov2;
import model.Country;

class Grafov2Test {
	

	public boolean setUpSceneInitGraph() {
		Country c = new Country("Colombia", 76000);
		Grafov2 test1 = new Grafov2(false, false, c);
		if(test1.getVertex(c) == c && !test1.isDirected() && !test1.isMultiple()) {
			return true;
		}else {
			return false;
		}
	}
	
	@Test
	void testInitGraph() {
		assertTrue(setUpSceneInitGraph()==true);
	}
	
	public Grafov2<Country> setUpSceneGraphMultiple() {
		Country c1 = new Country("Colombia", 1);
		Country c2 = new Country("EEUU", 2);
		Country c3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, true, c1);
		gr.addVertex(c2);
		gr.addVertex(c3);
		
		return gr;
	}
	
	public boolean setUpSceneAddVertex() {
		Grafov2<Country> gr = setUpSceneGraphMultiple();
		
		if(gr.getValues().size() == 3 && gr.getAdjmatrix().length == 3 && gr.getValues().size()==3) {
			return true;
		}else {
			return false;
		}
	}
	
	@Test
	void testAddVertex() {
		assertTrue(setUpSceneAddVertex());
	}
	
	public boolean setUpSceneAddEdges() {
		Grafov2<Country> gr = setUpSceneGraphMultiple();
		
		//edges from Colombia to EEUU
		gr.addEdge(0, 1, 10);
		gr.addEdge(0, 1, 8);
		
		if(gr.getAdjmatrix()[0][1].size()==2 && gr.getEdges(0, 1).get(0) == 10 && gr.getEdges(0, 1).get(1) == 8) {
			return true;
		}else {
			return false;
		}
	}
	
	@Test
	void testAddEdge() {
		assertTrue(setUpSceneAddEdges());
	}
	
	public boolean setUpSceneDeleteVertex() {
		Grafov2<Country> gr = setUpSceneGraphMultiple();
		Country c = new Country("EEUU", 2);
		gr.deleteVertex(0);
		System.out.println( gr.getValues().get(1) + "/" + c);
		if(gr.getAdjmatrix().length == 2 && gr.getValues().size() == 2 && gr.getValues().get(0).getName().equals("EEUU")) {
			return true;
		}else {
			return false;
		}
	}
	
	@Test
	void testDeleteVertex() {
		assertTrue(setUpSceneDeleteVertex());
	}
	
	@Test
	void testConsultWeight() {
		Grafov2<Country> gr = setUpSceneGraphMultiple();
		assertTrue(gr.consultWeight() == 3);
	}
}
