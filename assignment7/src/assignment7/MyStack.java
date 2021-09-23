package assignment7;

// Feel free to use Java's LinkedList if you aren't confident
// in your own MyLinkedList solution.
import assignment6.MyLinkedList;
import java.util.NoSuchElementException;

/**
 * Represents a generic stack (first in, last out).
 * 
 * @author ??
 * 
 * @param <T>
 *            -- the type of elements contained in the stack
 */
public class MyStack<T> {

	private MyLinkedList<T> stack;

	public MyStack() {
		stack = new MyLinkedList<T>();
	}

	/**
	 * Removes all of the elements from the stack.
	 */
	public void clear() {
		// FILL IN
	}

	/**
	 * Returns true if the stack contains no elements.
	 */
	public boolean isEmpty() {
		// FILL IN
		return false;
	}

	/**
	 * Returns the item at the top of the stack without removing it from the
	 * stack. Throws NoSuchElementException if the stack is empty.
	 */
	public T peek() throws NoSuchElementException {
		// FILL IN
		return null;
	}

	/**
	 * Returns and removes the item at the top of the stack. Throws
	 * NoSuchElementException if the stack is empty.
	 */
	public T pop() throws NoSuchElementException {
		// FILL IN
		return null;
	}

	/**
	 * Pushes the input item onto the top of the stack.
	 */
	public void push(T item) {
		// FILL IN
	}

	/**
	 * Returns the number of items in the stack.
	 */
	public int size() {
		return stack.size();
	}
}
