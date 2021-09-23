package assignment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Implementation to determine if two words are anagrams and finds the largest
 * group of anagrams in a list of words For the purposes of this code, an
 * anagram contains the same letters in the same frequency as the original word
 * 
 * @author Andre Watson and Ryan Dalby for CS 2420
 *
 */
public class AnagramUtil {

	/**
	 * Sorts a string by its characters. This is case-sensitive, and should be
	 * accomplished by insertionSort
	 * 
	 * @param s The string to be sorted
	 * @return A String whose characters have been sorted
	 */
	public static String sort(String s) {
		Character[] arrayOfString = new Character[s.length()];
		for (int i = 0; i < s.length(); i++) {
			arrayOfString[i] = (Character) s.charAt(i);
		}
		characterComparator cmp = new characterComparator();
		insertionSort(arrayOfString, cmp);
		String sortedString = "";
		for (int i = 0; i < arrayOfString.length; i++) {
			sortedString += arrayOfString[i];
		}
		return sortedString;
	}

	/**
	 * Implements a generic insertion sort 
	 * Code taken from lecture and modified for generics
	 * 
	 * @param toSort an array of type T to be sorted
	 * @param cmp    a comparator defining how to compare T
	 */
	public static <T> void insertionSort(T[] toSort, Comparator<? super T> cmp) {
		for (int i = 1; i < toSort.length; i++) {
			T toInsert = toSort[i];
			int j;
			for (j = i; j > 0; j--) {
				if (cmp.compare(toInsert, toSort[j - 1]) > 0) {
					break;
				}
				toSort[j] = toSort[j - 1];
			}
			toSort[j] = toInsert;
		}
	}

	/**
	 * Implements a generic shellsort 
	 * Code taken from lecture and modified for
	 * generics
	 * 
	 * @param toSort the array of type T to be sorted
	 * @param cmp    a comparator defining how to compare T
	 */
	public static <T> void shellSort(T[] toSort, Comparator<? super T> cmp) {
		//sourcing proper shellSort from lecture 7
		for(int gap = toSort.length/2; gap > 0; gap /= 2) {
			for(int i = gap; i < toSort.length; i++) {
				T toInsert = toSort[i];
				int j;
				for(j = i - gap; j >= 0 && (cmp.compare(toSort[j], toInsert) > 0); j -= gap ) {
					toSort[j+gap] = toSort[j];
				}
				toSort[j + gap] = toInsert;
			}
		}
	}

	/**
	 * Checks if two Strings are anagrams. Strings with different capitalization but
	 * the same letters are anagrams Does not include anagrams with added
	 * spaces/punctuation, only checks if the input strings have the same characters
	 * 
	 * @param s1 First string to compare
	 * @param s2 Second string to compare
	 * @return True if the strings are anagrams, false otherwise
	 */
	public static boolean areAnagrams(String s1, String s2) {
		String s1Lower = s1.toLowerCase();
		String s2Lower = s2.toLowerCase(); // put both strings into same case for comparison
		String s1LowerSorted = sort(s1Lower);
		String s2LowerSorted = sort(s2Lower);
		return s1LowerSorted.equals(s2LowerSorted); // check if the sorted strings are the same (they're anagrams)
	}

	/**
	 * Returns the largest group of anagrams in an array of Strings. Only groups of
	 * 2 or more (that is, words with at least one anagram) are returned
	 * 
	 * @param sArray The array of strings/words to look for anagrams in
	 * @return The largest group of anagrams, if a group of at least 2 items was
	 *         found. Otherwise, returns an empty array.
	 */
	public static String[] getLargestAnagramGroup(String[] sArray) {
		shellSort(sArray, new anagramComparator());
		ArrayList<String> largestSoFar = new ArrayList<String>();
		ArrayList<String> currentGroup = new ArrayList<String>();
		if (sArray.length > 1) { // checks that the array has at least 2 elements
			currentGroup.add(sArray[0]); // starts with the first element if so
		} else {
			return new String[0]; // returns empty array if not
		}
		for (int i = 1; i < sArray.length; i++) { // start with the second value, since we've added the first value to
													// our current group
			if (!areAnagrams(sArray[i], sArray[i - 1])) { // if they're not anagrams, the end of the group is reached
				if (currentGroup.size() > largestSoFar.size() && currentGroup.size() > 1) {
					largestSoFar.clear();
					largestSoFar.addAll(currentGroup); // only reassign if the group is both larger than the current largest
													// and has >1 word
				}
				currentGroup.clear(); // reset group every time consecutive words aren't anagrams
				currentGroup.add(sArray[i]); // add the start of the next group, compare from there

			} else {
				currentGroup.add(sArray[i]); // if they are anagrams, add it to the current group
			}

		}
		if (currentGroup.size() > largestSoFar.size() && currentGroup.size() > 1) { // checks the last group in case
			largestSoFar.clear();																		// it's larger than the others
			largestSoFar.addAll(currentGroup);
		}
		String[] largest = new String[0]; // initialize String[] to put anagrams in
		largest = largestSoFar.toArray(largest); // turn ArrayList into String[]
		return largest; // returns the largest group so far, which will be empty if no groups of 2 words
						// or more were found
		
	}
	
	

	private static class characterComparator implements Comparator<Character> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 * Compares characters using Character 's built-in compareTo
		 */
		@Override
		public int compare(Character o1, Character o2) {
			return o1.compareTo(o2);
		}
	}

	private static class anagramComparator implements Comparator<String> {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 * 
		 * Compares the sorted versions of strings, which gives anagrams equal values
		 * and will order them adjacent to each other
		 */
		@Override
		public int compare(String o1, String o2) {
			return AnagramUtil.sort(o1.toLowerCase()).compareTo(AnagramUtil.sort(o2.toLowerCase()));
		}

	}
}
