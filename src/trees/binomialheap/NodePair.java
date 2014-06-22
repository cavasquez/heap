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
	public final Node<T> previousWinner;
	public final Node<T> loser;
	public final Node<T> previousLoser;
	
	public NodePair(Node<T> winner, Node<T> previousWinner, Node<T> loser, Node<T> previousLoser)
	{
		this.winner = winner;
		this.previousWinner = previousWinner;
		this.loser = loser;
		this.previousLoser = previousLoser;
	}
}
