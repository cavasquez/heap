package heap;

import java.util.Random;

import trees.HeapInterface;
import trees.binomialheap.MinBinomialHeap;
import trees.leftisttree.MinLeftistTree;

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
		int MIN_BINOMIAL_HEAP = 1;
		
		int[] n = new int[5000];
		long[][] results = new long[2][7];
		int[] test = new int[7];
		test[0] = 100;
		test[1] = 500;
		test[2] = 1000;
		test[3] = 2000;
		test[4] = 3000;
		test[5] = 4000;
		test[6] = 5000;
		
		Instruction[] ops = null;
		int[] init = null;
		long start;
		long stop;
		HeapInterface<Integer> tree = null;
		
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
				/* First, generate the instructions and permutation */
				init = heap.generateRand(test[i]);
				ops = heap.generateInstructions(m);
				
				/* Perform the test on MinLeftistTree and MinBinomialHeap */
				for(int k = 0; k < 2; k++)
				{
					if(k == MIN_LEFTIST_TREE) tree = new MinLeftistTree<Integer>();
					else if(k == MIN_BINOMIAL_HEAP) tree = new MinBinomialHeap<Integer>();
					
					/* Initialize */
					for(int l = 0; l < test[i]; l++) { tree.insert(init[l]); }
					
					/* Start test */
					start = System.currentTimeMillis();
					for(int l = 0; l < m; l++)
					{
						switch(ops[l].op)
						{
							case INSERT: tree.insert(ops[l].val);
							break;
							case DELETE: tree.remove();
							break;
						}
					}
					stop = System.currentTimeMillis();
					
					/* Record time */
					results[k][i] += (stop - start);
				}
			}
		}
		
		/* Print report: */
		System.out.println("Average cost per operation:");
		String out = "tree\t";
		for(int i = 0; i < test.length; i++) out += (test[i] + "\t");
		System.out.println(out);
		
		for(int i = 0; i < reps; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				if(j == MIN_LEFTIST_TREE) out = "MinLeftistTree\t";
				else if(j == MIN_BINOMIAL_HEAP) out = "MinBinomialHeap";
				out += ((results[j][i] / (m * reps)) + "\t");
				System.out.println(out);
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
