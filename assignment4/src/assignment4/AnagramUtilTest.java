package assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

/**
 * Testing class for AnagramUtil
 * @author Andre Watson and Ryan Dalby
 *
 */
class AnagramUtilTest {

	@Test
	void sortPerformsCorrectly() {
		String toSort = AnagramUtil.sort("cockroach");
		assertEquals("accchkoor", toSort);
	}
	@Test
	void sortEmptyString() {
		assertEquals("", AnagramUtil.sort(""));
	}
	@Test
	void sortPerformsCorrectlyWithCase() {
		String toSort = AnagramUtil.sort("AbCd");
		assertEquals("ACbd", toSort);
	}
	@Test
	void insertionSortWorks() {
		Integer[] insertionTest = new Integer[] {0,2,-36,46,5,-10000};
		AnagramUtil.insertionSort(insertionTest, new IntegerComparator());
		assertEquals((Integer)46, insertionTest[5]);
	}
	@Test
	void shellSortWorks() {
		Integer[] shellTest = new Integer[] {0,2,-36,46,5,-10000};
		AnagramUtil.shellSort(shellTest, new IntegerComparator());
		assertEquals((Integer)46, shellTest[5]);
	}
	
	@Test
	void shellSortEmpty() {
		Integer[] shellTest = new Integer[] {};
		AnagramUtil.shellSort(shellTest, new IntegerComparator());
		assertEquals(0, shellTest.length);
	}
	@Test
	void shellSortOne() {
		Integer[] shellTest = new Integer[] {5};
		AnagramUtil.shellSort(shellTest, new IntegerComparator());
		assertEquals((Integer)5, shellTest[0]);
	}
	@Test
	void testAreAnagramsBasicTrue() {
		assertEquals(true, AnagramUtil.areAnagrams("taste", "state"));
	}
	@Test
	void testAreAnagramsBasicFalse() {
		assertEquals(false, AnagramUtil.areAnagrams("taste", "dog"));
	}
	@Test
	void testAreAnagramsWithCaseTrue() {
		assertEquals(true, AnagramUtil.areAnagrams("tAsTe", "StatE"));
	}
	@Test
	void testAreAnagramsWithCaseFalse() {
		assertEquals(false, AnagramUtil.areAnagrams("tAsTe", "ATeee"));
	}
	@Test
	void LargestAnagramGroupEmptyList() {
		String[] empty = new String[0];
		assertEquals(0, AnagramUtil.getLargestAnagramGroup(empty).length);
	}
	@Test
	void LargestAnagramGroupListOfOne() {
		String[] empty = new String[] {"String"};
		assertEquals(0, AnagramUtil.getLargestAnagramGroup(empty).length);
	}
	@Test
	void LargestAnagramGroupAllDifferent() {
		String[] allDifferent = new String[] {"car","taste","education","palindrome","anagram","lecture","bat"};
		assertEquals(0, AnagramUtil.getLargestAnagramGroup(allDifferent).length);
	}
	@Test
	void LargestAnagramGroupLargestAtEnd() {
		String[] toSearch = new String[] {"car","taste","education","palindrome","anagram","restful","fluster"};
		String[] largeGroup = AnagramUtil.getLargestAnagramGroup(toSearch);
		assertEquals("restful fluster ", stringArrayToStringHelper(largeGroup));
	}
	@Test
	void LargestAnagramGroupLargestInMiddle() {
		String[] words = AnagramTester.readFile("sample_word_list.txt");
		String[] largeGroup = AnagramUtil.getLargestAnagramGroup(words);
		assertEquals("Reacts Caters carets traces crates recast caster " , stringArrayToStringHelper(largeGroup));
	}
	@Test
	void LargestAnagramGroupLargestAtBeginning() {
		String[] toSearch = new String[] {"caaaar", "aaaarc", "taste","education","palindrome","anagram","restful","no"};
		String[] largeGroup = AnagramUtil.getLargestAnagramGroup(toSearch);
		assertEquals("caaaar aaaarc ", stringArrayToStringHelper(largeGroup));
	}
	@Test
	void LargestAnagramGroupWithCase() {
		String[] toSearch = new String[] {"CaR","taste","edUcation","palINdroMe","anagram","rEstFul","fLUsteR"};
		String[] largeGroup = AnagramUtil.getLargestAnagramGroup(toSearch);
		assertEquals("rEstFul fLUsteR ", stringArrayToStringHelper(largeGroup));
	}
	
	@Test
	void LargestAnagramGroupWithModerateList() {
		String [] words = AnagramTester.readFile("moderate_word_list.txt");
		String[] largeGroup = AnagramUtil.getLargestAnagramGroup(words);
		assertEquals(2, largeGroup.length);
	}
	
	
	
	//to use in testing both sorts directly
	protected class IntegerComparator implements Comparator<Integer>
	{

		@Override
		public int compare(Integer o1, Integer o2) {
			return (o1 - o2);
		}
	}
	
	/**
	 * Puts string arrays into a more convenient form for testing known orders
	 * 
	 * @param s the String[] to represent as a string
	 * @return A string containing each word in the array followed by a space
	 */
	private static String stringArrayToStringHelper(String[] s) {
		String representation = "";
		for(String string: s) {
			representation += (string + " ");
		}
		return representation;
	}

}
