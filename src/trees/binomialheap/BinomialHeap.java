package trees.binomialheap;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import trees.HeapInterface;

/**
 * BinomialHeap will be used to hold the elements and provide the basic methods
 * of a Binomial Heap. BinomialHeap will be extended to make a MinBinomialHeap
 * or a MaxBinomialHeap.
 * @author Carlos Vasquez
 *
 * @param <T> Generic Type
 */
public abstract class BinomialHeap<T extends Comparable<T>> implements HeapInterface<T> 
{
	private Node<T> root;
	
	public BinomialHeap(T value)
	{
		this.root = new Node<T>(value);
	}
	
	public BinomialHeap() { this(null); }
	
	/**
	 * Compare will be implemented by the subclass of BinomialHeap and used to
	 * determine whether or not BinomialHeap is a Min BinomialHeap or Max 
	 * BinomailHeap. This method will return a NodePair object that contains 
	 * node1 and node2 as a winner and loser depending on how the heap should
	 * be structured. The winner will be the node that should be on a higher 
	 * level in the tree than the loser node. If one node is null, the "winner"
	 * should always be the node that is not null.
	 * @param node1	the node to be compared to node2
	 * @param node2	the node to be compared to node1
	 * @return	the NodePair determining the "winner" and "loser" nodes.
	 */
	protected abstract NodePair<T> compare(Node<T> node1, Node<T> node2);
	
	public void insert(T value)
	{
		this.insert(new Node<T>(value));
	}
	
	/**
	 * Checks which of node or root or the "winner" of compare() and makes the 
	 * winner the new root and makes the loser the sibling of the winner.
	 * @param node
	 */
	private void insert(Node<T> node)
	{
		if(this.root == null) this.root = node;
		else
		{
			NodePair<T> temp = this.compare(this.root, node);
			this.root = temp.winner;
			this.root.addSibling(temp.loser);
		}
	}
	
	/**
	 * Returns the value of the root and does a merge pass on its siblings and
	 * finds the "winner" among the siblings to make the new root.
	 */
	public T remove()
	{
		Node<T> temp = this.root;
		
		/* Merge pass and find new root. Then return value */
		try { this.root = this.mergePass(this.root); } 
		catch (UnequalChildrenException e) { e.printStackTrace();	}
		return temp.getValue();
	}
	
	/**
	 * Merges all the siblings and children of node
	 * @param node	the root
	 * @return		the new root
	 */
	protected Node<T> mergePass(Node<T> node) throws UnequalChildrenException
	{
		Node<T> returner = null;
		Queue<Node<T>> list = new LinkedList<Node<T>>();
		Node<T> current = node.sibling;
		Node<T> temp = null;
		
		/* Put the siblings into the list */
		while(node != current)
		{
			temp = current.sibling;
			current.sibling = null;
			list.add(current);
			returner = this.compare(current, returner).winner;
			current = temp;
		}
		
		/* put children into the list */
		int count = node.getDegree();
		current = node.child;
		for(int i = 0; i < count; i++)
		{
			temp = current.sibling;
			current.sibling = null;
			list.add(current);
			returner = this.compare(current, returner).winner;
			current = temp;
		}
		
		/* Merge the Nodes in the list into commonDegrees */
		Vector<Node<T>> commonDegrees = new Vector<Node<T>>();
		NodePair<T> comp = null;
		while(list.peek() != null)
		{
			current = list.poll();
			this.ensureSize(commonDegrees, current.getDegree());
			if(commonDegrees.get(current.getDegree()) == null) commonDegrees.set(current.getDegree(), current);
			else if(commonDegrees.get(current.getDegree()) != current)
			{
				/* remove the common degree from list and merge it with its 
				 * common degree according to the comparator */
				temp = commonDegrees.get(current.getDegree());
				commonDegrees.set(current.getDegree(), null);
				comp = this.compare(current, temp);
				comp.winner.addChild(comp.loser);
				commonDegrees.set(comp.winner.getDegree(), comp.winner);
			}
		}
		
		return returner;
	}
	
	protected void merge(Vector<Node<T>> pass, Node<T> node)
	{
		
	}
	
	protected void ensureSize(Vector<Node<T>> vec, int size)
	{
		if(vec.size() < (size+1))
		{
			vec.ensureCapacity(size);
		    while (vec.size() < (size+1)) 
		    {
		        vec.add(null);
		    }
		}
	}
}
