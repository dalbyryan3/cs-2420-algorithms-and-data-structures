package assignment5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.jupiter.api.Test;
/**
 * Junit testing file for SortUtil
 * @author Ryan Dalby and Andre Watson
 *
 *
 */
class TestSortUtil {

	@Test
	void mergeSortNoInsertion() {
		ArrayList<Integer> test = SortUtil.generateAverageCase(50);
		IntegerComparator cmp = new IntegerComparator();
		SortUtil.setInsertionSortThreshold(1); //Using a threshold of 1 results in no insertion sort usage
		SortUtil.mergeSort(test, cmp);
		assertEquals(true, isSorted(test, cmp));
		
	}
	@Test 
	void mergeSortWithInsertion()
	{
		ArrayList<Integer> test = SortUtil.generateAverageCase(50);
		IntegerComparator cmp = new IntegerComparator();
		SortUtil.setInsertionSortThreshold(10); 
		SortUtil.mergeSort(test, cmp);
		assertEquals(true, isSorted(test, cmp));
	}
	@Test
	void mergeSortAlreadyOrdered()
	{
		ArrayList<Integer> test = SortUtil.generateBestCase(50);
		IntegerComparator cmp = new IntegerComparator();
		SortUtil.setInsertionSortThreshold(10); 
		SortUtil.mergeSort(test, cmp);
		assertEquals(true, isSorted(test, cmp));
	}
	@Test
	void mergeSortDirectValueTest()
	{
		ArrayList<Integer> test = SortUtil.generateWorstCase(5);
		IntegerComparator cmp = new IntegerComparator();
		SortUtil.setInsertionSortThreshold(1); 
		SortUtil.mergeSort(test, cmp);
		assertEquals("1 2 3 4 5 ", IntegerListToString(test));
	}
	@Test
	void mergeSortEmpty()
	{
		ArrayList<Integer> test = new ArrayList<Integer>();
		IntegerComparator cmp = new IntegerComparator();
		SortUtil.setInsertionSortThreshold(10); 
		SortUtil.mergeSort(test, cmp);
		assertEquals(true, isSorted(test, cmp));
	}
	
	@Test
	void quicksortRandomPivot() {
		ArrayList<Integer> test = SortUtil.generateAverageCase(50);
		IntegerComparator cmp = new IntegerComparator();
		SortUtil.setPivotAlgorithm(1);
		SortUtil.quicksort(test, cmp);
		assertEquals(true, isSorted(test, cmp));
	}
	@Test
	void quicksortFirstPivot() {
		ArrayList<Integer> test = SortUtil.generateAverageCase(50);
		IntegerComparator cmp = new IntegerComparator();
		SortUtil.setPivotAlgorithm(2);
		SortUtil.quicksort(test, cmp);
		assertEquals(true, isSorted(test, cmp));
	}
	
	@Test
	void quicksortMedianSampledPivot() {
		ArrayList<Integer> test = SortUtil.generateAverageCase(10);
		IntegerComparator cmp = new IntegerComparator();
		SortUtil.setPivotAlgorithm(3);
		SortUtil.quicksort(test, cmp);
		assertEquals(true, isSorted(test, cmp));
	}
	@Test
	void quicksortEmpty() {
		ArrayList<Integer> test = new ArrayList<Integer>();
		IntegerComparator cmp = new IntegerComparator();
		SortUtil.setPivotAlgorithm(3);
		SortUtil.quicksort(test, cmp);
		assertEquals(true, isSorted(test, cmp));
	}
	@Test
	void quicksortAlreadyOrdered() {
		ArrayList<Integer> test = SortUtil.generateBestCase(50);
		IntegerComparator cmp = new IntegerComparator();
		SortUtil.setPivotAlgorithm(3);
		SortUtil.quicksort(test, cmp);
		assertEquals(true, isSorted(test, cmp));
	}
	@Test
	void quicksortDirectValueTest() {
		ArrayList<Integer> test = SortUtil.generateWorstCase(10);
		IntegerComparator cmp = new IntegerComparator();
		SortUtil.setPivotAlgorithm(3);
		SortUtil.quicksort(test, cmp);
		assertEquals("1 2 3 4 5 6 7 8 9 10 ", IntegerListToString(test));
	}
	@Test
	void testGenerateBestCase()
	{
		IntegerComparator cmp = new IntegerComparator();
		assertEquals(true, isSorted(SortUtil.generateBestCase(10), cmp));
	}
	@Test
	void testGenerateWorstCase()
	{
		IntegerComparator cmp = new IntegerComparator();
		assertEquals((Integer)10, SortUtil.generateWorstCase(10).get(0));
	}
	
	
	
	
	//Helper Methods and Comparators:
	
	/**
	 * Helper method to test if list is sorted in ascending order
	 * @param list list to check if sorted
	 * @param cmp comparator of type T
	 * @return true if sorted properly
	 */
	private static <T> boolean isSorted(ArrayList<T> list, Comparator<? super T> cmp)
	{
		for(int i = 0; i < list.size()-1; i++)
		{
			if(cmp.compare(list.get(i), list.get(i+1)) > 0)
			{
				return false;
			}
		}
		return true;
		
	}
	public static String IntegerListToString(ArrayList<Integer> list)
	{
		String text = "";
		for(int i = 0; i < list.size(); i++)
		{
			text += list.get(i);
			text += " ";
		}
		return text;
	}
	
	
	class IntegerComparator implements Comparator<Integer>
	{

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1-o2;
		}
		
	}
	

}
