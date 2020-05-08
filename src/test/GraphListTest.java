package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

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
	//@Test
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
	<T> void testBFS() {
		try {
			//System.out.println(setUpSceneBFS().bfs("china"));
			System.out.println(setUpSceneBFS().dfs("china"));
			//setUpSceneBFS().bfs2("colombia");
		} catch (InvalidActionInSimpleGraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepeatedVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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