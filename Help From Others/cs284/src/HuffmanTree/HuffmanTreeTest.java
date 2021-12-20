package HuffmanTree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HuffmanTreeTest {
	
	@Test
	void first() {
		HuffmanTree t = new HuffmanTree("test");
		Boolean[] encoding = {true,false,true};
		assertEquals(t.bitsToString(encoding).equals("101"), true);
	}
}
