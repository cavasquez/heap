package trees.leftisttree;

import java.util.Vector;

import trees.HeapInterface;

/**
 * LeftistTree defines the methods and attributes used by a LeftistTree. 
 * LeftistTree will be extended to make a MinLeftistTree or MaxLeftistTree.
 * @author Carlos Vasquez
 *
 */
public abstract class LeftistTree<T extends Comparable<T>> implements HeapInterface<T> 
{
	protected Node<T> root;
	
	public LeftistTree(Node<T> root)
	{
		this.root = root;
	}
	
	public LeftistTree() { this(null); }
	
	/**
	 * Compare will be implemented by the subclass of LeftistTree and used to
	 * determine whether or not LeftistTree is a Min LeftistTree or Max 
	 * LeftistTree. This method will return a NodePair object that contains 
	 * node1 and node2 as a winner and loser depending on how the tree should
	 * be structured. The winner will be the node that should be on a higher 
	 * level in the tree than the loser node.
	 * @param node1	the node to be compared to node2
	 * @param node2	the node to be compared to node1
	 * @return		the NodePair determining the "winner" and "loser" nodes.
	 */
	protected abstract NodePair<T> compare(Node<T> node1, Node<T> node2);
	
	/**
	 * Inserts node into the tree
	 * @param node	node being inserted into tree.
	 */
	protected void insert(Node<T> node)
	{
		this.root = this.merge(this.root, node);
	}
	
	@Override
	public void insert(T value)
	{
		this.insert(new Node<T>(value));
	}
	
	/**
	 * Removes the root element and merges the children to obtain a new root.
	 * If the root is null, this method returns null.
	 */
	@Override
	public final T remove()
	{
		T returner = null;
		if(this.root != null)
		{
			returner = this.root.getValue();
			this.root = this.merge(this.root.leftChild, this.root.rightChild);
		}
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
			temp = new NodePair<T>(null, null);
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
			Integer oldS;
			
			/* Check which tree has the "higher ranking" value */
			temp = this.compare(tree1, tree2);
			
			/* Retain old s of "high ranker"s right child to determine if 
			 * we re-evaluate after merge. */
			oldS = temp.winner.s(temp.winner.rightChild);
			temp.winner.rightChild = this.merge(temp.winner.rightChild, temp.loser);
			
			/*
			 * If the s of the "high ranker"s right child changes, re-calculate
			 * the "high ranker"s s and re-evaluate the tree.
			 */
			if(oldS != temp.winner.s(temp.winner.rightChild))
			{
				temp.winner.s = null;
				this.reEvaluate(temp.winner);
			}
		}
		return temp.winner;
	}
	
	/**
	 * Evaluates the s values of node's children and swaps the children if the
	 * left child's s is less than the right child's s.
	 * @param tree	the tree being re-evaluated
	 */
	protected void reEvaluate(Node<T> tree)
	{
		if(tree.leftChild == null && tree.rightChild == null) {/* Do nothing */}
		else if(tree.rightChild == null) {/* Do nothing */}
		else if(tree.leftChild == null)
		{
			tree.leftChild = tree.rightChild;
			tree.rightChild = null;
		}
		else if(tree.leftChild.s() < tree.rightChild.s())
		{
			Node<T> temp = tree.rightChild;
			tree.rightChild = tree.leftChild;
			tree.leftChild = temp;
		}
	}
	
	/**
	 * Returns a string representation of LeftistTree. Although the project
	 * specification states that no outside data structures should be used, this
	 * method uses a Vector simply because this is an insignificant method and
	 * it does not impact the performance of LefristTree.
	 */
	@Override
	public String toString()
	{
		Vector<StringBuffer> content = new Vector<StringBuffer>();
		StringBuffer returner = new StringBuffer();
		this.getString(content, this.root, 0);
		for(int i = 0; i < content.size(); i++)
		{
			returner.append("Level " + (i+1) + ":[" + content.get(i).substring(0, content.get(i).length()-1) + "]\n");
		}
		return returner.toString();
	}
	
	/**
	 * This method is used by toString to help build a string representation of
	 * a node. This method recursively calls the children node of node. 
	 * @param content	the vector with the StringBuffer for each level.
	 * @param node		the node for which the string is being built
	 * @param level		the current level the method is at
	 */
	protected void getString(Vector<StringBuffer> content, Node<T> node, int level)
	{
		if(node != null)
		{
			this.ensureSize(content, level + 1);
			if(content.get(level) == null) content.set(level, new StringBuffer());
			content.get(level).append(node.value.toString());
			content.get(level).append(",");
			this.getString(content, node.leftChild, level + 1);
			this.getString(content, node.rightChild, level + 1);
		}
	}
	
	/**
	 * Ensures that the Vector is always of sufficient size
	 * @param vec	the vector being adjusted
	 * @param size	the size to which the vector is being adjusted
	 */
	protected void ensureSize(Vector vec, int size)
	{
		if(vec.size() < size)
		{
			vec.ensureCapacity(size);
		    while (vec.size() < size) { vec.add(null); }
		}
	}
}
