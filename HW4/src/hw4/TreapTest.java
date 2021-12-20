package hw4;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreapTest {

	@Test
	public void testAddE() {
		Treap<Integer> test1 = new Treap<Integer>();
		test1.add(4 ,19);
		test1.add(6, 20);
		
		Treap<Integer> test2 = new Treap<Integer>();
		test2.add(4, 19);
		test2.add(6, 20);
		
		assertEquals(test1.toString(), test2.toString());
		
		
	}
	
	@Test
	public void testAddRandom() {
		Treap<Integer> test1 = new Treap<Integer>();
		assertTrue(test1.add(5));
		assertTrue(test1.add(10));
		assertTrue(test1.add(20));
		assertTrue(test1.add(30));
		assertTrue(test1.add(40));
		
		assertFalse(test1.add(5));	
		
	}
	
	
	@Test
	public void testAddDupKey() {
		Treap<Integer> test1 = new Treap<Integer>();
		test1.add(4 ,19);
		assertFalse(test1.add(4, 20));
		
		Treap<Integer> test2 = new Treap<Integer>();
		test2.add(4, 19);
		test2.add(6, 20);
		
		assertNotEquals(test1.toString(), test2.toString());
	} 

	
	@Test
	public void testFind() {
		Treap<Integer> test1 = new Treap<Integer>();
		test1.add(1, 1);
		test1.add(4, 4);
		test1.add(7, 20);
		test1.add(3, 5);
		test1.add(10, 3);
		assertTrue(test1.find(3));
		assertTrue(test1.find(7));
		assertFalse(test1.find(20));
		
	}
	
	
	@Test
	public void testDelete() {
		Treap<Integer> t = new Treap<Integer>();
		t.add(1, 1);
		t.add(4, 4);
		t.add(7, 20);
		t.add(3, 5);
		t.add(10, 3);
		t.delete(4);
		
		Treap<Integer> t2 = new Treap<Integer>();
		t2.add(1, 1);
		t2.add(7, 20);
		t2.add(3, 5);
		t2.add(10, 3);
		
		assertEquals(t.toString(), t2.toString());
		
		assertFalse(t.delete(5));
		
	} 

	
	@Test
	public void testSeed() {
		Treap<Integer> t = new Treap<Integer>(100);
		t.add(1);
		t.add(2);
		t.add(3);
		t.add(4);
		t.add(5);
		
		Treap<Integer> t2 = new Treap<Integer>(50);
		t2.add(1);
		t2.add(2);
		t2.add(3);
		t2.add(4);
		t2.add(5);
		
		assertNotEquals(t.toString(), t2.toString());
		
	}
	
	@Test
	public void testPriority() {
		Treap<Integer> t = new Treap<Integer>();
		t.add(1, 1);
		t.add(2, 10);
		t.add(3, 20);
		
		Treap<Integer> t2 = new Treap<Integer>(50);
		t2.add(2, 10);
		t2.add(3, 20);
		t2.add(1, 1);
		
		assertEquals(t.toString(), t2.toString());
		
	}
	

}
