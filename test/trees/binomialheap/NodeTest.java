package trees.binomialheap;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Node
 * @author Carlos
 *
 */
public class NodeTest 
{
	@Test
	public void testConstructor() 
	{
		Node<Integer> test = new Node<Integer>(5);
		assertEquals(0, test.getDegree());
		assertEquals(true, test.getValue() == 5);
		assertNull(test.getChild());
		assertNull(test.getSibling());
	}
	
	@Test
	public void testAddSibling()
	{
		Node<Integer> test1 = new Node<Integer>(1);
		Node<Integer> test2 = new Node<Integer>(2);
		Node<Integer> test3 = new Node<Integer>(3);
		
		test1.addSibling(test2);
		
		assertSame(test2, test1.getSibling());
		assertSame(test1, test2.getSibling());
		
		test1.addSibling(test3);
		assertSame(test2, test1.getSibling());
		assertSame(test3, test2.getSibling());
		assertSame(test1, test3.getSibling());
	}
	
	@Test
	public void testAddChild()
	{
		Node<Integer> test1 = new Node<Integer>(1);
		Node<Integer> test2 = new Node<Integer>(2);
		Node<Integer> test3 = new Node<Integer>(3);
		Node<Integer> test4 = new Node<Integer>(4);
		
		try
		{
			test1.addChild(test2);
		} catch(UnequalChildrenException e) { fail("Fail"); }
		
		assertEquals(1, test1.getDegree());
		assertEquals(0, test2.getDegree());
		assertSame(test2, test1.getChild());
		assertNull(test2.getChild());
		assertNull(test1.getSibling());
		assertNull(test2.getSibling());
		
		try
		{
			test3.addChild(test4);
		} catch(UnequalChildrenException e) { fail("Fail"); }
		
		assertEquals(1, test3.getDegree());
		assertEquals(0, test4.getDegree());
		assertSame(test4, test3.getChild());
		assertNull(test4.getChild());
		assertNull(test3.getSibling());
		assertNull(test4.getSibling());
		
		try
		{
			test1.addChild(test3);
		} catch(UnequalChildrenException e) { fail("Fail"); }
		
		assertEquals(2, test1.getDegree());
		assertEquals(0, test2.getDegree());
		assertEquals(1, test3.getDegree());
		assertEquals(0, test4.getDegree());
		assertSame(test2, test1.getChild());
		assertSame(test4, test3.getChild());
		
		assertNull(test2.getChild());
		assertNull(test4.getChild());
		
		assertSame(test3, test2.getSibling());
		assertSame(test3, test2.getSibling());
		assertNull(test1.getSibling());
		assertNull(test4.getSibling());
		
		try 
		{ 
			test1.addChild(new Node<Integer>(9));
			fail("Exception should have ocurred");
		}
		catch(UnequalChildrenException e) {/* do nothing */}
	}
}
