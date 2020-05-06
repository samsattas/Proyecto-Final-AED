package datastructures;

public interface Queue<T> {

	void add(T element);
	T poll();
	T peek();
	boolean isEmpty();
	void clear();
	int size();
	
}
