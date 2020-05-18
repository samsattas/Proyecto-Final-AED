package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import datastructures.GraphList;
import exceptions.InvalidActionInThisGraphException;
import exceptions.RepeatedVertexException;
import exceptions.ThatVertexDoesNotExistException;
import model.Country;


public class GraphListTest {
	Country colombia = new Country("Colombia", 0);
	Country china = new Country("china", 1);
	Country rusia = new Country("rusia", 4);
	Country villacubito = new Country("villacubito", 3);
	Country canada = new Country("canada", 2);
	Country peru = new Country("peru", 5);
	
	
	Country a = new Country("Colombia", 1);
	Country b = new Country("EEUU", 2);
	Country c = new Country("Barrancabermeja", 3); 
	Country d = new Country("Brazil", 4);
	Country e = new Country("Canada", 5);
	
	
	
	private GraphList<String> setUpSceneAddEdgesTrueTrueTrue() throws InvalidActionInThisGraphException, RepeatedVertexException, ThatVertexDoesNotExistException  {
		GraphList<String> graph = new GraphList<>(false,true,true);
		graph.addVertex("colombia");
		graph.addVertex("china");
		graph.addVertex("rusia");
		graph.addVertex("villacubito");
		graph.addVertex("canada");
		graph.addEdge("colombia", "china", 1);
		graph.addEdge("china", "rusia", 2);
		graph.addEdge("rusia", "villacubito", 3);
		graph.addEdge("villacubito", "canada", 4);
		graph.addEdge("canada", "colombia", 5);
		return graph;
	}
	@Test
	void testAddEdgesDirected() {
		try {
			//assertEquals(2, setUpSceneAddEdgesTrueTrueTrue().getAdjacentList().get(0).size());
			assertEquals(1, setUpSceneAddEdgesTrueTrueTrue().getAdjacentList().get(0).get(0)[0]);
			assertEquals(0, setUpSceneAddEdgesTrueTrueTrue().getAdjacentList().get(1).get(0)[0]);
			
			assertEquals(2, setUpSceneAddEdgesTrueTrueTrue().getAdjacentList().get(1).get(1)[0]);
			assertEquals(1, setUpSceneAddEdgesTrueTrueTrue().getAdjacentList().get(2).get(0)[0]); 
			
			assertEquals(3, setUpSceneAddEdgesTrueTrueTrue().getAdjacentList().get(2).get(1)[0]);
			assertEquals(2, setUpSceneAddEdgesTrueTrueTrue().getAdjacentList().get(3).get(0)[0]);
			
			assertEquals(4, setUpSceneAddEdgesTrueTrueTrue().getAdjacentList().get(3).get(1)[0]);
			assertEquals(3, setUpSceneAddEdgesTrueTrueTrue().getAdjacentList().get(4).get(0)[0]);
			
			assertEquals(0, setUpSceneAddEdgesTrueTrueTrue().getAdjacentList().get(4).get(1)[0]);
			assertEquals(4, setUpSceneAddEdgesTrueTrueTrue().getAdjacentList().get(0).get(1)[0]);
		} catch (InvalidActionInThisGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail("Unexpected Exception");
		} catch (ThatVertexDoesNotExistException e) {
			fail("Unexpected Exception");
		}
	}
	
	private GraphList<String> setUpSceneBFS() throws InvalidActionInThisGraphException, RepeatedVertexException, ThatVertexDoesNotExistException  {
		GraphList<String> graph = new GraphList<>(true,false,false);
		GraphList<String> graphAux;
		graph.addVertex("0");
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("3");
		graph.addVertex("4");
		graph.addEdge("0", "2", 0);
		graph.addEdge("1", "0", 0);
		graph.addEdge("2", "1", 0);
		graph.addEdge("2", "3", 0);
		graph.addEdge("3", "1", 0);
		graph.addEdge("1", "4", 0); 
		graph.addEdge("4", "3", 0); 
		graphAux = graph.bfs("0");
		return graphAux;
	}
	@Test
	<T> void testBFS() {
		try {
			GraphList<String> graphAux = setUpSceneBFS();
			assertEquals(2,graphAux.getAdjacentList().get(graphAux.getValues().indexOf("0")).get(0)[0]);
			assertEquals(4,graphAux.getAdjacentList().get(graphAux.getValues().indexOf("1")).get(0)[0]);
			assertEquals(1,graphAux.getAdjacentList().get(graphAux.getValues().indexOf("2")).get(0)[0]);
			assertEquals(3,graphAux.getAdjacentList().get(graphAux.getValues().indexOf("2")).get(1)[0]);
		} catch (InvalidActionInThisGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail("Unexpected Exception");
		} catch (ThatVertexDoesNotExistException e) {
			fail("Unexpected Exception");
		}
	}
	
