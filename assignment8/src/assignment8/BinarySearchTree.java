package assignment8;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Implementation of a Binary Search Tree(BST)
 * 
 * @author Andre Watson, and Ryan Dalby
 *
 * @param <T> type of data in BST, must be comparable
 */

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {
	private BinaryNode root;
	private int size;

	/**
	 * Constructor for BST
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment8.SortedSet#add(java.lang.Comparable) Adds item into new node
	 * in BST as specified by sorted set
	 */
	@Override
	public boolean add(T item) {
		if (root == null) {
			root = new BinaryNode(item);
			size++;
			return true;
		}

		return addRecursive(item, root);
	}

	/**
	 * @param item the thing to add
	 * @param n    root node of BST or subtree
	 * @return true if something was added
	 */
	private boolean addRecursive(T item, BinaryNode n) {
		if (item.compareTo(n.data) == 0) // if this is true then item was already in the tree
		{
			return false;
		}
		if (item.compareTo(n.data) > 0) // if item is greater go to the right
		{
			if (n.right == null) // if there is nothing there we found place to add
			{
				n.right = new BinaryNode(item, n);
				size++;
				return true;
			}
			return addRecursive(item, n.right);
		} else // if item is less go to the left
		{
			if (n.left == null) // if there is nothing there we found place to add
			{
				n.left = new BinaryNode(item, n);
				size++;
				return true;
			}
			return addRecursive(item, n.left);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment8.SortedSet#addAll(java.util.Collection) Ensures all items in
	 * tree as by sorted sets add all
	 */
	@Override
	public boolean addAll(Collection<? extends T> items) {
		boolean addedAny = false;
		for (T item : items) {
			if (add(item)) {
				addedAny = true;
			}
		}
		return addedAny;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment8.SortedSet#clear() Sets BST back to empty BST
	 */
	@Override
	public void clear() {
		root = null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment8.SortedSet#contains(java.lang.Comparable) Checks if item is
	 * already in BST
	 */
	@Override
	public boolean contains(T item) {
		if (root == null) {
			return false;
		}
		return containsRecursive(item, root);
	}

	/**
	 * @param item thing to check
	 * @param n    root of BST or subtree
	 * @return true if item is there
	 */
	private boolean containsRecursive(T item, BinaryNode n) {
		if (item.compareTo(n.data) == 0) {
			return true;
		}
		if (item.compareTo(n.data) > 0) {
			if (n.right == null) {
				return false;
			}
			return containsRecursive(item, n.right);
		} else {
			if (n.left == null) {
				return false;
			}
			return containsRecursive(item, n.left);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment8.SortedSet#containsAll(java.util.Collection) Checks if every
	 * item is in BST as specified by sorted set
	 */
	@Override
	public boolean containsAll(Collection<? extends T> items) {
		for (T item : items) {
			if (!contains(item)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment8.SortedSet#first() Gets the smallest value in BST which is
	 * leftmost node
	 */
	@Override
	public T first() throws NoSuchElementException {
		if (root == null) {
			throw new NoSuchElementException();
		}
		return getLeftmostNode(root).data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment8.SortedSet#isEmpty()' Returns true if BST is empty as
	 * specified by sorted set
	 */
	@Override
	public boolean isEmpty() {
		return (root == null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment8.SortedSet#last() Returns biggest item in BST which is
	 * rightmost node
	 */
	@Override
	public T last() throws NoSuchElementException {
		if (root == null) {
			throw new NoSuchElementException();
		}
		return getRightmostNode(root).data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment8.SortedSet#remove(java.lang.Comparable) Removes specified
	 * item and returns true if BST changed
	 */
	@Override
	public boolean remove(T item) {
		if (root == null) // if BST is empty can't remove
		{
			return false;
		}
		if (root.data.compareTo(item) == 0) // if item to remove is in the root node
		{
			return rootRemove(root);
		}
		return removeRecursive(item, root); // means that item to remove is not in root node and thus has parent
	}

	/**
	 * Removes root properly handling all cases
	 * 
	 * @param n root node
	 * @return always true
	 */
	private boolean rootRemove(BinaryNode n) {
		if (numChildren(n) == 0) {
			root = null;
		}
		if (numChildren(n) == 1) {
			if (n.left != null) {
				root = n.left;
				root.parent = null;
			} else {
				root = n.right;
				root.parent = null;
			}
		}
		if (numChildren(n) == 2) {
			BinaryNode s = getSuccessor(n);
			T replacementData = s.data;
			removeHelper(s); // Ordinary remove on successor
			n.data = replacementData;
		}
		size--;
		return true; // removed root

	}

	/**
	 * @param item thing to be removed
	 * @param n    root of BST or subtree
	 * @return true if item was removed
	 */
	private boolean removeRecursive(T item, BinaryNode n) {
		if (item.compareTo(n.data) == 0) {
			removeHelper(n);
			return true;
		}
		if (item.compareTo(n.data) > 0) {
			if (n.right == null) {
				return false;
			}
			return removeRecursive(item, n.right);
		} else {
			if (n.left == null) {
				return false;
			}
			return removeRecursive(item, n.left);
		}
	}

	/**
	 * This helper method handles the 3 possible remove cases for a BST
	 * 
	 * @param n node to be removed
	 */
	private void removeHelper(BinaryNode n) {
		if (numChildren(n) == 0) // leaf node
		{
			if (n.data.compareTo(n.parent.data) > 0) // if leaf is to the right of its parent
			{
				n.parent.right = null;
			} else // if leaf is to the left of its parent
			{
				n.parent.left = null;
			}
			size--;
		} else if (numChildren(n) == 1) // 1 child
		{
			if (n.left != null) // after the bypass, should point to n's left child
			{
				n.left.parent = n.parent; // accurate parent for successor so that deleted node doesn't stay connected
				if (n.data.compareTo(n.parent.data) > 0) // if node is to the right of its parent
				{
					n.parent.right = n.left;
				} else // if node is to the left of its parent
				{
					n.parent.left = n.left;
				}
			} else if (n.right != null) // after the bypass, should point to n's right child
			{
				n.right.parent = n.parent;
				if (n.data.compareTo(n.parent.data) > 0) // if node is to the right of its parent
				{
					n.parent.right = n.right;
				} else // if node is to the left of its parent
				{
					n.parent.left = n.right;
				}
			}
			size--;
		} else {
			BinaryNode s = getSuccessor(n);
			T replacementData = s.data;
			removeHelper(s); // successor will never have 2 children so we will never recurse more than once
			n.data = replacementData;
			size--;

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment8.SortedSet#removeAll(java.util.Collection) Removes items as
	 * specified by sorted set.
	 */
	@Override
	public boolean removeAll(Collection<? extends T> items) {
		boolean isRemoved = false;
		for (T item : items) {
			if (remove(item)) {
				isRemoved = true;
			}
		}
		return isRemoved;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment8.SortedSet#size() returns size of BST
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment8.SortedSet#toArrayList() Gives the ArrayList representation
	 * of an in-order DFT of the BST
	 */
	@Override
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>(size);
		if (root == null) {
			return list;
		}
		getInOrder(list, root);
		return list;
	}

	/**
	 * @param list ArrayList to hold BST values
	 * @param n    root or root of subtree node
	 */
	private void getInOrder(ArrayList<T> list, BinaryNode n) {
		if (n == null) {
			return;
		}

		getInOrder(list, n.left);
		list.add(n.data);
		getInOrder(list, n.right);

	}

	/**
	 * Gets the leftmost node in the binary tree rooted at n
	 * 
	 * @param n
	 * @return The leftmost node
	 * 
	 *         From Lab7
	 */
	private BinaryNode getLeftmostNode(BinaryNode n) {
		// Base case, done for you
		if (n.left == null)
			return n;

		return getLeftmostNode(n.left);
	}

	/**
	 * Gets the rightmost node in the binary tree rooted at n
	 * 
	 * @param n
	 * @return The rightmost node
	 * 
	 *         From Lab7
	 */
	private BinaryNode getRightmostNode(BinaryNode n) {
		// Base case, done for you
		if (n.right == null)
			return n;

		return getRightmostNode(n.right);
	}

	/**
	 * Finds the successor of a given node. The successor is a node which can
	 * replace this node in a case-3 BST deletion. It is either the smallest node in
	 * the right subtree, or the largest node in the left subtree. Assumes that the
	 * node has two children. This method should not be called otherwise.
	 * 
	 * @param n - The node
	 * @return The node's successor
	 * 
	 *         From Lab7
	 */
	private BinaryNode getSuccessor(BinaryNode n) {
		return getLeftmostNode(n.right);
	}

	/**
	 * Number of children Use this to help figure out which BST deletion case to
	 * perform
	 * 
	 * @param n - The node
	 * @return The number of children of the given node
	 * 
	 *         From Lab7
	 */
	private int numChildren(BinaryNode n) {
		int numChildren = 0;
		if (n.left != null)
			numChildren++;
		if (n.right != null)
			numChildren++;
		return numChildren;
	}

	// Driver for writing this tree to a dot file. Taken from Lecture 14 code
	public void writeDot(String filename) {
		try {
			// PrintWriter(FileWriter) will write output to a file
			PrintWriter output = new PrintWriter(new FileWriter(filename));

			// Set up the dot graph and properties
			output.println("digraph BST {");
			output.println("node [shape=record]");

			if (root != null)
				writeDotRecursive(root, output);
			// Close the graph
			output.println("}");
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Recursive method for writing the tree to a dot file
	private void writeDotRecursive(BinaryNode n, PrintWriter output) throws Exception {
		output.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]");
		if (n.left != null) {
			// write the left subtree
			writeDotRecursive(n.left, output);

			// then make a link between n and the left subtree
			output.println(n.data + ":L -> " + n.left.data + ":D");
		}
		if (n.right != null) {
			// write the left subtree
			writeDotRecursive(n.right, output);

			// then make a link between n and the right subtree
			output.println(n.data + ":R -> " + n.right.data + ":D");
		}

	}

	private class BinaryNode {
		private T data;

		private BinaryNode left;

		private BinaryNode right;

		private BinaryNode parent;

		/**
		 * Constructor for BinaryNode
		 * 
		 * @param data   item to store in BST
		 * @param parent new nodes parent
		 */
		public BinaryNode(T data, BinaryNode parent) {
			this.data = data;

			left = null;
			right = null;

			this.parent = parent;
		}

		/**
		 * Constructor for BinaryNode without parent
		 * 
		 * @param data item to store in BST
		 */
		public BinaryNode(T data) {
			this(data, null);
		}

	}

}
