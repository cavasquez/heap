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
		assertEquals(true, test.root.value == 4);
		assertEquals(true, test.root.sibling.value == 7);
		assertEquals(true, test.root.sibling.sibling.value == 13);
		assertEquals(true, test.root.sibling.sibling.sibling.value == 4);

		assertEquals(true, test.remove() == 4);
		assertEquals(true, test.root.value == 7);
		assertEquals(true, test.root.sibling.value == 7);
		assertEquals(true, test.root.child.value == 13);
		assertEquals(true, test.root.child.sibling.value == 13);
		assertNull(test.root.child.child);
		
		test.insert(2);
		assertEquals(true, test.root.value == 2);
		assertEquals(true, test.root.sibling.value == 7);
		assertEquals(true, test.root.sibling.sibling.value == 2);
		assertNull(test.root.child);
		assertEquals(true, test.root.sibling.child.value == 13);
		assertEquals(true, test.root.sibling.child.sibling.value == 13);
		assertNull(test.root.sibling.child.child);
		
		test.insert(11);
		assertEquals(true, test.root.value == 2);
		assertEquals(true, test.root.sibling.value == 11);
		assertEquals(true, test.root.sibling.sibling.value == 7);
		assertEquals(true, test.root.sibling.sibling.sibling.value == 2);
		assertNull(test.root.child);
		assertEquals(true, test.root.sibling.sibling.child.value == 13);
		assertEquals(true, test.root.sibling.sibling.child.sibling.value == 13);
		assertNull(test.root.sibling.sibling.child.child);
		
		test.insert(8);
		assertEquals(true, test.root.value == 2);
		assertEquals(true, test.root.sibling.value == 8);
		assertEquals(true, test.root.sibling.sibling.value == 11);
		assertEquals(true, test.root.sibling.sibling.sibling.value == 7);
		assertEquals(true, test.root.sibling.sibling.sibling.sibling.value == 2);
		assertNull(test.root.child);
		assertEquals(true, test.root.sibling.sibling.sibling.child.value == 13);
		assertEquals(true, test.root.sibling.sibling.sibling.child.sibling.value == 13);
		assertNull(test.root.sibling.sibling.sibling.child.child);
		
		assertEquals(true, test.remove() == 2);
		assertEquals(true, test.root.value == 7);
		assertEquals(true, test.root.sibling.value == 7);
		
		assertEquals(true, test.root.child.value == 13);
		assertEquals(true, test.root.child.sibling.value == 8);
		assertEquals(true, test.root.child.sibling.sibling.value == 13);
		
		assertEquals(true, test.root.child.sibling.child.value == 11);
		assertEquals(true, test.root.child.sibling.child.sibling.value == 11);
		assertNull(test.root.child.sibling.child.child);

		test.insert(1);
		test.insert(10);
		test.insert(5);
		assertEquals(true, test.remove() == 1);		
		assertEquals(true, test.root.value == 5);
		assertEquals(true, test.root.sibling.value == 7);
		assertEquals(true, test.root.sibling.sibling.value == 5);
		
		assertEquals(true, test.root.child.value == 10);
		assertEquals(true, test.root.child.sibling.value == 10);
		assertNull(test.root.child.child);
		
		assertEquals(true, test.root.sibling.child.value == 13);
		assertEquals(true, test.root.sibling.child.sibling.value == 8);
		assertEquals(true, test.root.sibling.child.sibling.sibling.value == 13);
		
		assertEquals(true, test.root.sibling.child.sibling.child.value == 11);
		assertEquals(true, test.root.sibling.child.sibling.child.sibling.value == 11);
		
		System.out.println(test.toString());
		
		assertEquals(true, test.remove().equals(5));
		assertEquals(true, test.remove().equals(7));
		assertEquals(true, test.remove().equals(8));
		assertEquals(true, test.remove().equals(10));
		assertEquals(true, test.remove().equals(11));
		assertEquals(true, test.remove().equals(13));
		assertNull(test.remove());
	}
	
	@Test
	public void stressTest2()
	{
		MinBinomialHeap<Integer> test = new MinBinomialHeap<Integer>();
		try 
		{
			test.insert(1);
			test.root.addChild(new Node<Integer>(7));
			test.root.child.addChild(new Node<Integer>(8));
			test.root.child.addSibling(new Node<Integer>(3));
			
			test.root.addSibling(new Node<Integer>(2));
			test.root.sibling.addChild(new Node<Integer>(4));
			test.root.sibling.child.addChild(new Node<Integer>(6));
			test.root.sibling.child.child.addSibling(new Node<Integer>(7));
			test.root.sibling.child.child.addChild(new Node<Integer>(8));
			test.root.sibling.child.addSibling(new Node<Integer>(5));
			test.root.sibling.child.sibling.addChild(new Node<Integer>(6));
			test.root.sibling.child.sibling.addSibling(new Node<Integer>(9));
			
			test.root.sibling.addSibling(new Node<Integer>(4));
			
			test.root.sibling.sibling.addSibling(new Node<Integer>(5));
			test.root.sibling.sibling.sibling.addChild(new Node<Integer>(9));
			
			test.root.sibling.sibling.sibling.addSibling(new Node<Integer>(6));
			
			test.root.sibling.sibling.sibling.sibling.addSibling(new Node<Integer>(5));
			test.root.sibling.sibling.sibling.sibling.sibling.addChild(new Node<Integer>(9));
			
			assertEquals(true, test.remove().equals(1));
			assertEquals(true, test.remove().equals(2));
			assertEquals(true, test.remove().equals(4));
			assertEquals(true, test.remove().equals(4));
			assertEquals(true, test.remove().equals(5));
			assertEquals(true, test.remove().equals(5));
		} 
		catch (UnequalChildrenException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveEmpty()
	{
		MinBinomialHeap<Integer> test = new MinBinomialHeap<Integer>();
		test.remove();
	}
}
