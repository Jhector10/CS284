package lists;



public class SLL1<E> { //linked list with only one reference

	public static class Node<F> {
		// data fields
		private F data;
		private Node<F> next;
		
		Node(F data) {
			this.data=data;
			this.next=null;
		}
		
		Node(F data, Node<F> next) {
			this.data=data;
			this.next=next;
		}
		
	}
	
	// Data fields
	private Node<E> head;
	private int size;
	
	// Constructor
	SLL1() {
		head=null;
		size=0;
	}
	
	// Methods
	public E addFirst(E item) {
		head = new Node<E>(item, head); //<> is a diamond, tells java to figure out the info by itself
		size++;
		return item;
	}
	
	//add item at the end of the list
	public E add(E item) {
		//increase size by 1
		//iterate till you get to element size - 1
		
		if (head==null) {// the list is empty
			head = new Node<E>(item);
			size++;
			return item;
		}
		//the list is not empty
		Node<E> current = head;
		
		while (current.next!=null) {
			current = current.next;
		}
		current.next = new Node<E>(item);
		size++;
		return item;
	}
	
	//add an item at given index
	public E add(E item, int index) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException("Index out of bounds");
		}
		if (head==null) {
			addFirst(item);
			return item;
		} else {
			Node<E> current = head;
			for (int i=0; i<index-1; i++) {
				current = current.next;
			}
			current.next = new Node<E>(item, current.next);
			size++;
			return item;
		}
	}
	
	/**
	 * removes and returns first item from list
	 * @return element that was removed
	 * @throws IllegalStateException if the list is empty
	 */
	public E removeFirst() {
		if(head==null) {
			throw new IllegalStateException("removeFirst: list is empty");
		}
		E temp = head.data;
		head = head.next;
		size--;
		return temp;
	}
	/**
	 * removes and returns last item from list
	 * @return element that was removed
	 * @throws IllegalStateException if the list is empty
	 */
	public E removeLast() {
		if(head==null) {
			throw new IllegalStateException("removeLast: list is empty");
		}
		if (head.next == null) {//case when list has 1 element (singleton list)
			E temp = head.data;
			head = null;
			size--;
			return temp;
		}
		//case where list has more than one element
		Node<E> current = head;
		while (current.next.next!=null) {
			current = current.next;
		}
		E temp = current.next.data; //sets temp to E value in next node
		current.next = null; //cuts off next node
		size--;
		return temp;
	}
	
	/**
	 * removes and returns item located at index index from list
	 * @param index Index of item to be removed
	 * @return The element that was removed
	 * @throws IllegalArgumentException if the index does not exist
	 */
	public E remove(int index) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException("remove: index out of bounds");
		}
		if (head==null) {
			throw new IllegalStateException("remove: list is empty");
		}
		//list is non-empty and index is in range
		if (index==0) {
			return removeFirst();
		}
		//list is non-empty, index in range and larger than 0
		Node<E> current = head;
		for (int i=0; i<index; i++) {
			current=current.next;
		}
		E temp = current.next.data;
		current.next =current.next.next;
		size--;
		return temp;
	}
	

