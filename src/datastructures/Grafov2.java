package datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Grafov2<T> {
	private ArrayList<Double>[][] adjmatrix;
	private boolean directed;
	private ArrayList<Vertex> values;
	private boolean multiple;
	

	/*
	 * directed = means if the graph is directed or not
	 * i = the initial size of the graph
	 * multiple = means if the graph has multiple vertex or not
	 * v = the vertex to add because is not allowed to create an empty graph
	 */
	public Grafov2(boolean directed,  boolean multiple, Vertex v) {
		adjmatrix = new ArrayList[1][1];
		this.directed = directed;
		this.multiple = multiple;
		values = new ArrayList<>();
		values.add(v);
		adjmatrix[0][0] = new ArrayList<Double>();
	}
	
	
	
	public ArrayList<Double>[][] getAdjmatrix() {
		return adjmatrix;
	}


	public ArrayList<Vertex> getValues() {
		return values;
	}


	public boolean isMultiple() {
		return multiple;
	}


	public boolean isDirected() {
		return directed;
	}


	public T getVertex(T v) {
		T aux = null;
		for(int i = 0; i < values.size() && aux == null; i++) {
			if(values.get(i)==v) {
				aux = v;
			}
		}
		return aux;
	}
	
	public ArrayList<Double> getEdges(int i, int j) {
		return adjmatrix[i][j];
	}
	
	/*
	 * i = origin node
	 * j = destiny node
	 * w = weight
	 */
	public void addEdge(int i, int j, int w) {
		if(multiple) {
			addEdgeAux(i,j,w);
		}else {
			if(adjmatrix[i][j].isEmpty() && i!=j) {
				addEdgeAux(i,j,w);
			}
		}		
	}
	
	public void addEdgeAux(int i, int j, double w) {
		adjmatrix[i][j].add(w);
		if(!directed && j!=i) {
			adjmatrix[j][i].add(w);
		}
	}
	
	public void addVertex(Vertex v) {
		boolean cent = true;
		for(int i = 0; i < values.size(); i++) {
			if(values.get(i)==v) {
				cent = false;
				i = values.size();
			}
		}
		if(cent) {
			int x = adjmatrix.length+1;
			ArrayList<Double>[][] aux = new ArrayList[x][x];
			for(int j = 0; j < x; j++) {
				for(int k = 0; k < x; k++) {
					aux[j][k] = new ArrayList<Double>();
				}
			}
			adjmatrix = aux;	
			values.add(v);
		}
	}
	
	
	/*
	 * i = origin node
	 * j = destiny node
	 * w = weight
	 */
	public void deleteEdge(int i, int j, int w) {
		if(!adjmatrix[i][j].isEmpty()) {
			for(int x = 0; x < adjmatrix[i][j].size(); x++) {
				if(adjmatrix[i][j].get(x)==w) {
					adjmatrix[i][j].remove(x);
				}
				if(!isDirected()) {
					if(adjmatrix[j][i].get(x)==w) {
						adjmatrix[j][i].remove(x);
					}
				}
				
			}
			
		}
		
		
	}
	
	
	public void deleteVertex(int t) {
		int n = -1;
		if(t < values.size()) {
			n = t;
			values.remove(t);
		}
		
		int m = n;
		
		if(n!=-1) {
			for(;n < adjmatrix.length-1; n++) {
				for(;m < adjmatrix[n].length-1; m++) {
					adjmatrix[n][m] = adjmatrix[n][m+1];
				}
				adjmatrix[n] = adjmatrix[n+1];
			}
		
			ArrayList<Double>[][] aux = new ArrayList[adjmatrix.length-1][adjmatrix.length-1];
			for(int i = 0; i < aux.length; i++) {
				for(int j = 0; j < aux.length; j++) {
					aux[i][j] = adjmatrix[i][j];
				}
			}
			
			adjmatrix = aux;
		
		}
	}
	
	public int consultWeight() {
		return values.size();
	}
	
	
	/*
	 * v = origin vertex
	 */
	public ArrayList<Integer> bfs(int v) {
		boolean[] visited = new boolean[values.size()];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		ArrayList<Integer> tree = new ArrayList<Integer>();
//		try {
			queue.add(v);
			tree.add(v);
			while(!queue.isEmpty()) {
				for(int i = 0; i < values.size(); i++) {
					if(!adjmatrix[v][i].isEmpty() && !visited[i]) {
						queue.add(i);
						tree.add(i);
						visited[i] = true;
					}
				}
				visited[v] = true;
				v = queue.poll();
			}
//		} catch (IndexOutOfBoundsException e) {
//			
//		}
		return tree;
	}
	
	
	public ArrayList<Integer> dfs(int v) {
		boolean visited[] = new boolean[values.size()];
		ArrayList<Integer> aux = new ArrayList<Integer>();
		return dfsUtil(v, visited, aux);
	}
	
	private ArrayList<Integer> dfsUtil(int v, boolean[] visited, ArrayList<Integer> aux) {
		visited[v] = true;
		aux.add(v);
		for(int i = 0; i < values.size(); i++) {
			if(!adjmatrix[v][i].isEmpty() && !visited[i]) {
				dfsUtil(i, visited, aux);
			}
		}
		return aux;
	}
}

