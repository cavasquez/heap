package trees.leftisttree;

/**
 * MinLeftistTree extends LeftistTree and defines the compare to set the 
 * winner node to be the node with a value less than the opposing node. 
 * @author Carlos Vasquez
 *
 * @param <T> Generic Type
 */
public class MinLeftistTree<T extends Comparable<T>> extends LeftistTree<T> 
{
	public MinLeftistTree(Node<T> root) { super(root); }
	
	public MinLeftistTree() { super(); }

	/**
	 * This method assumes that value is never null and that neither node1
	 * nor node2 is ever null.
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
