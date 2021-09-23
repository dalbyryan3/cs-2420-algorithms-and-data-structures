package assignment3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class TimeArrayCollection { 
	private static Random rand;
	public static void main(String[] args)
	{
	    long startTime, midpointTime, stopTime;
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());
			
		//Allow thread to stabilize

	    startTime = System.nanoTime();
	    while (System.nanoTime() - startTime < 1000000000) { // empty block
	    }
	    
	    //Test
	    int timesToLoop = 5000; 
	    int problemSize = 1000;
	    Comparator<Integer> intComp = new IntegerComparator();
		ArrayCollection<Integer> test = new ArrayCollection<Integer>();
		ArrayList<Integer> randList = new ArrayList<Integer>();
		int randNum;
		while(test.size() < problemSize)
		{
			randNum = rand.nextInt();
			if(test.add(randNum)){
				randList.add(randNum);
			}

		}
    	ArrayList<Integer> testSorted = test.toSortedList(intComp);


	    startTime = System.nanoTime();
	    
	    //Execute code to examine
	    for (int i = 0; i < timesToLoop; i++) {
//	    	test.toSortedList(intComp);
	    	
	    	SearchUtil.binarySearch(testSorted,randList.get(0), intComp);
	    }
	    midpointTime = System.nanoTime();
	    
	    // run empty loop to get the cost of running the loop

	    for (int i = 0; i < timesToLoop; i++) { //empty loop
	    }
	    
	    stopTime = System.nanoTime();

	    // Compute the time, subtract the cost of running the loop
	    // from the cost of running the loop and computing the desired task
	    // Average it over the number of runs.

	    double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
	        / timesToLoop;

//	    System.out.println("It takes exactly " + averageTime
//	        + " nanoseconds to sort a problem size " + problemSize);
	    System.out.println("It takes exactly " + averageTime
		        + " nanoseconds to find something using binary search in a problem size " + problemSize);

	    
	    //Test Contains:
	    startTime = System.nanoTime();
	    while (System.nanoTime() - startTime < 1000000000) { // empty block
	    }
	    
	    //Test

	    startTime = System.nanoTime();
	    
	    //Execute code to examine
	    for (int i = 0; i < timesToLoop; i++) {	    	
	    	test.contains(randList.get(rand.nextInt(problemSize)));
	    }
	    midpointTime = System.nanoTime();
	    
	    // run empty loop to get the cost of running the loop

	    for (int i = 0; i < timesToLoop; i++) { //empty loop
	    }
	    
	    stopTime = System.nanoTime();

	    // Compute the time, subtract the cost of running the loop
	    // from the cost of running the loop and computing the desired task
	    // Average it over the number of runs.

	    averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
	        / timesToLoop;

//	    System.out.println("It takes exactly " + averageTime
//	        + " nanoseconds to sort a problem size " + problemSize);
	    System.out.println("It takes exactly " + averageTime
		        + " nanoseconds to find something using contains in a problem size " + problemSize);

	    
	    
	}

	

	public static Integer randomInt()
	{
		return rand.nextInt();
	}

}
