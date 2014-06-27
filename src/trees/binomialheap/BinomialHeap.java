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
	protected abstract NodePair<T> compare(Node<T> node1, Node<T> prevNode1, Node<T> node2, Node<T> prevNode2);
	
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
	 * finds the "winner" among the siblings to make the new root. If the root
	 * is null, this method will return null.
	 */
	public T remove()
	{
		T returner = null;
		//Node<T> temp = this.root;
		
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
	
	protected Node<T> pairwiseCombineCorrect(Node<T> node) throws UnequalChildrenException
	{
		DoublyLinkedList<Node<T>> first = new DoublyLinkedList<Node<T>>(this.root.sibling);
		
		CustomVector<DoublyLinkedList<Node<T>>> commonDegrees = 
				new CustomVector<DoublyLinkedList<Node<T>>>((Class<DoublyLinkedList<Node<T>>>) first.getClass());
		
		/* Initialize list */
		DoublyLinkedList<Node<T>> current;
		DoublyLinkedList<Node<T>> previous = first;
		Node<T> next = this.root.sibling.sibling;
		
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
		do
		{
			current = new DoublyLinkedList<Node<T>>(next);
			current.left = previous;
			previous.right = current;
			previous = current;
			next = next.sibling;
		} while(next != this.root.child);
		
		/* Link the list */
		first.left = current;
		current.right = first;
		current = first;
		
		/* Now combine the elements */
		do
		{
			this.pass(new Container(null, current), commonDegrees);
		}while(current != first);
		
		return null;
	}
	
	protected void pass(Container<T> cont,
			CustomVector<DoublyLinkedList<Node<T>>> commonDegrees) throws UnequalChildrenException
		{
			/* look for next root */
			cont.winning = this.compare(current.val, cont.winning).winner;
			this.mergeDegrees(hist, temp, comp, commonDegrees);
		}
	
	/**
	 * Merges all the siblings and children of node
	 * @param node	the root
	 * @return		the new root
	 */
	protected Node<T> pairwiseCombine(Node<T> node) throws UnequalChildrenException
	{
		History<T> hist = new History<T>(node.sibling, node, null);
		Node<T> temp = null;
		Node<T> children = node.child;
		NodePair<T> comp = null;
		
		/* common degrees will contain the nodes to the node with a sibling
		 * to a node that contains a given degree. The degree will act as a
		 * sort of key. */
		CustomVector<Node<T>> commonDegrees = new CustomVector<Node<T>>((Class<Node<T>>) node.getClass());
		
		/* Start by merging the siblings of node keep going until we hit node
		 * again. Also look for the "winner" */
		while(hist.current != node)
		{
			this.pass(hist, temp, comp, commonDegrees);
		}
		
		/* remove node from the list and make sure it's not in commonDegrees */
		this.ensureSize(commonDegrees, node.sibling.getDegree() + 1);
		boolean findPrev = false;
		if(commonDegrees.get(node.sibling.getDegree()) == node)
		{
			/* If the previous node of node.sibling.degree is the same as
			 * hist.previous, then the commonDegree at node.sibling.degree will
			 * point to itself. This is an issue because if a sibling is added
			 * to hist.previous, then commonDegrees will incorrectly point to
			 * the wrong "previous" node. Set a flag to find the new "previous"
			 * node */
			if(node.sibling == hist.previous) findPrev = true; 
			commonDegrees.set(node.sibling.getDegree(), hist.previous);
		}
		hist.previous.sibling = node.sibling;
		
		/* Now merge the children in with the siblings */
		if(children != null)
		{
			hist.previous.addSibling(children);
			hist.current = hist.previous.sibling;
		
			/* Check if we need to find the "previous" node for 
			 * node.sibling.getDegree() */
			if(findPrev)
			{
				Node<T> offender = commonDegrees.get(node.sibling.getDegree());
				Node<T> prevDegree = offender.sibling;
				while(prevDegree.sibling != offender) { prevDegree = prevDegree.sibling; }
				commonDegrees.set(node.sibling.getDegree(), prevDegree);
			}
			/* Make a final pass with the children */
			for(int i = 0; i < node.getDegree(); i++)
			{
				this.pass(hist, temp, comp, commonDegrees);
			}
		}		
		
		return hist.winning;
	}
	
	/**
	 * Helper function for pairwiseCompare. This method will merge all the
	 * nodes of common degrees with current and then return the node with
	 * the "winning" value between hist.current and returner
	 * @param returner		the old/next winner 
	 * @param hist			the collection of the current and previous nodes			
	 * @param temp			a temporary holder
	 * @param comp			the NodePair used for comparisons
	 * @param commonDegrees	the CustomVector of nodes by degree. The degree of a node
	 * 						should act as the index in the CustomVector and the node
	 * 						at the given index should be Node whose sibling has
	 * 						the degree of the index.
	 * @throws UnequalChildrenException
	 */
	protected void pass(History<T> hist,
		Node<T> temp,
		NodePair<T> comp,
		CustomVector<Node<T>> commonDegrees) throws UnequalChildrenException
	{
		/* look for next root */
		hist.winning = this.compare(hist.current, hist.winning).winner;
		this.mergeDegrees(hist, temp, comp, commonDegrees);
	}
	
	/**
	 * Performs the merge between hist.current and any nodes with the same
	 * degree in commonDegrees. The resulting node will also be merged with
	 * any nodes in commonDegrees. This is done recursively. Furthermore,
	 * hist is modified so that the "next" node in the list of siblings is
	 * appropriately set.
	 * @param hist			contains the current and previous nodes of the 
	 * 						current node who is being merged
	 * @param commonDegrees	the CustomVector of nodes by degree. The degree of a node
	 * 						should act as the index in the CustomVector and the node
	 * 						at the given index should be Node whose sibling has
	 * 						the degree of the index.
	 */
	public void mergeDegrees(History<T> hist,
			Node<T> temp,
			NodePair<T> comp,
			CustomVector<Node<T>> commonDegrees) throws UnequalChildrenException
	{
		this.ensureSize(commonDegrees, hist.current.getDegree() + 1);
		if(commonDegrees.get(hist.current.getDegree()) == null)
		{
			/* No other node with matching degree found. Add previous node to 
			 * commonDegrees and then set new pointers for current and previous. */
			commonDegrees.set(hist.current.getDegree(), hist.previous);
			hist.previous = hist.current;
			hist.current = hist.current.sibling;
		}
		else if(commonDegrees.get(hist.current.getDegree()) != hist.current) // this if should never be false
		{
			Node<T> common = null;
			temp = commonDegrees.get(hist.current.getDegree());
			boolean findPrev = false;
			
			/* remove the common degree from list and merge it with its 
			 * common degree according to the comparator */
			commonDegrees.set(hist.current.getDegree(), null);
			comp = this.compare(hist.current, hist.previous, temp.sibling, temp);
			
			/* Before removing the common degree from the list, ensure that the
			 * node with the common degree is not also a "previous" pointer for
			 * another element in the list if common is used, it is possible 
			 * that it may become a child of another node. If it does become 
			 * the child of another node and still exists in commonDegree, it 
			 * will incorrectly point to the wrong in the list of siblings. It 
			 * will only become the child of another node if it is the "loser" 
			 * from compare(). */
			common = temp.sibling;
			this.ensureSize(commonDegrees, common.sibling.getDegree() + 1);
			if(commonDegrees.get(common.sibling.getDegree()) == common &&
				common == comp.loser)
			{
				/* If the previous node of node.sibling.degree is the same as
				 * hist.previous, then the commonDegree at node.sibling.degree will
				 * point to itself. This is an issue because if a sibling is added
				 * to hist.previous, then commonDegrees will incorrectly point to
				 * the wrong "previous" node. Set a flag to find the new "previous"
				 * node */
				if(common.sibling == comp.previousLoser) findPrev = true; 
				
				/* Replace common with its "previous" in commonDegrees, which
				 * can be obtained by looking for the common's degree in 
				 * commonDegrees. 
				 * The modification of commons previous to point to commons 
				 * sibling will occur later (as it had to anyways) */
				commonDegrees.set(common.sibling.getDegree(), comp.previousLoser);
			}
			
			/* Remove loser from siblings while maintaining the current siblings */
			comp.previousLoser.sibling = comp.loser.sibling;
			
			/* Progress history here. If the loser is hist.current, it cannot
			 * become hist.previous. Set it to the previous of loser */
			if(hist.current == comp.loser) hist.previous = comp.previousLoser;
			else hist.previous = hist.current;
			hist.current = hist.current.sibling;
			
			/* remove siblings from loser and make it a child */
			comp.loser.sibling = comp.loser;
			comp.winner.addChild(comp.loser);
			
			/* Now, fix previous */
			if(findPrev)
			{
				Node<T> offender = commonDegrees.get(common.sibling.getDegree());
				Node<T> prevDegree = offender.sibling;
				while(prevDegree.sibling != offender) { prevDegree = prevDegree.sibling; }
				commonDegrees.set(common.sibling.getDegree(), prevDegree);
			}
			
			/* Attempt to merge the winner with another node if there exists
			 * another node of equal degree. If not, insert it into commonDegrees */
			this.ensureSize(commonDegrees, comp.winner.getDegree() + 1);
			if(commonDegrees.get(comp.winner.getDegree()) != null)
			{
				this.mergeDegrees(new History<T>(comp.winner, comp.previousWinner, null), temp, comp, commonDegrees);
			}
			else
			{
				commonDegrees.set(comp.winner.getDegree(), comp.previousWinner);
			}
		}
	}
	
	/**
	 * Ensures that the CustomVector is always of sufficient size
	 * @param vec	the CustomVector being adjusted
	 * @param size	the size to which the CustomVector is being adjusted
	 */
	protected void ensureSize(CustomVector vec, int size)
	{
		vec.ensureSize(size);
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
		this.ensureSize(content, level+1);
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
