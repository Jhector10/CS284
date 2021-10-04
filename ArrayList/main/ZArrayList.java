package main;

import java.util.Arrays;
import java.util.AbstractList;
import java.util.Collection;

public class ZArrayList<E> extends AbstractList<E>{
	
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
		this.addAll(c);
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
	
	@Override
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
	
	@Override
	public E get(int index) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException ("" + index);
		}
		return theData[index];
	}
	
	@Override
	public E set(int index, E anEntry) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException ("" + index);
		}
		
		E oldValue = theData[index];
		theData[index] = anEntry;
		return oldValue;
	}
	
	@Override
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

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	public int indexOf(Object item) {
		for(int i=0; i < size; i++) {
			if(theData[i] == null && item == null) {
				return i;
			}
			if(theData[i].equals(item)) {
				return i;
			}
		}
		
		return -1;
	}
	
	//returns true if the item is in the list, false otherwise
    public boolean member(E item) {
        for(int i = 0; i < size; i++) {
            if(theData[i] == item) {
                return true;
            }
        }
        
        return false;
    }
    
    // Removes all copies of the item from list
    public void removeAll(E item) {
        for(int i = size - 1; i >= 0; i--) {
            if(theData[i] == item) {
                this.remove(i);
            }
        }
        
    }
    
    // Returns true if an element is repeated, false otherwise
    public boolean hasRepititions() {
        for(int i = 0; i < size - 1; i++) {
            for(int j = i + 1; j < size; j++) {
                if(theData[i] == theData[j]) {
                    return true;
                }
            }
        }
        return false;
    }
	
	
	
	
}
