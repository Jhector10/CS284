package IDLList;

import java.util.ArrayList;
import java.util.StringJoiner;

public class IDLList<E> {
	
	private static class Node<E> {
		
		private E data;
		private Node<E> next;
		private Node<E> prev;
			
		private Node(E elem) {
			this.data = elem;
			this.next = null;
			this.prev = null;
		}
			
		private Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	} // Node Class Ends
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	public IDLList() {
		// Creates an empty double linked list 
		this.indices = new ArrayList<Node<E>>();
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	 public boolean add (int index, E elem) {
		 // adds elem at position index (counting from wherever head is). 
		 // It uses the index for fast access. It always returns true.
		 
		 if (index > size - 1 || index < 0) {
			 throw new IllegalArgumentException();
		 }
		 else if (elem == null) {
			 throw new IllegalArgumentException();
		 } if (index == 0) {
			 this.add(elem);
		 } else {
			 Node<E> current = indices.get(index);
			 Node<E> item = new Node<E>(elem, current.prev, current); 
			 // creates new node setting the node at index as next and previous one as prev
			 item.prev = current.prev;
			 item.next = current;
			 current.prev.next = item;
			 current.prev = item;
			 indices.add(index, item);
		 }
		 
		 size++;
		 return true;
	 }
	 
	 public boolean add (E elem) {
		 // adds elem at the head (i.e. it becomes the
		 // first element of the list). It always returns true.
		 
		 if (elem == null) {
			 throw new IllegalArgumentException();
		 } if (size == 0) {
			 Node<E> node = new Node<E>(elem); 
			 head = node;
			 tail = head;
			 indices.add(0, node);
		 } else {
			 Node<E> newTail = head;
			 head = new Node<E>(elem, null, newTail);
			 // set the head = to a new node with data elem and next as the old head
			 newTail.prev = head;
			 indices.add(0, head);
		 }
		 
		 size++;
		 return true;
	 }
	 
	 public boolean append (E elem) {
		 // that adds elem as the new last element of the
		 // list (i.e. at the tail). It always returns true.
		 if (elem == null) { 
			 throw new IllegalArgumentException();
		 }
		 
		 if (size == 0) { // if list is empty, add the element
			 this.add(elem);
		 } 
		 else {
			 Node<E> oldTail = tail; 
			 tail = new Node<E>(elem, oldTail, null);
			 // set the tail = to a new Node with data elem and prev as the old tail
			 oldTail.next = tail;
			 indices.add(tail);
		 }
		 
		 size++;
		 return true;
	 }
	 
	 public E get(int index) {
		 // that returns the object at position index from the
		 // head. It uses the index for fast access. Indexing starts 
		 // from 0, thus get(0) returns the
		 // head element of the list.
		 if (index > size - 1 || index < 0) {
			 throw new IllegalArgumentException();
		 }
		 
		 return indices.get(index).data;
	 }
	 
	 public E getHead() {
		 // returns the object at the head
		 if (size == 0) {
			 throw new IllegalArgumentException();
		 }
		 
		 return head.data;
	 }
	 
	 public E getLast() {
		 // returns the object at the tail
		 if (size == 0) {
			 throw new IllegalArgumentException();
		 }
		 
		 return tail.data;
	 }
	 
	 public int size() {
		 // returns the list size
		 return size;
	 }
	 
	 public E remove() {
		 // removes and returns the element at the head. Should
		 // throw an IllegalStateException if there is no such element.
		 if (head == null) {
			 throw new IllegalStateException();
		 }
		 
		 Node<E> node = head; // set a temp node as the head
		 
		 if (size == 1) { // if the size of the list is 1, remove the only element
			 head = null;
			 tail = null;
			 indices.clear();
		 } else {
			 head = head.next;
			 head.prev = null;
			 indices.remove(0); 
		 }
		 
		 size--;
		 return node.data;
	 }
	 
	 public E removeLast() {
		 // removes and returns the element at the tail. 
		 // Should throw an IllegalStateException if there 
		 // is no such element.
		 
		 if (size == 0) {
			 throw new IllegalStateException();
		 } 
		 
		 Node<E> node = tail; // set a temp node as the tail
		 
		 if (size == 1) { // if the size of the list is 1, remove the only element
			 head = null;
			 tail = null;
			 indices.clear();
		 } else {
			 tail = tail.prev;
			 tail.next = null;
			 indices.remove(size - 1);
		 }
		 
		 
		 size--;
		 return node.data;
	 }
	 
	 public E removeAt (int index) {
		 // removes and returns the element at the
		 // index. Use the index for fast access. 
		 // Should throw an IllegalStateException if
		 // there is no such element.
		 
		 if (size == 0) {
			 throw new IllegalStateException();
		 } if (index < 0 || index > size - 1) {
			 throw new IllegalArgumentException();
		 }
		 
		 Node<E> node = indices.get(index);
		 // set a temp node to the node at the index
		 
		 if (index == 0) { // if index is the head, remove the head
			 return remove();
		 } if (index == size - 1) { // if index is the last, remove the last
			 return removeLast();
		 } else {
			 node.prev.next = node.next;
			 node.next.prev = node.prev;
			 indices.remove(index);
		 }
		 
		 size--;
		 return node.data;
	 }
	 
	 public boolean remove(E elem) {
		 // that removes the first occurrence of elem in
		 // the list and returns true. Return false if 
		 // elem was not in the list.
		 
		 if (size == 0) {
			 throw new IllegalStateException();
		 } 
		 
		 if (head.data == elem) {
			 // if elem is at the head, use remove() to remove the head
			 remove();
			 return true;
		 }
		 
		 Node<E> node = head;
		 
		 for (int i = 0; i < size; i++) {
			 // iterate through the list to find the first occurrence of elem
			 if (node.data == elem) {
				 node.prev.next = node.next;
				 node.next.prev = node.prev;
				 indices.remove(i);
				 size--;
				 return true;
			 } else {
				 node = node.next;
			 }
		 }
		 
		 return false;
	 }
	 
	 public String toString() {
		 // represents the list as a array string to look like [1,2,3]
		 
		 Node<E> node = head; 
		 
		 StringJoiner arr = new StringJoiner(", ", "[","]"); 
		 
		 while (node != null) {
			 arr.add(node.data.toString());
			 node = node.next;
		 }
		 
		 return arr.toString();
	 }
	 
	 

}
