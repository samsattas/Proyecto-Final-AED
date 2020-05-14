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
	
	
	
	private GraphList<String> setUpSceneAddEdgesTrueTrueTrue() throws InvalidActionInSimpleGraphException, RepeatedVertexException  {
		int[] asd = new int[2];
		String data = "";
		GraphList<String> graph = new GraphList<>(false,true,true);
		graph.addVertex("colombia");
		graph.addVertex("china");
		graph.addVertex("rusia");
		graph.addVertex("villacubito");
		graph.addVertex("canada");
		graph.addEdges("colombia", "china", 1);
		graph.addEdges("china", "rusia", 2);
		graph.addEdges("rusia", "villacubito", 3);
		graph.addEdges("villacubito", "canada", 4);
		graph.addEdges("canada", "colombia", 5);
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
	private /*GraphList<String>*/String setUpSceneBFS() throws InvalidActionInSimpleGraphException, RepeatedVertexException  {
		double[] asd = new double[2];
		String data = "";
		GraphList<String> graph = new GraphList<>(true,true,true);
		GraphList<String> graphAux;
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("3");
		graph.addVertex("4");
		graph.addVertex("5");
		//graph.addVertex("peru");
		graph.addEdges("1", "3", 0);
		graph.addEdges("2", "1", 0);
		graph.addEdges("3", "2", 0);
		graph.addEdges("3", "4", 0);
		graph.addEdges("4", "2", 0);
		graph.addEdges("2", "5", 0); 
		graph.addEdges("5", "4", 0); 
		graphAux = graph.bfs("1");
		/*
		for (int i = 0; i < graphAux.getAdjacentList().size(); i++) {
			for (int j = 0; j < graphAux.getAdjacentList().get(i).size(); j++) {
				asd = graphAux.getAdjacentList().get(i).get(j);
				data += "Origen:"+ i  + " "+ "Destino:" + asd[0]+ " " + "Peso" + asd[1] + "\n";
			}
		}
		*/
		for (int j = 0; j < graphAux.getAdjacentList().get(3).size(); j++) {
			System.out.println(graphAux.getAdjacentList().get(2).get(j)[0]);
		}
		
		return data;
		//return graph;
	}
	@Test
	<T> void testBFS() {
		try {
			//System.out.println(setUpSceneBFS().bfs("china"));
			setUpSceneBFS();
			//setUpSceneBFS().bfs2("colombia");
		} catch (InvalidActionInSimpleGraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		gr.addEdges(v1, v3, -2);
		gr.addEdges(v3, v4, 2);
		gr.addEdges(v4, v2, -1);
		gr.addEdges(v2, v1, 4);
		gr.addEdges(v2, v3, 3);
		
		return gr.floydWarshall();	
	}
	@Test
	void testFloydWarshall() {
		
		double[][] matrix = setUpSceneFloydWarshall();
		/*
		for (int i = 0; i < matrix.length; i++) {
			System.out.println("Otra linea");
			for (int j = 0; j < matrix.length; j++) {
				data = matrix[i][j] + "";
				System.out.println(data);
			}
		}
		*/
		//System.out.println(matrix[0][3]);
		//System.out.println(matrix[0][3]);
		assertTrue(matrix[0][3] == 0 && matrix[3][2] == 1 && matrix[1][3] == 4 && matrix[1][2] == 2);
	}
	public double setUpScenegetMinimunEdgePlus() {
		Country v0 = new Country("Colombia", 1);
		Country v1 = new Country("EEUU", 2);
		
		
		GraphList<Country> gr = new GraphList<Country>(false, true, false);
		gr.addVertex(v0);
		gr.addVertex(v1);
		
		
		gr.addEdges(v0, v1, 5);
		gr.addEdges(v0, v1, 6);
		gr.addEdges(v0, v1, 4);
		gr.addEdges(v0, v1, 11);
		gr.addEdges(v0, v1, 14);
		gr.addEdges(v0, v1, 7);
		gr.addEdges(v0, v1, 48);
		gr.addEdges(v0, v1, 3);
		
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
		
		gr.addEdges(v0, v1, 5);
		gr.addEdges(v1, v3, 6);
		gr.addEdges(v1, v4, 4);
		gr.addEdges(v3, v4, 11);
		gr.addEdges(v2, v4, 1);
		gr.addEdges(v0, v4, 7);
		gr.addEdges(v0, v5, 48);
		gr.addEdges(v4, v5, 3);
		
		
		return gr.dijsktra(v0, v5);
	}
	
	@Test
	void testDijkstra() {
		double dist = setUpSceneDijkstra();
		assertEquals(dist, 10);
	}
	
	public /*GraphList<Country>*/ void setUpSceneKruskal() {
		Country a = new Country("Colombia", 1);//A
		Country b = new Country("EEUU", 2);//B
		Country c = new Country("Barrancabermeja", 3); //C
		Country d = new Country("Brazil", 4);//D
		Country e = new Country("Canada", 5);//E
		
		GraphList<Country> gr = new GraphList<Country>(false, false, false);
		//GraphList<Country> grAux;
		GraphList<Country> grAux;
		gr.addVertex(a);
		gr.addVertex(b);
		gr.addVertex(c);
		gr.addVertex(d);
		gr.addVertex(e);
		
		gr.addEdges(a, b, 1);
		gr.addEdges(a, e, 2);
		gr.addEdges(a, c, 4);
		gr.addEdges(d, c, 1);
		gr.addEdges(d, e, 2);
		gr.addEdges(d, b, 3);
		gr.addEdges(c, e, 3); 
		gr.addEdges(b, e, 3);
		grAux = gr.kruskal();
		
		//gr.getMinimunEdge(v4, v3);
		//
		gr = gr.kruskal();
		
		System.out.println("NOOOOOOO");
		System.out.println(gr.getMinimunEdge(a, b));
		System.out.println(gr.getMinimunEdge(a, e));
		System.out.println(gr.getMinimunEdge(a, c));
		System.out.println(gr.getMinimunEdge(d, c));
		System.out.println(gr.getMinimunEdge(d, e));
		System.out.println(gr.getMinimunEdge(d, b));
		System.out.println(gr.getMinimunEdge(c, e));
		System.out.println(gr.getMinimunEdge(b, e));
		System.out.println("NOOOOOOO");
		//
		/*
		System.out.println(grAux.getMinimunEdge(v1, v2));
		System.out.println(grAux.getMinimunEdge(v1, v5));
		System.out.println(grAux.getMinimunEdge(v4, v5));
		System.out.println(grAux.getMinimunEdge(v4, v3));
		*/
		//return gr;
	}
	
	@Test
	void testKruskal() {
		setUpSceneKruskal();
		//GraphList<Country> gr = setUpSceneKruskal();
		//gr.ed
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