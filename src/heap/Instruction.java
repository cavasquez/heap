package heap;

/**
 * Instruction is a data structure that holds a type of instruction that can
 * be applied to a HeapIntergace (insert or delete).
 * @author Carlos Vasquez
 *
 */
public class Instruction 
{
	/* An enum that represents the type of operation that can be applied to a 
	 * HeapInterface */
	public enum Operation
	{
		INSERT(0),
		DELETE(1);
		
		public final Integer val;
		
		private Operation(Integer val) { this.val = val; }
	}
	
	public final Operation op;
	public final Integer val;
	
	public Instruction(Operation op, Integer val)
	{
		this.op = op;
		this.val = val;
	}
	
	/**
	 * Determines what kind of instruction to return based on op
	 * @param op	the integer value of the Operation
	 * @param val	the value that should be assigned to the Operation
	 * @return		the Instruction that contains the Operation and val
	 */
	public static Instruction makeInstruction(Integer op, Integer val)
	{
		Instruction returner = null;

		if(op == Operation.INSERT.val) returner = new Instruction(Operation.INSERT, val);
		else if(op == Operation.DELETE.val) returner = new Instruction(Operation.DELETE, null);
		
		return returner;
	}
}
