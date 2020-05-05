package datastructures;

public class Vertex<T> {
	private final static String WHITE = "WHITE";
	private final static String GRAY = "GRAY";
	private final static String BLACK = "BLACK";
	
	private T value;
	private String color;
	private T prev;
	
	public Vertex(T value ) {
		this.value = value;
		color = WHITE;
		prev = null;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public T getPrev() {
		return prev;
	}

	public void setPrev(T prev) {
		this.prev = prev;
	}
	
	
}
