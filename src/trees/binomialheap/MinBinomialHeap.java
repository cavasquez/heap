package trees.binomialheap;

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
	protected NodePair<T> compare(Node<T> node1, Node<T> prevNode1, Node<T> node2, Node<T> prevNode2) 
	{
		NodePair<T> returner = null;
		if(node1 == null && node2 == null) returner = null;
		else if(node1 == null) returner = new NodePair<T>(node2, prevNode2, node1, prevNode1);
		else if(node2 == null) returner = new NodePair<T>(node1, prevNode1, node2, prevNode2);
		else if(node1.value.compareTo(node2.value) <= 0) returner = new NodePair<T>(node1, prevNode1, node2, prevNode2);  
		else returner = new NodePair<T>(node2, prevNode2, node1, prevNode1);
		return returner;
	}

}
