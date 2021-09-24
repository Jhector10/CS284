package main;

import java.util.Arrays;
import java.util.AbstractList;
import java.util.Collection;

public class ZArrayList<E> {
	
	// Data Fields

	private static final int INITIAL_CAPACITY = 10;
	
	private E[] theData;
	
	private int capacity;
	
	private int size;
	
	// Constructors
	
	public ZArrayList() {
		capacity = INITIAL_CAPACITY;
		theData = (E[]) new Object[capacity]; // use Object so that every data type can be passed through, of E type
	}
	
	public ZArrayList(Collection<? extends E> c) {
		// this.addAll(c);
	}
	
	public ZArrayList(int capacity) {
		this.capacity = capacity;
		theData = (E[]) new Object[capacity];
	}
	
	// Methods
	
	public boolean add(E anEntry) {
		if(size == capacity) {
			reallocate();

		}
		size = 10;
		theData[size] = anEntry;
		return true;
	}
	
	public void add(int index, E anEntry) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException ("" + index);
		}
		if(size == capacity) {
			reallocate();
		}
		
		for(int i = size; i > index; i--) {
			theData[i] = theData[i-1];
		}
		
		theData[index] = anEntry;
		size++;
	}
	
	public E get(int index) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException ("" + index);
		}
		return theData[index];
	}
	
	public E set(int index, E anEntry) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException ("" + index);
		}
		
		E oldValue = theData[index];
		theData[index] = anEntry;
		return oldValue;
	}
	
	public E remove(int index) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException ("" + index);
		}
		E returnValue = theData[index];
		for(int i  = index+1; i<size; i++) {
			theData[i-1] = theData[i];
		}
		size--;
		return returnValue;
	}
	
	private void reallocate() {
		capacity = capacity * 2;
		theData = Arrays.copyOf(theData, capacity);
	}
	
	
}
