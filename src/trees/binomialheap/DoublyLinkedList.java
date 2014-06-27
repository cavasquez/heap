package trees.binomialheap;

/**
 * DoublyLinkedList is used by BinomialHeap during MergePass in order to 
 * maintain a list of the merged nodes without having to worry about keeping
 * all the siblings in check. 
 * @author Carlos Vasquez
 *
 */
public class DoublyLinkedList<T> 
{
	public T val;
	public DoublyLinkedList<T> left;
	public DoublyLinkedList<T> right;
	
	public DoublyLinkedList(T val, DoublyLinkedList<T> left, DoublyLinkedList<T> right)
	{
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
	public DoublyLinkedList(T val) {  this(val, null, null); }
}
