package trees.leftisttree;

/**
 * Helper object used by Node.
 * @author Carlos Vasquez
 *
 * @param <T> Generic Type
 */
public class NodePair<T> 
{
	public Node<T> winner;
	public Node<T> loser;
	
	public NodePair(Node<T> winner, Node<T> loser)
	{
		this.winner = winner;
		this.loser = loser;
	}
}
