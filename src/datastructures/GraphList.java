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
	public ArrayList<Vertex<T>> bfs(T s) {
		Kueueue<Vertex<T>> queue = new Kueueue<>();
		ArrayList<Vertex<T>> newe = new ArrayList<>();
		Vertex<T> vertexA = new Vertex(s);
		ArrayList<Vertex<T>> valuesRetorn = new ArrayList<>();
		//GraphList<T> interno = new GraphList<>(true, false, false); 
		for (int i = 0; i < vertex.size(); i++) {
			Vertex<T> vr = new Vertex(vertex.get(i));
			newe.add(vr);
		}
		System.out.println(newe.size());
		for (int i = 0; i < vertex.size(); i++) {
			if(newe.get(i).getValue().equals(s)) {
				newe.get(i).setColor("Gray");
				newe.get(i).setBack(null);
				newe.get(i).setDistance(0);
				queue.add(newe.get(i));
			}
		}
		while(!queue.isEmpty()) {
			Vertex<T> vertexCompare = queue.poll();
			//System.out.println(vertexCompare.getColor());
			//System.out.println(vertexCompare.getDistance());
			for (int i = 0; i < adjacentList.size() ; i++) { 
				//if(newe.get(i)[0].equals(vertexCompare)) {
					for (int j = 0; j < adjacentList.get(i).size(); j++) {
						//T valor1 = newe.get(adjacentList.get(i).get(j)[0]).getValue();
						if(newe.get(adjacentList.get(i).get(j)[0]).getValue().equals(vertexCompare.getValue())) {
							
							System.out.println(adjacentList.size());
							System.out.println(newe.get(adjacentList.get(i).get(j)[0]).getColor());
							
							if(newe.get(adjacentList.get(i).get(j)[0]).getColor().equals("White")) {
								System.out.println("No mans sky");
								valuesRetorn.add(newe.get(j));
								newe.get(j).setColor("Gray");
								newe.get(j).setDistance((int) (vertexCompare.getDistance()+1));
								newe.get(j).setBack(vertexCompare);
								queue.add(newe.get(j));
							}
							
						}
						
					}
					newe.get(i).setColor("Black");
				//}
			}
		}
		return valuesRetorn;
	}
	public void bfs2(T s) {
		Kueueue<Integer> queue = new Kueueue<>();
		ArrayList<Boolean> visitado = new ArrayList<>();
		int initial = vertex.indexOf(s);
		visitado.add(initial, true);
		queue.add(initial);
		while(!queue.isEmpty()){
			int dequeue = queue.poll();
			for (int i = 0; i < adjacentList.get(dequeue).size(); i++) {
				
			}
		}
		
		
		
		for (int i = 0; i < adjacentList.size(); i++) {
			for (int j = 0; j < adjacentList.get(i).size(); j++) {
				if(adjacentList.get(i).get(j)[0]>visitado.size()) {
					visitado.add(adjacentList.get(initial).get(j)[0], true);
				} 
			}
		}
	}
}
