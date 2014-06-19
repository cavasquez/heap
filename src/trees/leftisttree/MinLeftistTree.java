package trees.leftisttree;

/**
 * A Minimum LeftistTree. In this data structure, the smaller elements are
 * at the higher levels (closer to the root).
 * @author Carlos Vasquez
 *
 * @param <T> Generic Type
 */
public class MinLeftistTree<T extends Comparable<T>> extends LeftistTree<T> 
{
	public MinLeftistTree(Node<T> root) 
	{
		super(root);
	}

	/**
	 * This method assumes that value is never null.
	 */
	@Override
	protected NodePair<T> compare(Node<T> node1, Node<T> node2) 
	{
		NodePair<T> returner = null;
		if(node1.value.compareTo(node2.value) <= 0) returner = new NodePair<T>(node1, node2);  
		else returner = new NodePair<T>(node2, node1);
		return returner;
	}

}
