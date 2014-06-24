package trees.binomialheap;

import java.lang.reflect.Array;

/**
 * Used http://stackoverflow.com/questions/529085/how-to-generic-array-creation
 * to deal with generic array
 * @author Carlos Vasquez
 * @param <T>
 */
public class CustomVector<T> 
{
	private T[] objects;
	private int size = 10;
	private Class<T> c;
	
	public CustomVector(Class<T> c)
	{
		this.c = c;
		final T[] arr = (T[]) Array.newInstance(c, size);
		this.objects = arr;
	}

	/**
	 * Returns the element at the index
	 * @param index	the index of the element
	 * @return		the element at index
	 */
	public T get(int index) 
	{
		return this.objects[index];
	}

	/**
	 * Returns the size of CustomVector
	 * @return
	 */
	public int size() 
	{
		return this.size;
	}

	/**
	 * Sets the array at index to val
	 * @param index	the index of the array
	 * @param val	the value that should be at index
	 */
	public void set(int index, T val) 
	{
		this.objects[index] = val;
	}
	
	/**
	 * Ensures that the array meets the appropriate requiredSize. This method
	 * will quadruple the array size until it meets the requiredSize.
	 * @param requiredSize	the required size of the array.
	 */
	public void ensureSize(int requiredSize)
	{
		if(this.size < requiredSize)
		{
			/* Calculate the new size and create the array. */
			while(this.size < requiredSize)
			{
				this.size *= 4;
			}
			final T[] arr = (T[]) Array.newInstance(c, size);
			
			/* populate the new array */
			for(int i = 0; i < this.objects.length; i++)
			{
				arr[i] = this.objects[i];
			}
			this.objects = arr;
		}
	}
	
}
