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
	 * Compare will be implemented by the subclass of LeftistTree and used to
	 * determine whether or not LeftistTree is a Min LeftistTree or Max 
	 * LeftistTree. This method will return a NodePair object that contains 
	 * node1 and node2 as a winner and loser depending on how the tree should
	 * be structured. The winner will be the node that should be on a higher 
	 * level in the tree than the loser node.
	 * @param node1	the node to be compared to node2
	 * @param node2	the node to be compared to node1
	 * @return	the NodePair determining the "winner" and "loser" nodes.
	 */
	protected abstract NodePair<T> compare(Node<T> node1, Node<T> node2);
	
	public void insert(T value)
	{
		this.root = this.merge(this.root, new Node<T>(value));
	}
	
	
	public T remove()
	{
		T returner = this.root.value();
		this.root = this.merge(this.root.leftChild, this.root.rightChild);
		return returner;
	}
	
	/**
	 * Merges tree1 and tree2 and returns the root of the merged tree
	 * @param tree1	the root node of the first tree
	 * @param tree2	the root node of the second tree
	 * @return		the node of the merged trees
	 */
	public Node<T> merge(Node<T> tree1, Node<T> tree2)
	{
		NodePair<T> temp = null;
		/* Check cases */
		if(tree1 == null && tree2 == null)
		{
			temp = null;
		}
		else if(tree1 == null)
		{
			temp = new NodePair<T>(tree2, null);
		}
		else if(tree2 == null)
		{
			temp = new NodePair<T>(tree1, null);
		}
		else
		{
			temp = this.compare(tree1, tree2);
			temp.winner.rightChild = this.merge(temp.winner.rightChild, temp.loser);
		}
		return temp.winner;
	}
}
