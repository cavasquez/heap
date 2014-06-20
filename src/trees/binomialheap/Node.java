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
	protected Integer degree;
	protected T value;
	protected Node<T> child;
	protected Node<T> sibling;
}
