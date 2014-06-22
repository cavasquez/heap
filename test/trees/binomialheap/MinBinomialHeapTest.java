package trees.binomialheap;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinBinomialHeapTest 
{

	@Test
	public void stressTest() 
	{
		MinBinomialHeap<Integer> test = new MinBinomialHeap<Integer>();
		test.insert(13);
		assertEquals(true, test.root.value == 13);
		assertEquals(true, test.root.sibling.value == 13);
		
		test.insert(4);
		assertEquals(true, test.root.value == 4);
		assertEquals(true, test.root.sibling.value == 13);
		assertEquals(true, test.root.sibling.sibling.value == 4);
		
		test.insert(7);
		assertEquals(true, test.remove() == 4);
		test.insert(2);
		test.insert(11);
		test.insert(8);
		assertEquals(true, test.remove() == 2);
		test.insert(1);
		test.insert(10);
		test.insert(5);
		assertEquals(true, test.remove() == 1);
		
		
	}

}
