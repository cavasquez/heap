package trees.binomialheap;

/**
 * Container is a helper class for BinomialHeap that will allow it to keep track
 * of the winner and current DoublyLinkedList.
 * @author Carlos Vasquez
 *
 */
public class Container<T extends Comparable<T>> 
{
	public Node<T> winning;
	public DoublyLinkedList<Node<T>> current;
	
	public Container(Node<T> winning, DoublyLinkedList<Node<T>> current) 
	{ 
		this.winning = winning;
		this.current = current;
	}
}
