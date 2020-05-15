package datastructures;

import java.util.ArrayList;

public interface Graph<G, T> {
	ArrayList<T> getValues();
	//ArrayList<Double> getEdges(int i, int j);
	void addEdge(T i, T j, double w);
	void addVertex(T v);
	void deleteEdge(T i, T j, int w);
	void deleteVertex(T t); // OJO DEBE SER T
	G bfs(int v); //
	G dfs(int v);
	G prim(T v);
	G kruskal();
	double dijkstra(T vertex1, T vertex2);
	double[][] floydWarshall();
}
