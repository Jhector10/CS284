package MidtermExams;

import java.util.LinkedList;
import java.util.Queue;

public class f18<E> {
	
	private static class NodeP<E> {
		
		private E data;
		private int priority; 
		private NodeP<E> next;
		
		public NodeP(E data, int priority, NodeP<E> next) {
			this.data = data;
			this.priority = priority;
			this.next = next;
		}
		
		public NodeP(E data, int priority) {
			this(data, priority, null);
		}	
		
	}
	
	private int size = 0;
	private NodeP<E> head = null;
	
	public int top_priority() {
		/*that returns the priority of the node in the list 
		    that has the highest priority. If the list is
			empty, it should throw an EmptyListException. 
			Note that the list is not sorted in any way.
		 */
		if (size == 0) {
			throw new IllegalArgumentException();
		}
		
		NodeP<E> node = head;
		int track = node.priority;
		
		for (int i = 0; i < size && node != null; i++) {
			if (node.priority < node.next.priority) {
				track = node.next.priority;
				node.next = head;
			} else {
				node.next = head;
			}
			
		}
		
		return track;
	}
	
	private NodeP<E> build_priority_index() {
		
		NodeP<E>[] priority_index = null;
		
		return head;
		
	}
	
	/*
	 * Indicate for each of the following code fragments its complexity using big-O notation.
		You may assume that n > 2. In case you might require it, here is the formula for the sum
		of the numbers from 1 to n: i = n(n+1)/2. You must justify your answer by proving a
		polynomial T(n) that approximates the execution time.
	 */
	
	public static void method1(int n) {
		int counter = 0;
		for(int i = 0; i < n; i++) {
			for(int j = n; j > i+2; j--) {
				System.out.println(counter);
				counter++;
			}
		}
	}
	
	public static void method2(int n) {
		int counter = 0;
		for(int i = 1; i<n; i*=2) {
			for(int j=0; j<n; j++) {
				System.out.println(counter);
				counter++;
			}
		}
	}
	

	public static void main(String[] args) {
		
		Queue<Integer> myQueue = new LinkedList<Integer>();
		myQueue . offer (3);
		myQueue . offer (9);
		myQueue . offer (7);
		int x = myQueue.poll();
		int y = myQueue.remove ();
		myQueue.offer(x+y);
		int z = myQueue.remove ();
		int w = myQueue.poll();
		
		f18.method1(3);
		System.out.println();

	}

}

