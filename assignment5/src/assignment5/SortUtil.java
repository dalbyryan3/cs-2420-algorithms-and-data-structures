package assignment5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
/**
 * Implementation of recursive mergesort(with ability to switch to insertion sort at a threshold)
 * and quicksort(with ability to switch between 3 different pivot selection methods)
 * These sorts only work on generic ArrayLists
 * @author Ryan Dalby and Andre Watson
 *
 *
 */
public class SortUtil {
	
	private static int insertionSortThreshold = 1; //Will define when mergeSort will switch to insertion sort to finish the sorting process
	private static int pivotAlgorithm; //1 is random pivot, 2 is first pivot, 3 is median sampled pivot
	
	/**
	 * Driver method for mergeSort
	 * @param list original list to be sorted
	 * @param cmp comparator for type T
	 */
	public static <T> void mergeSort(ArrayList<T> list, Comparator<? super T> cmp)
	{
		ArrayList<T> temp = new ArrayList<T>(list.size()); //Insure capacity of temp 
		for(int i = 0; i < list.size(); i++) //Add nulls to insure size of temp
		{
			temp.add(null);
		}
		
		mergeSortRecursive(list, temp, cmp, 0, list.size()-1);
		
	}
	/**
	 * Main implementation of mergeSort that switches to insertion sort once split arrays reach insertionSortThreshold size, then uses merge to combine all the smaller arrays
	 * @param list original list to be sorted
	 * @param temp temp array to hold sorted values when merge is used
	 * @param cmp comparator for type T
	 * @param start start index that defines subarray
	 * @param end end index that defines subarray
	 */
	private static <T> void mergeSortRecursive(ArrayList<T> list, ArrayList<T> temp, Comparator<? super T> cmp, int start, int end)
	{
		if((end-start) <= insertionSortThreshold)  //Merge sort with insertionSortThreshold, base case depends on value of insertionSortThreshold
		{
			insertionSort(list, cmp, start, end);
			return;
		}
//		if(start >= end) //Typical mergesort, base case when partitions are size 1
//		{
//			return;
//		}
		
		int mid = start + ((end - start)/2);
		
		mergeSortRecursive(list, temp, cmp, start, mid); //left subarray
		mergeSortRecursive(list, temp, cmp, mid+1, end); //right subarray
		merge(list, temp, cmp, start, mid, end); //merge halves	
		
	}
	
