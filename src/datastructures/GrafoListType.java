package datastructures;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidActionInSimpleGraphException;
import model.Country;

public class GrafoListType<T> {
	private boolean directed;
	private List<int[]>[] adjacentList;
	private ArrayList<T> values;
	private boolean multiple;
	public  GrafoListType(int n, boolean d) {
		adjacentList = new List[n];
		values = new ArrayList<T>();
		directed = d;
		for(int i=0; i<n; i++) {
			adjacentList[i] = new ArrayList<int[]>(); 
		}
	}
	//i = origin
	//j = destiny
	public void initialEdges(int i, int j, int weight){
			adjacentList[i].add(new int[]{j, weight});
			if(!directed) {
				adjacentList[j].add(new int[]{j, weight});
			}
		} 
	public void addEdges(int i, int j, int weight, boolean multiple) throws InvalidActionInSimpleGraphException{
		if(multiple) {
			adjacentList[i].add(new int[]{j, weight});
			if(!directed) {
				adjacentList[j].add(new int[]{j, weight});
			}
		} else if(adjacentList[i].size()==2){
			throw new InvalidActionInSimpleGraphException("Accion no permitida en grafo simple");
		} else if(adjacentList[i].size()<2) {
			validateUsedVertex(i, j);
		} else {
			adjacentList[i].add(new int[]{j, weight});
			if(!directed) {
				adjacentList[j].add(new int[]{j, weight});
			}
		} 
	}
	private void validateUsedVertex(int i, int j) throws InvalidActionInSimpleGraphException {
		for (int k = 0; k < adjacentList[j].size(); k++) {
			for (int k2 = 0; k2 < adjacentList[i].size(); k2++) {
				if(adjacentList[j].get(k)[0] == adjacentList[i].get(k2)[0]) {
					throw new InvalidActionInSimpleGraphException("Accion no permitida en grafo simple");
				}
			}
		}
	}
	public int[] getEdge(int i, int j) {
		int[] current= {-1,-1};
		for (int k = 0; k < adjacentList[i].size(); k++) {
			current = adjacentList[i].get(k);
			if(current[0]==j) {
				return current;
			}
		}
		return current;
	}
	public ArrayList<int[]> getEdges(int i, int j) {
		ArrayList<int[]> edges = new ArrayList<int[]>();
		int[] current= {-1,-1};
		for (int k = 0; k < adjacentList[i].size(); k++) {
			current = adjacentList[i].get(k);
			if(current[0]==j) {
				edges.add(current);
			}
		}
		return edges;
	}
	public void deleteEdge(int i, int j) {
		int[] current= {-1,-1};
		for (int k = 0; k < adjacentList[i].size(); k++) {
			current = adjacentList[i].get(k);
			if(current[0]==j) {
				adjacentList[i].remove(k);
			}
		}
	}
	public void deleteVertex(int vertex) {
		for (int i = vertex; i < adjacentList.length-1; i++) {
			adjacentList[vertex] = adjacentList[vertex+1];
		}
		for (int i = 0; i < adjacentList.length; i++) {
			adjacentList[i].remove(vertex);
		}
		adjacentList[vertex] = null;
		deleteVertex(vertex);
	}
	public void deleteVertex(T v) {
		int n = -1;
		for(int x = 0; x < values.size(); x++) {
			if(v.equals(x)) {
				values.remove(x);
			}
		}
	}
	public void addVertex(String name, int id) {
		List<int[]>[] adjacentListAux = new List[adjacentList.length+1]; 
		for (int i = 0; i < adjacentList.length; i++) {
			adjacentListAux[i] = adjacentList[i];
		}
		adjacentListAux[adjacentListAux.length]=new ArrayList<int[]>();
		adjacentList = adjacentListAux;
		addVertex(name, id);
		
	}
	public void addVertexValue(String name, int id) {
		Country country = new Country(name, id);
		values.add((T) country);
	}
	
	
	
	
}
