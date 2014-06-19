package trees.leftisttree;

/**
 * Node will contain the basic elements and methods that make up a 
 * LeftistTree. Node will be extended to create a MinNode or a 
 * MaxNode which determines the kind of .
 * @author Carlos Vasquez
 * 
 * @param <T> Generic Type
 */
public class Node<T extends Comparable<T>>
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
