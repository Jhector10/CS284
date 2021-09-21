package search;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class SearchTest2 {
	@Test
	public void testForNonExistentElement() {
		Search s = new Search();
		assertEquals(-1, s.search(2));
		assertEquals(-1, s.search(0));
	}
	
	@Test
	public void testForTargetAsFirstElement() {
		Search s = new Search();
		assertEquals(0, s.search(5));
	}
	

}
