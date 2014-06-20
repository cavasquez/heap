package trees.leftisttree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LeftistTreeTest 
{
	/**
	 * Test class that tests abstract class LeftistTree
	 */
	private static class TestLTree<T extends Comparable<T>> extends LeftistTree<T>
	{
		public TestLTree(Node<T> root) {super(root);}

		@Override
		protected NodePair<T> compare(Node<T> node1, Node<T> node2) 
		{
			/* node2 wins */
			return new NodePair<T>(node1, node2);
		}
	}
	
	Node<Integer> root;
	TestLTree<Integer> test;
	
	@Before
	public void start()
	{
		root = new Node<Integer>();
		test = new TestLTree<Integer>(root);
	}
	
	@Test
	public void testInsert_Node()
	{
		
	}
	
	@Test
	public void testInsert_T()
	{
		
	}
	
	@Test
	public void remove()
	{
		/* Test single node */
		assertNull(test.remove());
		assertNull(test.root);
		
		/* Test only left child */
		root = new Node<Integer>();
		test = new TestLTree<Integer>(root);
		Node<Integer> temp = new Node<Integer>();
		test.root.leftChild = temp;
		
		assertNull(test.remove());
		assertSame(temp, test.root);
		
		/* Test only right child */
		root = new Node<Integer>();
		test = new TestLTree<Integer>(root);
		test.root.rightChild = temp;
		
		assertNull(test.remove());
		assertSame(temp, test.root);
		
		/* Test two children */
		root = new Node<Integer>();
		test = new TestLTree<Integer>(root);
		Node<Integer> otherTemp = new Node<Integer>();
		test.root.leftChild = temp;
		test.root.rightChild = otherTemp;
		
		assertNull(test.remove());
		assertSame(temp, test.root);		
		assertSame(otherTemp, test.root.leftChild);
		assertNull(test.root.rightChild);
	}
	
	@Test
	public void testMerge()
	{
		Node<Integer> temp1 = new Node<Integer>(1);
		test.merge(test.root, temp1);
		
		assertSame(root, test.root);
		assertSame(temp1, test.root.leftChild);
		assertNull(test.root.rightChild);
		assertEquals(0, test.root.s().compareTo(1));
		assertEquals(0, test.root.leftChild.s().compareTo(1));
		
		Node<Integer> temp2 = new Node<Integer>(2);
		test.merge(test.root, temp2);
		
		assertSame(root, test.root);
		assertSame(temp1, test.root.leftChild);
		assertSame(temp2, test.root.rightChild);
		assertEquals(0, test.root.s().compareTo(2));
		assertEquals(0, test.root.leftChild.s().compareTo(1));
		assertEquals(0, test.root.rightChild.s().compareTo(1));
		
		Node<Integer> tree2 = new Node<Integer>(3);
		Node<Integer> temp3 = new Node<Integer>(3);
		Node<Integer> temp4 = new Node<Integer>(4);
		Node<Integer> temp5 = new Node<Integer>(5);
		Node<Integer> temp6 = new Node<Integer>(6);
		Node<Integer> temp7 = new Node<Integer>(7);
		Node<Integer> temp8 = new Node<Integer>(8);
		
		tree2.rightChild = temp3;
		tree2.leftChild = temp4;
		temp4.leftChild = temp5;
		temp5.leftChild = temp6;
		temp3.leftChild = temp7;
		temp4.rightChild = temp8;
		
		Node<Integer> stuff = test.merge(root, tree2);
		
		assertSame(root, stuff);
		assertEquals(0, stuff.s().compareTo(2));
		assertSame(temp1, stuff.leftChild);
		assertEquals(0, stuff.leftChild.s().compareTo(1));
		assertSame(temp2, stuff.rightChild);
		assertEquals(0, stuff.rightChild.s().compareTo(1));
		
		assertSame(tree2, temp2.leftChild);
		assertEquals(0, temp2.leftChild.s().compareTo(2));
		assertNull(temp2.rightChild);
		
		assertSame(temp4, tree2.leftChild);
		assertEquals(0, tree2.leftChild.s().compareTo(2));
		assertSame(temp3, tree2.rightChild);
		assertEquals(0, tree2.rightChild.s().compareTo(1));
		
		assertSame(temp7, temp3.leftChild);
		assertEquals(0, temp3.leftChild.s().compareTo(1));
		assertNull(temp3.rightChild);
		
		assertNull(temp7.leftChild);
		assertNull(temp7.rightChild);
		
		assertSame(temp5, temp4.leftChild);
		assertEquals(0, temp4.leftChild.s().compareTo(1));
		assertSame(temp8, temp4.rightChild);
		assertEquals(0, temp4.rightChild.s().compareTo(1));
		
		assertSame(temp6, temp5.leftChild);
		assertEquals(0, temp5.leftChild.s().compareTo(1));
		assertNull(temp5.rightChild);
		
		assertNull(temp6.leftChild);
		assertNull(temp6.rightChild);
		
		assertNull(temp8.leftChild);
		assertNull(temp8.rightChild);
	}
	
	@Test
	public void testReEvaluate()
	{
		test.reEvaluate(test.root);
		assertNull(test.root.leftChild);
		assertNull(test.root.rightChild);
		
		Node<Integer> temp = new Node<Integer>();
		test.root.leftChild = temp;
		test.reEvaluate(test.root);
		
		assertSame(temp, test.root.leftChild);
		assertNull(test.root.rightChild);
		
		test.root.leftChild = null;
		test.root.rightChild = temp;
		test.reEvaluate(test.root);
		
		assertSame(temp, test.root.leftChild);
		assertNull(test.root.rightChild);
		
		Node<Integer> temp1 = new Node<Integer>();
		Node<Integer> temp2 = new Node<Integer>();
		temp1.s = 5;
		temp2.s = 1;
		
		test.root.leftChild = temp1;
		test.root.rightChild = temp2;
		test.reEvaluate(test.root);
		
		assertSame(temp1, test.root.leftChild);
		assertSame(temp2, test.root.rightChild);
		
		test.root.leftChild = temp2;
		test.root.rightChild = temp1;
		test.reEvaluate(test.root);
		
		assertSame(temp1, test.root.leftChild);
		assertSame(temp2, test.root.rightChild);
	}
}
