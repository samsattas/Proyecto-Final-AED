package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import model.Vertex;

public class GraphList<T> {
	
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
	public void addVertex(T vertex) {
		this.vertex.add(vertex);
		this.adjacentList.add(new ArrayList<double[]>());
	}
	public void addEdges(T origin, T destiny, double weight) {
		int originIndex = vertex.indexOf(origin);
		int destinyIndex = vertex.indexOf(destiny);
		if(originIndex==-1 || destinyIndex==-1) {
			//error
			//System.out.println(this.adjacentList.size());
		}
		else if(!loop && origin == destiny) {
			//error
			//System.out.println("Error");
		}
		else {
			if(!multiple) {
				for (int i = 0; i < adjacentList.get(originIndex).size(); i++) {
					if(destinyIndex == adjacentList.get(originIndex).get(i)[0]) {
						//error
						//System.out.println("Error");
					}
				}
			}
			//System.out.println(originIndex);
			//System.out.println(destinyIndex);
			double[] vertex = {destinyIndex, weight};
			adjacentList.get(originIndex).add(vertex);
			if(!directed) {
				double[] oppositeVertex = {originIndex,weight};
				adjacentList.get(destinyIndex).add(oppositeVertex);
			}
		}
	}
	public void deleteVertex(T vertex){

        int index = this.vertex.indexOf(vertex);

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
	public void deleteEdge(int origin, int destiny, int weight) {
		for (int i = 0; i < adjacentList.get(origin).size(); i++) {
			if(adjacentList.get(origin).get(i)[0]==destiny) {
				adjacentList.get(origin).remove(i);
			}
			if(multiple) {
				for (int j = 0; j < adjacentList.get(destiny).size(); j++) {
					if(adjacentList.get(destiny).get(j)[0]==origin) {
						adjacentList.get(destiny).remove(j);
					}
				}
			}
		}
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
	public ArrayList<T> getVertex() {
		return vertex;
	}
	public void setVertex(ArrayList<T> vertex) {
		this.vertex = vertex;
	}
	public ArrayList<ArrayList<double[]>> getAdjacentList() {
		return adjacentList;
	}
	public void setAdjacentList(ArrayList<ArrayList<double[]>> adjacentList) {
		this.adjacentList = adjacentList;
	}
	public ArrayList<Integer> bfs(T s) {
		Kueueue<Integer> queue = new Kueueue<>();
		boolean[] visitado = new boolean[vertex.size()];
		ArrayList<Integer> ordenVisita = new ArrayList<>();
		int initial = vertex.indexOf(s);
		ordenVisita.add(initial);
		visitado[initial]=true;
		queue.add(initial); 
		while(!queue.isEmpty()){
			int dequeue = queue.poll();
			for (int i = 0; i < adjacentList.get(dequeue).size(); i++) {
				double adyacente = adjacentList.get(dequeue).get(i)[0];
				if(adyacente>visitado.length-1) {
					visitado[(int) adyacente] = true;
					queue.add((int) adyacente); 
					ordenVisita.add((int) adyacente);
				} else if(adyacente<=visitado.length-1) {
					if(!visitado[(int) adyacente]) {
						visitado[(int) adyacente]=true;
						queue.add((int) adyacente);
						ordenVisita.add((int) adyacente);
					}
				}
			}
		}
		return ordenVisita;
	}
	public ArrayList<Integer> dfs (T v) {
		boolean[] visitado = new boolean[vertex.size()];
		ArrayList<Integer> aux = new ArrayList<Integer>();
		return dfsAux(visitado, aux, vertex.indexOf(v));
	}
	private ArrayList<Integer> dfsAux(boolean[] visitado, ArrayList<Integer> array, int vertex ){
		visitado[vertex] = true;
		array.add(vertex);
		for (int i = 0; i < this.vertex.size(); i++) {
			if(this.vertex.size()!=0 && !visitado[i]) {
				dfsAux(visitado,array,i);
			}
		}
		return array;
	}
	public double[][] floydWarshall() { 
        double dist[][] = translate(); 
        
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
	public double[][] translate() {
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
}
