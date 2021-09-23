package assignment5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;



public class TimeSortUtil {
	private static Random rand;
	private static int problemSize = 10000;
    private static ArrayList<Integer> test = SortUtil.generateAverageCase(problemSize); //Generates random ordered list of problem size
    private static ArrayList<Integer> testWorst = SortUtil.generateWorstCase(problemSize); //Generates random ordered list of problem size
    private static ArrayList<Integer> testBest = SortUtil.generateBestCase(problemSize); //Generates random ordered list of problem size


	public static void main(String[] args) {
		int threshold;
		int timesToLoop = 250;
		double testTime;
		
		System.out.println("Best Case:");
	    threshold = 50;
		SortUtil.setInsertionSortThreshold(threshold);
		testTime = timeTestMergesort(testBest, timesToLoop);
	    System.out.println("Mergesort Average Time: " + testTime 
		        + " ns\nProblem Size: " + problemSize  + "\nThreshold: " + threshold);
	    System.out.println();
		
		SortUtil.setPivotAlgorithm(3);
		testTime = timeTestQuicksort(testBest, timesToLoop);
	    System.out.println("Quicksort Average Time: " + testTime 
		        + " ns\nProblem Size: " + problemSize + "\nPivot Selection: median random sampled");
	    System.out.println();
	    System.out.println();

		System.out.println("Average Case:");
	    threshold = 50;
		SortUtil.setInsertionSortThreshold(threshold);
		testTime = timeTestMergesort(test, timesToLoop);
	    System.out.println("Mergesort Average Time: " + testTime 
		        + " ns\nProblem Size: " + problemSize  + "\nThreshold: " + threshold);
	    System.out.println();
		
		SortUtil.setPivotAlgorithm(2);
		testTime = timeTestQuicksort(test, timesToLoop);
	    System.out.println("Quicksort Average Time: " + testTime 
		        + " ns\nProblem Size: " + problemSize + "\nPivot Selection: first pivot");
	    System.out.println();
	    System.out.println();
	    
		System.out.println("Worst Case:");
	    threshold = 50;
		SortUtil.setInsertionSortThreshold(threshold);
		testTime = timeTestMergesort(testWorst, timesToLoop);
	    System.out.println("Mergesort Average Time: " + testTime 
		        + " ns\nProblem Size: " + problemSize  + "\nThreshold: " + threshold);
	    System.out.println();
		
		SortUtil.setPivotAlgorithm(3);
		testTime = timeTestQuicksort(testWorst, timesToLoop);
	    System.out.println("Quicksort Average Time: " + testTime 
		        + " ns\nProblem Size: " + problemSize + "\nPivot Selection: median random sampled");
	    System.out.println();
	    System.out.println();
		
//		SortUtil.setPivotAlgorithm(1);
//		testTime = timeTestQuicksort(test, timesToLoop);
//	    System.out.println("Average Time: " + testTime 
//		        + " ns\nProblem Size: " + problemSize + "\nPivot Selection: random pivot");
//	    System.out.println();
//	    
//		SortUtil.setPivotAlgorithm(2);
//		testTime = timeTestQuicksort(test, timesToLoop);
//	    System.out.println("Average Time: " + testTime 
//		        + " ns\nProblem Size: " + problemSize + "\nPivot Selection: first pivot");
//	    System.out.println();
//	    
//		SortUtil.setPivotAlgorithm(3);
//		testTime = timeTestQuicksort(test, timesToLoop);
//	    System.out.println("Average Time: " + testTime 
//		        + " ns\nProblem Size: " + problemSize + "\nPivot Selection: random sampled median");
//	    System.out.println();
		
//	    threshold = 1;
//		SortUtil.setInsertionSortThreshold(threshold);
//		testTime = timeTestMergesort(test, timesToLoop);
//	    System.out.println("Average Time: " + testTime 
//		        + " ns\nProblem Size: " + problemSize + "\nThreshold: " + threshold);
//	    System.out.println();
//	    
//	    
//	    threshold = 5;
//		SortUtil.setInsertionSortThreshold(threshold);
//		testTime = timeTestMergesort(test, timesToLoop);
//	    System.out.println("Average Time: " + testTime 
//		        + " ns\nProblem Size: " + problemSize  + "\nThreshold: " + threshold);
//	    System.out.println();
//	    
//	    
//	    threshold = 10;
//		SortUtil.setInsertionSortThreshold(threshold);
//		testTime = timeTestMergesort(test, timesToLoop);
//	    System.out.println("Average Time: " + testTime 
//		        + " ns\nProblem Size: " + problemSize + "\nThreshold: " + threshold);
//	    System.out.println();
//	    
//	    
//	    threshold = 50;
//		SortUtil.setInsertionSortThreshold(threshold);
//		testTime = timeTestMergesort(test, timesToLoop);
//	    System.out.println("Average Time: " + testTime 
//		        + " ns\nProblem Size: " + problemSize  + "\nThreshold: " + threshold);
//	    System.out.println();
//	    
//	    
//	    threshold = problemSize/2;
//		SortUtil.setInsertionSortThreshold(threshold); //threshold = n/2
//		testTime = timeTestMergesort(test, timesToLoop);
//	    System.out.println("Average Time: " + testTime 
//		        + " ns\nProblem Size: " + problemSize  + "\nThreshold: " + threshold);
//	    System.out.println();
	    

	}
	
