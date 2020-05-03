package datastructures;

import java.util.ArrayList;
import java.util.List;


/*
 * all the code that is commented with "//" is the
 * way to use the graph but with ArrayList
 */
public class Grafov2<T> {
	private ArrayList<Integer>[][] adjmatrix;
//	private ArrayList<ArrayList<ArrayList<Integer>>> asd;
	private boolean directed;
	private ArrayList<T> values;
	private boolean multiple;
	

	/*
	 * directed = means if the graph is directed or not
	 * i = the initial size of the graph
	 * multiple = means if the graph has multiple vertex or not
	 * v = the vertex to add because is not allowed to create an empty graph
	 */
	public Grafov2(boolean directed,  boolean multiple, T v) {
		
		adjmatrix = new ArrayList[1][1];
		this.directed = directed;
		this.multiple = multiple;
		values = new ArrayList<>();
		values.add(v);
		adjmatrix[0][0] = new ArrayList<Integer>();
//		asd = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
//		for(int j = 0; j < i; j++) {
//			asd.addAll(new ArrayList<ArrayList<ArrayList<Integer>>>());
//			for(int k = 0; k < i; k++) {
//				asd.get(j).addAll(new ArrayList<ArrayList<Integer>>());
//				adjmatrix[j][k] = new ArrayList<Integer>();
//			}
//		}
	}
	
	
	
	public ArrayList<Integer>[][] getAdjmatrix() {
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


	public T getVertex(T v) {
		T aux = null;
		for(int i = 0; i < values.size() && aux == null; i++) {
			if(values.get(i)==v) {
				aux = v;
			}
		}
		return aux;
	}
	
	public ArrayList<Integer> getEdges(int i, int j) {
//		ArrayList<Integer> aux = new ArrayList<Integer>();
//		for (int k = 0; k < adjmatrix.length; k++) {
//			aux.add(adjmatrix[i][j].get(k));
//		}
		
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
			if(adjmatrix[i][j].isEmpty()) {
				addEdgeAux(i,j,w);
			}
		}
		
//		if(multiple) {
//			addEdgeAux(i,j,w);
//		}else {
//			if(asd.get(i).get(j).isEmpty()) {
//				addEdgeAux(i,j,w);
//			}
//		}
		
	}
	
	public void addEdgeAux(int i, int j, int w) {
		adjmatrix[i][j].add(w);
		if(!directed && j!=i) {
			adjmatrix[j][i].add(w);
		}
		
//		asd.get(i).get(j).add(w);
//		if(!directed && j!=i) {
//			asd.get(j).get(i).add(w);
//		}
	}
	
	public void addVertex(T v) {
		int x = adjmatrix.length+1;
		ArrayList<Integer>[][] aux = new ArrayList[x][x];
		for(int j = 0; j < x; j++) {
			for(int k = 0; k < x; k++) {
				aux[j][k] = new ArrayList<Integer>();
			}
		}
		adjmatrix = aux;
		
//		asd.add(new ArrayList<ArrayList<Integer>>());
//		for(int i = 0; i < asd.size(); i++) {
//			asd.get(i).add(new ArrayList<Integer>());
//		}
				
		values.add(v);
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
		
			ArrayList<Integer>[][] aux = new ArrayList[adjmatrix.length-1][adjmatrix.length-1];
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
	
	//aniadir, eliminar, esdirigido, consultrpeso
	//Array.fill(elarray, el valor);
	
}

