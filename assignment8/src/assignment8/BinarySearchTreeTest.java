package assignment8;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;


/**
 * Test contains methods, first, last, isEmpty, size, and toArrayList of BinarySearchTree
 * @author Andre Watson, Ryan Dalby
 *
 */
class BinarySearchTreeTest {

	@Test
	void testContains() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		ArrayList<Integer> items = new ArrayList<Integer>(Arrays.asList(15, 10, 14, 12, 11, 13, 18, 16, 19, 17));
		b.addAll(items);
		
		assertEquals(false, b.contains(20));
		
		assertEquals(true, b.contains(10));
	}
	
	@Test
	void testContainsAll() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		ArrayList<Integer> items = new ArrayList<Integer>(Arrays.asList(15, 10, 14, 12, 11, 13, 18, 16, 19, 17));
		b.addAll(items);
		
		ArrayList<Integer> allIn = new ArrayList<Integer>(Arrays.asList(15, 10, 12, 11, 13, 16, 17));
		ArrayList<Integer> allOut = new ArrayList<Integer>(Arrays.asList(1,2,3));
		ArrayList<Integer> oneOut = new ArrayList<Integer>(Arrays.asList(15, 10, 14, 85, 11, 13, 18, 16, 19, 17));

		
		assertEquals(false, b.containsAll(oneOut));
		
		assertEquals(false, b.containsAll(allOut));
		
		assertEquals(true, b.containsAll(allIn));

	}
	
	@Test
	void testFirst() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertThrows(NoSuchElementException.class, () -> {
			b.first();
		});
		ArrayList<Integer> items = new ArrayList<Integer>(Arrays.asList(15, 10, 14, 12, 11, 13, 18, 16, 19, 17));
		b.addAll(items);
		
		assertEquals((Integer) 10, b.first());
		

		
	}
	
	@Test
	void testIsEmpty() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		
		assertEquals(true, b.isEmpty());
		
		b.add(1);
		assertEquals(false, b.isEmpty());
		

	}
	
	@Test
	void testLast() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertThrows(NoSuchElementException.class, () -> {
			b.last();
		});
		
		ArrayList<Integer> items = new ArrayList<Integer>(Arrays.asList(15, 10, 14, 12, 11, 13, 18, 16, 19, 17));
		b.addAll(items);
		assertEquals((Integer) 19, b.last());
		
	}
	
	@Test 
	void testSize() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(0, b.size());
		b.add(1);
		assertEquals(1, b.size());
		
		ArrayList<Integer> items = new ArrayList<Integer>(Arrays.asList(15, 10, 14, 12, 11, 13, 18, 16, 19, 17));
		b.addAll(items);
		assertEquals(11, b.size());

	}
	
	@Test
	void testToArrayList() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		
		ArrayList<Integer> empty = new ArrayList<Integer>();
		assertEquals(empty, b.toArrayList());
		
		
		ArrayList<Integer> items = new ArrayList<Integer>(Arrays.asList(15, 10, 14, 12, 11, 13, 18, 16, 19, 17));
		ArrayList<Integer> itemsSorted = new ArrayList<Integer>(Arrays.asList(10,11,12,13,14,15,16,17,18,19));
		b.addAll(items);
		assertEquals(itemsSorted, b.toArrayList());
	}
	
	

}
