package datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class GraphMatrix<T> implements Graph<GraphMatrix<T>, T>{
	private ArrayList<Double>[][] adjmatrix;
	private boolean directed;
	private ArrayList<T> values;
	private boolean multiple;
	

	/*
	 * directed = means if the graph is directed or not
	 * multiple = means if the graph has multiple vertex or not
	 */
	public GraphMatrix(boolean directed,  boolean multiple) {
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
	
	
	public double getMinimunEdge(T a, T b) {
		int x = values.indexOf(a);
		int y = values.indexOf(b);
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
		
		for (int k = 0; k < values.size(); k++) {
			if(values.get(k).equals(i)) {
				x = k;
			}
			if(values.get(k).equals(j)) {
				y = k;
			}
		}
		
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
			for (int i = 0; i < values.size(); i++) {
				for (int j = 0; j < values.size(); j++) {
					for (int k = 0; k < adjmatrix[i][j].size(); k++) {
						aux[i][j].add(adjmatrix[i][j].get(k));
					}
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
	public void deleteEdge(T i, T j, double w) {
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
	
	
	public void deleteVertex(T v) {
		int t = values.indexOf(v);
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
	public GraphMatrix<T> bfs( T v1) {
		GraphMatrix<T> gr = new GraphMatrix<T>(directed, multiple);
		for (int i = 0; i < values.size(); i++) {
			gr.addVertex(values.get(i));
		}
		boolean[] visited = new boolean[values.size()];
		LinkedList<T> queue = new LinkedList<T>();
 
		queue.add(v1);
		while(!queue.isEmpty()) {
			for(int i = 0; i < values.size(); i++) {
				if(!adjmatrix[values.indexOf(v1)][i].isEmpty() && !visited[i]) {
					gr.addVertex(values.get(i));
					gr.addEdge(v1, values.get(i), 0);
					queue.add(values.get(i));
					visited[i] = true;
				}
			}
			visited[values.indexOf(v1)] = true;
			v1 = queue.poll();
		}
		return gr;
	}
	
	
	/*
	 * v = origin vertex
	 */
	public GraphMatrix<T> dfs(T v) {
		boolean visited[] = new boolean[values.size()];
		GraphMatrix<T> gr = new GraphMatrix<T>(directed, multiple);
		for (int i = 0; i < values.size(); i++) {
			gr.addVertex(values.get(i));
		}
		return dfsUtil(v, visited, gr);
	}
	
	private GraphMatrix<T> dfsUtil(T v, boolean[] visited, GraphMatrix<T> gr) {
		visited[values.indexOf(v)] = true;
		
		for(int i = 0; i < values.size(); i++) {
			if(!adjmatrix[values.indexOf(v)][i].isEmpty() && !visited[i]) {
				
				dfsUtil(values.get(i), visited, gr);
				gr.addEdge(v, values.get(i), 0);
			}
		}
		return gr;
	}
	
	public GraphMatrix<T> prim(T v1) {
		GraphMatrix<T> gr = new GraphMatrix<T>(directed, multiple);
		for (int i = 0; i < values.size(); i++) {//adding all the vertex to the new graph
			gr.addVertex(values.get(i));
		}
		boolean[] visited = new boolean[values.size()];
		visited[values.indexOf(v1)] = true;//set this one true because is where it starts
		ArrayList<Integer> added = new ArrayList<Integer>();
		added.add(values.indexOf(v1));
		while(primCheck(visited, gr.getValues().size())){//will repeat this process until all the vertex are visited and added
			double aux = Double.POSITIVE_INFINITY;
			int auxOrigin = -1;
			int auxDestiny = -1;
			
			for (int i = 0; i < added.size(); i++) {//checking as origin all the vertexes connected
				for (int j = 0; j < values.size(); j++) {//checking the connection with the rest of the vertexes
					if(!adjmatrix[added.get(i)][j].isEmpty()) {
						if(getMinimunEdge(values.get(added.get(i)), values.get(j))<aux && !visited[j]) {
							aux = getMinimunEdge(values.get(added.get(i)), values.get(j));
							auxOrigin = added.get(i);
							auxDestiny = j;
						}
					}
				}
			}
			added.add(auxDestiny);
			gr.addEdge(values.get(auxOrigin), values.get(auxDestiny), getMinimunEdge(values.get(auxOrigin), values.get(auxDestiny)));
			
			visited[auxDestiny] = true;
		}
		
		return gr;
		
	}



	private boolean primCheck(boolean[] visited, int size) {
		boolean aux = true;
		int count = 0;
		
		for(int i = 0; i < size; i++) {
			if(visited[i]) {
				count++;
			}
		}
		if(count == visited.length) {
			aux = false;
		}
		
		return aux;
	}



	public GraphMatrix<T> kruskal() {
		GraphMatrix<T> gr = new GraphMatrix<T>(isDirected(), isMultiple());
		if(!gr.isDirected()) {
			ArrayList<ArrayList<Integer>> added = new ArrayList<ArrayList<Integer>>();
			
			for(int i = 0; i < getValues().size(); i++) {
				gr.addVertex(getValues().get(i));
				added.add(new ArrayList<Integer>());
				added.get(i).add(i);
			}
			
			ArrayList<double[]> edges = new ArrayList<double[]>();
			boolean[] visited = new boolean[values.size()];
			for(int i = 0; i < values.size(); i++) {
				for (int j = 0; j < values.size(); j++) {
					if(!adjmatrix[i][j].isEmpty() && !visited[j]) {
						double[] e = new double[] {i, j, getMinimunEdge(values.get(i), values.get(j))};
						edges.add(e);
					}
				}
				visited[i] = true;
			}
			
	        Collections.sort(edges,new Comparator<double[]>() {
	        	 public int compare(double[] ints, double[] otherInts) {
	                 if(ints[2]>otherInts[2]) {
	                 	return 1;
	                 } else if(ints[2]<otherInts[2]) {
	                 	return -1;
	                 } else {
	                 	return 0;
	                 }
	             }
	        });
	        int edgesSize = edges.size();
	        for(int i = 0; i < edgesSize; i++) {
	        	if(!kruskalUtil(edges.get(0), added)) {
	        		T auxO = values.get((int)edges.get(0)[0]);
		        	T auxD = values.get((int)edges.get(0)[1]);
		        	gr.addEdge(auxO, auxD, edges.get(0)[2]);
		        	added = kruskalAdd(added, (int)edges.get(0)[0], (int)edges.get(0)[1]);
		        	
	        	}
	        	edges.remove(0);
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
		
		for (int k = 0; k < values.size(); k++) {
			if(values.get(k).equals(vertex1)) {
				v1 = k;
			}
			if(values.get(k).equals(vertex2)) {
				v2 = k;
			}
		}
		
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
					distances[i] = distances[auxOrigin]+getMinimunEdge(values.get(auxOrigin), values.get(i));
				}
				double auxdist = distances[auxOrigin]+getMinimunEdge(values.get(auxOrigin), values.get(i));
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