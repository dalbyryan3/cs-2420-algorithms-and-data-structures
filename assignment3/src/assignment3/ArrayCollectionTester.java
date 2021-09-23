package assignment3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Testing class for ArrayCollection and also SearchUtil
 * @author Ryan Dalby, and Andre Watson
 *
 */
class ArrayCollectionTester {

	@Test
	void testAdd()
	{
	ArrayCollection<Integer> test = new ArrayCollection<Integer>();	
	assertEquals(true, test.add(5)); //makes sure we can add
	}
	
	@Test
	void testAddTwice() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(5);
		assertEquals(false, test.add(5)); //makes sure we can't add same thing twice
	}
	@Test
	void testGrow() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		for(int i =0; i<100; i++)
		{
			test.add(i);
		}
		assertEquals(100,test.size());
	}
	@Test 
	void testAddElement() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(5);
		assertEquals((Integer) 5, test.iterator().next()); //makes sure we added the correct element
	}
	
	@Test
	void testAddAll() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		ArrayCollection<Integer> toAdd = new ArrayCollection<Integer>();
		toAdd.add(9);
		toAdd.add(2);
		toAdd.add(-10);
		boolean t = test.addAll(toAdd);
		assertEquals(true, t); //check if any were added 
	}
	@Test
	void testAddAllElements() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		ArrayCollection<Integer> toAdd = new ArrayCollection<Integer>();
		toAdd.add(5);
		toAdd.add(8);
		test.addAll(toAdd);
		Iterator<Integer> testItr = test.iterator();
		testItr.next(); //goes over first element
		assertEquals((Integer) 8, testItr.next()); //makes sure last element we added the correct element
	}
	@Test
	void testAddAllDuplicates() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(5);
		test.add(8);
		ArrayCollection<Integer> toAdd = new ArrayCollection<Integer>();
		toAdd.add(5);
		toAdd.add(8);
		assertEquals(false, test.addAll(toAdd)); //check if none were added 
	}
	@Test
	void testSize() {
			ArrayCollection<Integer> test = new ArrayCollection<Integer>();
			test.add(9);
			test.add(2);
			test.add(-10);
			assertEquals(3, test.size()); //checks that ArrayCollection is right size
	}
	@Test
	void testClear() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.clear();
		assertEquals(0, test.size()); //checks our ArrayCollection is empty
	}
	@Test
	void testContainsTrue() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(8);
		assertEquals(true, test.contains(8));
		
	}
	@Test
	void testContainsFalse() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(18);
		assertEquals(false, test.contains(8));
		
	}
	@Test
	void testContainsEmpty() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		assertEquals(false, test.contains(8));
	}
	@Test
	void testContainsAllTrue() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		ArrayCollection<Integer> checkAll = new ArrayCollection<Integer>();
		test.add(9);
		test.add(2);
		test.add(-10);
		test.add(89);
		test.add(25);
		checkAll.add(-10);
		checkAll.add(89);
		assertEquals(true, test.containsAll(checkAll));
	}
	@Test
	void testContainsAllSomeFalse() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		ArrayCollection<Integer> checkAll = new ArrayCollection<Integer>();
		test.add(9);
		test.add(2);
		test.add(-10);
		test.add(89);
		test.add(25);
		checkAll.add(-10);
		checkAll.add(5);
		assertEquals(false, test.containsAll(checkAll));
	}
	@Test
	void testContainsAllFalse() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		ArrayCollection<Integer> checkAll = new ArrayCollection<Integer>();
		test.add(9);
		test.add(2);
		test.add(-10);
		test.add(89);
		test.add(25);
		checkAll.add(-9);
		checkAll.add(5);
		assertEquals(false, test.containsAll(checkAll));
	}
	@Test
	void testContainsAllEmpty() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		ArrayCollection<Integer> checkAll = new ArrayCollection<Integer>();
		test.add(9);
		test.add(2);
		test.add(-10);
		test.add(89);
		test.add(25);
		assertEquals(false, test.containsAll(checkAll));
	}
	@Test
	void testIsEmptyTrue() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		assertEquals(true, test.isEmpty());
	}
	@Test
	void testIsEmptyFalse() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(5);
		assertEquals(false, test.isEmpty());
	}
	@Test
	void testRemoveNone()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(9);
		test.add(2);
		test.add(-10);
		test.add(89);
		test.add(25);
		assertEquals(false, test.remove(5));
	}
	@Test
	void testRemoveTrue()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(9);
		test.add(2);
		test.add(-10);
		test.add(89);
		test.add(25);
		assertEquals(true, test.remove(89));
	}
	@Test
	void testRemoveShifted()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(9);
		test.add(2);
		test.add(-10);
		test.add(89);
		test.add(25);
		test.remove(9);
		assertEquals((Integer)2, test.iterator().next()); //checks 2 is in first slot
	}
	@Test
	void testRetainAllTrue()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		ArrayCollection<Integer> toRetain = new ArrayCollection<Integer>();
		test.add(9);
		test.add(2);
		test.add(-10);
		toRetain.add(9);
		toRetain.add(-10);
		assertEquals(true, test.retainAll(toRetain)); //should remove some items
	}
	@Test
	void testRetainAllFalse()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		ArrayCollection<Integer> toRetain = new ArrayCollection<Integer>();
		test.add(9);
		test.add(-10);
		toRetain.add(9);
		toRetain.add(-10);
		assertEquals(false, test.retainAll(toRetain)); //should retain all items, none will be removed
	}
	@Test
	void testRetainAllValues()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		ArrayCollection<Integer> toRetain = new ArrayCollection<Integer>();
		test.add(9);
		test.add(-10);
		toRetain.add(-10);
		test.retainAll(toRetain);
		assertEquals((Integer)(-10), test.iterator().next()); //checks that correct value was retained
	}
	@Test
	void testToArrayValues()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(9);
		test.add(-10);
		Object[] testArr = test.toArray();
		assertEquals((Integer)(-10), testArr[1]);
	}
	@Test
	void testToArraySize()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(9);
		test.add(-10);
		Object[] testArr = test.toArray();
		assertEquals(2, testArr.length);
	}
	@Test
	void testToArrayEmpty()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		Object[] testArr = test.toArray();
		assertEquals(0, testArr.length);
	}
	
	
	
	//Iterator tests
	@Test
	void testHasNextFalseEmpty()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		Iterator itr = test.iterator();
		assertEquals(false, itr.hasNext());
		
	}
	@Test
	void testHasNextTrue()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(9);
		test.add(-10);
		Iterator itr = test.iterator();
		assertEquals(true, itr.hasNext());
		
	}
	@Test
	void testHasNextFalseAtEnd()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(9);
		test.add(-10);
		Iterator itr = test.iterator();
		itr.next();
		itr.next();
		assertEquals(false, itr.hasNext());
	}
	
	@Test
	void testNext()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(9);
		test.add(-10);
		Iterator itr = test.iterator();
		itr.next();
		assertEquals((Integer)(-10), itr.next());
	}
	void testNextThrows()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		Iterator itr = test.iterator();
		assertThrows(NoSuchElementException.class, ()->{
		itr.next();
		});
	}
	
	@Test
	void testIteratorRemoveWithoutNext() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		Iterator itr = test.iterator();
		assertThrows(IllegalStateException.class, ()->{
		itr.remove();
		});
		
	}
	@Test
	void testIteratorRemoveTwice() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(5);
		test.add(7);
		Iterator itr = test.iterator();
		itr.next();
		itr.remove();
		assertThrows(IllegalStateException.class, ()->{
		itr.remove();
		});
		
	}
	@Test
	void testNextAfterRemove() {
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(5);
		test.add(7);
		Iterator itr = test.iterator();
		itr.next();
		itr.remove();
		assertEquals(7, itr.next());
	}
	
	
	//SearchUtil and toSortedList
	protected class IntegerComparator implements Comparator<Integer>
	{

		@Override
		public int compare(Integer o1, Integer o2) {
			return (o1 - o2);
		}
		
	}
	protected class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String o1, String o2) {
			return (o1.compareTo(o2));
		}
		
	}
	protected class CharacterComparator implements Comparator<Character>
	{

		@Override
		public int compare(Character o1, Character o2) {
			return (o1.compareTo(o2));
		}
		
	}
	@Test
	void testToSortedListInteger()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(9);
		test.add(2);
		test.add(-10);
		test.add(89);
		test.add(25);
		ArrayList<Integer> sorted = test.toSortedList(new IntegerComparator());
		assertEquals((Integer)(-10), sorted.get(0));
	}
	@Test
	void testToSortedListString()
	{
		ArrayCollection<String> test = new ArrayCollection<String>();
		test.add("zebra");
		test.add("vampire");
		test.add("cat");
		test.add("dog");
		test.add("person");
		ArrayList<String> sorted = test.toSortedList(new StringComparator());
		assertEquals("cat", sorted.get(0));
	}
	@Test
	void testToSortedListCharacter()
	{
		ArrayCollection<Character> test = new ArrayCollection<Character>();
		test.add('d');
		test.add('b');
		test.add('o');
		test.add('y');
		test.add('u');
		ArrayList<Character> sorted = test.toSortedList(new CharacterComparator());
		assertEquals((Character)'b', sorted.get(0));
	}
	@Test
	void testBinarySearchIntegerCompTrue()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(9);
		test.add(2);
		test.add(-10);
		test.add(89);
		test.add(25);
		ArrayList<Integer> sorted = test.toSortedList(new IntegerComparator());
		assertEquals(true, SearchUtil.binarySearch(sorted, 89, new IntegerComparator()));
		
	}
	@Test
	void testBinarySearchIntegerCompFalse()
	{
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		test.add(9);
		test.add(2);
		test.add(-10);
		test.add(89);
		test.add(25);
		ArrayList<Integer> sorted = test.toSortedList(new IntegerComparator());
		assertEquals(false, SearchUtil.binarySearch(sorted, 512, new IntegerComparator()));
		
	}
	@Test
	void testBinarySearchStringCompTrue()
	{
		ArrayCollection<String> test = new ArrayCollection<String>();
		test.add("zebra");
		test.add("vampire");
		test.add("cat");
		test.add("dog");
		test.add("person");
		ArrayList<String> sorted = test.toSortedList(new StringComparator());
		assertEquals(true, SearchUtil.binarySearch(sorted, "zebra", new StringComparator()));
		
	}
	@Test
	void testBinarySearchStringCompFalse()
	{
		ArrayCollection<String> test = new ArrayCollection<String>();
		test.add("zebra");
		test.add("vampire");
		test.add("cat");
		test.add("dog");
		test.add("person");
		ArrayList<String> sorted = test.toSortedList(new StringComparator());
		assertEquals(false, SearchUtil.binarySearch(sorted, "test", new StringComparator()));
		
	}
	@Test
	void testBinarySearchCharacterCompTrue()
	{
		ArrayCollection<Character> test = new ArrayCollection<Character>();
		test.add('d');
		test.add('b');
		test.add('o');
		test.add('y');
		test.add('u');
		ArrayList<Character> sorted = test.toSortedList(new CharacterComparator());
		assertEquals(true, SearchUtil.binarySearch(sorted, 'b', new CharacterComparator()));
		
	}
	@Test
	void testBinarySearchCharacterCompFalse()
	{
		ArrayCollection<Character> test = new ArrayCollection<Character>();
		test.add('d');
		test.add('b');
		test.add('o');
		test.add('y');
		test.add('u');
		ArrayList<Character> sorted = test.toSortedList(new CharacterComparator());
		assertEquals(false, SearchUtil.binarySearch(sorted, ' ', new CharacterComparator()));
		
	}
	
	
	
	


}
