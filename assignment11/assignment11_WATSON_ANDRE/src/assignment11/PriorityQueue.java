package assignment11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items. 
 * The queue is implemented as a min heap. 
 * The min heap is implemented implicitly as an array.
 * 
 * @author Erin Parker & Ryan Dalby & André Watson
 */

public class PriorityQueue<T> {

	private int currentSize;

	private T[] array;

	private Comparator<? super T> cmp;

	/**
	 * Constructs an empty priority queue. Orders elements according
	 * to their natural ordering (i.e., T is expected to be Comparable)
	 * T is not forced to be Comparable.
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue() {
		currentSize = 0;
		cmp = null;
		array = (T[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * Construct an empty priority queue with a specified comparator.
	 * Orders elements according to the input Comparator (i.e., T need not
	 * be Comparable).
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue(Comparator<? super T> c) {
		currentSize = 0;
		cmp = c;
		array = (T[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() {
		currentSize = 0;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in constant time.)
	 */
	public T findMin() throws NoSuchElementException {
		if(currentSize == 0) {
			throw new NoSuchElementException();
		}
		return array[0];
	}


	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in logarithmic time.)
	 */
	public T deleteMin() throws NoSuchElementException {
		// FILL IN -- do not return null

		// if the heap is empty, throw a NoSuchElementException

		// store the minimum item so that it may be returned at the end

		// replace the item at minIndex with the last item in the tree

		// update size

		// percolate the item at minIndex down the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateDown helper method!

		// return the minimum item that was stored
		
		T min = findMin(); //should throw no such element exception if heap is empty

		array[0] = array[currentSize-1]; //root (which is min) replaced with last filled node
	
		array[currentSize - 1] = null; 
		
		int currentPosition = 0;
		int swapIndex = findChildToSwap(currentPosition);  //will return -1 if no children accessible
		while(swapIndex != -1 &&  compare(array[currentPosition], array[swapIndex]) > 0) { // check that a child exists and that a swap should happen
			
			//then we swap
			T temp = array[swapIndex];
			array[swapIndex] = array[currentPosition];
			array[currentPosition] = temp;
			currentPosition = swapIndex;
			
			swapIndex = findChildToSwap(currentPosition); //redundant the first time, but okay
		}
		currentSize--;
		return min;
	}

	/**
	 * 
	 * Helper method to find the child to swap with, if within the heap
	 * 
	 * @param currentPosition position of the parent percolating down
	 * @return the index of the smaller child, if it exists. Returns -1 if no children exist.
	 */
	private int findChildToSwap(int currentPosition) {
		
		if((currentPosition*2)+2 < currentSize && array[(currentPosition*2) +2] != null) { //if the right child is not out of bounds and exists
			if( compare(array[currentPosition*2 +1], array[currentPosition*2 +2]) <= 0) { //if left child is the lesser or equal
				return currentPosition*2 +1; //left will be the one that needs to swap
			}
			else {
				return currentPosition*2+2; //right will swap
			}
		}
		//no right child, but a left child could exist on its own
		if((currentPosition*2)+1 < currentSize && array[(currentPosition*2)+1] != null) { //if left child is not out of bounds and exists
			return currentPosition*2 +1;
		}
		
		return -1; //if there are no children or both child indices are out of bounds, return special case to indicate no more checks should take place
	}

	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in constant time.)
	 * 
	 * @param x -- the item to be inserted
	 */
	@SuppressWarnings("unchecked")
	public void add(T x) {
		// FILL IN

		// if the array is full, double its capacity

		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained

		// update size

		// percolate the new item up the levels of the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateUp helper method!
		if(x == null) {
			return;
		}
		
		if(array.length - 1 == currentSize) { //if there's no empty slots to add, regrow array
			T[] temp = (T[]) new Object[array.length*2];
			for(int i = 0; i < array.length; i++) {
				temp[i] = array[i];
			}
			array = temp;
		}
		
		array[currentSize] = x;
	
			
			int parentPosition = (currentSize - 1)/2; //find first parent position

			int childPosition = currentSize; //find first child position
			while(childPosition > 0 && compare(x, array[parentPosition]) < 0) { //checks up to the root, then stops
				T temp = array[parentPosition]; //store swapping value
				array[parentPosition] = x; 
				array[childPosition] = temp;
				childPosition = parentPosition; //redefine child position to where we swapped to. We can end the while loop once we swap to the root or once no swap happens.
				parentPosition = (parentPosition -1) /2; //redefine parent position to the parent of where we swapped to.
			}
		
			currentSize++;

	}

	/**
	 * Generates a DOT file for visualizing the binary heap.
	 */
	public void generateDotFile(String filename) {
		try {
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for(int i = 0; i < currentSize; i++) {
				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]");
				if(((i*2) + 1) < currentSize)
					out.println("\tnode" + i + ":f0 -> node" + ((i*2) + 1) + ":f1");
				if(((i*2) + 2) < currentSize)
					out.println("\tnode" + i + ":f2 -> node" + ((i*2) + 2) + ":f1");
			}

			out.println("}");
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Internal method for comparing lhs and rhs using Comparator if provided by the
	 * user at construction time, or Comparable, if no Comparator was provided.
	 */
	private int compare(T lhs, T rhs) {
		if (cmp == null)
			return ((Comparable<? super T>) lhs).compareTo(rhs); // safe to ignore warning
		// We won't test your code on non-Comparable types if we didn't supply a Comparator

		return cmp.compare(lhs, rhs);
	}



	//LEAVE IN for grading purposes
	public Object[] toArray() {    
		Object[] ret = new Object[currentSize];
		for(int i = 0; i < currentSize; i++)
			ret[i] = array[i];
		return ret;
	}
	
	
}
