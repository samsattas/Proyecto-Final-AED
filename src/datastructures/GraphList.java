package datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class GraphList<T>implements Graph<GraphList<T>, T>{
	
	private boolean directed;
	private boolean multiple;
	private boolean loop;
	private ArrayList<T> vertex;
	private ArrayList<ArrayList<double[]>> adjacentList;
	
	public GraphList(boolean directed, boolean multiple, boolean loop) {
		this.directed = directed;
		this.multiple = multiple;
		this.loop = loop;
		adjacentList = new ArrayList<>();
		vertex = new ArrayList<>();
	}
	public ArrayList<T> getValues(){
		return vertex;
	}
	public boolean isMultiple() {
		return multiple;
	}
	public boolean isDirected() {
		return directed;
	}
	public boolean hasLoop() {
		return loop;
	}
	public double getMinimunEdge(T x, T y) {
		int r = vertex.indexOf(x);
		int z = vertex.indexOf(y);
		double aux = Double.POSITIVE_INFINITY;
		for(int i = 0; i < adjacentList.get(r).size(); i++) {
			if(adjacentList.get(r).get(i)[0] == z && adjacentList.get(r).get(i)[1]<aux) {
				aux = adjacentList.get(r).get(i)[1];
			}
		}
		return aux;
	}
	
	
	
	
	
	
	
	
	//FALTA GET EDGES //ES ESTE????
	public ArrayList<ArrayList<double[]>> getAdjacentList() {
		return adjacentList;
	}
	
	
	
	
	
	
	
	
	
	public void addEdge(T i, T j, double w) {
		int originIndex = vertex.indexOf(i);
		int destinyIndex = vertex.indexOf(j);
		if(originIndex==-1 || destinyIndex==-1) {
			//error
			//System.out.println(this.adjacentList.size());
		}
		else if(!loop && i == j) {
			//error
			//System.out.println("Error");
		}
		else {
			if(!multiple) {
				for (int h = 0; h < adjacentList.get(originIndex).size(); h++) {
					if(destinyIndex == adjacentList.get(originIndex).get(h)[0]) {
						//error
						//System.out.println("Error");
					}
				}
			}
			//System.out.println(originIndex);
			//System.out.println(destinyIndex);
			double[] vertex = {destinyIndex, w};
			adjacentList.get(originIndex).add(vertex);
			if(!directed) {
				double[] oppositeVertex = {originIndex,w};
				adjacentList.get(destinyIndex).add(oppositeVertex);
			}
		}
	}
	
	public void addVertex(T vertex) {
		this.vertex.add(vertex);
		this.adjacentList.add(new ArrayList<double[]>());
	}
	
	
	public void deleteEdge(T i, T j, double w) {
		int origin = vertex.indexOf(i);
		int destiny = vertex.indexOf(j);
		for (int h = 0; h < adjacentList.get(origin).size(); h++) {
			if(adjacentList.get(origin).get(h)[0]==destiny) {
				adjacentList.get(origin).remove(h);
			}
			if(multiple) {
				for (int t = 0; t < adjacentList.get(destiny).size(); t++) {
					if(adjacentList.get(destiny).get(t)[0]==origin) {
						adjacentList.get(destiny).remove(t);
					}
				}
			}
		}
	}
	public void deleteVertex(T t){

        int index = this.vertex.indexOf(t);

        this.vertex.remove(index);
        adjacentList.remove(index);

        for(int i = 0; i < adjacentList.size(); i++){
            for(int j = 0; j < adjacentList.get(i).size(); j++){
                if(adjacentList.get(i).get(j)[0] > index){
                    adjacentList.get(i).get(j)[0] -= 1;
                }
                else if(adjacentList.get(i).get(j)[0] == index){
                    adjacentList.get(i).remove(j);
                    if(j<adjacentList.get(i).size()) {
                    	j--;
                    }
                }
            }
        }
    }
	public int consultWeight() {
		return vertex.size();
	}
	//////////////////
	
	/////////////////
	@Override
	public GraphList<T> bfs(T v) {
		GraphList<T> graph = new GraphList<>(directed,multiple,loop);
		for (int i = 0; i < vertex.size(); i++) {
			graph.addVertex(vertex.get(i));
		}
		Kueueue<Integer> queue = new Kueueue<>();
		boolean[] visitado = new boolean[vertex.size()];
		ArrayList<Integer> ordenVisita = new ArrayList<>();
		
		
		int initial = vertex.indexOf(v);
		ordenVisita.add(initial);
		visitado[initial]=true;
		queue.add(initial);
		graph.addVertex(v);
		
        while(!queue.isEmpty()){ 
            int dequeue = queue.poll();

            for (int i = 0; i < adjacentList.get(dequeue).size(); i++) {
                int adyacente = (int)adjacentList.get(dequeue).get(i)[0];
                
                if(!visitado[adyacente]) {
                    visitado[adyacente] = true;
                    queue.add(adyacente); 
                    ordenVisita.add(adyacente);
                    graph.addEdge(vertex.get(dequeue), vertex.get(adyacente), 0);
                }
            }
        }
        return graph;
    }

	public ArrayList<Integer> dfs (T v) {
		boolean[] visitado = new boolean[vertex.size()];
		ArrayList<Integer> aux = new ArrayList<Integer>();
		return dfsUtil(vertex.indexOf(v),visitado, aux);
	}
	private ArrayList<Integer> dfsUtil(int v, boolean[] visited, ArrayList<Integer> aux ){
		visited[v] = true;
		aux.add(v);
		for (int i = 0; i < this.vertex.size(); i++) {
			if(this.vertex.size()!=0 && !visited[i]) {
				dfsUtil(i, visited,aux);
			}
		}
		return aux;
	}
	@Override
	public GraphList<T> prim(T v) {
		GraphList<T> gr = new GraphList<>(directed, multiple, loop);
		if(gr.isDirected()) {
			
		}else {
			boolean[] visited = new boolean[vertex.size()];
	//		visited[values.indexOf(v)] = true;
			for(int i = 0; i < vertex.size(); i++) {
				if(vertex.get(i).equals(v)) {
					visited[i] = true;
				}
			}
			gr.addVertex(v);
			while(gr.consultWeight()<vertex.size()) {
				int shortestDestiny = -1;
				double shortestEdge = Double.POSITIVE_INFINITY;
				int auxOrigin = -1;
				for(int i = 0; i < gr.getValues().size(); i++) { //origin
					for(int j = 0; j < adjacentList.get(i).size(); j++) {//destiny
						if(adjacentList.get(i).size()/*.get(j).length**/>0 && !visited[j]) {//if i and j have a connection and j hasn't be discovered, then...
							//for(int k = 0; k < adjacentList.get(i).get(j).length; k++) {//looking for the shortest edge from i to j
								if(adjacentList.get(i).get(j)[1]<shortestEdge) {
									auxOrigin = i;
									shortestEdge = adjacentList.get(i).get(j)[1];
									shortestDestiny = j;
								}
							//}
						}
					}
				}
				int auxdestiny =  gr.consultWeight();
				if(shortestDestiny!=-1) {
					visited[shortestDestiny] = true;
					gr.addVertex(vertex.get(shortestDestiny));
				}
				if(auxOrigin!=-1) {
					gr.addEdge(gr.getValues().get(auxOrigin), gr.getValues().get(auxdestiny), shortestEdge);
					//int asd = gr.getAdjmatrix()[0][1].size();
					//int asd2 = asd;
				}
			}
		}
		
		return gr;
	}
	@Override
	public GraphList<T> kruskal() {
		GraphList<T> gr = new GraphList<>(isDirected(), isMultiple(), loop);
		if(!gr.isDirected()) {
			ArrayList<ArrayList<Integer>> added = new ArrayList<ArrayList<Integer>>();
			
			for(int i = 0; i < vertex/*getValues()*/.size(); i++) {
				gr.addVertex(vertex/*getValues()*/.get(i));
				added.add(new ArrayList<Integer>());
				added.get(i).add(i);
			}
			
			ArrayList<double[]> edges = new ArrayList<double[]>();
			boolean[] visited = new boolean[vertex.size()];
			for(int i = 0; i <vertex.size(); i++) {
				for (int j = 0; j < adjacentList.get(i).size(); j++) {
					int destiny = (int) adjacentList.get(i).get(j)[0];
					if(!visited[destiny]) {
						double[] e = new double[] {i, destiny, getMinimunEdge(vertex.get(i),vertex.get(destiny))};
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
	        		T auxO =vertex/* values*/.get((int)edges.get(0)[0]);
		        	T auxD = vertex /*values*/.get((int)edges.get(0)[1]);
		        	gr./*addEdge*/addEdge(auxO, auxD, edges.get(0)[2]);//OJO
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
	
	
	public boolean kruskalUtil(double[] toAdd, ArrayList<ArrayList<Integer>> added) {
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
	@Override
	public double dijsktra(T vertex1, T vertex2) {
		int v1 = vertex.indexOf(vertex1);
		int v2 = vertex.indexOf(vertex2);
		
		boolean[] visited = new boolean[vertex.size()];
		visited[v1] = true;
		
		double[] distances = new double[vertex.size()];
		for(int i = 0; i < distances.length; i++) {
			distances[i] = Double.POSITIVE_INFINITY;
		}
		distances[v1] = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(v1);
		
		while(!pq.isEmpty()) {
			int auxOrigin = pq.poll();
			for (int i = 0; i < vertex.size(); i++) {
				if(!visited[i]) {
					pq.add(i);
					visited[i] = true;
					distances[i] = distances[auxOrigin]+getMinimunEdge(vertex.get(auxOrigin), vertex.get(i));
				}
				double auxdist = distances[auxOrigin]+getMinimunEdge(vertex.get(auxOrigin), vertex.get(i));
				if(auxdist < distances[i]) {
					distances[i] = auxdist;
				}
			}
		}
		return distances[v2];
	}
	@Override
	public double[][] floydWarshall() { 
        double dist[][] = floydWarshallAux(); 
        
        int i, j, k; 
        
        for (k = 0; k < vertex.size(); k++) { 
            for (i = 0; i < vertex.size(); i++) { 
                for (j = 0; j < vertex.size(); j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                    {
                    	 dist[i][j] = dist[i][k] + dist[k][j]; 
                    }     
                } 
            } 
        } 
        return dist;
	}
	private double[][] floydWarshallAux() {
		 double dist[][] = new double[vertex.size()][vertex.size()]; 
	        int i, j; 
	        for (int k2 = 0; k2 < dist.length; k2++) {
	        	for (int l = 0; l < dist.length; l++) {
	        		if(k2==l) {
	        			dist[k2][l] = 0;
	        		}else { 
	        			dist[k2][l] = Integer.MIN_VALUE;
	        		}
				}
			}
	        for (i = 0; i < adjacentList.size(); i++) {
	            for (j = 0; j < adjacentList.get(i).size(); j++) {
	            		dist[i][(int) adjacentList.get(i).get(j)[0]] = adjacentList.get(i).get(j)[1];/*adjmatrix[i][j].get(0)*/  
	            }
	        }
	        for (int k2 = 0; k2 < dist.length; k2++) {
	        	for (int l = 0; l < dist.length; l++) {
	        		if(dist[k2][l] == Integer.MIN_VALUE) {
	        			dist[k2][l] = 99999999;
	        		}
				}
			}
	   return dist;
	}
	
	public void setAdjacentList(ArrayList<ArrayList<double[]>> adjacentList) {
		this.adjacentList = adjacentList;
	}	
}
