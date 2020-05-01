package datastructures;

import java.util.ArrayList;
import java.util.List;

public class Grafov2<T> {
	private ArrayList<Integer>[][] adjmatrix;
	private boolean directed;
	private ArrayList<T> values;
	private boolean multiple;
	
	public Grafov2(boolean directed, int i, boolean multiple) {
		adjmatrix = new ArrayList[i][i];
		this.directed = directed;
		this.multiple = multiple;
		values = new ArrayList<>();
		
		for(int j = 0; j < i-1; j++) {
			for(int k = 0; k < i; k++) {
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
		
		adjmatrix[i][j].add(w);
		if(!directed && j!=i) {
			adjmatrix[j][i].add(w);
		}
	}
	
	public void addVertex() {
		
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
	
	public void deleteVertex() {
		
	}
	
	public int consultWeight() {
		return values.size();
	}
	
	//aniadir, eliminar, esdirigido, consultrpeso
	//Array.fill(elarray, el valor);
	
}

