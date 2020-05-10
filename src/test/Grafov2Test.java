package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import datastructures.Grafov2;

import model.Country;

class Grafov2Test {
	

	public boolean setUpSceneInitGraph() {
		Country v = new Country("Colombia", 76000);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, false);
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
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, true);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		
		return gr;
	}
	
	public Grafov2<Country> setUpSceneGraphMultipleDirected() {
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(true, true);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		
		return gr;
	}
	
	public Grafov2<Country> setUpSceneGraphDirected() {
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(true, false);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		
		return gr;
	}
	
	public Grafov2<Country> setUpSceneGraph() {
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, false);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
	
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
				
		gr.deleteVertex(0);
		gr.deleteVertex(0);

		Country aux = gr.getValues().get(0);
		
		if(gr.getAdjmatrix().length == 1 && gr.getValues().size() == 1 && aux.getName().equals("Barrancabermeja")) {
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
	
	
	public Grafov2<Country> setUpSceneBFS() {
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		Country v4 = new Country("Brazil", 4);
		Country v5 = new Country("Canada", 5);
		
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
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		Country v4 = new Country("Brazil", 4);
		Country v5 = new Country("Canada", 5);
		Country v6 = new Country("Australia", 6);
		
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
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		Country v4 = new Country("Brazil", 4);
		Country v5 = new Country("Canada", 5);
		Country v6 = new Country("Australia", 6);
		
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
		
		/*
		 * 		0
		 * 	   / \
		 *    /   \
		 *   1	   5	
		 */
		
		return gr;
	}
	
	@Test
	void testPrim() {
		Grafov2<Country> gr = setUpScenePrim();
		Country v1 = new Country("Colombia", 1);
		Grafov2<Country> asd = gr.prim(v1);
		
//		for(int i = 0; i < asd.consultWeight(); i++) {
//			System.out.println(asd.getEdges(0, i).size());
//		}
		
//		assertTrue(gr.consultWeight() == 6);
		
	}
	
	
	public double setUpSceneDijkstra() {
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		Country v4 = new Country("Brazil", 4);
		Country v5 = new Country("Canada", 5);
		Country v6 = new Country("Australia", 6);
		
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
		
		return gr.dijkstra(v1, v6);
	}
	
	@Test
	void testDijkstra() {
		double dist = setUpSceneDijkstra();
		assertTrue(dist == 10);
	}
	
	
	public double[][] setUpSceneFloydWarshall() {
		Grafov2<Country> gr = new Grafov2<Country>(true, false);
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		Country v4 = new Country("Brazil", 4);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		gr.addVertex(v4);
		
		gr.addEdge(0, 2, -2);
		gr.addEdge(2, 3, 2);
		gr.addEdge(3, 1, -1);
		gr.addEdge(1, 0, 4);
		gr.addEdge(1, 2, 3);
		
		return gr.floydWarshall();
		
		
	}
	
	@Test
	void testFloydWarshall() {
		double[][] matrix = setUpSceneFloydWarshall();
		assertTrue(matrix[0][3] == 0 && matrix[3][2] == 1 && matrix[1][3] == 4 && matrix[1][2] == 2);
	}
}
