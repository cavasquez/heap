package trees.binomialheap;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Vector;

import org.junit.Test;

public class NodePairTest {

	@Test
	public void testConstructor() {
		Node<Integer> temp1 = new Node<Integer>();
		Node<Integer> temp2 = new Node<Integer>();
		
		NodePair<Integer> test = new NodePair<Integer>(temp1, null, temp2, null);
		assertSame(temp1, test.winner);
		assertSame(temp2, test.loser);
		
		test = new NodePair<Integer>(temp2, null, temp1, null);
		assertSame(temp2, test.winner);
		assertSame(temp1, test.loser);
		
		test = new NodePair<Integer>(temp1, null, null, null);
		assertSame(temp1, test.winner);
		assertNull(test.loser);
		
		test = new NodePair<Integer>(null, null, temp1, null);
		assertNull(test.winner);
		assertSame(temp1, test.loser);
	}

}
