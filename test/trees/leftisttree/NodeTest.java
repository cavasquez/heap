package trees.leftisttree;

import static org.junit.Assert.*;
import org.junit.Test;

public class NodeTest {

	@Test
	public void testConstructor() 
	{
		/* Test Node(Integer, T, Node<T>, Node<T>) */
		/* Test Node() */
		Node<Integer> test = new Node<Integer>();
		assertNull(test.s);
		assertNull(test.value);
		assertNull(test.rightChild);
		assertNull(test.leftChild);
		
		/* Test Node(T) */
		test = new Node<Integer>(5);
		assertNull(test.s);
		assertEquals(0, test.value.compareTo(5));
		assertNull(test.rightChild);
		assertNull(test.leftChild);
		
		/* Test Node(Integer, T, Node<T>, Node<T>) */
		Node<Integer> temp1 = new Node<Integer>();
		Node<Integer> temp2 = new Node<Integer>();
		
		test = new Node<Integer>(Integer.MAX_VALUE, temp1, temp2);
		assertEquals(null, test.s);
		assertEquals(0, test.value.compareTo(Integer.MAX_VALUE));
		assertSame(temp1, test.leftChild);
		assertSame(temp2, test.rightChild);
		assertNotSame(temp1, test.rightChild);
		assertNotSame(temp2, test.leftChild);
	}
	
	@Test
	public void testS_Node()
	{
		Node<Integer> test = new Node<Integer>();
		assertEquals(0, test.s(null).compareTo(0));
		assertEquals(0, test.s(test).compareTo(1));
	}
	
	@Test
	public void testS()
	{
		Node<Integer> test = new Node<Integer>();
		assertEquals(0, test.s().compareTo(1));
		
		test = new Node<Integer>();
		test.leftChild = new Node<Integer>();
		test.leftChild.leftChild = new Node<Integer>();
		test.rightChild = new Node<Integer>();
		
		assertEquals(0, test.s().compareTo(2));
		assertEquals(0, test.rightChild.s().compareTo(1));
		assertEquals(0, test.leftChild.s().compareTo(1));
		assertEquals(0, test.leftChild.leftChild.s().compareTo(1));
		
		test = new Node<Integer>();
		test.leftChild = new Node<Integer>();
		
		assertEquals(0, test.s().compareTo(1));
		assertNull(test.rightChild);
		assertEquals(0, test.leftChild.s().compareTo(1));
		
		test = new Node<Integer>();
		test.leftChild = new Node<Integer>();
		test.leftChild.leftChild = new Node<Integer>();
		test.leftChild.rightChild = new Node<Integer>();
		test.rightChild = new Node<Integer>();
		
		assertEquals(0, test.s().compareTo(2));
		assertEquals(0, test.rightChild.s().compareTo(1));
		assertEquals(0, test.leftChild.s().compareTo(2));
		assertEquals(0, test.leftChild.leftChild.s().compareTo(1));
		assertEquals(0, test.leftChild.rightChild.s().compareTo(1));
	}
	
	@Test
	public void testValue()
	{
		Node<Integer> test = new Node<Integer>();
		test.value = Integer.MIN_VALUE;
		assertEquals(0, test.getValue().compareTo(Integer.MIN_VALUE));
	}
	
	@Test
	public void testGetLeftChild()
	{
		Node<Integer> test = new Node<Integer>();
		Node<Integer> temp = new Node<Integer>();
		
		assertNull(test.getLeftChild());
		test.leftChild = temp;
		assertSame(temp, test.getLeftChild());
	}
	
	@Test
	public void testGetRightChild()
	{
		Node<Integer> test = new Node<Integer>();
		Node<Integer> temp = new Node<Integer>();
		
		assertNull(test.getRightChild());
		test.rightChild = temp;
		assertSame(temp, test.getRightChild());
	}
}
