import java.util.NoSuchElementException;

public class LinkedStack<E> implements StackInt<E> {
	
	private static class Node<E> {
		
		private final E data;
		private final Node<E> next;
		
		private Node(E dataItem) {
			data = dataItem;
			next = null;
		}
		
		private Node(E dataItem, Node<E> nodeRef) {
			data = dataItem;
			next = nodeRef;
		}
	} // Node Class Ends
	
	private Node<E> topOfStackRef = null; // Stack is empty in the beginning
	
	public LinkedStack() {
		topOfStackRef = null;
	}
	
	public LinkedStack(E[] data) {
		topOfStackRef = null;
		for (E item: data) {
			push(item);
		}
	}

	@Override
	public E push(E obj) {
		topOfStackRef = new Node<E>(obj, topOfStackRef);
		return obj;
	}

	@Override
	public E peek() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return topOfStackRef.data;
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E popData = topOfStackRef.data;
		topOfStackRef = topOfStackRef.next;
		return popData;
	}

	@Override
	public boolean isEmpty() {
		return topOfStackRef == null;
	}

}
