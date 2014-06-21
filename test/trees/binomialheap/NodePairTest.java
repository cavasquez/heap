package trees.binomialheap;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodePairTest {

	@Test
	public void testConstructor() {
		Node<Integer> temp1 = new Node<Integer>();
		Node<Integer> temp2 = new Node<Integer>();
		
		NodePair<Integer> test = new NodePair<Integer>(temp1, temp2);
		assertSame(temp1, test.winner);
		assertSame(temp2, test.loser);
		
		test = new NodePair<Integer>(temp2, temp1);
		assertSame(temp2, test.winner);
		assertSame(temp1, test.loser);
		
		test = new NodePair<Integer>(temp1, null);
		assertSame(temp1, test.winner);
		assertNull(test.loser);
		
		test = new NodePair<Integer>(null, temp1);
		assertNull(test.winner);
		assertSame(temp1, test.loser);
	}

}
