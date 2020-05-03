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
	public  GrafoListType(T v, int n, boolean directeD, boolean multiplE) {
		adjacentList = new List[n];
		values = new ArrayList<T>();
		values.add(v);
		directed = directeD;
		multiple = multiplE; 
		for(int i=0; i<n; i++) {
			adjacentList[i] = new ArrayList<int[]>(); 
		}
	}
	//i = origin
	//j = destiny
	public void addEdges(int i, int j, int weight) throws InvalidActionInSimpleGraphException{
		if(multiple) {
			initialEdges(i,j,weight);
		}/* else if(adjacentList[i].size()>=2){
			throw new InvalidActionInSimpleGraphException("Accion no permitida en grafo simple");
		} */else if(adjacentList[i].size()==0) {
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
		/*
		for (int k = 0; k < adjacentList[j].size(); k++) {
			for (int k2 = 0; k2 < adjacentList[i].size(); k2++) {
				if(directed) {
					validateDirectedUsedVertex(i,j);
				}
				if(adjacentList[j].get(k)[0] == adjacentList[i].get(k2)[0] ) {
					//throw new InvalidActionInSimpleGraphException("Accion no permitida en grafo simple");
				}
			}
		}
		*/
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
	/*
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
	*/
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
		
		if(directed) {
			auxDelete(i, j);
		} else {
			auxDelete(i, j);
			auxDelete(j, i);
		}
	}
	//i origen
	//j destino
	private void auxDelete(int i, int j) {
		for (int h = 0; h < adjacentList[i].size(); h++) {
			if(adjacentList[i].get(h)[0]==j) {
				adjacentList[i].remove(h);
			}
		}
	}
	public void deleteVertex(int vertex) {
		List<int[]>[] adjacentListAux  = new List[adjacentList.length-1];
		for (int i = 0; i < adjacentList.length; i++) {
			for (int j = 0; j < adjacentList[i].size(); j++) {
				if(adjacentList[i].get(j)[0] == vertex) {
					deleteEdge(i,vertex);
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
		//values.remove(vertex);
	}
	public void addVertex() {
		List<int[]>[] adjacentListAux  = new List[adjacentList.length+1];
		for(int k=0; k<adjacentListAux.length; k++) {
			adjacentListAux[k] = new ArrayList<int[]>(); 
		}
		for (int j = 0; j < adjacentList.length; j++) {
			adjacentListAux[j] = adjacentList[j];
		}
		adjacentList = adjacentListAux;
		//values.remove(vertex);
		/*
		List<int[]>[] adjacentListAux = new List[adjacentList.length+1]; 
		for (int i = 0; i < adjacentList.length; i++) {
			adjacentListAux[i] = adjacentList[i];
		}
		adjacentListAux[adjacentListAux.length]=new ArrayList<int[]>();
		adjacentList = adjacentListAux;
		addVertex(name, id);
		*/
	}
	public void addVertexValue(String name, int id) {
		Country country = new Country(name, id);
		values.add((T) country);
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
