package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import datastructures.Grafov2;
import datastructures.Vertex;
import model.Country;

class Grafov2Test {
	

	public boolean setUpSceneInitGraph() {
		Country c = new Country("Colombia", 76000);
		Vertex<Country> v = new Vertex<Country>(c);
		Grafov2<Vertex<Country>> gr = new Grafov2<Vertex<Country>>(false, false);
		gr.addVertex(v);
		if(gr.getVertex(v).equals(v) && !gr.isDirected() && !gr.isMultiple()) {
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
		
		Vertex<Country> v1 = new Vertex<Country>(c1);
		Vertex<Country> v2 = new Vertex<Country>(c2);
		Vertex<Country> v3 = new Vertex<Country>(c3);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, true);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		
		return gr;
	}
	
	public Grafov2<Country> setUpSceneGraphMultipleDirected() {
		Country c1 = new Country("Colombia", 1);
		Country c2 = new Country("EEUU", 2);
		Country c3 = new Country("Barrancabermeja", 3);
		
		Vertex v1 = new Vertex(c1);
		Vertex v2 = new Vertex(c2);
		Vertex v3 = new Vertex(c3);
		
		Grafov2<Country> gr = new Grafov2<Country>(true, true);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		
		return gr;
	}
	
	public Grafov2<Country> setUpSceneGraphDirected() {
		Country c1 = new Country("Colombia", 1);
		Country c2 = new Country("EEUU", 2);
		Country c3 = new Country("Barrancabermeja", 3);
		
		Vertex v1 = new Vertex(c1);
		Vertex v2 = new Vertex(c2);
		Vertex v3 = new Vertex(c3);
		
		Grafov2<Country> gr = new Grafov2<Country>(true, false);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		
		return gr;
	}
	
	public Grafov2<Vertex<Country>> setUpSceneGraph() {
		Country c1 = new Country("Colombia", 1);
		Country c2 = new Country("EEUU", 2);
		Country c3 = new Country("Barrancabermeja", 3);
		
		Vertex<Country> v1 = new Vertex<Country>(c1);
		Vertex<Country> v2 = new Vertex<Country>(c2);
		Vertex<Country> v3 = new Vertex<Country>(c3);
		
		Grafov2<Vertex<Country>> gr = new Grafov2<Vertex<Country>>(false, false);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		
//		Vertex<Country> aux = gr.getValues().get(0);
//		aux.getValue().getName();
	
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
		Grafov2<Vertex<Country>> gr = setUpSceneGraph();
		
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
		Grafov2<Vertex<Country>> gr = setUpSceneGraph();
				
		gr.deleteVertex(0);
		gr.deleteVertex(0);

		Vertex<Country> aux = gr.getValues().get(0);
		
		if(gr.getAdjmatrix().length == 1 && gr.getValues().size() == 1 && aux.getValue().getName().equals("Barrancabermeja")) {
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
		Grafov2<Vertex<Country>> gr = setUpSceneGraph();
		gr.addEdge(1, 0, 14);
		gr.deleteEdge(0, 1, 14);
		
		assertTrue(gr.getEdges(0, 1).size() == 0 && gr.getEdges(1, 0).isEmpty());
	}
	
	@Test
	void testConsultWeight() {
		Grafov2<Country> gr = setUpSceneGraphMultiple();
		assertTrue(gr.consultWeight() == 3);
	}
	
	
	public Grafov2<Country> setUpSceneBFS() {
		Country c1 = new Country("Colombia", 1);
		Country c2 = new Country("EEUU", 2);
		Country c3 = new Country("Barrancabermeja", 3);
		Country c4 = new Country("Brazil", 4);
		Country c5 = new Country("Canada", 5);
		
		Vertex<Country> v1 = new Vertex<Country>(c1);
		Vertex<Country> v2 = new Vertex<Country>(c2);
		Vertex<Country> v3 = new Vertex<Country>(c3);
		Vertex<Country> v4 = new Vertex<Country>(c4);
		Vertex<Country> v5 = new Vertex<Country>(c4);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, true);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		gr.addVertex(v4);
		gr.addVertex(v5);
		
		gr.addEdge(0, 1, 5);
		gr.addEdge(1, 3, 6);
		gr.addEdge(3, 4, 11);
		gr.addEdge(2, 4, 11);
		gr.addEdge(0, 4, 11);
		
		return gr;
	}
	
