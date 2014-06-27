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
	public final DoublyLinkedList<Node<T>> winnerHolder;
	public final Node<T> loser;
	public final DoublyLinkedList<Node<T>> loserHolder;
	
	public NodePair(Node<T> winner, 
			DoublyLinkedList<Node<T>> winnerHolder, 
			Node<T> loser, 
			DoublyLinkedList<Node<T>> loserHolder)
	{
		this.winner = winner;
		this.winnerHolder = winnerHolder;
		this.loser = loser;
		this.loserHolder = loserHolder;
	}
}
