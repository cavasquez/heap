package trees.binomialheap;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinBinomialHeapTest 
{

	@Test
	public void stressTest() 
	{
		MinBinomialHeap<Integer> test = new MinBinomialHeap<Integer>();
		System.out.println("Insert 13");
		test.insert(13);		
		assertEquals(true, test.root.value == 13);
		assertEquals(true, test.root.sibling.value == 13);
		
		System.out.println("Insert 4");
		test.insert(4);		
		assertEquals(true, test.root.value == 4);
		assertEquals(true, test.root.sibling.value == 13);
		assertEquals(true, test.root.sibling.sibling.value == 4);
		
		System.out.println("Insert 7");
		test.insert(7);
		System.out.println("remove 4");
		assertEquals(true, test.remove() == 4);
		System.out.println("Insert 2");
		test.insert(2);
		System.out.println("Insert 11");
		test.insert(11);
		System.out.println("Insert 8");
		test.insert(8);
		System.out.println("remove 2");
		assertEquals(true, test.remove() == 2);
		System.out.println("insert 1");
		test.insert(1);
		System.out.println("remove 10");
		test.insert(10);
		System.out.println("remove 5");
		test.insert(5);
		System.out.println("remove 1");
		assertEquals(true, test.remove() == 1);
		
		
	}

}
