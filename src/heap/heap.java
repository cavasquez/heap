package heap;

import java.util.Random;

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
	 * will apply m operations on the two data structures and measure their
	 * performance. This test will be run reps times for each set of data and 
	 * average the time between them.
	 * @param reps	the number of repetitions
	 */
	protected void random(int reps, int m)
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
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 7; j++) { results[i][j] = 0; }
		}
		
		/* Start the test(s) */
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < reps; j++)
			{
				/* First, generate the instructions */
			}
		}
	}
	
	/**
	 * Returns a random permutation of the numbers between 0 and size.
	 * @param size	the size of the permutation
	 * @return		a random permutation of the numbers between 0 and size
	 */
	protected static int[] generateRand(int size)
	{
		int[] rand = new int[size];
		/* Initialize */
		for(int i = 0; i < size; i++) { rand[i] = i; }
		
		/* Scramble: swap A[i] and A[random(i,n-1)] */
		Random gen = new Random();
		for(int i = 0; i < size; i++)
		{
			rand[i] = rand[gen.nextInt(size - i) + i];
		}
		return rand;
	}
	
	/**
	 * Returns a randomly generated array of Instruction of size m. The 
	 * INSERT instructions will be a positive integer value.
	 */
	protected static Instruction[] generateInstructions(int m)
	{
		Instruction[] ops = new Instruction[m];
		
		Random gen = new Random();
		for(int i = 0; i < m; i++)
		{
			ops[i] = Instruction.makeInstruction(gen.nextInt() % 2, Math.abs(gen.nextInt()));
		}
		
		return ops;
	}
}
