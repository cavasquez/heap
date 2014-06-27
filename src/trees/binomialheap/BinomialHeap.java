package trees.binomialheap;

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
	protected Node<T> root;
	
	public BinomialHeap(T value) { this.root = new Node<T>(value); }
	public BinomialHeap() { this.root = null; }
	
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
	
	/**
	 * Similarly to compare(Node<T>, Node<T>), this method should return a 
	 * NodePair<T> where a "winner" and "loser" node are chosen between node1
	 * and node 2. Furthermore, the DoublyLinkedList "holder" associated with
	 * each node should be made a loserHolder or winnerHolder depending on
	 * where the associated Node<T> is placed.
	 * @param node1
	 * @param node1Holder
	 * @param node2
	 * @param node2Holder
	 * @return
	 */
	protected abstract NodePair<T> compare(Node<T> node1, 
			DoublyLinkedList<Node<T>> node1Holder, 
			Node<T> node2, 
			DoublyLinkedList<Node<T>> node2Holder);
	
	public void insert(T value)
	{
		this.insert(new Node<T>(value));
	}
	
	/**
	 * Checks which of node or root or the "winner" of compare() and makes the 
	 * winner the new root and makes the loser the sibling of the winner.
	 * @param node	the node being inserted into the BinomialHeap.
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
	 * Returns the value of the root and does a pairwise combine on its siblings 
	 * and finds the "winner" among the siblings to make the new root. If the 
	 * root is null, this method will return null.
	 */
	public T remove()
	{
		T returner = null;
		
		if(this.root != null)
		{
			returner = this.root.getValue();
			/* Merge pass and find new root. Then return value */
			try { this.root = this.pairwiseCombine(this.root); } 
			catch (UnequalChildrenException e) 
			{ 
				e.printStackTrace();
				this.root = null;
			}
		}
		return returner;
	}
	
	/**
	 * This method obtains the siblings and children of node and combines all 
	 * these elements of similar degree recursively. It will also finds the
	 * "winner" amongst the nodes as determined by the compare() method and 
	 * returns that node. This method restructures the tree. 
	 * @param node	the node whose siblings and children are being combined
	 * @return		the "winning" child or sibling
	 * @throws UnequalChildrenException	This method will throw an 
	 * UnequalChildrenException if it attempts to merge two nodes of differing 
	 * degrees. However, this exception should never be thrwon if the logic is
	 * sound.
	 */
	protected Node<T> pairwiseCombine(Node<T> node) throws UnequalChildrenException
	{
		DoublyLinkedList<Node<T>> first = new DoublyLinkedList<Node<T>>(this.root);
		Node<T> returner = null;
		
		CustomVector<DoublyLinkedList<Node<T>>> commonDegrees = 
				new CustomVector<DoublyLinkedList<Node<T>>>((Class<DoublyLinkedList<Node<T>>>) first.getClass());
		
		/* Initialize list */
		DoublyLinkedList<Node<T>> current = null;
		DoublyLinkedList<Node<T>> previous = first;
		Node<T> next = this.root.sibling;
		
		/* Start with siblings */
		while(next != this.root)
		{
			current = new DoublyLinkedList<Node<T>>(next);
			current.left = previous;
			previous.right = current;
			previous = current;
			next = next.sibling;
		}
				
		/* Initialize children */
		next = this.root.child;
		if(next != null)
		{
			do
			{
				current = new DoublyLinkedList<Node<T>>(next);
				current.left = previous;
				previous.right = current;
				previous = current;
				next = next.sibling;
			} while(next != this.root.child);
		}
		
		if(current != null)
		{
			/* Link the list */
			first.left = current;
			current.right = first;
			current = first.right;
			
			/* Now combine the elements */
			Container<T> cont = new Container<T>(new DoublyLinkedList<Node<T>>(null), current);
			do
			{
				this.pass(cont, commonDegrees);
			}while(cont.current != first);
			
			/* Combine the nodes the list and remove the root */
			current = first.right;
			/* Remove the root */
			first.left.right = first.right;
			first.right.left = first.left;
			first = current;
			
			/* Combine the list */
			do
			{
				current.val.sibling = current.right.val;
				current = current.right;
			}while(current != first);
			returner = cont.winning.val;
		}
		else returner = null;
		
		return returner;
	}
	
	/**
	 * This method will compare cont.current and cont.winner to find a new 
	 * winner. It will then attempt to do a emrge using cont according to 
	 * commonDegrees
	 * @param cont			the container that holds the current node and 
	 * 						winning node
	 * @param commonDegrees	the structure that holds the nodes that have been 
	 * 						passed thus far based on their degrees.
	 * @throws UnequalChildrenException
	 */
	protected void pass(Container<T> cont,
			CustomVector<DoublyLinkedList<Node<T>>> commonDegrees) throws UnequalChildrenException
		{
			/* look for next root */
			cont.winning = this.compare(cont.current.val, cont.current, cont.winning.val, cont.winning).winnerHolder;
			this.mergeDegrees(cont, commonDegrees);
		}
	
	/**
	 * mergeDegrees will attempt to merge cont.current with a node in 
	 * commonDegrees who has an equal number of children to cont.current 
	 * (recursively). This method is recursive so that it can combine the 
	 * resulting combined nodes with other nodes of the same degree. Finally,
	 * this method will "progress" cont.current by setting cont.current to its
	 * right node.	
	 * @param cont			the container that holds the current node and 
	 * 						winning node
	 * @param commonDegrees	the structure that holds the nodes that have been 
	 * 						passed thus far based on their degrees.
	 * @throws UnequalChildrenException
	 */
	protected void mergeDegrees(Container<T> cont,
			CustomVector<DoublyLinkedList<Node<T>>> commonDegrees) throws UnequalChildrenException
		{
			commonDegrees.ensureSize(cont.current.val.getDegree() + 1);
			DoublyLinkedList<Node<T>> temp = commonDegrees.get(cont.current.val.getDegree());
			if(temp == null)
			{
				/* No other node with matching degree found. Set current 
				 * DoublyLinkedList to commonDegree at the position dictated by
				 * cont.current.val.getDegree() and progress */  
				commonDegrees.set(cont.current.val.getDegree(), cont.current);
				cont.current = cont.current.right;
			}
			else
			{
				/* Remove the element from commonDegrees now that it will be
				 * merged and no longer have the previous degree or become a 
				 * child of another node and so it should not be merged with 
				 * another node. */
				commonDegrees.set(cont.current.val.getDegree(), null);
				
				/* Another node with common degree has been found. Merge the two
				 * and remove the loser from the list. */
				NodePair<T> comp = this.compare(cont.current.val, cont.current, temp.val, temp);
				
				/* Remove the loser from the list */
				comp.loserHolder.left.right = comp.loserHolder.right;
				comp.loserHolder.right.left = comp.loserHolder.left;
				
				/* Merge the winner node with the loser node. When the loser
				 * becomes a child, it's siblings are stripped from it. */
				comp.winner.addChild(comp.loser);
				
				/* Check to see if there are any more merges that can be done */
				commonDegrees.ensureSize(comp.winner.getDegree() + 1);
				temp = commonDegrees.get(comp.winner.getDegree());
				if(temp != null) this.mergeDegrees(new Container<T>(null, comp.winnerHolder), commonDegrees);
				/* Set the common degree */
				else commonDegrees.set(comp.winner.getDegree(), comp.winnerHolder);
				/* Progress */
				cont.current = cont.current.right;
			}
		}
	
	/**
	 * Returns a string representation of the BinomialTree where the contents of 
	 * each level are grouped.
	 */
	@Override
	public String toString()
	{
		CustomVector<StringBuffer> content = new CustomVector<StringBuffer>(StringBuffer.class);
		StringBuffer returner = new StringBuffer();
		this.getString(content, this.root, this.root, 0, true);
		for(int i = 0; i < content.size(); i++)
		{
			returner.append("Level " + (i+1) + ":[" + content.get(i).substring(0, content.get(i).length()-1) + "]\n");
		}
		return returner.toString();
	}
	
	/**
	 * /**
	 * Puts a string representation of node into content divided by "level"
	 * @param content		the holder that keeps track of the strings
	 * @param current		the current node being parsed
	 * @param levelRoot		the node pointed to by the parent
	 * @param level			the current level
	 * @param fromLevelRoot	whether or not this method was started by levelRoot
	 */
	protected void getString(CustomVector<StringBuffer> content, Node<T> current, Node<T> levelRoot, int level, boolean fromLevelRoot)
	{
		content.ensureSize(level + 1);
		if(content.get(level) == null) content.set(level, new StringBuffer());
		if(current != levelRoot || fromLevelRoot)
		{
			content.get(level).append(current.getValue().toString());
			content.get(level).append(",");
			if(current.child != null ) this.getString(content, current.child, current.child, level+1, true);
			if(current != levelRoot || fromLevelRoot) this.getString(content, current.sibling, levelRoot, level, false);
		}		
	}
}
