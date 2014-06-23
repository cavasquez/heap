package trees.binomialheap;

/**
 * Node will contain the basic elements and methods that make up a 
 * BinomialHeap. Particularly, this node contains the following elements:
 * Value
 * Degree: number of children
 * Child: pointer to one of the node's children (or null if no child)
 * Sibling: Pointer to sibling node (creates circularly linked list)
 * Value: the data
 * @author Carlos Vasquez
 * 
 * @param <T> Generic Type
 */
public class Node<T extends Comparable<T>> 
{
	private int degree;
	protected T value;
	protected Node<T> child;
	protected Node<T> sibling;
	
	private Node(Integer degree, T value, Node<T> child, Node<T> sibling)
	{
		this.degree = degree;
		this.value = value;
		this.child = child;
		if(sibling == null) this.sibling = this;
	}
	
	public Node() { this(0, null, null, null); }
	
	public Node(T value) { this(0, value, null, null); }
	
	/**
	 * Adds the sibling to this node's list of siblings. 
	 * @param sibling	the node(s) being melded with this node's siblings.
	 */
	public void addSibling(Node<T> sibling)
	{
		Node<T> temp = this.sibling;
		this.sibling = sibling.sibling;
		sibling.sibling = temp;
	}
	
	/***
	 * Adds child to this nodes list of children. This method checks to see if
	 * child has a degree that is equal to the degree of this object. If they
	 * are unequal, then this method will throw an UnequalChildrenException. 
	 * A BinomialTree can only become a child of another BinomialTree of an
	 * equal degree.
	 * 
	 * The child is added "before" the current child. 
	 * @param child	the child being added to this node.
	 * @throws UnequalChildrenException
	 */
	public void addChild(Node<T> child) throws UnequalChildrenException
	{
		if(this.degree == child.degree)
		{
			if(this.child == null) this.child = child;
			else this.child.addSibling(child);
			this.degree++;
		}
		else throw new UnequalChildrenException(this + " and " + child + " have unequal children (" + this.degree + "," + child.degree + ")");
	}
	
	public String print()
	{
		return ("(" + this.value + "," + this.degree + ")");
	}
	
	public int getDegree() { return this.degree; }
	public T getValue() { return this.value; }
	public Node<T> getChild() { return this.child; }
	public Node<T> getSibling() { return this.sibling; }
}
