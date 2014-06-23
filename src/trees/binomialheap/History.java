package trees.binomialheap;

/**
 * A Helper class for BinomialHeap
 * @author Carlos Vasquez
 *
 */
public class History<T extends Comparable<T>>
{
	public Node<T> current;
	public Node<T> previous;
	public Node<T> winning;
	
	/**
	 * 
	 * @param current
	 * @param previous
	 * @param winning
	 */
	public History(Node<T> current, Node<T> previous, Node<T> winning)
	{
		this.current = current;
		this.previous = previous;
		this.winning = winning;
	}
}
