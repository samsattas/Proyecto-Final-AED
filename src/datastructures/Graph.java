package datastructures;

import java.util.ArrayList;

public interface Graph<T> {
	ArrayList<T> getValues();
	boolean isMultiple();
	boolean isDirected();
	boolean hasLoop();
	double getMinimunEdge(int x, int y); //OJO DEBE SER T
	ArrayList<Double> getEdges(int i, int j);
	void addEdge(T i, T j, double w);
	void addVertex(T v);
	void deleteEdge(T i, T j, int w);
	void deleteVertex(int t);
	int consultWeight();
	ArrayList<Integer> bfs(int v);
	ArrayList<Integer> dfs(int v);
	ArrayList<Integer> dfsUtil(int v, boolean[] visited, ArrayList<Integer> aux);
	Grafov2<T> prim(T v);
	Grafov2<T> kruskal();
	ArrayList<ArrayList<Integer>> kruskalAdd(ArrayList<ArrayList<Integer>> added, int o, int d);
	boolean kruskalUtil(double[] toAdd, ArrayList<ArrayList<Integer>> added);
	double dijkstra(T vertex1, T vertex2);
	double[][] floydWarshall();
}
