package model;

public class Vertex<T> {
	private T value;
	private String color;
	private int distance;
	private Vertex<T> back;
	public Vertex(T value) {
		super();
		this.value = value;
		this.color = "White";
		this.distance = Integer.MAX_VALUE;
		this.back = null;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public T getValue() {
		return value;
	}
	public Vertex<T> getBack() {
		return back;
	}
	public void setBack(Vertex<T> vertexCompare) {
		this.back = vertexCompare;
	}
	
	
}
