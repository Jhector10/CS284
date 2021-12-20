package stackqueue;

public class MyStack<E> {//stacks are First in Last out (FILO)
	//Data fields
	private Node<E> top;
	private int size;
	
	private class Node<F>{
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
	
	//constructor
	MyStack(){
		top = null;
		size = 0;
	}
	
	/**
	 * add onto stack, creating a new top element
	 * @param item
	 * @return
	 */
	public E push(E item) {
		top = new Node<>(item,top);
		size++;
		return item;
	}
	
	/**
	 * remove and return top element of stack
	 * @return
	 */
	public E pop() {
		if (top==null) {
			throw new IllegalStateException("pop: empty stack");
		}
		E temp = top.data;
		top = top.next;
		size--;
		return temp;
	}
	
	/**
	 * view top element of stack without removing it
	 * @return
	 */
	public E top() {//this is also a peek
		if (top==null) {
			throw new IllegalStateException("top: empty stack");
		}
		return top.data;
	}
	
	public boolean isEmpty() {
		return top==null;
	}
	
	//1,1,1,1,1,1,1,2,3,4,3,3 --> 1,2,3,4,3
		public boolean removeAdjacentDuplicates()
			{
			MyStack<E> holder = new MyStack<E>();
			if(top != null)
				{holder.push(this.pop());}
			
			while(top != null)
				{
				if(this.top().equals(holder.top()))
					{
					this.pop();
					}
				else
					{
					holder.push(this.pop());
					}
				}
			while(holder.top != null)
				{this.push(holder.pop());}
			
			return true;
			}
		
//		public String toString() {
//			  StringBuilder s = new StringBuilder();
//			  Node<E> current = top;
//			  s.append(" --> ");
//			  while(current!=null) {
//				  s.append(current.data.toString()+" --> ");
//				  current=current.next;
//				  
//			  }
//			  return s.toString();
//		  }
		
		public static void main(String[] args) {
			PalindromeChecker p1 = new PalindromeChecker("kaya    k");
			PalindromeChecker p2 = new PalindromeChecker("kayaks");
			
			System.out.println(p1.isPalindrome());
			System.out.println(p2.isPalindrome());
			
//			MyStack<Integer> s1 = new MyStack<Integer>();
//			s1.push(1);
//			s1.push(1);
//			s1.push(2);
//			s1.push(2);
//			s1.push(2);
//			s1.push(1);
//			s1.push(3);
//			
//			System.out.println(s1.toString());
//			s1.removeAdjacentDuplicates();
//			System.out.println(s1.toString());

		}
}
