package trees.leftisttree;

import trees.HeapInterface;

/**
 * LeftistTree defines the methods and attributes used by a LeftistTree.
 * @author Carlos Vasquez
 *
 */
public abstract class LeftistTree<T> implements HeapInterface<T> 
{
	private Node<T> root;
	
	public LeftistTree(Node<T> root)
	{
		this.root = root;
	}
	
	/**
	 * Inserts node into the tree
	 * @param node	node being inserted into tree.
	 */
	protected void insert(Node<T> node)
	{
		this.root = this.root.merge(node);
	}
	
	/**
	 * Removes the root element and merges the children to obtain a new root.
	 */
	public final T remove()
	{
		T returner = this.root.value();
		this.root = this.root.getLeftChild().merge(this.root.getRightChild());
		return returner;
	}
}
