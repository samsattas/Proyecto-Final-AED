package datastructures;

import java.util.ArrayList;
import java.util.List;

public class Grafov2<T> {
	private ArrayList<Integer>[][] adjmatrix;
//	private ArrayList<ArrayList<ArrayList<Integer>>> asd;
	private boolean directed;
	private ArrayList<T> values;
	private boolean multiple;
	

	
	public Grafov2(boolean directed, int i, boolean multiple) {
		
		adjmatrix = new ArrayList[i][i];
		this.directed = directed;
		this.multiple = multiple;
		values = new ArrayList<>();
		
//		asd = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		for(int j = 0; j < i; j++) {
//			asd.addAll(new ArrayList<ArrayList<ArrayList<Integer>>>());
			for(int k = 0; k < i; k++) {
//				asd.get(j).addAll(new ArrayList<ArrayList<Integer>>());
				adjmatrix[j][k] = new ArrayList<Integer>();
			}
		}
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
	
	public boolean isDirected() {
		return directed;
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
	
	public void deleteVertex(T v) {
		int n = -1;
		for(int x = 0; x < values.size(); x++) {
			if(v == values.get(x)) {
				n = x;
				values.remove(x);
			}
		}
		int m = n;
		
		if(n!=-1) {
			for(;n < adjmatrix.length; n++) {
				for(;m < adjmatrix[n].length; m++) {
					adjmatrix[n][m] = adjmatrix[n][m+1];
				}
				adjmatrix[n] = adjmatrix[n+1];
			}
		}
		ArrayList<Integer>[][] aux = new ArrayList[adjmatrix.length-1][adjmatrix.length-1];
		aux = adjmatrix;
		adjmatrix = aux;
	}
	
	public int consultWeight() {
		return values.size();
	}
	
	//aniadir, eliminar, esdirigido, consultrpeso
	//Array.fill(elarray, el valor);
	
}

