package assignment10;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;


/**
 * @author Andre Watson and Ryan Dalby
 *
 */

public class QuadProbeHashTable implements Set<String>{
	private String[] backing;
	private HashFunctor hasher;
	private int currentSize;
	private int collisions;

	/**
	 * Constructor for a Quadratic Probing Hash Table
	 * @param capacity minimum size of hash table, will be increased to next prime size
	 * @param functor hash function
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor)
	{
		boolean hasFound = false;
		int n = capacity;
		while(!hasFound) //Will find next largest prime to make our backing array that size in order to do quad probing
		{
			if(BigInteger.valueOf(n).isProbablePrime(100))
			{
				hasFound = true;
				break;
			}
			n++;	
		}
		backing = new String[n];
		hasher = functor;
	}
	
	@Override
	public boolean add(String item) {
		if(currentSize == (backing.length/2) - 1) //make sure load factor is under lambda = 0.5
		{
			rehash();
		}
		int hashIndex = Math.abs(hasher.hash(item)) % backing.length;
		int i = 0;
		int increment = 0;
		do //should never loop infinitely since the load factor is under 0.5 and our backing array size is a prime 
		{ 
			int currentIndex = (hashIndex + increment) % backing.length; //allows for circular traversal

			if(backing[currentIndex] == null)
			{
				backing[currentIndex] = item;
				currentSize++;		
				return true;
			}
			//If we get here currentIndex is not null
			if(backing[currentIndex].equals(item)) //prevents duplicates as required for a set
			{
				return false; 
			}
			collisions++; //each time we loops through here we've passed a collision 
			i++;
			increment = i*i; //Increase increment quadratically 		
		}
		while((hashIndex + increment) % backing.length != hashIndex);
		
		return false; //fall-through case
	}

	/**
	 * Helper method in order to rehash our hash table
	 */
	private void rehash() {
		int newCapacity = 2 * backing.length;
		boolean hasFound = false;
		while(!hasFound) //Will find next largest prime to make our backing array that size in order to do quad probing
		{
			if(BigInteger.valueOf(newCapacity).isProbablePrime(100))
			{
				hasFound = true;
				break;
			}
			newCapacity++;	
		}
		String[] newBacking = new String[newCapacity];
		ArrayList<String> toRehash = new ArrayList<String>();
		for(int i = 0; i < backing.length; i++)
		{
			if(backing[i] != null)
			{
				toRehash.add(backing[i]);
			}
		}
		
		backing = newBacking; //sets backing to our new empty resized backing array
		currentSize = 0; //reset size as it will be incremented again by addAll
		addAll(toRehash); //rehash and restore all previous values
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean hasAdded = false;
		for(String s : items)
		{
			hasAdded = (add(s) || hasAdded); //if anything has been added 
		}
		return hasAdded; //should only return false if add has a problem every time
	}

	@Override
	public void clear() {
		int prevSize = backing.length;
		backing = new String[prevSize];
		currentSize = 0;
		
	}

	@Override
	public boolean contains(String item) {
		int hashIndex = Math.abs(hasher.hash(item)) % backing.length;
		int i = 0;
		int increment = 0;
		do 
		{ 
			int currentIndex = (hashIndex + increment) % backing.length; //allows for circular traversal
			if(backing[currentIndex] == null) //if no item there, was never added
			{
				return false;
			}
			if(backing[currentIndex].equals(item)) //if finds item before getting through all the hash table 
			{
				return true;
			}
			i++;
			increment = i*i; //Increase increment quadratically 		
		}
		while((hashIndex + increment) % backing.length != hashIndex); //while we haven't visited everything(stops once we get back to beginning, if proper quadratic probing is accomplished)
			
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean contains = true;
		for(String s : items)
		{
			contains = (contains(s) && contains); //if contains is ever false then we don't contain all
		}
		return contains;
	}

	@Override
	public boolean isEmpty() {
		return (currentSize == 0);
	}

	@Override
	public int size() {
		return currentSize;
	}
	
	public int getCollisions() {
		return collisions;
	}

}
