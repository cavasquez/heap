package trees.binomialheap;

/**
 * UnequalChildrenException is thrown by Node whenever a Node attempts to add
 * a child who has a degree that is not equal to the degree of the callee.
 * @author Carlos Vasquez
 *
 */
public class UnequalChildrenException extends Exception
{
	public UnequalChildrenException() { super(); }
	public UnequalChildrenException(String message) { super(message); }
	public UnequalChildrenException(String message, Throwable cause) { super(message, cause); }
	public UnequalChildrenException(Throwable cause) { super(cause); }
}