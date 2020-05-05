package datastructures;

public class Vertex<E> {
	private final static String WHITE = "WHITE";
	private final static String GRAY = "GRAY";
	private final static String BLACK = "BLACK";
	
	private E value;
	private String color;
	private E prev;
	
	public Vertex(E value ) {
		this.value = value;
		color = WHITE;
		prev = null;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String aCadena() {
		return value.toString();
	}

//	public T getPrev() {
//		return prev;
//	}
//
//	public void setPrev(T prev) {
//		this.prev = prev;
//	}
//	
	
}