	@Test
	void testBFS() {
		Grafov2<Country> gr = setUpSceneBFS();
		ArrayList<Integer> aux = gr.bfs(0);
		String aux2 = aux.size()+ ": " + aux.get(0) + "," + aux.get(1) + "," + aux.get(2) + "," + aux.get(3) + "," + aux.get(4);
		assertTrue(aux2.equals("5: 0,1,4,3,2"));
	}
	
	public Grafov2<Country> setUpSceneDFS() {
		Country c1 = new Country("Colombia", 1);
		Country c2 = new Country("EEUU", 2);
		Country c3 = new Country("Barrancabermeja", 3);
		Country c4 = new Country("Brazil", 4);
		Country c5 = new Country("Canada", 5);
		Country c6 = new Country("Australia", 6);
		
		Vertex<Country> v1 = new Vertex<Country>(c1);
		Vertex<Country> v2 = new Vertex<Country>(c2);
		Vertex<Country> v3 = new Vertex<Country>(c3);
		Vertex<Country> v4 = new Vertex<Country>(c4);
		Vertex<Country> v5 = new Vertex<Country>(c5);
		Vertex<Country> v6 = new Vertex<Country>(c6);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, true);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		gr.addVertex(v4);
		gr.addVertex(v5);
		gr.addVertex(v6);
		
		gr.addEdge(0, 1, 5);
		gr.addEdge(1, 3, 6);
		gr.addEdge(3, 4, 11);
		gr.addEdge(2, 4, 11);
		gr.addEdge(0, 4, 11);
		gr.addEdge(0, 5, 48);
		
		return gr;
	}
	
	@Test
	void testDFS() {
		Grafov2<Country> gr = setUpSceneDFS();
		ArrayList<Integer> aux = gr.dfs(0);
		String s = aux.size()+": " + aux.get(0) + "," + aux.get(1) + "," + aux.get(2) + "," + aux.get(3) + "," + aux.get(4) + "," + aux.get(5);
		assertTrue(s.equals("6: 0,1,3,4,2,5"));
	}
	
	public Grafov2<Country> setUpScenePrim() {
		Country c1 = new Country("Colombia", 1);
		Country c2 = new Country("EEUU", 2);
		Country c3 = new Country("Barrancabermeja", 3);
		Country c4 = new Country("Brazil", 4);
		Country c5 = new Country("Canada", 5);
		Country c6 = new Country("Australia", 6);
		
		Vertex<Country> v1 = new Vertex(c1);
		Vertex<Country> v2 = new Vertex(c2);
		Vertex<Country> v3 = new Vertex(c3);
		Vertex<Country> v4 = new Vertex(c4);
		Vertex<Country> v5 = new Vertex(c5);
		Vertex<Country> v6 = new Vertex(c6);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, true);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		gr.addVertex(v4);
		gr.addVertex(v5);
		gr.addVertex(v6);
		
		gr.addEdge(0, 1, 5);
		gr.addEdge(1, 3, 6);
		gr.addEdge(1, 4, 4);
		gr.addEdge(3, 4, 11);
		gr.addEdge(2, 4, 1);
		gr.addEdge(0, 4, 7);
		gr.addEdge(0, 5, 48);
		gr.addEdge(4, 5, 3);
		
		return gr;
	}
	
	@Test
	void testPrim() {
		Grafov2<Country> gr = setUpScenePrim();
		Country c1 = new Country("Colombia", 1);
		Vertex<Country> v1 = new Vertex(c1);
		Grafov2<Country> asd = gr.prim(v1);
		
		for(int i = 0; i < asd.consultWeight(); i++) {
			System.out.println(asd.getEdges(0, i).size());
		}
		
//		assertTrue(gr.consultWeight() == 6);
		
	}
	
	@Test
	void atestAddEdge2() {
		Grafov2<Country> gr = setUpScenePrim();
//		gr.addEdge(0, 1, 5);
//		gr.addEdge(1, 3, 6);
//		gr.addEdge(1, 4, 4);
//		gr.addEdge(3, 4, 11);
//		gr.addEdge(2, 4, 1);
//		gr.addEdge(0, 4, 7);
//		gr.addEdge(0, 5, 48);
//		gr.addEdge(4, 5, 3);
		
//		gr = gr.prim(v);
//		assertTrue(gr.getEdges(0, 1).size()==1);
//		for(int i = 0; i < gr.consultWeight(); i++) {
//			System.out.println(gr.getEdges(0, i).size());
//		}
	}
}
