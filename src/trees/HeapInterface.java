package trees;

/**
 * HeapInterface defines the methods that any Heap must support.
 * @author Carlos Vasquez
 *
 */
public interface HeapInterface<T extends Comparable<T>>
{
	/**
	 * Adds the value to the Heap.
	 * @param value	the value being added to the Heap
	 */
	public void insert(T value);
	
	/**
	 * Removes an element from the Heap.
	 * @return	the element removed from the Heap.
	 */
	public T remove();
}
