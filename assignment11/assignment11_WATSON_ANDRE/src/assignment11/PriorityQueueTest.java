package assignment11;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * JUnit testing class for PriorityQueue
 * 
 * @author Ryan Dalby and André Watson
 *
 */
class PriorityQueueTest {

	@Test
	void testBasicAdd() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(10);
		pq.add(2010);
		pq.add(-65);
		pq.add(3); 
		assertEquals((Integer) (-65), pq.findMin());
	}
	@Test
	void testAddingNull() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(10);
		pq.add(2010);
		pq.add(-65);
		pq.add(3); 
		pq.add(null); //will not add nulls
		assertEquals((Integer) (-65), pq.findMin());
	}
	
	@Test
	void testMultipleDeletions() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(10);
		pq.add(2010);
		pq.add(-65);
		pq.add(3); 
		pq.deleteMin();
		pq.deleteMin();
		pq.deleteMin();
		assertEquals((Integer) 2010 , pq.findMin());
		pq.deleteMin();
		assertThrows(NoSuchElementException.class, () -> {
			pq.findMin();
		});
		assertThrows(NoSuchElementException.class, () -> {
			pq.deleteMin();
		});
		
	}
	
	@Test
	void testDeleteOnOneItem() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(1);
		assertEquals((Integer) 1, pq.deleteMin());
	}
	
	@Test
	void testGrowing() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i =0; i < 1024; i++) {
			pq.add(i);
		}
		assertEquals(1024, pq.size());
		for(int i =0; i < 1024; i++) {
			assertEquals((Integer)i, pq.deleteMin());
		}
		assertEquals(0, pq.size());
	}
	
	@Test
	void testClear() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i =0; i < 1024; i++) {
			pq.add(i);
		}
		pq.clear();
		assertEquals(0, pq.size());
		assertThrows(NoSuchElementException.class, () -> {
			pq.findMin();
		});
		assertThrows(NoSuchElementException.class, () -> {
			pq.deleteMin();
		});
		pq.add(10); //make sure nothing's broken w/r/t overwriting "cleared" values
		assertEquals((Integer)10, pq.findMin());
		assertEquals((Integer) 10, pq.deleteMin());
	}
	
	
}
