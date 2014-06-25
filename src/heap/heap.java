package heap;

/**
 * Heap is the driver class for the heap project.
 * @author Carlos Vasquez
 *
 */
public class heap 
{
	public static void main(String[] args)
	{
		switch(args[0])
		{
			case"-r":
			break;
			case "-il":
			break;
			case "-ib":
			break;
			default: System.out.println("Invalid command. Valid commands are: -r, -il {file}, -ib {file}");
		}
	}
	
	/**
	 * This method is called when the "-r" flag is provided. It will randomly
	 * generate some sets of 100, 500, 1000, 2000, 3000, 4000, and 5000 elements
	 * to initialize a MinLeftistTree and MinBinomialHeap with. Then this method
	 * will apply 5000 operations on the two data structures and measure their
	 * performance. This test will be run 5 times for each set of data and 
	 * average the time between them.
	 */
	protected void random()
	{
		int MIN_LEFTIST_TREE = 0;
		int MIN_LEFTIST_HEAP = 1;
		
		int[] n = new int[5000];
		int[][] results = new int[2][7];
		int[] test = new int[7];
		test[0] = 100;
		test[1] = 500;
		test[2] = 1000;
		test[3] = 2000;
		test[4] = 3000;
		test[5] = 4000;
		test[6] = 5000;
		
		/* Initialize elements */
		for(int i = 0; i < 5; i++)
		{
			
		}
	}
	
	protected void generateRand(int[] rand)
	{
		
	}
	
	
}
