package datastructures;

import datastructures.Kueueue;
import datastructures.Queue;
import datastructures.Kueueue.Node;

public class Kueueue<T> implements Queue<T> {

	private Node<T> head;
	private Node<T> butt;
	private int size;

	public Kueueue() {
		this.size = 0;
	}

	public Kueueue(Kueueue<T> copy) {
		
		if(copy != null){
			Node<T> nod = copy.head;
		
			addAll(nod);		
		}
	}

	public void addAll(Node<T> nod) {

		if (nod != null) {
			this.add(nod.getE());
			nod = nod.getNext();
			addAll(nod);
		}
	}

	@Override
	public void add(T element) {
	
		if(size == 0)
			this.butt = null;
		
		Node<T> b = butt;
		Node<T> toInsert = new Node<T>(element, null, b);
		this.butt = toInsert;

		if (b == null)
			head = toInsert;
		else
			b.next = toInsert;

		size++;
	}

	@Override
	public void clear() {
		this.head = null;
		this.butt = null;

	}

	@Override
	public T poll() {

		Node<T> unlinked = head;
		if (head != null) {
			this.head = head.next;
			if (head != null)
				head.setPrev(null);
			size--;
		}
		if(unlinked == null)
			return null;
		else
			return unlinked.e;
	}

	@Override
	public T peek() {

		if(head != null)
			return head.e;
		else
			return null;
	}

	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}

	@Override
	public int size() {

		return size;
	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public Node<T> getButt() {
		return butt;
	}

	public void setButt(Node<T> butt) {
		this.butt = butt;
	}

	public class Node<T> {

		private T e;

		private Node<T> next;
		private Node<T> prev;

		public Node(T e, Node<T> next, Node<T> prev) {

			this.e = e;
			if(next != null)
				next.setPrev(this);
			this.next = next;
			if(prev != null)
				prev.setNext(this);
			this.prev = prev;
			
		}

		public T getE() {
			return e;
		}

		public void setE(T e) {
			this.e = e;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public Node<T> getPrev() {
			return prev;
		}

		public void setPrev(Node<T> prev) {
			this.prev = prev;
		}

	}

}
