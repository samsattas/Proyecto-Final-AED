package datastructures;

import java.util.ArrayList;
import java.util.List;

import model.Vertex;

public class GraphList<T> {
	
	private boolean directed;
	private boolean multiple;
	private boolean loop;
	private ArrayList<T> vertex;
	private ArrayList<ArrayList<int[]>> adjacentList;
	
	public GraphList(boolean directed, boolean multiple, boolean loop) {
		this.directed = directed;
		this.multiple = multiple;
		this.loop = loop;
		adjacentList = new ArrayList<>();
		vertex = new ArrayList<>();
	}
	public void addVertex(T vertex) {
		this.vertex.add(vertex);
		this.adjacentList.add(new ArrayList<int[]>());
	}
	public void addEdges(T origin, T destiny, int weight) {
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
			int[] vertex = {destinyIndex, weight};
			adjacentList.get(originIndex).add(vertex);
			if(!directed) {
				int[] oppositeVertex = {originIndex,weight};
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
	public ArrayList<T> getVertex() {
		return vertex;
	}
	public void setVertex(ArrayList<T> vertex) {
		this.vertex = vertex;
	}
	public ArrayList<ArrayList<int[]>> getAdjacentList() {
		return adjacentList;
	}
	public void setAdjacentList(ArrayList<ArrayList<int[]>> adjacentList) {
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
				int adyacente = adjacentList.get(dequeue).get(i)[0];
				if(adyacente>visitado.length-1) {
					visitado[adyacente] = true;
					queue.add(adyacente); 
					ordenVisita.add(adyacente);
				} else if(adyacente<=visitado.length-1) {
					if(!visitado[adyacente]) {
						visitado[adyacente]=true;
						queue.add(adyacente);
						ordenVisita.add(adyacente);
					}
				}
			}
		}
		return ordenVisita;
	}
}