	/**
	 * Insertion sort to be used in our mergeSort
	 * @param list original list to be sorted
	 * @param cmp comparator for type T
	 * @param start start index that defines subarray
	 * @param end end index that defines subarray
	 */
	private static <T> void insertionSort(ArrayList<T> list, Comparator<? super T> cmp, int start, int end) {
		for(int i = start; i <= end; i++)
		{
			T toInsert = list.get(i);
			int j;
			for(j = i; j > start; j--)
			{
				if(cmp.compare(toInsert, list.get(j-1)) > 0)
				{
					break;
				}
				list.set(j, list.get(j-1));
			}
			list.set(j, toInsert);			
		}
		
	}
	/**
	 * Merges two sorted arrays defined by start to mid and mid to end into one sorted array in temp then copies back to orginal array
	 * @param list original list to be sorted
	 * @param temp temp array to hold sorted values when merge is used
	 * @param cmp comparator for type T
	 * @param start start index that defines first sub array
	 * @param mid end index that defines first sub array and mid+1 defines start of second sub array
	 * @param end end index that defines second subarray
	 */
	private static <T> void merge(ArrayList<T> list, ArrayList<T> temp, Comparator<? super T> cmp, int start, int mid, int end)
	{
		//Sub array 1 is [start, mid]
		//Sub array 2 is [mid+1, end]
		int i1 = start; 
		int i2 = mid + 1;
		int insertPos = start; //Spot in temp where value goes
		while(i1<=mid && i2<=end)
		{
			if(cmp.compare(list.get(i1), list.get(i2)) >= 0) //This will handle case of equal values
			{
				temp.set(insertPos, list.get(i2)); //smaller goes at start index of temp, then next time it gets here will go start index of temp+1(keep track of this with insertPos)
				i2++;
				insertPos++;
			}
			else if(cmp.compare(list.get(i1), list.get(i2)) < 0)
			{
				temp.set(insertPos, list.get(i1));
				i1++;
				insertPos++;
			}
		} 
		while(i1 <= mid) //i1 never reached mid(end of subarray 1) so copy rest to end of temp
		{
			temp.set(insertPos, list.get(i1));
			i1++;
			insertPos++;
		}
		while(i2 <= end) //i2 never reached end(end of subarray 2) so copy rest to end of temp
		{
			temp.set(insertPos, list.get(i2));
			i2++;
			insertPos++;
		}
		for(int i = start; i <= end; i++) //copy values from temp back into list
		{
			list.set(i, temp.get(i));
		}
	}
	
	
	
	
	/**
	 * Driver method for quicksort
	 * @param list original list to be sorted
	 * @param cmp comparator for type T
	 */
	public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> cmp)
	{
		quicksortRecursive(list, cmp, 0, list.size()-1);
	}
	
	/**
	 * Main implementation of quicksort
	 * @param list original list to be sorted
	 * @param cmp comparator for type T
	 * @param start beginning index of partition
	 * @param end ending index of partition
	 */
	private static <T> void quicksortRecursive(ArrayList<T> list, Comparator<? super T> cmp, int start, int end)
	{
		if(end<= start) //base case
		{
			return;
		}
		
		int pivotOriginalIndex;
		switch(pivotAlgorithm) //Chooses pivot selection method
		{
			case 1: pivotOriginalIndex = randomPivot(start, end);
					break;
			case 2: pivotOriginalIndex = firstPivot(start);
					break;
			default: pivotOriginalIndex = medianSampledPivot(list, cmp, start, end);
					break;
		}
		
		T pivotVal = list.get(pivotOriginalIndex);
		swapValues(list, pivotOriginalIndex, end); //Swap pivot with end
		
		int L = start;
		int R = end-1; 
		while(L <= R) //While left and right haven't crossed
		{
			while(L < end && cmp.compare(list.get(L), pivotVal) <= 0)
			{
				L++; //Increase L until it finds inversion with pivot
			}
			while(R >= start && cmp.compare(list.get(R), pivotVal) >= 0)
			{
				R--; //Decrease R until it finds swapping partner for L
			}
			if(L < R) //Check prevents a swap after they cross
			{
				swapValues(list, L, R);
			}
		}
		swapValues(list, L, end); // we get all things larger than pivot on right and all things smaller on left
		
		quicksortRecursive(list, cmp, start, L-1); //Uses L because it is where pivot was put back
		quicksortRecursive(list, cmp, L+1, end);

	}
	
	/**
	 * Will swap values at index1 and index2
	 * @param list list that contains values to be swapped
	 * @param swapIndex1 index of first item to be swapped
	 * @param swapIndex2 index of second item to be swapped
	 */
	private static <T> void swapValues(ArrayList<T> list, int swapIndex1, int swapIndex2)
	{
		T temp = list.get(swapIndex1);
		list.set(swapIndex1, list.get(swapIndex2));
		list.set(swapIndex2, temp);
	}
	
	/**
	 * Generates random pivot in a partition from start to end
	 * @param start start index of partition in list
	 * @param end end index of partition in list
	 * @return index of pivot
	 */
	private static int randomPivot(int start, int end)
	{
		Random rand = new Random();
		return start + rand.nextInt(end-start);
	}
	/**
	 * Returns first pivot index
	 * @param start index
	 * @return start index
	 */
	private static int firstPivot(int start)
	{
		return start;
	}
	
	/**
	 * Return the index of the median of 5 randomly selected values inside of the partition as indicated by start and end
	 * @param list original list 
	 * @param cmp comparator for type T
	 * @param start start index of partition in list
	 * @param end end index of partition in list
	 * @return index of pivot
	 */
	private static <T> int medianSampledPivot(ArrayList<T> list, Comparator<? super T> cmp, int start, int end)
	{
		Random rand = new Random();
		int[] samples = new int[5];
		for(int i = 0; i < 5; i++) //Samples 5 times
		{
			samples[i] = start + rand.nextInt(end-start);//Will get a random index between start and end
		}
		
		//Implemented new insertion sort because we did not want to create a comparator that needed the original array
		for(int i = 1; i < 5; i++) //We will perform an insertion sort that compares the values associated with the indices and yet will swap the indices
		{
			int toInsert = samples[i];
			int j;
			for(j = i; j > 0; j--)
			{
				if(cmp.compare(list.get(toInsert), list.get(samples[j-1])) > 0)
				{
					break;
				}
				samples[j] = samples[j-1];
			}
			samples[j] = toInsert;			
		}		
		return samples[2]; // returns median index
	}
	

	
	
	
	/**
	 * Generates an ArrayList of Integers ordered from 1 to size consecutively
	 * @param size defines size of best case to be generated
	 * @return an ArrayList of consecutive integers from 1 to size
	 */
	public static ArrayList<Integer> generateBestCase(int size)
	{
		ArrayList<Integer> bestCase = new ArrayList<Integer>();
		for(int i = 0; i < size; i++)
		{
			bestCase.add(i+1);
		}
		return bestCase;
		
	}
	
	/**
	 * Generates an ArrayList of Integers ordered from 1 to size in random order
	 * @param size defines size of average case to be generated
	 * @return an ArrayList of integers from 1 to size in random order
	 */
	public static ArrayList<Integer> generateAverageCase(int size)
	{
		Random rand = new Random();
		ArrayList<Integer> averageCase = new ArrayList<Integer>(); 
		int timesToSwap = size;
		int temp; //to temporarily hold value when swapping
		int randIndex1;//random index holder 1
		int randIndex2;//random index holder 1

		
		for(int i = 0; i < size; i++)
		{
			averageCase.add(i+1);
		}
		for(int i = 0; i < timesToSwap; i++) 
		{
			randIndex1 = rand.nextInt(size);
			randIndex2 = rand.nextInt(size);
			temp = averageCase.get(randIndex1);
			averageCase.set(randIndex1, averageCase.get(randIndex2));
			averageCase.set(randIndex2, temp);
		}
		return averageCase;
	}
	
	/**
	 * Generates an ArrayList of Integers ordered from size to 1
	 * @param size defines size of worst case to be generated
	 * @return an ArrayList of integers from size to 1
	 */
	public static ArrayList<Integer> generateWorstCase(int size)
	{
		ArrayList<Integer> worstCase = new ArrayList<Integer>();
		for(int i = size; i > 0; i--)
		{
			worstCase.add(i);
		}
		return worstCase;
	}
	
	/**
	 * Setter for insertionSortThreshold
	 * @param threshold threshold for insertionSortThreshold to be set to
	 */
	public static void setInsertionSortThreshold(int threshold)
	{
		insertionSortThreshold = threshold;
	}
	
	/**
	 * Setter for pivotAlgorithm
	 * @param selection 1 is random pivot, 2 is first pivot, 3 is median sampled pivot
	 */
	public static void setPivotAlgorithm(int selection)
	{
		pivotAlgorithm = selection;
	}


}
