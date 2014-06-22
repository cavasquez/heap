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
	
	/**
	 * 
	 * @param current
	 * @param previous
	 */
	public History(Node<T> current, Node<T> previous)
	{
		this.current = current;
		this.previous = previous;
	}
}
