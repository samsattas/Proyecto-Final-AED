package datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Grafov2<T> {
	private ArrayList<Double>[][] adjmatrix;
	private boolean directed;
	private ArrayList<T> values;
	private boolean multiple;
	

	/*
	 * directed = means if the graph is directed or not
	 * multiple = means if the graph has multiple vertex or not
	 */
	public Grafov2(boolean directed,  boolean multiple) {
		adjmatrix = new ArrayList[0][0];
		this.directed = directed;
		this.multiple = multiple;
		values = new ArrayList<>();
	}
	
	
	
	public ArrayList<Double>[][] getAdjmatrix() {
		return adjmatrix;
	}


	public ArrayList<T> getValues() {
		return values;
	}


	public boolean isMultiple() {
		return multiple;
	}


	public boolean isDirected() {
		return directed;
	}
	
	
	public boolean hasLoop() {
		boolean aux = false;
		for(int i = 0; i < values.size(); i++) {
			if(adjmatrix[i][i].size()>0) {
				aux = true;
			}
		}
		
		return aux;
	}
	
	
	public double getMinimunEdge(int x, int y) {
		double aux = Double.POSITIVE_INFINITY;
		for(int i = 0; i < adjmatrix[x][y].size(); i++) {
			if(adjmatrix[x][y].get(i)<aux) {
				aux = adjmatrix[x][y].get(i);
			}
		}
		return aux;
	}


	public T getVertex(T v) {
		T aux = null;
		for(int i = 0; i < values.size() && aux == null; i++) {
			if(values.get(i).equals(v)) {
				aux = v;
			}
		}
		return aux;
	}
	
	public ArrayList<Double> getEdges(int i, int j) {
		return adjmatrix[i][j];
	}
	
	/*
	 * x = origin node
	 * y = destiny node
	 * w = weight
	 */
	public void addEdge(int x, int y, double w) {
		if(!isMultiple()) {
			if(adjmatrix[x][y].isEmpty()) {
				adjmatrix[x][y].add(w);
				if(!isDirected()) {
					adjmatrix[y][x].add(w);
				}
			}
		}else {
			adjmatrix[x][y].add(w);
			if(!isDirected()) {
				adjmatrix[y][x].add(w);
			}
		}
				
	}
	
	private void addEdgeAux(int i, int j, double w) {
		adjmatrix[i][j].add(w);
		if(!isDirected() && j!=i) {
			adjmatrix[j][i].add(w);
		}
	}
	
	public void addVertex(T v) {
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
		//I use -1 because there can't be a position -1
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
		return tree;
	}
	
	/*
	 * v = origin vertex
	 */
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
	
	public Grafov2<T> prim(T v) {
		boolean[] visited = new boolean[values.size()];
		Grafov2<T> gr = new Grafov2<T>(isDirected(), isMultiple());
//		values.indexOf(v);
		for(int i = 0; i < values.size(); i++) {
			if(values.get(i).equals(v)) {
				visited[i] = true;
			}
		}
		gr.addVertex(v);
		while(gr.consultWeight()<values.size()) {
			int shortestDestiny = -1;
			double shortestEdge = Double.POSITIVE_INFINITY;
			int auxOrigin = -1;
			for(int i = 0; i < gr.getValues().size(); i++) { //origin
				for(int j = 0; j < values.size(); j++) {//destiny
					if(adjmatrix[i][j].size()>0 && !visited[j]) {//if i and j have a connection and j hasn't be discovered, then...
						for(int k = 0; k < adjmatrix[i][j].size(); k++) {//looking for the shortest edge from i to j
							if(adjmatrix[i][j].get(k)<shortestEdge) {
								auxOrigin = i;
								shortestEdge = adjmatrix[i][j].get(k);
								shortestDestiny = j;
							}
						}
					}
				}
			}
			int auxdestiny =  gr.consultWeight();
			if(shortestDestiny!=-1 && auxOrigin!=-1) {
				visited[shortestDestiny] = true;
				gr.addVertex(values.get(shortestDestiny));
				gr.addEdge(auxOrigin, auxdestiny, shortestEdge);
				int asd = gr.getAdjmatrix()[0][1].size();
				int asd2 = asd;
			}
		}
		
		return gr;
	}
	
	
	/*
	 * vertex1 = origin vertex
	 * vertex2 = destiny vertex
	 * return = the cost of the shortest way from vertex1 to vertex2
	 */
	public double dijkstra(T vertex1, T vertex2) {
		int v1 = values.indexOf(vertex1);
		int v2 = values.indexOf(vertex2);
		
		boolean[] visited = new boolean[values.size()];
		visited[v1] = true;//origin is already visited
		
		double[] distances = new double[values.size()];
		for(int i = 0; i < distances.length; i++) {
			distances[i] = Double.POSITIVE_INFINITY;
		}
		distances[v1] = 0;//distance from origin to origin is 0
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(v1);
		
		while(!pq.isEmpty()) {
			int auxOrigin = pq.poll();
			for (int i = 0; i < values.size(); i++) {
				if(adjmatrix[auxOrigin][i].size()>0 && !visited[i]) {
					pq.add(i);
					visited[i] = true;
					distances[i] = distances[auxOrigin]+getMinimunEdge(auxOrigin, i);
				}
				double auxdist = distances[auxOrigin]+getMinimunEdge(auxOrigin, i);
				if(auxdist < distances[i]) {
					distances[i] = auxdist;
				}
			}
		}
		 return distances[v2];
	}
	
	
	public double[][] floydWarshall() { 
        double dist[][] = new double[values.size()][values.size()]; 
        int i, j, k; 
  
       
        for (i = 0; i < values.size(); i++) {
            for (j = 0; j < values.size(); j++) {
            	if(!adjmatrix[i][j].isEmpty()) {
            		dist[i][j] = adjmatrix[i][j].get(0); 
            	}else {
            		dist[i][j] = Double.POSITIVE_INFINITY;
            	}
                
            }
        } 
  
        
        for (k = 0; k < values.size(); k++) { 
            for (i = 0; i < values.size(); i++) { 
                for (j = 0; j < values.size(); j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
        return dist;
	}
}

