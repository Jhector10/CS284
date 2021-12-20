package lists;

import java.util.ArrayList;

public class IDLList<E> {
	
	public static class Node<E>{
		//data fields
		E data;
		Node<E> next;
		Node<E> prev;
		
		//constructors
		Node (E elem){
			//creates node holding elem
			this.data = elem;
		}
		Node(E elem, Node<E> prev, Node<E> next){
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	//data fields
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	//constructors
	/**creates empty double linked list
	 * requires index maintenance (assign/modify the index)
	 * @return 
	 * @return 
	 * 
	 */
	IDLList() {
		this.head = null;
		this.tail = head;
		this.size = 0;
		this.indices = new ArrayList<Node<E>>();
	}
	
	//methods
	
	/**adds elem at position index, counting from head
	 * uses index for fast access, always returns true
	 * requires index maintenance (assign/modify the index)
	 * @param index
	 * @param elem
	 * @return true
	 */
	public boolean add(int index, E elem) {
		if(index<0 || index>size) {
			throw new IllegalArgumentException("Index is out of bounds");
		}
		if (head==null) {
			add(elem);
		} else {
			Node<E> current = head;
			for(int i=0; i<index-1; i++) {
				current = current.next;
			}
			current.next = new Node<E>(elem,current,current.next);
			size++;
			tail = tail.next;
			indices.add(index, current.next);
		}
		return true;
	}
	/**adds elem at the head, elem becomes first element of list
	 * always returns true
	 * requires index maintenance (assign/modify the index)
	 * @param elem
	 * @return true
	 */
	public boolean add(E elem) {
		head = new Node<E>(elem, null, head);
		indices.add(0, head);
		size++;
		//if head.next is null head is also tail, otherwise tail.next = tail
		if(head.next==null) {
			tail = head;
		}else {
			tail = tail.next;
		}
		return true;
	}
	/**adds elem as the last new element of the list
	 * always returns true
	 * requires index maintenance (assign/modify the index)
	 * @param elem
	 * @return true
	 */
	public boolean append(E elem) {
		if(head==null) {
			add(elem);
		}else {
			Node<E> temp = new Node<E>(elem, tail, null);
			tail.next = temp;
			tail= temp;
			indices.add(temp);
			size++;
		}
		return true;
	}
	/**returns object at position index from the head
	 * uses index for fast access
	 * index starts at 0, get(0) returns head element
	 * @param index
	 * @return object index away from had
	 */
	public E get(int index) {
		if(index<0 || index>size) {
			throw new IllegalArgumentException("Index is out of bounds");
		} else {
			Node<E> current = head;
			for(int i=0; i<index; i++) {
			current = current.next;
			}
			return current.data;
		}
	}
	/**returns object at the head
	 * 
	 * @return object at head
	 */
	public E getHead() {
		if(head==null) {
			return null;
		}else {
			return head.data;
		}
	}
	/**returns the object at the tail
	 * 
	 * @return object at tail
	 */
	public E getLast() {
		if(tail==null) {
			return null;
		}else {
			return tail.data;
		}
	}
	/**returns the list size
	 * 
	 * @return list size
	 */
	public int size() {
		return size;
	}
	/**removes and returns the element at the head
	 * throws an IllegalStateException if there is no such element
	 * requires index maintenance (assign/modify the index)
	 * @return element at head that was removed
	 */
	public E remove() {
		if(head==null) {
			throw new IllegalStateException("remove(): list is empty");
		}
		E temp = head.data;
		if(head.next == null) {
			head = null;
		} else {
			head = head.next;
			head.prev = null;
		}
		size--;
		indices.remove(0);
		return temp;
	}
	/**removes and returns the element at the tail
	 * throws an IllegalStateException if there is no such element
	 * @return element at tail that was removed
	 */
	public E removeLast() {
		if(head==null) {
			throw new IllegalStateException("removeLast(): list is empty");
		}
		if (head.next==null) {
			E temp = remove();
			tail = null;
			return temp;
		} else {
			E temp = tail.data;
			tail = tail.prev;
			tail.next = null;
			size--;
			indices.remove((indices.size()-1));
			return temp;
		}
		
	}
	/**removes and returns the element at the index index
	 * uses index for fast access
	 * throws IllegalStateException if there is no such element
	 * requires index maintenance (assign/modify the index)
	 * @param index
	 * @return element at index that was removed
	 */
	public E removeAt(int index) {
		if (index<0 || index>size) {
			throw new IllegalStateException("removeAt: index out of bounds");
		} else if (head==null) {
			throw new IllegalStateException("removeAt: list is empty");
		} else if (index==0) {
			return remove();
		} else if (index == size-1) {
			return removeLast();
		} else {
			Node<E> current = head;
			for (int i=0; i<index-1; i++) {
				current = current.next;
			}
			E temp = current.next.data;
			current.next = current.next.next;
			current.next.prev = current.prev;
			size--;
			indices.remove(index);
			return temp;
		}
	}
	/**removes the first occurrence of elem in the list and returns true
	 * returns false if elem is not in list
	 * requires index maintenance (assign/modify the index)
	 * @param elem
	 * @return true if in list false if not in list
	 */
	public boolean remove(E elem) {
		//use arrayList.indexOf(Object O) to check whether object is in IDL, -1 means no
		//if else statement for return or remove
		
		int location = -1;
		for (int i=0; i<size; i++) {
			Node<E> t = indices.get(i);
			if(t.data == elem) {
				location = i;
				break;
			}
		}
		
		boolean result;
		if(location == -1) {
			result = false;
		}else {
			result = true;
			removeAt(location);
			indices.remove(location);
		}
		return result;
	}
	@Override
	/**presents a string representation of the list
	 * 
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node<E> current = head;
		
		result.append("[");
		while(current != null) {
			if(current.next != null) {
				result.append(current.data.toString()+", ");
			}else {
				result.append(current.data.toString());
			}
			current = current.next;
		}
		result.append("]");
		return result.toString();
	}
	/**
	 * allows for comparison of objects based off of contents not memory location
	 * used for test file
	 */
	public boolean equals(IDLList<E> idList) {
		return toString().equals(idList.toString());
	}
	
	public static void main(String[] args) {
//		IDLList<String> l1 = new IDLList<String>(); //empty list
//		IDLList<String> l2 = new IDLList<String>(); //single list
//		IDLList<String> l3 = new IDLList<String>(); //multiple list
//		IDLList<String> l4 = new IDLList<String>(); //multiple list, adjacent duplicates
//		
//		
//		l2.add("a");
//		
//		l3.append("a");
//		l3.append("b");
//		l3.append("c");
//		
//		l4.append("a");
//		l4.append("b");
//		l4.append("c");
//		l4.append("d");
//		l4.append("d");
//		
//		System.out.println(l1);
//		System.out.println(l2);
//		System.out.println(l3);
//		System.out.println(l4);
//		
//		l3.removeAt(1);
//		System.out.println(l3);
//		
//		System.out.println(l4.remove("d"));
//		System.out.println(l4);
	}
}
