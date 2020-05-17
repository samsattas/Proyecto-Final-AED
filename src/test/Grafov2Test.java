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
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, true);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		
		gr.addEdge(v1, v2, 10);
		gr.addEdge(v1, v2, 8);
		
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
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(true, true);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		//edges from Colombia to EEUU
		gr.addEdge(v1, v2, 10);
		gr.addEdge(v1, v2, 8);
		
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
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(true, false);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		//edges from Colombia to EEUU
		gr.addEdge(v1, v2, 10);
		gr.addEdge(v1, v2, 8);
		
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
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, false);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		//edges from Colombia to EEUU
		gr.addEdge(v1, v2, 10);
		gr.addEdge(v1, v2, 8);
		
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
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(true, true);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		
		gr.addEdge(v2, v1, 14);
		gr.addEdge(v1, v2, 10);
		gr.addEdge(v1, v2, 2);
		gr.addEdge(v1, v2, 8);
		gr.deleteEdge(v1, v2, 10);
		
		assertTrue(gr.getEdges(0, 1).get(0) == 2 && !gr.getEdges(1, 0).isEmpty());
	}
	
	@Test
	void testDeleteEdgeMultiple() {
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, true);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		
		gr.addEdge(v2, v1, 14);
		gr.addEdge(v1, v2, 10);
		gr.addEdge(v1, v2, 2);
		gr.addEdge(v1, v2, 8);
		gr.deleteEdge(v1, v2, 10);
		
		assertTrue(gr.getEdges(0, 1).get(1) == 2 && gr.getEdges(1, 0).size() == 3);
	}
	
	@Test
	void testDeleteEdgeDirected() {
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(true, false);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		
		gr.addEdge(v2, v1, 14);
		gr.addEdge(v1, v2, 10);
		gr.deleteEdge(v1, v2, 10);
		
		assertTrue(gr.getEdges(0, 1).size() == 0 && !gr.getEdges(1, 0).isEmpty());
	}
	
	@Test
	void testDeleteEdge() {
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, false);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		
		gr.addEdge(v2, v1, 14);
		gr.deleteEdge(v1, v2, 14);
		
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
		
		gr.addEdge(v1, v2, 0);
		gr.addEdge(v2, v4, 0);
		gr.addEdge(v4, v5, 0);
		gr.addEdge(v3, v5, 0);
		gr.addEdge(v1, v5, 0);
		
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
		
		gr.addEdge(v1, v2, 5);
		gr.addEdge(v2, v4, 6);
		gr.addEdge(v4, v5, 11);
		gr.addEdge(v3, v5, 11);
		gr.addEdge(v1, v5, 11);
		gr.addEdge(v1, v6, 48);
		
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
		
		gr.addEdge(v1, v2, 5);
		gr.addEdge(v2, v4, 6);
		gr.addEdge(v2, v5, 4);
		gr.addEdge(v4, v5, 11);
		gr.addEdge(v3, v5, 1);
		gr.addEdge(v1, v5, 7);
		gr.addEdge(v1, v6, 48);
		gr.addEdge(v5, v6, 3);
		
		return gr.prim3(v1);
	}
	
	@Test
	void testPrim() {
		Grafov2<Country> gr = setUpScenePrim();
		
		assertTrue(gr.consultWeight() == 6 );
		assertTrue(gr.getAdjmatrix()[1][4].get(0) == 4);
		assertTrue(gr.getAdjmatrix()[0][1].get(0) == 5);
		assertTrue(gr.getAdjmatrix()[1][3].get(0) == 6);
		assertTrue(gr.getAdjmatrix()[4][2].get(0) == 1);
		assertTrue(gr.getAdjmatrix()[5][4].get(0) == 3);
		
	}
	
	
<<<<<<< HEAD
	public Grafov2<Country> setUpSceneKruskal() {		
		Country a = new Country("Colombia", 1);//A
        Country b = new Country("EEUU", 2);//B
        Country c = new Country("Barrancabermeja", 3); //C
        Country d = new Country("Brazil", 4);//D
        Country e = new Country("Canada", 5);//E

        Grafov2<Country> gr = new Grafov2<Country>(false, false);

        gr.addVertex(a);
        gr.addVertex(b);
        gr.addVertex(c);
        gr.addVertex(d);
        gr.addVertex(e);

        gr.addEdge(a, b, 1);
        gr.addEdge(a, e, 2);
        gr.addEdge(a, c, 4);
        gr.addEdge(d, c, 1);
        gr.addEdge(d, e, 2);
        gr.addEdge(d, b, 3);
        gr.addEdge(c, e, 3); 
        gr.addEdge(b, e, 3);
		
//		gr = gr.kruskal();
		
//		System.out.println("NOOOOOOO");
//        System.out.println(gr.getMinimunEdge(a, b));
//        System.out.println(gr.getMinimunEdge(a, e));
//        System.out.println(gr.getMinimunEdge(a, c));
//        System.out.println(gr.getMinimunEdge(d, c));
//        System.out.println(gr.getMinimunEdge(d, e));
//        System.out.println(gr.getMinimunEdge(d, b));
//        System.out.println(gr.getMinimunEdge(c, e));
//        System.out.println(gr.getMinimunEdge(b, e));
//        System.out.println("NOOOOOOO");
        
        return gr;
=======
	public Grafov2<Country> setUpSceneKruskal() {
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3); 
		Country v4 = new Country("Brazil", 4);
		Country v5 = new Country("Canada", 5);
		
		Grafov2<Country> gr = new Grafov2<Country>(false, false);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3); 
		gr.addVertex(v4);
		gr.addVertex(v5);
		
		gr.addEdge(v1, v2, 1);
		gr.addEdge(v1, v4, 4);
		gr.addEdge(v1, v5, 2);
		gr.addEdge(v2, v3, 3);
		gr.addEdge(v2, v5, 3);
		gr.addEdge(v4, v5, 3);
		gr.addEdge(v4, v3, 1);
		gr.addEdge(v3, v5, 2);
		
		return gr;
>>>>>>> 08718692065fef5d59d1e8463bf432150c933208
	}
	
	@Test
	void testKruskal() {
		Grafov2<Country> gr = setUpSceneKruskal().kruskal();
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
		
		gr.addEdge(v1, v2, 5);
		gr.addEdge(v2, v4, 6);
		gr.addEdge(v2, v5, 4);
		gr.addEdge(v4, v5, 11);
		gr.addEdge(v3, v5, 1);
		gr.addEdge(v1, v5, 7);
		gr.addEdge(v1, v6, 48);
		gr.addEdge(v5, v6, 3);
		
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
		
		gr.addEdge(v1, v3, -2);
		gr.addEdge(v3, v4, 2);
		gr.addEdge(v4, v2, -1); 
		gr.addEdge(v2, v1, 4);
		gr.addEdge(v2, v3, 3);
		
		return gr.floydWarshall();
	}
	
	@Test
	void testFloydWarshall() {
		double[][] matrix = setUpSceneFloydWarshall();
		assertTrue(matrix[0][3] == 0 && matrix[3][2] == 1 && matrix[1][3] == 4 && matrix[1][2] == 2);
	}
	
	
}
