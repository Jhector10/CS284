package IDLList;

import static org.junit.Assert.*;

import org.junit.Test;

import IDLList.IDLList;

public class IDLListTest {

	@Test
	public void testConstructor() {
		// Test to create an empty constructor and that size = 0
		IDLList<Integer> arr = new IDLList<>();
		assertEquals(0, arr.size());
	}

	@Test
	public void testAdd() {
		IDLList<Integer> arr = new IDLList<>();
		
		arr.add(5);
		arr.add(6);
		arr.add(7);
		arr.add(8);
		arr.add(9);
	
		assertEquals(5, arr.size()); // test that size = 5
		
		assertTrue(arr.add(6)); // test that adding a elem is true
		
		arr.add(10);
		assertTrue(10 == arr.getHead()); // test that 10 is the new head
		assertTrue(10 == arr.get(0));
		
		assertEquals(7, arr.size()); // test that size increased from adding two elems
		
		// Should be an exception for adding a null elem
		try { 
			arr.add(null);
			fail();
		} catch (IllegalArgumentException ex) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testAddIndex() {
		IDLList<Integer> arr = new IDLList<>();
		
		arr.add(15);
		arr.add(14);
		arr.add(13);
		arr.add(12);
		arr.add(11);
		arr.add(10);
		
		assertEquals(6, arr.size());
		
		assertTrue(arr.add(2, 20));
		assertTrue(20 == arr.get(2)); // test to see that 20 is new index 2
		
		assertTrue(12 == arr.get(3)); // test to see that 12 moved to index 3
		
		assertTrue(10 == arr.get(0)); // test to see that 10 is still head
		
		assertTrue(arr.add(0, 30));
		assertTrue(30 == arr.getHead()); // test to see that 30 is new head
		
		// Should be exceptions for adding elem to out of range indexes
		try {
			arr.add(-1, 5);
			fail();
		} catch (IllegalArgumentException ex) {
			assertTrue(true);
		}
		
		try {
			arr.add(10, 5);
			fail();
		} catch (IllegalArgumentException ex) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testAppend() {
		IDLList<Integer> arr = new IDLList<>();
		
		arr.add(15);
		arr.add(14);
		arr.add(13);
		arr.add(12);
		arr.add(11);
		arr.add(10);
		
		assertEquals(6, arr.size());
		
		assertTrue(arr.append(20));
		
		assertEquals(7, arr.size());
		
		// test to see that 20 is at the end of the list
		assertTrue(20 == arr.get(arr.size() - 1));
		
		assertTrue(arr.append(25));
		
		assertTrue(25 == arr.get(arr.size() - 1));
		
		// test to see that 15 stays at index 5
		assertTrue(15 == arr.get(5));
	}

	@Test
	public void testGet() {
		IDLList<Integer> arr = new IDLList<>();
		
		// Should be an exception for getting an elem in empty list
		try {
			arr.get(5);
			arr.get(-5);
			fail();
		} catch (IllegalArgumentException ex) {
			assertTrue(true);
		}
		
		arr.add(15);
		arr.add(14);
		arr.add(13);
		arr.add(12);
		arr.add(11);
		arr.add(10);
		
		// Should be an exception for getting an elem out of range
		try {
			arr.get(10);
			fail();
		} catch (IllegalArgumentException ex) {
			assertTrue(true);
		}
		
		assertEquals(6, arr.size());
		
		assertTrue(15 == arr.get(5)); // test to get 15 from index 5
		assertTrue(13 == arr.get(3));
	}

	@Test
	public void testGetHead() {
		IDLList<Integer> arr = new IDLList<>();
		
		// Should be an exception for getting head of an empty list
		try {
			arr.getHead();
			fail();
		} catch (IllegalArgumentException ex) {
			assertTrue(true);
		}
		
		
		arr.add(15);
		arr.add(14);
		arr.add(13);
		arr.add(12);
		arr.add(11);
		arr.add(10);
		
		assertEquals(6, arr.size());
		
		assertTrue(10 == arr.getHead());
		
		arr.add(60);
		assertTrue(60 == arr.getHead()); // test to see that 60 is the new head
	}

	@Test
	public void testGetLast() {
		IDLList<Integer> arr = new IDLList<>();
		
		// Should be an exception for getting tail in an empty list
		try {
			arr.getLast();
			fail();
		} catch (IllegalArgumentException ex) {
			assertTrue(true);
		}
		
		arr.add(15);
		arr.add(14);
		arr.add(13);
		arr.add(12);
		arr.add(11);
		arr.add(10);
		
		assertEquals(6, arr.size());
		
		assertTrue(15 == arr.getLast());
		
		arr.append(60);
		assertTrue(60 == arr.getLast()); // test to see that 60 is new tail
	}

	@Test
	public void testSize() {
		IDLList<Integer> arr = new IDLList<>();
		
		assertEquals(0, arr.size()); // testing sizes as the list gets bigger
		arr.add(15);
		assertEquals(1, arr.size());
		arr.add(14);
		assertEquals(2, arr.size());
		arr.add(13);
		assertEquals(3, arr.size());
		arr.add(12);
		arr.add(11);
		arr.add(10);
		
		assertEquals(6, arr.size());
	}
	
	@Test
	public void testRemove() {
		IDLList<Integer> arr = new IDLList<>();
		
		// Should be an exception for removing from empty list
		try {
			arr.remove();
			fail();
		} catch (IllegalStateException ex) {
			assertTrue(true);
		}
		
		arr.add(11);
		arr.add(10);
		
		assertEquals(2, arr.size());
		
		assertTrue(10 == arr.remove()); // test to show that remove returns head (10)
		
		assertTrue(11 == arr.getHead()); // test to show that 11 is the new head
		
		assertEquals(1, arr.size());
		
		assertTrue(11 == arr.remove());
		
		// Should be exception after removing all of the elem from list
		try {
			arr.remove();
			fail();
		} catch (IllegalStateException ex) {
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testRemoveLast() {
		IDLList<Integer> arr = new IDLList<>();
		
		// Should be an exception for removing from empty list
		try {
			arr.removeLast();
			fail();
		} catch (IllegalStateException ex) {
			assertTrue(true);
		}
		
		arr.add(11);
		arr.add(10);
		
		assertEquals(2, arr.size());
		
		assertTrue(11 == arr.removeLast()); // test to show that it returns tail (11)
		
		assertTrue(10 == arr.getHead()); // test to show head now equals tail
		assertTrue(10 == arr.getLast());
		
		assertEquals(1, arr.size());
		
		assertTrue(10 == arr.removeLast());
		
		try {
			arr.removeLast();
			fail();
		} catch (IllegalStateException ex) {
			assertTrue(true);
		}
	}

	@Test
	public void testRemoveAt() {
		IDLList<Integer> arr = new IDLList<>();
		
		// Should return exception, outside range
		try {
			arr.removeAt(100);
			fail();
		} catch (IllegalStateException ex) {
			assertTrue(true);
		}
		
		arr.add(15); // 5
		arr.add(14); // 4
		arr.add(13); // 3
		arr.add(12); // 2
		arr.add(11); // 1
		arr.add(10); // 0
		
		assertEquals(6, arr.size());
		
		// test to show that 12 is removed from index 2
		assertTrue(12 == arr.removeAt(2)); 
		
		assertEquals(5, arr.size());
		
		// test to show that 13 moved once 12 is removed
		assertTrue(13 == arr.get(2));
		assertTrue(11 == arr.get(1));
	}

	@Test
	public void testRemoveE() {
		IDLList<Integer> arr = new IDLList<>();
		
		arr.add(12); // 5
		arr.add(10); // 4
		arr.add(13); // 3
		arr.add(12); // 2
		arr.add(11); // 1
		arr.add(10); // 0
		
		assertEquals(6, arr.size());
		
		// test to show that removing an elem not in list returns false
		assertFalse(arr.remove(100)); 
		
		assertEquals(6, arr.size());
		
		assertTrue(10 == arr.getHead());
		
		assertTrue(arr.remove(12)); // test to show first 12 is removed (index 2)
		
		assertTrue(13 == arr.get(2));
		assertTrue(12 == arr.getLast()); // test to show that last 12 was not removed
		
		assertTrue(arr.remove(10));
		
		// test to show that 11 is the new head after removing the old head (10)
		assertTrue(11 == arr.getHead()); 
		
		assertEquals(4, arr.size());
	}

	@Test
	public void testToString() {
		// testing the toString method
		IDLList<Integer> arr = new IDLList<>();
		
		arr.add(12); // 5
		arr.add(10); // 4
		arr.add(13); // 3
		arr.add(12); // 2
		arr.add(11); // 1
		arr.add(10); // 0
		
		assertEquals("[10, 11, 12, 13, 10, 12]", arr.toString());
	}
}
