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
	private T value;
	private Node<T> child;
	private Node<T> sibling;
	
	private Node(Integer degree, T value, Node<T> child, Node<T> sibling)
	{
		this.degree = degree;
		this.value = value;
		this.child = child;
		this.sibling = sibling;
	}
	
	public Node() { this(0, null, null, null); }
	
	public Node(T value) { this(0, value, null, null); }
	
	/**
	 * Adds the sibling to this node's list of siblings. The sibling is added
	 * "before" the current sibling. This method does not check to see if
	 * sibling is null.
	 * @param sibling
	 */
	public void addSibling(Node<T> sibling)
	{
		Node<T> temp = this.sibling;
		if(this.sibling != null) temp = this.sibling;
		else temp = this;
		sibling.sibling = this;
		temp.sibling = sibling;
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
		else throw new UnequalChildrenException(this + " and " + child + " have unequal children");
	}
	
	public int getDegree() { return this.degree; }
	public T getValue() { return this.value; }
	public Node<T> getChild() { return this.child; }
	public Node<T> getSibling() { return this.sibling; }
}
