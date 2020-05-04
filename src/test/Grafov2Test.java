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
	
	public Grafov2<Country> setUpSceneGraphMultipleDirected() {
		Country c1 = new Country("Colombia", 1);
		Country c2 = new Country("EEUU", 2);
		Country c3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(true, true, c1);
		gr.addVertex(c2);
		gr.addVertex(c3);
		
		return gr;
	}
	
	public Grafov2<Country> setUpSceneGraphDirected() {
		Country c1 = new Country("Colombia", 1);
		Country c2 = new Country("EEUU", 2);
		Country c3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(true, false, c1);
		gr.addVertex(c2);
		gr.addVertex(c3);
		
		return gr;
	}
	
	public Grafov2<Country> setUpSceneGraph() {
		Country c1 = new Country("Colombia", 1);
		Country c2 = new Country("EEUU", 2);
		Country c3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, false, c1);
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
	
	public boolean setUpSceneAddEdgesMultiple() {
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
	void testAddEdgeMultiple() {
		assertTrue(setUpSceneAddEdgesMultiple());
	}
	
	public boolean setUpSceneAddEdgesMultipleDirected() {
		Grafov2<Country> gr = setUpSceneGraphMultipleDirected();
		
		//edges from Colombia to EEUU
		gr.addEdge(0, 1, 10);
		gr.addEdge(0, 1, 8);
		
		if(gr.getAdjmatrix()[0][1].size()==2 && gr.getEdges(0, 1).get(1) == 8 && gr.getEdges(1, 0).isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	@Test
	void testAddEdgeMultipleDirected() {
		assertTrue(setUpSceneAddEdgesMultipleDirected());
	}
	
	public boolean setUpSceneAddEdgesDirected() {
		Grafov2<Country> gr = setUpSceneGraphDirected();
		
		//edges from Colombia to EEUU
		gr.addEdge(0, 1, 10);
		gr.addEdge(0, 1, 8);
		
		if(gr.getAdjmatrix()[0][1].size()==1 && gr.getEdges(0, 1).get(0) == 10 && gr.getEdges(1, 0).isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	@Test
	void testAddEdgeDirected() {
		assertTrue(setUpSceneAddEdgesDirected());
	}
	
	public boolean setUpSceneAddEdges() {
		Grafov2<Country> gr = setUpSceneGraph();
		
		//edges from Colombia to EEUU
		gr.addEdge(0, 1, 10);
		gr.addEdge(0, 1, 8);
		
		if(gr.getAdjmatrix()[0][1].size()==1 && gr.getEdges(0, 1).get(0) == 10 && !gr.getEdges(1, 0).isEmpty()) {
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
		Grafov2<Country> gr = setUpSceneGraph();
		Country c = new Country("EEUU", 2);
		gr.deleteVertex(0);
		gr.deleteVertex(0);
		if(gr.getAdjmatrix().length == 1 && gr.getValues().size() == 1 && gr.getValues().get(0).getName().equals("Barrancabermeja")) {
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
	void testDeleteEdgeMultipleDirected() {
		Grafov2<Country> gr = setUpSceneGraphMultipleDirected();
		gr.addEdge(1, 0, 14);
		gr.addEdge(0, 1, 10);
		gr.addEdge(0, 1, 2);
		gr.addEdge(0, 1, 8);
		gr.deleteEdge(0, 1, 10);
		
		assertTrue(gr.getEdges(0, 1).get(0) == 2 && !gr.getEdges(1, 0).isEmpty());
	}
	
	@Test
	void testDeleteEdgeMultiple() {
		Grafov2<Country> gr = setUpSceneGraphMultiple();
		gr.addEdge(1, 0, 14);
		gr.addEdge(0, 1, 10);
		gr.addEdge(0, 1, 2);
		gr.addEdge(0, 1, 8);
		gr.deleteEdge(1, 0, 10);
		
		assertTrue(gr.getEdges(0, 1).get(1) == 2 && gr.getEdges(1, 0).size() == 3);
	}
	
	@Test
	void testDeleteEdgeDirected() {
		Grafov2<Country> gr = setUpSceneGraphDirected();
		gr.addEdge(1, 0, 14);
		gr.addEdge(0, 1, 10);
		gr.deleteEdge(0, 1, 10);
		
		assertTrue(gr.getEdges(0, 1).size() == 0 && !gr.getEdges(1, 0).isEmpty());
	}
	
	@Test
	void testDeleteEdge() {
		Grafov2<Country> gr = setUpSceneGraph();
		gr.addEdge(1, 0, 14);
		gr.deleteEdge(0, 1, 14);
		
		assertTrue(gr.getEdges(0, 1).size() == 0 && gr.getEdges(1, 0).isEmpty());
	}
	
	@Test
	void testConsultWeight() {
		Grafov2<Country> gr = setUpSceneGraphMultiple();
		assertTrue(gr.consultWeight() == 3);
	}
}
