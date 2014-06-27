package heap;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test cases for the heap class.
 * @author Carlos Vasquez
 */
public class heapTest 
{
	@Test
	public void testRandom() 
	{
		heap.random(1, 1000);
	}
	
	@Test
	public void testGenerateRand()
	{
		int[] test = heap.generateRand(100);
		assertEquals(100, test.length);
	}
	
	@Test
	public void testGenerateInstructions()
	{
		Instruction[] test = heap.generateInstructions(100);
		assertEquals(100, test.length);
		
		for(int i = 0; i < 100; i++)
		{
			assertEquals(true, test[i] != null);
			if(test[i].op == Instruction.Operation.INSERT) assertEquals(true, test[i].val != null);
		}
	}
	
	@Test
	public void testInput()
	{
		
	}
}
