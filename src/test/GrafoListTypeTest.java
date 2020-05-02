package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import datastructures.GrafoListType;
//import exceptions.InvalidActionInSimpleGraphException;
import exceptions.InvalidActionInSimpleGraphException;

class GrafoListTypeTest {
	private void setUpSceneStartEdges()  {
		GrafoListType graph = new GrafoListType(5, false, false);
		graph.initialEdges(0, 1, 2);
		graph.initialEdges(1, 4, 2);
		graph.initialEdges(4, 3, 2);
		graph.initialEdges(3, 2, 2);
		graph.initialEdges(2, 0, 2);
		int destiny = 0;
		int weight = 0;
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			System.out.println(graph.getAdjacentList()[i].size());
			for (int j = 0; j < graph.getAdjacentList()[i].size(); j++) {
				//destiny = graph.getAdjacentList()[i].get(0);
				//weight = graph.getAdjacentList()[i].get(1);
			}
		}	
	}
	@Test
	void test() {
		setUpSceneStartEdges();
	}
	private void setUpSceneStartEdges2() throws InvalidActionInSimpleGraphException  {
		GrafoListType graph = new GrafoListType(5, false, false);
		graph.addEdges(0, 1, 2);
		graph.addEdges(1, 4, 2);
		graph.addEdges(4, 3, 2);
		graph.addEdges(3, 2, 2);
		graph.addEdges(2, 0, 2);
		int destiny = 0;
		int weight = 0;
		for (int i = 0; i < graph.getAdjacentList().length; i++) {
			System.out.println(graph.getAdjacentList()[i].size());
			for (int j = 0; j < graph.getAdjacentList()[i].size(); j++) {
				//destiny = graph.getAdjacentList()[i].get(0);
				//weight = graph.getAdjacentList()[i].get(1);
			}
		}	
	}
	@Test
	void test2() {
		try {
			setUpSceneStartEdges2();
		} catch (InvalidActionInSimpleGraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
