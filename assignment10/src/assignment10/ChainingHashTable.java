package assignment10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Andre Watson and Ryan Dalby
 *
 */
public class ChainingHashTable implements Set<String> {
	private ArrayList<LinkedList<String>> backing;
	private HashFunctor hasher;
	private int currentSize;
	private int currentCapacity;
	private int collisions;
	
	/**
	 * Constructor for a separate chaining hash table
	 * @param capacity initial size of hash table
	 * @param functor hash function
	 */
	public ChainingHashTable(int capacity, HashFunctor functor)
	{
		backing = new ArrayList<LinkedList<String>>(capacity);
		currentCapacity = capacity;
		for(int i = 0; i < currentCapacity; i++) //fill with empty linked lists
		{
			backing.add(new LinkedList<String>());
		}
		hasher = functor;
		currentSize = 0;
		collisions = 0;
	}

	@Override
	public boolean add(String item) {
		if(currentCapacity == 0 || currentSize / currentCapacity > .75) //Rehash if currentCapacity is 0 or average list size is greater than desired load factor
		{
			rehash();
		}
		
		int hashIndex = Math.abs(hasher.hash(item)) % currentCapacity;
		for(String s : backing.get(hashIndex))
		{
			if(s.equals(item))
			{
				return false; //Prevents duplicates as specified by a set
			}
			collisions++;//for each value we pass in the bucket already there it is a collision
		}
		backing.get(hashIndex).add(item); //add item in hashIndex bucket
		currentSize++;
		return true; //should always add
	}

	/**
	 * Helper method for rehashing our chaining hash table
	 */
	private void rehash() {
		ArrayList<String> storage = new ArrayList<String>(); //will hold previous data
		for(LinkedList<String> list : backing)
		{
			for(String s : list) 
			{
				storage.add(s);
			}
		}
		backing = new ArrayList<LinkedList<String>>(currentCapacity * 2); //make new backing
		currentCapacity = currentCapacity * 2; //set new current capacity to twice its size
		for(int i = 0; i < currentCapacity; i++) //fill with empty linked lists
		{
			backing.add(new LinkedList<String>());
		}
		currentSize = 0; //reset current size as it will be incremented in addAll again
		addAll(storage);//add previous data back into our hash table with different indices
		
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean addedAny = false;
		for(String s : items)
		{
			addedAny =  add(s) || addedAny;
		}
		return addedAny;
	}

	@Override
	public void clear() {
		backing = new ArrayList<LinkedList<String>>(currentCapacity);
		for(int i = 0; i < currentCapacity; i++) //fill with empty linked lists
		{
			backing.add(new LinkedList<String>());
		}
		currentSize = 0;
		
	}

	@Override
	public boolean contains(String item) {
		int hashIndex = Math.abs(hasher.hash(item)) % currentCapacity;
		for(String s : backing.get(hashIndex)) //look at the linked list in the correct bucket
		{
			if(s.equals(item)) //if we find item
			{
				return true;
			}
		}
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
