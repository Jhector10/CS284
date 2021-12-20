package lists;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest {
	
	@Test
	void first() {
		IDLList<Integer> l1 = new IDLList<Integer>();
		assertEquals(l1.equals(new IDLList<Integer>()), true);
	}
	
	@Test
	void second() {
		//adding
		IDLList<String> l1 = new IDLList<String>(); //empty list
		IDLList<String> l2 = new IDLList<String>(); //single list
		l2.add("a");
		
		assertEquals(l1.equals(l2), false);
		
		assertEquals(l2.add(1,"b"), true);
		assertEquals(l2.append("c"), true);
	}
	@Test
	void third() {
		//getting/size
		IDLList<String> l1 = new IDLList<String>(); //empty list
		IDLList<String> l2 = new IDLList<String>(); //single list
		IDLList<String> l3 = new IDLList<String>(); //multiple list
		
		l2.add("a");
		l3.append("a");
		l3.append("b");
		l3.append("c");
		
		assertEquals(l1.getHead(), null);
		assertEquals(l1.getLast(), null);
		assertEquals(l1.size(), 0);
		
		assertEquals(l2.getHead(),"a");
		assertEquals(l2.getLast(),"a");
		assertEquals(l2.size(), 1);
		
		assertEquals(l3.getHead(),"a");
		assertEquals(l3.getLast(),"c");
		assertEquals(l3.size(), 3);
		assertEquals(l3.get(1), "b");
	}
	@Test
	void fourth() {
		//removing
		IDLList<String> l1 = new IDLList<String>(); //empty list
		IDLList<String> l2 = new IDLList<String>(); //single list
		IDLList<String> l3 = new IDLList<String>(); //multiple list
		IDLList<String> l4 = new IDLList<String>(); //multiple list, adjacent duplicates
		

		l2.add("a");
		
		l3.append("a");
		l3.append("b");
		l3.append("c");
		
		l4.append("a");
		l4.append("b");
		l4.append("c");
		l4.append("d");
		l4.append("d");
		
		assertEquals(l2.remove(), "a");
		assertEquals(l4.removeLast(), "d");
		assertEquals(l3.removeAt(1), "b");
	}
}
