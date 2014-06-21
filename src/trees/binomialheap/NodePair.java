package trees.binomialheap;

import trees.binomialheap.Node;

/**
 * A Helper class for BinomialHeap.
 * @author Carlos Vasquez
 *
 * @param <T> Generic Type
 */
public class NodePair<T extends Comparable<T>> 
{
	public final Node<T> winner;
	public final Node<T> loser;
	
	public NodePair(Node<T> winner, Node<T> loser)
	{
		this.winner = winner;
		this.loser = loser;
	}
}
