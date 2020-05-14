package datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	//////////////////
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
	/////////////////
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
	/*
	public GraphList<T> bfs(T s) {
		GraphList<T> graph = new GraphList<>(directed,multiple,loop);
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
				double adyacentWeight = adjacentList.get(dequeue).get(i)[1];
				if(adyacente>visitado.length-1) { 
					visitado[(int) adyacente] = true;
					queue.add((int) adyacente); 
					ordenVisita.add((int) adyacente);
					
					graph.addVertex(vertex.get(i));
					graph.addVertex(vertex.get((int) adyacente));
					graph.addEdges(vertex.get(i), vertex.get((int) adyacente), adyacentWeight);
					
				} else if(adyacente<=visitado.length-1) {
					if(!visitado[(int) adyacente]) {
						visitado[(int) adyacente]=true;
						queue.add((int) adyacente);
						ordenVisita.add((int) adyacente);
						
						graph.addVertex(vertex.get(i));
						graph.addVertex(vertex.get((int) adyacente));
						graph.addEdges(vertex.get(i), vertex.get((int) adyacente), adyacentWeight);
						
					}
				}
			}
		}
		return graph;
	}
	*/
	public GraphList<T> bfs(T s) {
		/*
  		ArrayList<Integer> ordenVisita = new ArrayList<>();
        GraphList<T> graph = new GraphList<>(directed,multiple,loop);

        Kueueue<Integer> queue = new Kueueue<>();
        boolean[] visitado = new boolean[vertex.size()];

        int initial = vertex.indexOf(s);
        queue.add(initial); 

        visitado[initial]=true;
        graph.addVertex(vertex.get(initial));
        ordenVisita.add(initial);
        */
		GraphList<T> graph = new GraphList<>(directed,multiple,loop);
		//graph.setVertex(vertex);
		for (int i = 0; i < vertex.size(); i++) {
			graph.addVertex(vertex.get(i));
		}
		Kueueue<Integer> queue = new Kueueue<>();
		boolean[] visitado = new boolean[vertex.size()];
		ArrayList<Integer> ordenVisita = new ArrayList<>();
		
		
		int initial = vertex.indexOf(s);
		ordenVisita.add(initial);
		visitado[initial]=true;
		queue.add(initial);
		graph.addVertex(s);
		
        while(!queue.isEmpty()){ 
            int dequeue = queue.poll();

            for (int i = 0; i < adjacentList.get(dequeue).size(); i++) {
                int adyacente = (int)adjacentList.get(dequeue).get(i)[0];
                
                if(!visitado[adyacente]) {
                    visitado[adyacente] = true;
                    queue.add(adyacente); 
                    ordenVisita.add(adyacente);
                    //graph.addVertex(vertex.get(adyacente));
                    graph.addEdges(vertex.get(dequeue), vertex.get(adyacente), 0);
                }
            }
        }
        return graph;
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
	////////////////////////////////SECCION DE EDICION///////////////////////////////////
	
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
				for(int i = 0; i < gr.getVertex().size(); i++) { //origin
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
					gr.addEdges(gr.getVertex().get(auxOrigin), gr.getVertex().get(auxdestiny), shortestEdge);
					//int asd = gr.getAdjmatrix()[0][1].size();
					//int asd2 = asd;
				}
			}
		}
		
		return gr;
	}
	
	
	
	
	
	
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
		        	gr./*addEdge*/addEdges(auxO, auxD, edges.get(0)[2]);//OJO
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
	////////////////////////////////SECCION DE EDICION///////////////////////////////////
	public boolean isMultiple() {
		return multiple;
	}
	public boolean isDirected() {
		return directed;
	}
	public int consultWeight() {
		return vertex.size();
	}
	/*
	public double getMinimunEdge(int x, int y) {
		
		double aux = Double.POSITIVE_INFINITY;
		for(int i = 0; i < adjacentList.get(x).size(); i++) {
			if(adjacentList.get(x).get(i)[1]<aux) {
				aux = adjacentList.get(x).get(i)[1];
			}
		}
		return aux;
	}
	*/
	
	
	
	
	
	
}
