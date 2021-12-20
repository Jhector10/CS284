package anagrams;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AnagramsTest {
	
	@Test
	void first() {
		Anagrams a = new Anagrams();
		assertEquals(a.anagramTable.isEmpty(),true);
	}	
}
