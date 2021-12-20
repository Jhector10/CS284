import java.util.*;

public abstract class AbstractHeap<E> {
	
	private final ArrayList<E> theData;
	
	protected abstract int compare(E e1, E e2);
	
	public AbstractHeap() {
		theData = new ArrayList<>(10);
	}
	
	public boolean offer(E item) {
		theData.add(item);
		
		int child = theData.size() - 1; // find the element after adding it to the ArrayList
		int parent = (child - 1) / 2;
		
		while (parent >= 0 && compare(theData.get(parent), theData.get(child)) > 0) {
			// check that parent is greater than child so that we can swap them
			swap(parent, child);
			child = parent;
			parent = (child - 1) / 2;
		}
		
		return true;
	}
	
	public E poll() {
		if (isEmpty()) {
			return null;
		}
		
		E result = theData.get(0);
		
		if(theData.size() == 1) {
			theData.remove(0);
			return result;
		}
		
		theData.set(0, theData.remove(theData.size() - 1));
		
		int parent = 0;
		
		while(true) {
			int leftChild = 2 * parent - 1;
			
			if (leftChild >= theData.size()) {
				break;
			}
			
			int rightChild = leftChild + 1;
			int minChild = leftChild;
			
			if (rightChild < theData.size() && compare(theData.get(leftChild), theData.get(rightChild)) > 0) {
				minChild = rightChild;
			}
			
			if (compare(theData.get(parent), theData.get(minChild)) > 0) {
				swap(parent, minChild);
			} 
			
			else {
				break;
			}
		}
		return result;
	}
	
	private void swap(int a, int b) {
		// swapping the two indices
		E temp = theData.get(a);
		theData.set(a, theData.get(b));
		theData.set(b, temp);
	}
	
	public boolean isEmpty() {
		return theData.size() == 0;
	}

}
