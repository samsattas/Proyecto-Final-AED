package datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidActionInSimpleGraphException;
import exceptions.RepeatedVertexException;
import model.Country;

public class GrafoListType<T> {
	private boolean directed;
	private List<int[]>[] adjacentList;
	private ArrayList<T> values;
	private boolean multiple;
	public  GrafoListType(T v,boolean directeD, boolean multiplE) {
		adjacentList = new List[1];
		values = new ArrayList<T>();
		values.add(v);
		directed = directeD;
		multiple = multiplE; 
			adjacentList[0] = new ArrayList<int[]>(); 
	}
	//i = origin
	//j = destiny
	public void addEdges(int i, int j, int weight) throws InvalidActionInSimpleGraphException{
		if(multiple) {
			initialEdges(i,j,weight);
		} else if(adjacentList[i].size()==0) {
			initialEdges(i,j,weight);
		} else if(adjacentList[i].size()>0) {
			validateUsedVertex(i, j);
			initialEdges(i,j,weight);
		}
	}
	private void validateUsedVertex(int i, int j) throws InvalidActionInSimpleGraphException {
		if(directed) {
			validateDirectedUsedVertex(i,j);
		}
		if(i==j) {
			throw new InvalidActionInSimpleGraphException("Accion no permitida en grafo simple");
		}
		for (int k = 0; k < adjacentList[i].size(); k++) {
				if(adjacentList[i].get(k)[0] == j) {
					throw new InvalidActionInSimpleGraphException("Accion no permitida en grafo simple");
				}
			}
		for (int k2 = 0; k2 < adjacentList[j].size(); k2++) {
				if(adjacentList[j].get(k2)[0] == i) {
					throw new InvalidActionInSimpleGraphException("Accion no permitida en grafo simple");
				}
			}
	}
	private void validateDirectedUsedVertex(int i, int j) throws InvalidActionInSimpleGraphException {	
		for (int h = 0; h < adjacentList[i].size(); h++) {
			if(adjacentList[i].get(h)[0] == j) {
				throw new InvalidActionInSimpleGraphException("Accion no permitida en grafo simple");
			}
		}
	}
	private void initialEdges(int i, int j, int weight){
		adjacentList[i].add(new int[]{j, weight});
		if(!directed) {
			adjacentList[j].add(new int[]{i, weight});
		}
	}
	public ArrayList<int[]> getEdges(int i, int j, int w) {
		ArrayList<int[]> edges = new ArrayList<int[]>();
		int[] current= {-1,-1};
		for (int k = 0; k < adjacentList[i].size(); k++) {
			current = adjacentList[i].get(k);
			if(current[0]==j && current[1]==w) {
				edges.add(current);
			}
		}
		return edges;
	}
	public void deleteEdge(int i, int j, int w) {
		
		if(directed) {
			auxDelete(i, j, w);
		} else {
			auxDelete(i, j, w);
			auxDelete(j, i, w);
		}
	}
	//i origen
	//j destino
	private void auxDelete(int i, int j, int w) {
		for (int h = 0; h < adjacentList[i].size(); h++) {
			if(adjacentList[i].get(h)[0]==j && (adjacentList[i].get(h)[1]==w)) {
				adjacentList[i].remove(h);
			}
		}
	}
	private void deleteVertex(int vertex) {
		List<int[]>[] adjacentListAux  = new List[adjacentList.length-1];
		int i = vertex;
		for (; i < adjacentList.length-1; i++) {
			adjacentList[i] = adjacentList[i+1];
		}
		adjacentList[i] = null;
		for(int k=0; k<adjacentListAux.length; k++) {
			adjacentListAux[k] = new ArrayList<int[]>();
			adjacentListAux[k] = adjacentList[k];
		}
		adjacentList = adjacentListAux;
		for (int j = 0; j < adjacentList.length; j++) {
			for (int j2 = 0; j2 < adjacentList[j].size(); j2++) {
				if(adjacentList[j].get(j2)[0] == vertex) {
					adjacentList[j].remove(j2);
				}
			}
		}
		/*
		List<int[]>[] adjacentListAux  = new List[adjacentList.length-1];
		for (int i = 0; i < adjacentList.length; i++) {
			for (int j = 0; j < adjacentList[i].size(); j++) {
				if(adjacentList[i].get(j)[0] == vertex) {
					deleteEdge(i,vertex, adjacentList[i].get(j)[1]);
				}
			}
		}
		int i = vertex;
		for (; i < adjacentList.length-1; i++) {
			adjacentList[i] = adjacentList[i+1];
		}
		adjacentList[i] = null;
		for(int k=0; k<adjacentListAux.length; k++) {
			adjacentListAux[k] = new ArrayList<int[]>(); 
		}
		for (int j = 0; j < adjacentListAux.length; j++) {
			adjacentListAux[j] = adjacentList[j];
		}
		adjacentList = adjacentListAux;
		*/
	}
	private void addVertex() {
		List<int[]>[] adjacentListAux  = new List[adjacentList.length+1];
		for(int k=0; k<adjacentListAux.length; k++) {
			adjacentListAux[k] = new ArrayList<int[]>(); 
		}
		for (int j = 0; j < adjacentList.length; j++) {
			adjacentListAux[j] = adjacentList[j];
		}
		adjacentList = adjacentListAux;
	}
	public void addVertexValue(/*String name, int id*/T v) throws RepeatedVertexException{
		for (int i = 0; i < values.size(); i++) {
			if(values.get(i).equals(v)) {
				throw new RepeatedVertexException("The vertex is repeated");
			}
		}
		values.add(v);
		addVertex();
	}
	public void deleteVertexValue(/*int vertex*/T v) throws IndexOutOfBoundsException{
		int vertex = 100;
		for (int i = 0; i < values.size(); i++) {
			if(values.get(i).equals(v)) {
				vertex = i;
			}
		}
		values.remove(vertex);
		deleteVertex(vertex);
		//System.out.println("Tamanio values" + values.size());
		//System.out.println("Tamanio adjacentlist" + adjacentList.length);
		//int i = 0;
		/*
		try {
			for (int i = 0; i < values.size(); i++) {
				if(values.get(i).equals(v)) {
					values.remove(i);
					deleteVertex(i);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("The vertex does not exist");
		}
		*/
	}
	public int consultWeight() {
		return values.size();
	}
	public List<int[]>[] getAdjacentList() {
		return adjacentList;
	}
	public void setAdjacentList(List<int[]>[] adjacentList) {
		this.adjacentList = adjacentList;
	}
	public ArrayList<T> getValues() {
		return values;
	}
	public void setValues(ArrayList<T> values) {
		this.values = values;
	}
}