	public static double timeTestQuicksort(ArrayList<Integer> list, int numLoops)
	{
	    long startTime, midpointTime, stopTime;
	    rand = new Random();
	    IntegerComparator cmp = new IntegerComparator();
	    ArrayList<Integer> copyList = new ArrayList<Integer>();
	    
		//Allow thread to stabilize
	    startTime = System.nanoTime();
	    while (System.nanoTime() - startTime < 1000000000) { // empty block
	    }
	    
	    //Test
	    int timesToLoop = numLoops; 
	    
	    
	    startTime = System.nanoTime();
	    
	    //Execute code to examine
	    for (int i = 0; i < timesToLoop; i++) {   
	    	resetList(copyList, list);
	    	SortUtil.quicksort(copyList, cmp);
	    }
	    midpointTime = System.nanoTime();
	    
	    // run empty loop to get the cost of running the loop and cost of reset and copy of list

	    for (int i = 0; i < timesToLoop; i++) { //empty loop
	    	resetList(copyList, list);
	    }
	    
	    stopTime = System.nanoTime();

	    // Compute the time, subtract the cost of running the loop
	    // from the cost of running the loop and computing the desired task
	    // Average it over the number of runs.

	    return (((midpointTime - startTime) - (stopTime - midpointTime))/ timesToLoop);
	}
	
	public static double timeTestMergesort(ArrayList<Integer> list, int numLoops)
	{
	    long startTime, midpointTime, stopTime;
	    rand = new Random();
	    IntegerComparator cmp = new IntegerComparator();
	    ArrayList<Integer> copyList = new ArrayList<Integer>();
	    
		//Allow thread to stabilize
	    startTime = System.nanoTime();
	    while (System.nanoTime() - startTime < 1000000000) { // empty block
	    }
	    
	    //Test
	    int timesToLoop = numLoops; 
	    
	    
	    startTime = System.nanoTime();
	    
	    //Execute code to examine
	    for (int i = 0; i < timesToLoop; i++) {   
	    	resetList(copyList, list);
	    	SortUtil.mergeSort(copyList, cmp);
	    }
	    midpointTime = System.nanoTime();
	    
	    // run empty loop to get the cost of running the loop and cost of reset and copy of list

	    for (int i = 0; i < timesToLoop; i++) { //empty loop
	    	resetList(copyList, list);
	    }
	    
	    stopTime = System.nanoTime();

	    // Compute the time, subtract the cost of running the loop
	    // from the cost of running the loop and computing the desired task
	    // Average it over the number of runs.

	    return (((midpointTime - startTime) - (stopTime - midpointTime))/ timesToLoop);
	}
	
	static void resetList(ArrayList<Integer> listToReset, ArrayList<Integer> resetList)
	{
		listToReset.clear();
		for(int i = 0; i < resetList.size(); i++)
		{
			listToReset.add(resetList.get(i));
		}
	}
	static class IntegerComparator implements Comparator<Integer>
	{

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1-o2;
		}
		
	}
	

}
