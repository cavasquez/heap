package trees.binomialheap;

/**
 * MinBinomialHeap extends BinomialHeap and defines the compare to set the 
 * winner node to be the node with a value less than the opposing node. 
 * @author Carlos Vasquez
 *
 * @param <T> a generic type
 */
public class MinBinomialHeap<T extends Comparable<T>> extends BinomialHeap<T>
{

	@Override
	protected NodePair<T> compare(Node<T> node1, Node<T> node2) 
	{
		NodePair<T> returner = null;
		if(node1 == null && node2 == null) returner = null;
		else if(node1 == null) returner = new NodePair<T>(node2, null, node1, null);
		else if(node2 == null) returner = new NodePair<T>(node1, null, node2, null);
		else if(node1.value.compareTo(node2.value) <= 0) returner = new NodePair<T>(node1, null, node2, null);  
		else returner = new NodePair<T>(node2, null, node1, null);
		return returner;
	}

	@Override
	protected NodePair<T> compare(Node<T> node1, 
			DoublyLinkedList<Node<T>> node1Holder, 
			Node<T> node2, 
			DoublyLinkedList<Node<T>> node2Holder) 
	{
		NodePair<T> returner = null;
		if(node1 == null && node2 == null) returner = null;
		else if(node1 == null) returner = new NodePair<T>(node2, node2Holder, node1, node1Holder);
		else if(node2 == null) returner = new NodePair<T>(node1, node1Holder, node2, node2Holder);
		else if(node1.value.compareTo(node2.value) <= 0) returner = new NodePair<T>(node1, node1Holder, node2, node2Holder);  
		else returner = new NodePair<T>(node2, node2Holder, node1, node1Holder);
		return returner;
	}

}
