package trees.leftisttree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MinLeftistTreeTest 
{
	Node<Integer> root;
	MinLeftistTree<Integer> test;
	
	@Before
	public void start()
	{
		root = new Node<Integer>();
		test = new MinLeftistTree<Integer>(root);
	}
	
	@Test
	public void testInsert() 
	{
		test = new MinLeftistTree<Integer>(null);
		test.insert(13);
		test.insert(4);
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
		
		assertEquals(true, test.root.value == 5);
		assertEquals(true, test.root.leftChild.value == 7);
		assertEquals(true, test.root.rightChild.value == 10);
		
		assertEquals(true, test.root.leftChild.leftChild.value == 13);
		assertEquals(true, test.root.leftChild.rightChild.value == 8);
		
		assertNull(test.root.leftChild.leftChild.leftChild);
		assertNull(test.root.leftChild.leftChild.rightChild);
		
		assertEquals(true, test.root.leftChild.rightChild.leftChild.value == 11);
		assertNull(test.root.leftChild.rightChild.rightChild);
		assertNull(test.root.leftChild.leftChild.leftChild);
		assertNull(test.root.leftChild.leftChild.rightChild);
		
		assertNull(test.root.rightChild.leftChild);
		assertNull(test.root.rightChild.rightChild);
		
		System.out.println(test.toString());
	}

	@Test
	public void testCompare()
	{
		Node<Integer> node1 = new Node<Integer>(2);
		Node<Integer> node2 = new Node<Integer>(1);
		NodePair<Integer> pair = test.compare(node1, node2);
		
		assertEquals(node2, pair.winner);
		assertEquals(node1, pair.loser);
		
		node1 = new Node<Integer>(Integer.MIN_VALUE);
		node2 = new Node<Integer>(Integer.MAX_VALUE);
		pair = test.compare(node1, node2);
		
		assertEquals(node1, pair.winner);
		assertEquals(node2, pair.loser);
	}
}
