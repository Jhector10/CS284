package trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TreapTest {
	
	@Test
	void first() {//testing methods on empty treap
		Treap<Character>t1 = new Treap<Character>();
		assertEquals(t1.find('a'),false);
		assertEquals(t1.delete('a'),false);
		assertEquals(t1.delete(null),false);
		assertEquals(t1.find(null),false);
	}
	
	@Test
	void second() {
		Treap<Character>t2 = new Treap<Character>();
		t2.add('a');
		assertEquals(t2.delete('a'),true);
		assertEquals(t2.delete(null),false);
		assertEquals(t2.delete('b'),false);
	}
}
