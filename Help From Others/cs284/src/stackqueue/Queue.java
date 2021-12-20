package stackqueue;

public class Queue<E>{  //Queues are First in First out (FIFO)
	
	public static class Node<F> {  
		// data fields  
		private F data;  
		private Node<F> next;  
		
		// Constructors  
		public Node(F data, Node<F> next) {
			super();
			this.data = data;
			this.next = next;
		}  
		
		public Node(F data) {
			super();
			this.data = data;
			this.next = null;
		}  
	}  
	
	// Data fields  
	private Node<E> front;  
	private Node<E> rear;  
	private int size;  
	
	// Constructors  
	Queue() {
		front=null;  
		rear=null;  
		size=0;  
	}  
	
	// Methods  
	/**
	 * anything that arrives in queue is placed at back
	 * @param item
	 */
	public void offer(E item) {
		if (front==null) { 
			// Queue is empty   
			front = new Node<E>(item);  
			rear = front;
		} else { 
			// Queue is non-empty  
			rear.next = new Node<E>(item);  
			rear = rear.next;
		}  
		size++;  
	}
	/**
	 * view front element of queue without removing it
	 * @return
	 */
	public E peek() {
		if (front==null) {
			throw new IllegalStateException("peek: empty queue");
		}  
		return front.data;
	}
	/**
	 * removes and returns front element of queue
	 * @return
	 */
	public E poll() {
		if (front==null) {
			throw new IllegalStateException("peek: empty queue");
		}  
		E temp = front.data;  
		if (front==rear) {
			front = null;  rear = null;
		} else {
			front = front.next;
		}
		size--;
		return temp;
	}
	
	public boolean isEmpty() {
		return (front==null);
	}
	
	/**
	 * removes elements from queue that are adjacent and are the same
	 * @return
	 */
	public boolean removeAdjacentDuplicates() {
		Queue<E> holder = new Queue<E>();
		if(front != null) {
			holder.offer(this.poll());
		}
		while(this.front != null) {
			if(this.peek().equals(holder.rear.data)) {
				this.poll();
			} else {
				holder.offer(this.poll());
			}
		}
		
		this.front = holder.front;
		this.rear = holder.rear;
		this.size = holder.size;
		
		return true;
	}
	
	 public String toString() {
		  StringBuilder s = new StringBuilder();
		  Node<E> current = front;
		  s.append("<--");
		  while(current!=null) {
			  s.append(current.data.toString()+"<--");
			  current=current.next;
			  
		  }
		  return s.toString();
	  }
	
	 //recursion practice
	 public static double fact(double n) {
		 if (n<=0) {
			 return 1;
		 }else {
			 return n*fact(n-1);
		 }
	 }
	 
	 public static double sum(int n) {
		 if (n==0) {
			 return 0;
		 }else {
			 return n+sum(n-1);
		 }
	 }
	 
	 public static double fib(int n) {//exponential time function
		 if(n<2) {
			 return 1;
		 }else {
			 return fib(n-1)+fib(n-2);
		 }
	 }
	 
	 //O(n) time complexity version
	 //is tail recursive
	 //has one recursive call and works like a while loop
	 //has one recursive call, which is why its so fast
	 public static double ffib(double old, double current, double n) {//fasterfib = ffib
		 if (current==1) {
			 return current;
		 } else {
			 return ffib(current, old+current, n-1);
		 }
	 }
	 
	 public static double ffib(double n) {
		 return ffib(1,1,n);
	 }
	 
	 public static double ffib2(double n) {
		 double current = 1;
		 double old = 1;
		 double temp;
		 
		 for (int i=0; i<n; i++) {
			 temp = old;
			 old = current;
			 current = temp + current;
		 }
		 
		 return current;
	 }
	 //recursion practice done
	 
	public static void main(String[] args) {
		Queue<String> q = new Queue<>();
		Queue<Integer> p = new Queue<Integer>();
		
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.offer("e");
		
		System.out.println(q);
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q);
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q);
		
		p.offer(1);
		p.offer(1);
		p.offer(2);
		p.offer(2);
		p.offer(2);
		p.offer(1);
		p.offer(3);
		  
		System.out.println(p.toString());
		p.removeAdjacentDuplicates();
		System.out.println(p.toString());
	}

}