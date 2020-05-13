package datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

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
	public void addEdge(T i, T j, double w) {
		int x = values.indexOf(i);
		int y = values.indexOf(j);
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
	public void deleteEdge(T i, T j, int w) {
		int a = values.indexOf(i);
		int b = values.indexOf(j);
		if(!adjmatrix[a][b].isEmpty()) {
			for(int x = 0; x < adjmatrix[a][b].size(); x++) {
				if(adjmatrix[a][b].get(x)==w) {
					adjmatrix[a][b].remove(x);
				}
				if(!isDirected()) {
					if(adjmatrix[b][a].get(x)==w) {
						adjmatrix[b][a].remove(x);
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
		Grafov2<T> gr = new Grafov2<T>(isDirected(), isMultiple());
		if(gr.isDirected()) {
			
		}else {
			boolean[] visited = new boolean[values.size()];
	//		visited[values.indexOf(v)] = true;
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
				if(shortestDestiny!=-1) {
					visited[shortestDestiny] = true;
					gr.addVertex(values.get(shortestDestiny));
				}
				if(auxOrigin!=-1) {
					gr.addEdge(gr.getValues().get(auxOrigin), gr.getValues().get(auxdestiny), shortestEdge);
					int asd = gr.getAdjmatrix()[0][1].size();
					int asd2 = asd;
				}
			}
		}
		
		return gr;
	}
	
	
	public Grafov2<T> kruskal() {
		Grafov2<T> gr = new Grafov2<T>(isDirected(), isMultiple());
		if(!gr.isDirected()) {
			ArrayList<ArrayList<Integer>> added = new ArrayList<ArrayList<Integer>>();
			
			for(int i = 0; i < getValues().size(); i++) {
				gr.addVertex(getValues().get(i));
				added.add(new ArrayList<Integer>());
				added.get(i).add(i);
			}
			
			ArrayList<double[]> edges = new ArrayList<double[]>();
			
			for(int i = 0; i < values.size(); i++) {
				for (int j = 0; j < values.size(); j++) {
					if(adjmatrix[i][j].isEmpty()) {
						double[] e = new double[] {i, j, getMinimunEdge(i, j)};
						edges.add(e);
						
					}
				}
			}
			
	        Collections.sort(edges,new Comparator<double[]>() {
	        	 public int compare(double[] ints, double[] otherInts) {
	                 if(ints[2]>otherInts[2]) {
	                 	return 1;
	                 } else if(ints[0]<otherInts[0]) {
	                 	return -1;
	                 } else {
	                 	return 0;
	                 }
	             }
	        });
			
	        for(int i = 0; i < edges.size(); i++) {
	        	if(!kruskalUtil(edges.get(i), added)) {
	        		T auxO = values.get((int)edges.get(0)[0]);
		        	T auxD = values.get((int)edges.get(0)[1]);
		        	gr.addEdge(auxO, auxD, edges.get(0)[2]);
		        	added = kruskalAdd(added, (int)edges.get(0)[0], (int)edges.get(0)[1]);
	        	}
	        	
	        }
			
		} else {
			gr = null;
		}
		
		return gr;
	}
	
	
	private ArrayList<ArrayList<Integer>> kruskalAdd(ArrayList<ArrayList<Integer>> added, int o, int d){
		int auxo = -1;
		int auxd = -1;
		for(int i = 0; i < added.size(); i++) {
			for (int j = 0; j < added.get(i).size(); j++) {
				if(added.get(i).get(j) == o) {
					auxo = i;
				}
				if(added.get(i).get(j) == d) {
					auxd = i;
				}
			}
		}
		
		for(int i = 0; i < added.get(auxd).size(); i++) {
			added.get(auxo).add(added.get(auxd).get(i));
		}
		
		added.remove(auxd);
		
		return added;
	}
	
	
	private boolean kruskalUtil(double[] toAdd, ArrayList<ArrayList<Integer>> added) {
		boolean aux = false;
		int origin = -1, destiny = -1;
		for(int i = 0; i < added.size(); i++) {
			for(int j = 0; j < added.get(i).size(); j++) {
				if(toAdd[0] == added.get(i).get(j)) {
					origin = i;
				}
				if(toAdd[1] == added.get(i).get(j)) {
					destiny = i;
				}
			}
		}
		
		if(origin == destiny) {
			aux = true;
		}
		
		return aux;
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
		
		while(!pq.isEmpty()) {//ends when the queue is empty
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
	
	
	/*
	 * return = matrix of the minimum cost from all vertex to all vertex
	 */
	public double[][] floydWarshall() { 
        double dist[][] = new double[values.size()][values.size()]; 
        int i, j, k; 
  
       
        for (i = 0; i < values.size(); i++) {
            for (j = 0; j < values.size(); j++) {
            	if(!adjmatrix[i][j].isEmpty()) {
            		dist[i][j] = adjmatrix[i][j].get(0); 
            	}else if(i==j){
            		dist[i][j] = 0;
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