	@Test
	<T> void testBFSNoneExistentEdges() {
		try {
			GraphList<String> graphAux = setUpSceneBFS();
			assertEquals(0,graphAux.getAdjacentList().get(graphAux.getValues().indexOf("3")).get(0)[0]);
			fail();
		} catch (InvalidActionInThisGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail("Unexpected Exception");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Pass!");
		} catch (ThatVertexDoesNotExistException e) {
			fail("Unexpected Exception");
		}
		try {
			GraphList<String> graphAux = setUpSceneBFS();
			assertEquals(0,graphAux.getAdjacentList().get(graphAux.getValues().indexOf("1")).get(1)[0]);
			fail();
		} catch (InvalidActionInThisGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail("Unexpected Exception");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Pass!");
		} catch (ThatVertexDoesNotExistException e) {
			fail("Unexpected Exception");
		}
		try {
			GraphList<String> graphAux = setUpSceneBFS();
			assertEquals(0,graphAux.getAdjacentList().get(graphAux.getValues().indexOf("4")).get(0)[0]);
			fail();
		} catch (InvalidActionInThisGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			fail("Unexpected Exception");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Pass!");
		} catch (ThatVertexDoesNotExistException e) {
			fail("Unexpected Exception");
		}
	}
	public GraphList<Country> setUpSceneDFS() throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException, RepeatedVertexException {
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		Country v4 = new Country("Brazil", 4);
		Country v5 = new Country("Canada", 5);
		Country v6 = new Country("Australia", 6);
		
		GraphList<Country> gr = new GraphList<Country>(false, true, false);
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
		
		return gr.dfs(v1);
	}
	@Test
	void testDFS() { 
		GraphList<Country> gr;
		try {
			gr = setUpSceneDFS();
			assertEquals(1,gr.getAdjacentList().get(0).get(0)[0]);
			assertEquals(4,gr.getAdjacentList().get(2).get(0)[0]);
			assertEquals(4,gr.getAdjacentList().get(5).get(0)[0]);
		} catch (ThatVertexDoesNotExistException e) {
			fail("Unexpected Exception");
		} catch (InvalidActionInThisGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//assertEquals(-7,gr.getAdjacentList().get(2).get(0)[0]);
		//assertEquals(0,gr.getAdjacentList().get(2).get(1)[0]);
	
	}
	
	
	
	
	public double[][] setUpSceneFloydWarshall() throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException, RepeatedVertexException {
		GraphList<Country> gr = new GraphList<Country>(true, false, false);
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
		double[][] matrix;
		try {
			matrix = setUpSceneFloydWarshall();
			assertTrue(matrix[0][3] == 0 && matrix[3][2] == 1 && matrix[1][3] == 4 && matrix[1][2] == 2);
		} catch (ThatVertexDoesNotExistException e) {
			fail("Unexpected Exception");
		} catch (InvalidActionInThisGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public double setUpScenegetMinimunEdgePlus() throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException, RepeatedVertexException {
		Country v0 = new Country("Colombia", 1);
		Country v1 = new Country("EEUU", 2);
		
		
		GraphList<Country> gr = new GraphList<Country>(false, true, false);
		gr.addVertex(v0);
		gr.addVertex(v1);
		
		
		gr.addEdge(v0, v1, 5);
		gr.addEdge(v0, v1, 6);
		gr.addEdge(v0, v1, 4);
		gr.addEdge(v0, v1, 11);
		gr.addEdge(v0, v1, 14);
		gr.addEdge(v0, v1, 7);
		gr.addEdge(v0, v1, 48);
		gr.addEdge(v0, v1, 3);
		
		return gr.getMinimunEdge(v0, v1);
	}
	@Test
	void testMinimumEdge() {
		try {
			System.out.println(setUpScenegetMinimunEdgePlus());
		} catch (ThatVertexDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidActionInThisGraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public double setUpSceneDijkstra() throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException, RepeatedVertexException {
		Country v0 = new Country("Colombia", 1);
		Country v1 = new Country("EEUU", 2);
		Country v2 = new Country("Barrancabermeja", 3); 
		Country v3 = new Country("Brazil", 4);
		Country v4 = new Country("Canada", 5);
		Country v5 = new Country("Australia", 6);
		
		GraphList<Country> gr = new GraphList<Country>(false, true, false);
		gr.addVertex(v0);
		gr.addVertex(v1);
		gr.addVertex(v2);
		gr.addVertex(v3);
		gr.addVertex(v4);
		gr.addVertex(v5);
		
		gr.addEdge(v0, v1, 5);
		gr.addEdge(v1, v3, 6);
		gr.addEdge(v1, v4, 4);
		gr.addEdge(v3, v4, 11);
		gr.addEdge(v2, v4, 1);
		gr.addEdge(v0, v4, 7);
		gr.addEdge(v0, v5, 48);
		gr.addEdge(v4, v5, 3);
		
		
		return gr.dijkstra(v0, v5);
	}
	
	@Test
	void testDijkstra() {
		double dist;
		try {
			dist = setUpSceneDijkstra();
			assertEquals(dist, 10);
		} catch (ThatVertexDoesNotExistException e) {
			fail("Unexpected Exception");
		} catch (InvalidActionInThisGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public GraphList<Country> setUpSceneKruskal() throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException, RepeatedVertexException {	
		GraphList<Country> gr = new GraphList<Country>(false, false, false);
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
		
		gr = gr.kruskal();		
		return gr;
	}
	
	@Test
	void testKruskal() {
		try {
			//setUpSceneKruskal();
			assertEquals(1,setUpSceneKruskal().getMinimunEdge(a, b));
			assertEquals(2,setUpSceneKruskal().getMinimunEdge(a, e));
			assertEquals(Double.POSITIVE_INFINITY,setUpSceneKruskal().getMinimunEdge(a, c));
			assertEquals(1,setUpSceneKruskal().getMinimunEdge(d, c));
			assertEquals(2,setUpSceneKruskal().getMinimunEdge(d, e));
			assertEquals(Double.POSITIVE_INFINITY,setUpSceneKruskal().getMinimunEdge(d, b));
			assertEquals(Double.POSITIVE_INFINITY,setUpSceneKruskal().getMinimunEdge(c, e));
			assertEquals(Double.POSITIVE_INFINITY,setUpSceneKruskal().getMinimunEdge(b, e));
		} catch (ThatVertexDoesNotExistException e1) {
			fail("Unexpected Exception");
		} catch (InvalidActionInThisGraphException e1) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public GraphList<Country> setUpScenePrim() throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException, RepeatedVertexException {
		Country v1 = new Country("Colombia", 1);
		Country v2 = new Country("EEUU", 2);
		Country v3 = new Country("Barrancabermeja", 3);
		Country v4 = new Country("Brazil", 4);
		Country v5 = new Country("Canada", 5);
		Country v6 = new Country("Australia", 6);
		
		GraphList<Country> gr = new GraphList<Country>(false, true, false);
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
		
		return gr.prim(v1); 
	}
	
	@Test
	void testPrim() {
		GraphList<Country> gr;
		try {
			gr = setUpScenePrim();
			assertTrue(gr.consultWeight() == 6 );
			assertEquals(4, gr.getAdjacentList().get(1).get(1)[1]);
			assertEquals(5, gr.getAdjacentList().get(0).get(0)[1]);
			assertEquals(6, gr.getAdjacentList().get(1).get(2)[1]);
			assertEquals(1, gr.getAdjacentList().get(4).get(1)[1]);
			assertEquals(3, gr.getAdjacentList().get(5).get(0)[1]);
		} catch (ThatVertexDoesNotExistException e) {
			fail("Unexpected Exception");
		} catch (InvalidActionInThisGraphException e) {
			fail("Unexpected Exception");
		} catch (RepeatedVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}	
	private GraphList<Country> setUpSceneAddEdgesDirectedMultiple() throws InvalidActionInThisGraphException, RepeatedVertexException, ThatVertexDoesNotExistException  {
		GraphList<Country> gr = new GraphList<Country>(true, true, false);
		gr.addVertex(colombia);
		gr.addVertex(china);
		gr.addVertex(rusia);
		gr.addVertex(villacubito);
		gr.addVertex(canada);
		gr.addEdge(colombia, china, 23);
		gr.addEdge(colombia, china, 44);
		gr.addEdge(canada, rusia, 75);
		gr.addEdge(canada, rusia, 14);
		return gr;
	}
	@Test
	void testAddEdgesDirectedMultiple() {
		try {
			GraphList<Country> gr = setUpSceneAddEdgesDirectedMultiple();
			ArrayList<double[]> adjacentsColombia = gr.getEdges(colombia);
			ArrayList<double[]> adjacentsChina = gr.getEdges(china);
			ArrayList<double[]> adjacentsRusia = gr.getEdges(rusia);
			assertEquals(0,adjacentsChina.size());
			assertEquals(0,adjacentsRusia.size());
			assertEquals("china",gr.getValues().get((int)adjacentsColombia.get(0)[0]).getName());
			assertEquals(23,(adjacentsColombia.get(0)[1]));
			assertEquals("china",gr.getValues().get((int)adjacentsColombia.get(1)[0]).getName());
			assertEquals(44,(adjacentsColombia.get(1)[1]));
		} catch (InvalidActionInThisGraphException e) {
			fail();
		} catch (RepeatedVertexException e) {
			fail();
		} catch (ThatVertexDoesNotExistException e) {
			fail();
		}
	}
	private GraphList<Country> setUpSceneAddEdgesNoDirectedNoMultiple() throws InvalidActionInThisGraphException, RepeatedVertexException, ThatVertexDoesNotExistException {
		GraphList<Country> gr = new GraphList<Country>(false , false, false);
		gr.addVertex(colombia);
		gr.addVertex(china);
		gr.addVertex(rusia);
		gr.addVertex(villacubito);
		gr.addVertex(canada);
		gr.addEdge(colombia, china, 23);
		return gr;
	}
	@Test
	void testAddEdgesNoDirectedNoMultiple() {
		try {
			GraphList<Country> gr = setUpSceneAddEdgesNoDirectedNoMultiple();
			ArrayList<double[]> adjacentsColombia = gr.getEdges(colombia);
			ArrayList<double[]> adjacentsChina = gr.getEdges(china);
			assertEquals(1,adjacentsChina.size());
			assertEquals(1,adjacentsColombia.size());
			assertEquals("china",gr.getValues().get((int)adjacentsColombia.get(0)[0]).getName());
			assertEquals(23,(adjacentsColombia.get(0)[1]));
			assertEquals("Colombia",gr.getValues().get((int)adjacentsChina.get(0)[0]).getName());
			assertEquals(23,(adjacentsColombia.get(0)[1]));
		} catch (InvalidActionInThisGraphException e) {
			fail();
		} catch (RepeatedVertexException e) {
			fail();
		} catch (ThatVertexDoesNotExistException e) {
			fail();
		}
	}
	private GraphList<Country> setUpSceneAddEdgesNoDirectedMultiple() throws InvalidActionInThisGraphException, RepeatedVertexException, ThatVertexDoesNotExistException  {
		GraphList<Country> gr = new GraphList<Country>(false , true, false);
		gr.addVertex(colombia);
		gr.addVertex(china);
		gr.addVertex(rusia);
		gr.addVertex(villacubito);
		gr.addVertex(canada);
		gr.addEdge(colombia, china, 23);
		gr.addEdge(colombia, china, 44);
		return gr;
	}
	@Test
	void testAddEdgesNoDirectedMultiple() {
		try {
			GraphList<Country> gr = setUpSceneAddEdgesNoDirectedMultiple();
			ArrayList<double[]> adjacentsColombia = gr.getEdges(colombia);
			ArrayList<double[]> adjacentsChina = gr.getEdges(china);
			assertEquals(2,adjacentsChina.size());
			assertEquals(2,adjacentsColombia.size());
			assertEquals("china",gr.getValues().get((int)adjacentsColombia.get(0)[0]).getName());
			assertEquals(23,(adjacentsColombia.get(0)[1]));
			assertEquals("china",gr.getValues().get((int)adjacentsColombia.get(1)[0]).getName());
			assertEquals(44,(adjacentsColombia.get(1)[1]));
			assertEquals("Colombia",gr.getValues().get((int)adjacentsChina.get(0)[0]).getName());
			assertEquals(23,(adjacentsChina.get(0)[1]));
			assertEquals("Colombia",gr.getValues().get((int)adjacentsChina.get(1)[0]).getName());
			assertEquals(44,(adjacentsChina.get(1)[1]));
		} catch (InvalidActionInThisGraphException e) {
			fail();
		} catch (RepeatedVertexException e) {
			fail();
		} catch (ThatVertexDoesNotExistException e) {
			fail();
		}
	}
	///////////////////Test Exceptions//////////////////
	private GraphList<Country> setUpSceneAddEdgesDirectedNoMultipleE() throws InvalidActionInThisGraphException, RepeatedVertexException, ThatVertexDoesNotExistException  {
		GraphList<Country> gr = new GraphList<Country>(true, false, false);
		gr.addVertex(colombia);
		gr.addVertex(china);
		gr.addVertex(rusia);
		gr.addVertex(villacubito);
		gr.addVertex(canada);
		gr.addEdge(colombia, china, 23);
		gr.addEdge(colombia, china, 44);
		return gr;
	}
	@Test
	void testAddEdgesDirectedE() {
		try {
			GraphList<Country> gr = setUpSceneAddEdgesDirectedNoMultipleE();
			fail();
		} catch (InvalidActionInThisGraphException e) {
			System.out.println("Pass!");
		} catch (RepeatedVertexException e) {
			fail();
		} catch (ThatVertexDoesNotExistException e) {
			fail();
		}
	}
	private GraphList<Country> setUpSceneAddEdgesNoDirectedNoMultipleE() throws InvalidActionInThisGraphException, RepeatedVertexException, ThatVertexDoesNotExistException {
		GraphList<Country> gr = new GraphList<Country>(false , false, false);
		gr.addVertex(colombia);
		gr.addVertex(china);
		gr.addVertex(rusia);
		gr.addVertex(colombia);
		gr.addVertex(villacubito);
		gr.addVertex(canada);
		gr.addEdge(colombia, china, 23);
		gr.addEdge(colombia, china, 64);
		return gr;
	}
	@Test
	void testAddEdgesNoDirectedNoMultipleE() {
		try {
			GraphList<Country> gr = setUpSceneAddEdgesNoDirectedNoMultipleE();
			fail();
		} catch (InvalidActionInThisGraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			System.out.println("Pass!");
		} catch (ThatVertexDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private GraphList<Country> setUpScenegetEdges() throws InvalidActionInThisGraphException, RepeatedVertexException, ThatVertexDoesNotExistException {
		GraphList<Country> gr = new GraphList<Country>(true, true, false);
		gr.addVertex(colombia);
		gr.addVertex(china);
		gr.addVertex(rusia);
		gr.addVertex(villacubito);
		gr.addVertex(canada);
		gr.addEdge(colombia, china, 44);
		gr.addEdge(colombia, china, 23);
		return gr;
	}
	@Test
	void testScenegetEdges() {
		try {
			GraphList<Country> gr = setUpScenegetEdges();
			assertEquals(23,gr.getMinimunEdge(colombia, china));
		} catch (InvalidActionInThisGraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ThatVertexDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private GraphList<Country> setUpSceneDeleteEdgeNoDirected() throws InvalidActionInThisGraphException, RepeatedVertexException, ThatVertexDoesNotExistException {
		GraphList<Country> gr = new GraphList<Country>(false, true, false);
		gr.addVertex(colombia);
		gr.addVertex(china);
		gr.addVertex(rusia);
		gr.addVertex(villacubito);
		gr.addVertex(canada);
		gr.addEdge(colombia, china, 44);
		gr.addEdge(colombia, china, 23);
		return gr;
	}
	@Test
	void testDeleteEdgeNoDirected() {
		try {
			GraphList<Country> gr = setUpSceneDeleteEdgeNoDirected();
			gr.deleteEdge(colombia, china, 23);
			ArrayList<double[]> adjacentsColombia = gr.getEdges(colombia);
			ArrayList<double[]> adjacentsChina = gr.getEdges(china);
			assertEquals(1,adjacentsColombia.size());
			assertEquals(1,adjacentsChina.size());
			assertEquals("china",gr.getValues().get((int)adjacentsColombia.get(0)[0]).getName());
			assertEquals(44,(adjacentsColombia.get(0)[1]));
			assertEquals("Colombia",gr.getValues().get((int)adjacentsChina.get(0)[0]).getName());
			assertEquals(44,(adjacentsChina.get(0)[1]));
		} catch (InvalidActionInThisGraphException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ThatVertexDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private GraphList<Country> setUpSceneDeleteVertex() throws InvalidActionInThisGraphException, RepeatedVertexException, ThatVertexDoesNotExistException {
		GraphList<Country> gr = new GraphList<Country>(false, true, false);
		gr.addVertex(colombia);
		gr.addVertex(china);
		gr.addVertex(rusia);
		gr.addVertex(villacubito);
		gr.addVertex(canada);
		gr.addEdge(colombia, china, 44);
		gr.addEdge(colombia, china, 23);
		gr.addEdge(china, rusia, 14);
		return gr;
	}
	@Test
	void testDeleteVertex() {
		try {
			GraphList<Country> gr = setUpSceneDeleteVertex();
			gr.deleteVertex(colombia);
			ArrayList<double[]> adjacentsChina = gr.getEdges(china);
			assertEquals(1,adjacentsChina.size());
			assertEquals("rusia",gr.getValues().get((int)adjacentsChina.get(0)[0]).getName());
			assertEquals(14,(adjacentsChina.get(0)[1]));
		} catch (InvalidActionInThisGraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ThatVertexDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private GraphList<Country> setUpSceneDeleteVertexValue() throws InvalidActionInThisGraphException, RepeatedVertexException, ThatVertexDoesNotExistException {
		GraphList<Country> gr = new GraphList<Country>(false, true, false);
		gr.addVertex(colombia);
		gr.addVertex(china);
		gr.addEdge(colombia, china, 44);
		gr.addEdge(colombia, china, 23);
		return gr;
	}
	@Test
	void testDeleteVertexValue() {
		try {
			GraphList<Country> gr = setUpSceneDeleteVertexValue();
			gr.deleteVertex(colombia);
			assertEquals(1,gr.getAdjacentList().size());
			assertEquals(1,gr.getValues().size());
			assertEquals("china",gr.getValues().get(0).getName());
		} catch (InvalidActionInThisGraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ThatVertexDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/////////
	private void setUpSceneDeleteVertexDoesnotExist() throws InvalidActionInThisGraphException, RepeatedVertexException {
		
	}
	@Test
	void testDeleteVertexValueDoesnotExist() {
		
	}
	/////////
	private void setUpSceneDeleteVertexValueSize() throws InvalidActionInThisGraphException, RepeatedVertexException {
		
	}
	@Test
	void testDeleteVertexValueSize() {
		
	}
	private void setUpSceneAddVertex() throws InvalidActionInThisGraphException, RepeatedVertexException {
		
	}
	@Test
	void testAddVertex() {
		
	}
	private void setUpSceneAddVertexValue() throws InvalidActionInThisGraphException, RepeatedVertexException {
		
	}
	@Test
	void testAddVertexValue() {
		
	}
	private void setUpSceneAddVertexValueSize() throws InvalidActionInThisGraphException, RepeatedVertexException {
		
	}
	@Test
	void testAddVertexValueSize() {
		
	}
	private void setUpSceneAddVertexValueRepeated() throws InvalidActionInThisGraphException, RepeatedVertexException {
		
	}
	@Test
	void testAddVertexValueRepeated() {
		
	}
	
}