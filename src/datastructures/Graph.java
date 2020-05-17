package datastructures;

import java.util.ArrayList;

import exceptions.InvalidActionInThisGraphException;
import exceptions.RepeatedVertexException;
import exceptions.ThatVertexDoesNotExistException;

public interface Graph<G, T> {
	ArrayList<T> getValues();
	//ArrayList<Double> getEdges(int i, int j);
	void addEdge(T i, T j, double w) throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException;
	void addVertex(T v) throws RepeatedVertexException;
	void deleteEdge(T i, T j, double w);
	void deleteVertex(T t); // OJO DEBE SER T
	G bfs(T v) throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException, RepeatedVertexException; //
	G dfs(T v) throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException, RepeatedVertexException;
	G prim(T v) throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException, RepeatedVertexException;
	G kruskal() throws ThatVertexDoesNotExistException, InvalidActionInThisGraphException, RepeatedVertexException;
	double[][] floydWarshall();
	double dijkstra(T vertex1, T vertex2);
}
