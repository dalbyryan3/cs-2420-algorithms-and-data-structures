package assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SearchUtil { 

	/**
     * @author Ryan Dalby, and Andre Watson
	 * @param      <T> The type of elements contained in the list
	 * @param list An ArrayList to search through. This ArrayList is assumed to be
	 *             sorted according to the supplied comparator. This method has
	 *             undefined behavior if the list is not sorted.
	 * @param item The item to search for
	 * @param cmp  Comparator for comparing Ts or a super class of T
	 * @return true if "item" exists in "list", false otherwise
	 */

	public static <T> boolean binarySearch(ArrayList<T> list, T item, Comparator<? super T> cmp) {
		List<T> newList = list.subList(0, list.size());
		//Taken mostly from 1410 assignment, with modifications for comparator, generic ArrayList
		int lowIndex = 0;
		int middleIndex;
		int highIndex = newList.size() - 1;
		while (lowIndex <= highIndex) { //goes down to closest item, then checks one last time
			middleIndex = (lowIndex+highIndex)/2;
			if(cmp.compare(newList.get(middleIndex), item) == 0) { 
				return true;
			} else if (cmp.compare(newList.get(middleIndex), item) < 0) { //if item is above the middle
				lowIndex = middleIndex + 1; //ignore the lower half and start again
			} else if (cmp.compare(newList.get(middleIndex),  item) > 0) { //if item is below the middle
				highIndex = middleIndex -1; //ignore the upper half and start again
			}
		} 
		return false; //if not found 
	}
}