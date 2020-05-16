package datastructures;

import java.util.ArrayList;

public interface Graph<G, T> {
	ArrayList<T> getValues();
	//ArrayList<Double> getEdges(int i, int j);
	void addEdge(T i, T j, double w);
	void addVertex(T v);
	void deleteEdge(T i, T j, double w);
	void deleteVertex(T t); // OJO DEBE SER T
	G bfs(T v); //
	//G dfs(T v);
	G prim(T v);
	G kruskal();
	double[][] floydWarshall();
	double dijsktra(T vertex1, T vertex2);
}
