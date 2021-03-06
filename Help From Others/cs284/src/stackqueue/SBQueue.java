package stackqueue;

public class SBQueue<E extends Comparable<E>> {


	public static class MyStack<E> {

		public static class Node<F> {
			// Data fields
			private F data;
			private Node<F> next;

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
		private Node<E> top;
		private int size;

		// Constructor
		MyStack() {
			top=null;
			size=0;				
		}

		// Methods

		public E push(E item) {
			top = new Node<>(item,top);
			size++;
			return item;
		}

		public E pop() {
			if (top==null) {
				throw new IllegalStateException("pop: empty stack");
			}
			E temp = top.data;
			top = top.next;
			size--;
			return temp;		
		}

		public E top() {
			if (top==null) {
				throw new IllegalStateException("top: empty stack");
			}
			return top.data;
		}

		public boolean isEmpty() {
			return top==null;
		}



	}
	// Data fields
	private MyStack<E> a;
	private MyStack<E> b;

	public SBQueue() {
		this.a = new MyStack<E>();
		this.b = new MyStack<E>();
	}

	public int size() {
		return this.a.size + this.b.size;
	}

	public boolean isEmpty() {
		return a.isEmpty() && b.isEmpty();
	}

	/**
	 * uses two stacks to implement queue method offer
	 * @param data
	 */
	public void offer(E data) {
	    //TODO
		a.push(data);
	}

	/**
	 * utilizes two stacks to implement queue method poll
	 * @return
	 */
	public E poll() {
	    // TODO
		//reverse, pop, then reverse again
		if(a.top()==null) {
			throw new IllegalStateException("poll: a is empty");
		}
		while (a.top!=null) {
	    	b.push(a.pop());
	    }
	    E item = b.pop();
	    while(b.top()!=null) {
	    	a.push(b.pop());
	    }
	    return item;
	}

	public String toString() {
		return (a.toString().length() > b.toString().length()) ? a.toString() : b.toString();
	}

	public static void main(String[] args) {

		Queue<Integer> nums = new Queue<>();
		for (int i = 1; i < 10; i++) {
			System.out.println("Offering: " + i);
			nums.offer(i);
		}
		System.out.println(nums);
		System.out.println("Offering: 11");
		nums.offer(11);
		System.out.println(nums);
		System.out.println("Polling: " + nums.poll().toString());
		System.out.println(nums);
		System.out.println("Polling: " + nums.poll().toString());
		System.out.println(nums);
	}

// Expected  output
//
// Offering: 1
// Offering: 2
// Offering: 3
// Offering: 4
// Offering: 5
// Offering: 6
// Offering: 7
// Offering: 8
// Offering: 9
// <--1<--2<--3<--4<--5<--6<--7<--8<--9<--
// Offering: 11
// <--1<--2<--3<--4<--5<--6<--7<--8<--9<--11<--
// Polling: 1
// <--2<--3<--4<--5<--6<--7<--8<--9<--11<--
// Polling: 2
// <--3<--4<--5<--6<--7<--8<--9<--11<--

}
