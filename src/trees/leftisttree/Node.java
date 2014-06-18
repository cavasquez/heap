package trees.leftisttree;

/**
 * Node will contain the basic elements and methods that make up a 
 * LeftistTree. Node will be extended to create a MinNode or a 
 * MaxNode which determines the kind of .
 * @author Carlos Vasquez
 * 
 * @param <T> Generic Type
 */
public abstract class Node<T>
{
	/**
	 * The S value for the Node (the length of a shortest path from this Node
	 * to an external Node)
	 */
	protected Integer s;
	protected T value;
	protected Node<T> parent;
	protected Node<T> leftChild;
	protected Node<T> rightChild;
	
	private Node(Integer s, T value, Node<T> parent, Node<T> leftChild, Node<T> rightChild)
	{
		this.s = s;
		this.value = value;
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public Node()
	{
		this(null, null, null, null, null);
	}
	
	public Node(T value)
	{
		this(null, value, null, null, null);
	}
	
	public Node(T value, Node<T> parent, Node<T> leftChild, Node<T> rightChild)
	{
		this(null, value, parent, leftChild, rightChild);
	}
	
	/**
	 * Compares this with node and returns the "winner"
	 * @param node	the Node being compared
	 * @return		the "winning" node
	 */
	public abstract NodePair<T> compare(Node<T> node);
	
	/**
	 * Merge the value with this node.
	 * @param value	the value being merged
	 * @return	the resulting node
	 */
	public abstract Node<T> merge(T value);
	
	/**
	 * Merges tree with this Node
	 * @param tree	the tree being merged
	 * @return	the product of the merge
	 */
	public final Node<T> merge(Node<T> tree)
	{
		NodePair<T> pair = this.compare(tree);
		pair.winner.rightChild = pair.winner.rightChild.merge(pair.loser);

		// Must re-evaluate s of nodes and swap
		return pair.winner;
	}
	
	public final Integer s(Node<T> node)
	{
		if(node == null) return 0;
		else return node.s();
	}
	
	public final Integer s()
	{
		if(this.s == null)
		{
			
			this.s = Math.min(this.s(this.leftChild), this.s(this.rightChild)) + 1;	
		}
		return this.s;
	}
	
	/**
	 * Returns value
	 * @return	value
	 */
	public final T value()
	{
		return this.value;
	}
	
	/**
	 * Returns parent node
	 * @return	parent node
	 */
	public final Node<T> getParent()
	{
		return this.parent;
	}
	
	/**
	 * Sets parent node
	 * @param parent	parent node
	 */
	public final void setParent(Node<T> parent)
	{
		this.parent = parent;
	}
	
	/**
	 * Returns left child node
	 * @return	left child node
	 */
	public final Node<T> getLeftChild()
	{
		return this.leftChild;
	}
	
	/**
	 * Returns right child node
	 * @return	right child node
	 */
	public final Node<T> getRightChild()
	{
		return this.rightChild;
	}
}
