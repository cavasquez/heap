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
	
	public void insert(T value)
	{
		this.root = this.root.merge(value);
	}
	
	
	public T remove()
	{
		T returner = this.root.value();
		this.root = this.root.getLeftChild().merge(this.root.getRightChild());
		return returner;
	}
}