//Exercise Booklet Methods
	
	/**
	 * returns boolean indicating whether list is singleton list
	 * @return true==singleton false==not-singleton
	 */
	public boolean isSingleton() {
		if(size==1) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * returns boolean indicating whether all elements in list are non-null
	 * @return true==all non null 
	 */
	public boolean allNonNull() {
		Node<E> current = head;
		if(isSingleton()==true && head.data == null) {
			return false;
		}
		while(current!=null) {
			if(current.data==null) {
				return false;
			}else {
				current=current.next;
			}
		}
		return true;
	}
	
	/**
	 * checks whether item is in list
	 * @param item
	 * @return true if item is in list, false if not
	 */
	public boolean mem(E item) {
		boolean found = false;
		Node<E> current = head;
		
		while(!found && current!=null) { //!found == true because initial value is F
			found = found || current.data.equals(item); //updates found, works like a boolean or operation, works because both are booleans
			current = current.next;
		}
		return found;
	}
	
	/**
	 * checks whether item is in list
	 * @param item item that is being checked to be in list
	 * @return true if item is in list, false if not
	 */
	public boolean mem2(E item) {
	//generally not good practice for more than 1 return statement per method/function
		Node<E> current = head;
		
		while(current!=null) { //!found == true because initial value is F
			if (current.data.equals(item)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	/**
	 * checks if list has duplicate elements
	 * @return true if duplicates, false if not duplicates
	 */
	public boolean nonDuplicates() {
		//no items of 1 item
		if(head == null || head.next == null) {
			return false;
		} 
		//general case - multiple of same item, more than 2 items
		else {//have to compare every node to every node ahead of it
			Node<E> current = head;
			Node<E> current2 = current.next;
			while(current.next!=null) {
				while(current2 != null) {
					//compare
					//is a duplicate
					if(current2.data == current.data) {
						return true;
					//is not a duplicate	
					}else {
						current2 = current2.next;
					}
				}
				current = current.next;
				current2 = current.next; //moving it to point after current, curr2 is null after while loop
			}
			return false;
		}
	}
	
	/**
	 * clones a list, walks over list and copies every node
	 */
	public SLL1<E> clone(){
//		SLL<E> result = new SLL<E>();
//		
//	//this method does not use add and is a linear time operation
//	//calling add makes the time complexity O(n^2) because calling a linear in a linear
//		Node<E> current = head;
//		Node<E> copy = new Node<E>(null); //dummy node
//		Node<E> newHead = copy; //stores head of copy so it can be referenced
//		while (current.next!=null) {
//			copy.next = new Node<E>(current.data);
//			current = current.next;
//			copy = copy.next;
//		}
//		result.head = newHead.next;
//		result.size = size;
//		
//		
//		return result;
		SLL1<E> l = new SLL1<E>();
		Node<E> current = head;
		Node<E> temp = new Node<E>(null);
		Node<E> newCopy = temp;
		while(current!=null) {
			//l.append(current);
			temp.next = new Node<E>(current.data);
			current=current.next;
			temp = temp.next;	
		}
		l.head = newCopy.next;
		l.size = size;
		return l;
	}
	
	/**
	 * appends l2 to another list
	 * @param l2 list being appended
	 * @return original list after l2 is appended to it
	 */
	public SLL1<E> append(SLL1<E> l2){
		return null;
	}
	
	/**
	 * reverses list by making new list
	 */
	public void reverse() {
		Node<E> current = head;
		SLL1<E> newList = new SLL1<E>();
		
		while(current!=null) {
			newList.addFirst(current.data);
			current = current.next;
		}
		this.head = newList.head;
	}
	
	/**
	 * returns list of booleans indicating whether element of list is null or not
	 * @return list of booleans
	 */
	public SLL1<Boolean> areNull(){
		Node<E> current = head;
		SLL1<Boolean> result = new SLL1<Boolean>();
		
		while(current!=null) {
			if(current.data==null) {
				result.add(true);
			}else {
				result.add(false);
			}
			current = current.next;
		}
		return result;
	}
	
	/**returns new list that copies original list to itself n times
	 * in form of list appended to itself n times
	 * @param n number of times list is repeated
	 * @return 
	 */
	public SLL1<E> repeatLN(Integer n){
		SLL1<E> result = new SLL1<E>();
		Node<E> current = head;
		for(int i=0; i<n+1; i++) {
			while(current!=null) {
				result.add(current.data);
				current = current.next;
			}
		}
		return result;
	}
	
	/**
	 * repeats each element of the list n times
	 * @param n number of times each element is repeated
	 */
	public void stutterNL(int n) {
		Node<E> current = head;
		E currData;
		while(current != null) {
			currData = current.data;
			for(int i=0; i < n-1; i++) {
				current.next = new Node<E>(currData, current.next);
				current = current.next;
				size++;
			}
			current = current.next;
		}
	}
	
	/**
	 * goes through list and removes duplicates that are adjacent, leaving only 1 of a thing
	 */
	public void removeAdjacentDuplicates() {
		if(head==null || head.next==null) {
			return;
		}else {
			Node<E> current = head;
			while(current.next != null) {
				if(current.data==current.next.data) {
					current.next = current.next.next;
					size--;
				}else {
					current = current.next;
				}
			}
		}
	}
	
	/**
	 * takes in a list and removes all null elements
	 */
	public void filterNonNull() {
		Node<E> current = head;
		if(head==null&&size==1) {
			removeFirst();
		}else {
			while(current.next!=null) {
				int index = 0;
				if(current.next.data==null) {
					remove(index+1);
				}
				index++;
			}
		}
	}
	
	/**combines two lists and returns one list
	 * example: [1,3,5]+[2,4,6]->[1,2,3,4,5,6]
	 * @param l2
	 * @return
	 */
	public SLL1<E> zipL(SLL1<E> l2){
		return null;
	}
	
	/**
	 * removes all instances of item from list
	 * @param item item to remove from list
	 */
	public void removeAll(E item) {
		while(head.next!=null&&head.data.equals(item)) {
			head = head.next;
		}
		if(head==null) {
			return ;
		}
		Node<E> current = head;
		while(current.next!=null) {
			if(current.next.data==item) {
				current.next = current.next.next;;
				size--;
			}
			current = current.next;
		}
	}
	
	//recursion
	
	private Node<E> addR(E item, Node<E> current) {
		if (current == null) {
			return new Node<E>(item);
		}
		current.next = addR(item,current.next);
		return current;
	}

	/**
	 * recursive add, adds to end of list
	 * @param item
	 * @return
	 */
	public E addR(E item) {
		head = addR(item,head);
		size++;
		return item;
	}
	
//	public Node<E> addR(E item, int index, Node<E> current){
//		if (index<0 || index>size) {
//			throw new IllegalArgumentException("Index out of bounds");
//		}
//		if (current==null) {
//			addR(item);
//		} else if (index == 0) {
//			addR(item);
//			current.next = current;
//		} else {
//			current.next = addR(item, index-1, current.next); 
//		}
//		return current;
//	}
//	
//	public E addR(E item, int index) {
//		head = addR(item, index, head);
//		size++;
//		return item;
//	}
	
	private Node<E> removeLastR(Node<E> current) {
		if (current.next==null) {
			return null;
		} else {
			current.next = removeLastR(current.next);
			return current;
		}
		
	}
	
	public void removeLastR() {
		if (head==null) {
			throw new IllegalStateException("removeLastR: list is empty");
		}
		head = removeLastR(head);
		size--;
	}
	
	public boolean memR(E item, Node<E> current) {
		if (current==null) {//list is empty
			return false;
		}
		if (current.data.equals(item)) {
			//element is in list
			return true;
		} else {
			return memR(item, current.next);
		}
	}
	
	public boolean memR(E item) {
		return memR(item, head);
	}
	
	public boolean hasDuplicates2() {
		if(head == null || head.next == null) {
			return false;
		} else {
			Node<E> current = head;
			Node<E> curr2 = current.next;
			while(current.next != null) {
				while(curr2 != null) {
					if(current.data == curr2.data) {
						return true;
					}
					else {
						curr2 = curr2.next;
					}
				}
				current = current.next;
				curr2 = current.next;
			}
		}
		return false;
	}
	
	public boolean hasDupesClone() {
		if(head == null || head.next == null) {
			return false;
		}
		
		SLL1<E> newList = this.clone();
		E compa = newList.head.data;
		
		while(newList.head.next != null) {
			newList.head = newList.head.next;
			if(newList.mem(compa)) {
				return true;
			} else {
				compa = newList.head.data;
			}
		}
		return false;
	}
	
	public void removeAdjacentDuplicates2() {
		if(head == null || head.next == null) {
			return;
		}
		
		Node<E> current = head;
		while(current.next != null) {
			if(current.data == current.next.data) {
				current.next = current.next.next;
				size--;
			} else {
				current = current.next;
			}
		}
	}
	
	public void	reverse2() {
		Node<E> current = head;
		SLL1<E> newList = new SLL1<E>();
		
		while(current != null) {
			newList.addFirst(current.data);
			current = current.next;
		}
		this.head = newList.head;
	}
	
	public void stutter2(int n) {
		Node<E> current = head;
		while(current != null) {
			for(int i = 0; i < n-1; i++) {
				current.next = new Node<E>(current.data, current.next);
				current = current.next;
				size++;
			}
			current = current.next;
		}
	}
	
	private Node<E> cloneR(Node<E> current) {
		if (current==null) {
			return null;
		} else {
			return new Node<E>(current.data,cloneR(current.next));
		}
	}

	public SLL1<E> cloneR() {
		SLL1<E> result = new SLL1<E>();
		result.head = cloneR(head);
		result.size = size;
		return result;
	}


	private Node<E> addR(E item, int index, Node<E> current) {
		if (index==0) {
			return new Node<E>(item,current);
		} else {
			current.next = addR(item,index-1,current.next);
			return current;
		}
	}

	public void addR(E item, int index) {
		if (index<0 || index > size ) {
			throw new IllegalArgumentException("Index out of bounds");
		}
		head = addR(item,index,head);
		size++;
	}

	
	private StringBuilder toString(Node<E> current) {
		StringBuilder result = new StringBuilder();
		
		if(current==null) {
			return result;
		} else {
			if (current.next==null) {
				result.append(current.data.toString());
				result.append(toString(current.next));
				return result;
			}
			result.append(current.data.toString()+',');
			result.append(toString(current.next));
			return result;
		}
	}

	@Override
	public String toString() {//recursive implementation
		return '['+toString(head).toString()+']';
	}
	
	public static void incList(Node<Integer> l) {
		if (l==null) {
			return;
		} else {
			l.data++;
			incList(l.next);
		}
	}
	
	
//	@Override
//	public String toString() {
//		
//		//non-efficient way of making string
////		String result = "";
////		Node<E> current = head;
////		
////		while(current != null) {
////			result = result + current.data.toString();
////			current = current.next;
////		}
////		return result;
//		
//		//more efficient method
//		StringBuilder result = new StringBuilder();
//		Node<E> current = head;
//		
//		result.append("[");
//		while(current != null) {
//			if(current.next != null) {
//				result.append(current.data.toString()+", ");
//			}else {
//				result.append(current.data.toString());
//			}
//			current = current.next;
//		}
//		result.append("]");
//		return result.toString();
//	}
//	

	
	public static void main(String[] args) {
//		SLL<Integer> l = new SLL<Integer>();
//		SLL<Integer> l2 = new SLL<Integer>();
//
//		boolean is10 = l.mem(10);
//		
//		SLL<Integer> lclone = l.clone();
//		
//		l.addFirst(6);
//		l.addFirst(5);
//		l.addFirst(4);
//		l.addFirst(3);
//		l.addFirst(2);	
//		System.out.println(l);
//
//		l2.addFirst(2);
//		l2.addFirst(5);
//		l2.addFirst(5);
//		l2.addFirst(2);
//		l2.addFirst(2);	
//		
//		System.out.println(l2);
//		System.out.println(l.hasDuplicates2());
//		System.out.println(l2.hasDuplicates2());
//		System.out.println(l.hasDupesClone());
//		System.out.println(l2.hasDupesClone());
//
//		l2.removeAdjacentDuplicates();
//		l.reverse();
//		System.out.println(l);
//
//		l.stutter2(3);
//		System.out.println(l);
//		l.removeAdjacentDuplicates2();
//		System.out.println(l);
		
	}
	
}
