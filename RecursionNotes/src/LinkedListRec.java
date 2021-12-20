
public class LinkedListRec<E> {
	
	private static class Node<E> {

	    private E data;
	    private Node<E> next = null;

	    public Node(E dataItem) {
	        data = dataItem;
	        next = null;
	    }
	        
	    public Node(E dataItem, Node<E> nodeRef) {
	        data = dataItem;
	        next = nodeRef;
	    }

	}
	
	private Node<E> head;
	private int size;
	
	private int size(Node<E> head) {
		if (head == null) {
			return 0;
		} else {
			return 1 + size(head.next);
		}
	}
	
	public int size() {
		return size(head);
	}
	
	private void replace(Node<E> head, E oldObj, E newObj) {
		if (head != null) {
			if (oldObj.equals(head.data)) {
				head.data = newObj;
			}
			
			replace(head.next, oldObj, newObj);
		}
	}
	
	public void replace(E oldObj, E newObj) {
		replace(head, oldObj, newObj);
	}
	
	private void add(Node<E> head, E data) {
		// if the list has just one element, add to it
		if (head.next == null) {
			head.next = new Node<E>(data);
		} else {
			add(head.next, data); // add to the back of list
		}
	}
	
	public void add(E data) {
		if (head == null) {
			head = new Node<E>(data);
		} else {
			add(head, data);
		}
	}
	
	private String toString(Node<E> head) {
		if (head == null) {
			return "";
		} else {
			return head.data + "\n" + toString(head.next);
		}
	}
	
	@Override
	public String toString() {
		return toString(head);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
