package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Daniel Kopta and Ryan Dalby, Andre Watson
 * 
 * Implements the Collection interface using an array as storage.
 * The array must grow as needed.
 * An ArrayCollection can not contain duplicates.
 * All methods should be implemented as defined by the Java API, unless otherwise specified.
 * 
 * It is your job to fill in the missing implementations and to comment this class. Every method should have
 * comments (Javadoc-style prefered). The comments should at least indicate what the method
 * does, what the arguments mean, and what the returned value is. It should specify any special cases that the method
 * handles. Any code that is not self-explanatory should be commented.
 *
 * @param <T> - generic type placeholder
 */
public class ArrayCollection<T> implements Collection<T> {

	private T data[]; // Storage for the items in the collection
	private int size; // Keep track of how many items this collection holds

	// There is no clean way to convert between T and Object, so we suppress the warning.
	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0;
		// We can't instantiate an array of unknown type T, so we must create an Object array, and typecast
		data = (T[]) new Object[10]; // Start with an initial capacity of 10
	}

	/**
	 * This is a helper function specific to ArrayCollection
	 * Doubles the size of the data storage array, retaining its current contents.
	 */
	@SuppressWarnings("unchecked")
	private void grow()
	{
		T[] temp = data; //temporarily holds data
		data = (T[]) new Object[(data.length * 2)]; //resize to 2 times size
		for(int i =0; i < temp.length; i++) //put data back in
		{
			data[i] = temp[i];
		}
	}


	/**
	 * Checks if parameter is in ArrayCollection and if it is returns false if not it is added into the array and returns true
	 * @param arg0 object to be added to ArrayCollection
	 * @return true if added
	 * 
	 **/
	public boolean add(T arg0) {
		for(int i = 0; i < size + 1; i++) //Check if arg0 is already in ArrayCollection
		{
			if(arg0.equals(data[i]))
			{
				return false;
			}
		}
		
		if(data.length == size + 1) //Check if our array size needs to be expanded for next element
		{
			grow();
		}
		data[size] = arg0; //add to index specified by size
		size++;
		return true;
	}

	/**
	 * Adds a collection to ArrayCollection
	 * @param arg0 Collection to be added
	 * @return true if anything was added to ArrayCollection
	 * 
	 **/
	public boolean addAll(Collection<? extends T> arg0) {
		boolean didAdd = false;
		for(T element:arg0)
		{
			if(add(element)) //if adds then addAll will give true
			{
				didAdd = true;
			}
		}
		return didAdd;
	}

	@SuppressWarnings("unchecked")
	/** 
	 * Clears ArrayCollection and sets size to 0
	 **/
	public void clear() {
		size = 0;
		data = (T[]) new Object[10]; // Reset ArrayCollection with an initial capacity of 10
		
	}

	/** 
	 * Checks if argument is contained in ArrayCollection
	 * @param arg0 object to check
	 * @return true if object is in ArrayCollection
	 **/
	public boolean contains(Object arg0) {
		for(int i = 0; i < size + 1; i++)
		{
			if(arg0.equals(data[i]))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if all elements in collection are in ArrayCollection
	 * @param arg0 collection to check
	 * @return true if all elements are in ArrayCollection
	 * 
	 **/
	public boolean containsAll(Collection<?> arg0) {
		if(arg0.isEmpty() ) //if arg0 is empty returns false because no value to check against
		{
			return false;
		}
		for(Object element : arg0)
		{
			if(! this.contains(element)) // if a single element is not in ArrayCollection returns false
			{
				return false;
			}
		}
		return true;
	}

	/** 
	 * Checks if empty
	 * @return true if ArrayCollection is empty
	 **/
	public boolean isEmpty() {
		if(size == 0)
		{
			return true;
		}
		return false;
	}

	
	/** 
	 * Returns iterator for ArrayCollection
	 * @return ArrayCollectionIterator
	 **/
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	/** 
	 * Removes given object if in ArrayCollection
	 * @param arg0 Object to remove
	 * @return true if successfully removed
	 **/
	public boolean remove(Object arg0) {
		for(int i = 0; i < size + 1; i++)
		{
			if(arg0.equals(data[i])) //Checks if arg0 is in data and if so removes
			{
				data[i] = null;
				for(int j = i+1; j < size+1; j++) //Shifts all of our elements over to fill removed space
				{
					data[j-1] = data[j];
					data[j] = null;
				}
				size--;
				return true;
			}
		}
		return false;
	}

	/**
	 * Attempts to remove everything in collection from ArrayCollection
	 * @param arg0 collection to remove
	 * @return true if something was removed
	 **/
	public boolean removeAll(Collection<?> arg0) {
		boolean didRemove = false;
		for(Object element : arg0)
		{
			if(remove(element))
			{
				didRemove = true;
			}
		}
		return didRemove;
	}

	/** 
	 * Retains all values that are both in the ArrayCollection and in the passed in Collection
	 * @param arg0 collection to be compared against for what to retain
	 * @return true if an item was removed
	 **/
	public boolean retainAll(Collection<?> arg0) {
		Iterator<T> itr = this.iterator();
		boolean hasRemoved = false;
		while(itr.hasNext())
		{
			if(!arg0.contains(itr.next())) //if next item is not in arg0 then remove
			{
				itr.remove();
				hasRemoved = true;
			}
		}
		return hasRemoved;
	}

	/** 
	 * Gives size of ArrayCollection
	 * @return size of ArrayCollection
	 **/
	public int size() {
		return size;
	}

	/** 
	 * Gives Array representation of ArrayCollection
	 * @return Array equivalent of ArrayCollection
	 **/
	public Object[] toArray() {
		Object[] createdArray = new Object[size];
		for(int i = 0; i < size; i++)
		{
			createdArray[i] = data[i];
		}
		return createdArray;
	}

	/* 
	 * Don't implement this method (unless you want to).
	 * It must be here to complete the Collection interface.
	 * We will not test this method.
	 */
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	/**
	 * Sorting method specific to ArrayCollection - not part of the Collection interface.
     * Must implement a selection sort (see Assignment 2 for code).
	 * @param cmp a Comparator to use in sort
	 * @return ArrayList<T> that is sorted using a selection sort
	 */
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> createdArray = new ArrayList<T>(); 
		for(int i = 0; i < size; i++) //Copies data from ArrayCollection to ArrayList<T>
		{
			createdArray.add(i, data[i]);
		}
		
		//Selection sort on createdArray
	    for (int i = 0; i < createdArray.size() - 1; i++) {
	    int j, minIndex;
	    for (j = i + 1, minIndex = i; j < createdArray.size(); j++)
	    if (cmp.compare(createdArray.get(j), createdArray.get(minIndex)) < 0)
	    minIndex = j;
	    T temp = createdArray.get(i);
	    createdArray.set(i, createdArray.get(minIndex));
	    createdArray.set(minIndex, temp);
	    }
	    
	    return createdArray;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		private boolean hasCalledNext;
		private int currentPosition;
		/**
		 * Constructor for iterator
		 **/
		public ArrayCollectionIterator()
		{
			hasCalledNext = false;
			currentPosition = 0;
		}

		/**
		 * checks if there is another element to iterate over
		 * @return true if there is a next element, false otherwise
		 **/
		public boolean hasNext() {
			//Holder for hasCalledNext so we can be sure that our hasCalledNext value stays the same ever after trying next
			boolean hasCalledNextHolder = hasCalledNext; 
			try {
				next();
			}
			catch(NoSuchElementException e) {
				return false;
			}
			currentPosition--; //Decrement current position to account for trying next
			hasCalledNext = hasCalledNextHolder; //makes hasCalledNext the same as before trying next
			return true;
		}

		/** 
		 * Moves the iterator's position, and returns the value it was at
		 * Throws an exception if the iterator is at the last element already
		 **/
		public T next() {
			if(currentPosition == size) { 
				throw(new NoSuchElementException());
			}
			hasCalledNext = true; //allow remove
			currentPosition++;
			return data[currentPosition - 1];
			
		}

		/** 
		 * Only runs if next was called beforehand.
		 * Removes the item that was returned by next previously.
		 **/
		public void remove() {
			if(!hasCalledNext) {
				throw( new IllegalStateException());
				}
			ArrayCollection.this.remove(data[currentPosition -1]);
			currentPosition--; //move position back
			hasCalledNext = false; //prevent more remove calls until next is called
			
		}

	}

}
