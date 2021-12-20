package recitation2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class basicTest
	{
	
	@Test
	void first()
		{
		basic b1 = new basic(5, 2);

		
		assertEquals(b1.add(), 7);
		assertEquals(b1.multiply(), 10);
		assertEquals(b1.divide(), 2);

		}
	
	@Test
	void second()
		{
		basic b1 = new basic(10, -3);
		System.out.println(b1);

		assertEquals(b1.add(), 7);
		assertEquals(b1.multiply(), -30);
		assertEquals(b1.divide(), -3);
		}
	
	@Test
	void third()
		{
		basic b1 = new basic(14, 0);
		
		assertEquals(b1.add(), 14);
		assertEquals(b1.multiply(), 0);
		
		try
			{assertEquals(b1.divide(), 2);
			fail("bad");}
		catch(Exception e)
			{assertTrue(true);}
		
		
	
		}

	}
