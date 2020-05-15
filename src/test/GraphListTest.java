package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import datastructures.Grafov2;
import datastructures.GraphList;
import exceptions.InvalidActionInSimpleGraphException;
import exceptions.RepeatedVertexException;
import model.Country;
import model.Vertex;

class GraphListTest {
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
	
	
	
	private GraphList<String> setUpSceneAddEdgesTrueTrueTrue() throws InvalidActionInSimpleGraphException, RepeatedVertexException  {
		int[] asd = new int[2];
		String data = "";
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
		} catch (InvalidActionInSimpleGraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private GraphList<String> setUpSceneBFS() throws InvalidActionInSimpleGraphException, RepeatedVertexException  {
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
			assertEquals(2,graphAux.getAdjacentList().get(graphAux.getVertex().indexOf("0")).get(0)[0]);
			assertEquals(4,graphAux.getAdjacentList().get(graphAux.getVertex().indexOf("1")).get(0)[0]);
			assertEquals(1,graphAux.getAdjacentList().get(graphAux.getVertex().indexOf("2")).get(0)[0]);
			assertEquals(3,graphAux.getAdjacentList().get(graphAux.getVertex().indexOf("2")).get(1)[0]);
			
			//assertEquals(3,graphAux.getAdjacentList().get(graphAux.getVertex().indexOf("3")).get(0)[0]);
			
		} catch (InvalidActionInSimpleGraphException e) {
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			e.printStackTrace();
		}
	}
	@Test
	<T> void testBFSNoneExistentEdges() {
		try {
			GraphList<String> graphAux = setUpSceneBFS();
			assertEquals(0,graphAux.getAdjacentList().get(graphAux.getVertex().indexOf("3")).get(0)[0]);
			fail();
		} catch (InvalidActionInSimpleGraphException e) {
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			e.printStackTrace();
		} catch(IndexOutOfBoundsException e) {
			
		}
		try {
			GraphList<String> graphAux = setUpSceneBFS();
			
			assertEquals(0,graphAux.getAdjacentList().get(graphAux.getVertex().indexOf("1")).get(1)[0]);
			fail();
		} catch (InvalidActionInSimpleGraphException e) {
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			e.printStackTrace();
		} catch(IndexOutOfBoundsException e) {
			
		}
		try {
			GraphList<String> graphAux = setUpSceneBFS();
			assertEquals(0,graphAux.getAdjacentList().get(graphAux.getVertex().indexOf("4")).get(0)[0]);
			fail();
		} catch (InvalidActionInSimpleGraphException e) {
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			e.printStackTrace();
		} catch(IndexOutOfBoundsException e) {
			
		}
		
		//System.out.println("f");
	}
	public double[][] setUpSceneFloydWarshall() {
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
		double[][] matrix = setUpSceneFloydWarshall();
		assertTrue(matrix[0][3] == 0 && matrix[3][2] == 1 && matrix[1][3] == 4 && matrix[1][2] == 2);
	}
	public double setUpScenegetMinimunEdgePlus() {
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
		//System.out.println(setUpScenegetMinimunEdgePlus());
	}
	public double setUpSceneDijkstra() {
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
		
		
		return gr.dijsktra(v0, v5);
	}
	
	@Test
	void testDijkstra() {
		double dist = setUpSceneDijkstra();
		assertEquals(dist, 10);
	}
	
	public GraphList<Country> setUpSceneKruskal() {	
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
		setUpSceneKruskal();
		assertEquals(1,setUpSceneKruskal().getMinimunEdge(a, b));
		assertEquals(2,setUpSceneKruskal().getMinimunEdge(a, e));
		assertEquals(Double.POSITIVE_INFINITY,setUpSceneKruskal().getMinimunEdge(a, c));
		assertEquals(1,setUpSceneKruskal().getMinimunEdge(d, c));
		assertEquals(2,setUpSceneKruskal().getMinimunEdge(d, e));
		assertEquals(Double.POSITIVE_INFINITY,setUpSceneKruskal().getMinimunEdge(d, b));
		assertEquals(Double.POSITIVE_INFINITY,setUpSceneKruskal().getMinimunEdge(c, e));
		assertEquals(Double.POSITIVE_INFINITY,setUpSceneKruskal().getMinimunEdge(b, e));
	}
	
	
	
	
	
	
	
	/*
	private void setUpSceneAddEdgesDirectedMultiple() throws InvalidActionInSimpleGraphException, RepeatedVertexException  {
		
	}
	@Test
	void testAddEdgesDirectedMultiple() {
		
	}
	private void setUpSceneAddEdgesNoDirectedNoMultiple() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		
	}
	@Test
	void testAddEdgesNoDirectedNoMultiple() {
		
	}
	private void setUpSceneAddEdgesNoDirectedMultiple() throws InvalidActionInSimpleGraphException, RepeatedVertexException  {
		
	}
	@Test
	void testAddEdgesNoDirectedMultiple() {
		
	}
	///////////////////Test Exceptions//////////////////
	private void setUpSceneAddEdgesDirectedNoMultipleE() throws InvalidActionInSimpleGraphException, RepeatedVertexException  {
		
	}
	@Test
	void testAddEdgesDirectedE() {
		
	}
	private void setUpSceneAddEdgesNoDirectedNoMultipleE() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
	
	}
	@Test
	void testAddEdgesNoDirectedNoMultipleE() {
		
	}
	private void setUpScenegetEdges() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		
	}
	@Test
	void testScenegetEdges() {
		
	}
	private void setUpSceneDeleteEdgeNoDirected() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		
	}
	@Test
	void testDeleteEdgeNoDirected() {
		
	}
	private void setUpSceneDeleteEdgeDirected() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		
	}
	@Test
	void testDeleteEdgeDirected() {
		
	}
	private void setUpSceneDeleteEdgesOfADeletedVertex() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		
	}
	@Test
	void testDeleteVertex() {
		
	}
	private void setUpSceneDeleteVertexValue() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		
	}
	@Test
	void testDeleteVertexValue() {
		
	}
	/////////
	private void setUpSceneDeleteVertexDoesnotExist() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		
	}
	@Test
	void testDeleteVertexValueDoesnotExist() {
		
	}
	/////////
	private void setUpSceneDeleteVertexValueSize() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		
	}
	@Test
	void testDeleteVertexValueSize() {
		
	}
	private void setUpSceneAddVertex() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		
	}
	@Test
	void testAddVertex() {
		
	}
	private void setUpSceneAddVertexValue() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		
	}
	@Test
	void testAddVertexValue() {
		
	}
	private void setUpSceneAddVertexValueSize() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		
	}
	@Test
	void testAddVertexValueSize() {
		
	}
	private void setUpSceneAddVertexValueRepeated() throws InvalidActionInSimpleGraphException, RepeatedVertexException {
		
	}
	@Test
	void testAddVertexValueRepeated() {
		
	}
	*/
}