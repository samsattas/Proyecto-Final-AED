package datastructures;

import java.util.ArrayList;

import exceptions.InvalidActionInThisGraphException;
import exceptions.ThatVertexDoesNotExistException;

public interface Graph<G, T> {
	ArrayList<T> getValues();
	//ArrayList<Double> getEdges(int i, int j);
	void addEdge(T i, T j, double w) throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException;
	void addVertex(T v);
	void deleteEdge(T i, T j, double w);
	void deleteVertex(T t); // OJO DEBE SER T
	G bfs(T v) throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException; //
	//G dfs(T v);
	G prim(T v) throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException;
	G kruskal() throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException;
	double[][] floydWarshall();
	double dijkstra(T vertex1, T vertex2);
}
