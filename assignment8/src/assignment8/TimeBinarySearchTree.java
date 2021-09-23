package assignment8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;


public class TimeBinarySearchTree {
	private static Random rand;
	static int timesToLoop = 50000; //times to loop for all other timing cases
	static int timesToLoop2 = 1000; //times to loop for addAll (add N case)
	static int maxN = 10000;
	static int minN = 1000;
	static int Nincrement = 500;
	static ArrayList<Integer> itemsRandom = new ArrayList<Integer>();
	static ArrayList<Integer> itemsSorted = new ArrayList<Integer>();
	
	
	public static void main(String[] args) {
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		//Allow thread to stabilize:
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		
//		System.out.println("BST contains with sorted items vs random items");
//		for(int N = minN; N <= maxN; N += Nincrement)
//		{
//			timeSearchBST(N);
//		}
		itemsRandom.clear();
		itemsSorted.clear();
		System.out.println("BST addAll vs TreeSet then BST contains vs TreeSet contains:");
		for(int N = minN; N <= maxN; N += Nincrement)
		{
			timeBSTvsTreeSet(N);
		}
	}

	//This method will time adding to BST
	private static void timeSearchBST(int n) {
		long startTime, midpointTime, stopTime;
		
		//Create values to be added beforehand, create enough to be used for biggest N value
		int val;
		while(itemsRandom.size() < n) //makes sure n items are in itemsRandom and itemsSorted
		{
			val = rand.nextInt();
			//If item is not in items then can add.  We can't have duplicates in our BST
			if(!itemsRandom.contains(val)) 
			{
				itemsRandom.add(val);
				itemsSorted.add(val);
			}
		}
		Collections.sort(itemsSorted);
		
		int lastItem = itemsSorted.get(n-1); //get last item
		BinarySearchTree<Integer> bSorted = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> bRandom = new BinarySearchTree<Integer>();
		bSorted.addAll(itemsSorted);
		bRandom.addAll(itemsRandom);
		
		//Timing code:
		//Time BST with sorted items:
		startTime = System.nanoTime();
		
		for (int i = 0; i < timesToLoop; i++)
		{
			bSorted.contains(lastItem); //We will time how long it takes to get last item
		 }

		midpointTime = System.nanoTime();

		// Run an loop that creates new object to capture the cost of the overhead
		for (long i = 0; i < timesToLoop; i++) 
		{
		}

		stopTime = System.nanoTime();

		// Subtract the cost of running the loop
		// from the cost of running the loop plus the real work.
		// Average it over the number of runs.
		double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

		System.out.print(n + "\t"+ averageTime + "\t");
		
		//Time BST with random items:
		startTime = System.nanoTime();
		
		for (int i = 0; i < timesToLoop; i++)
		{
			bRandom.contains(lastItem); //We will time how long it takes to get last item
		}

		midpointTime = System.nanoTime();

		// Run an loop that creates new object to capture the cost of the overhead
		for (long i = 0; i < timesToLoop; i++) 
		{
		}

		stopTime = System.nanoTime();

		// Subtract the cost of running the loop
		// from the cost of running the loop plus the real work.
		// Average it over the number of runs.
		averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

		System.out.println("\t"+ averageTime);


	}
	
	
	private static void timeBSTvsTreeSet(int n) {
		long startTime, midpointTime, stopTime;
		
		//Create values to be added beforehand, create enough to be used for biggest N value
		int val;
		while(itemsRandom.size() < n) //makes sure n items are in itemsRandom
		{
			val = rand.nextInt();
			//If item is not in items then can add.  We can't have duplicates in our BST
			if(!itemsRandom.contains(val)) 
			{
				itemsRandom.add(val);
				itemsSorted.add(val);
			}
			
		}
		Collections.sort(itemsSorted);

		
		BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();
		TreeSet<Integer> tSet= new TreeSet<Integer>();

		
		//Timing code:
		//Time addAll BST:
		startTime = System.nanoTime();
		
		for (int i = 0; i < timesToLoop2; i++)
		{
			BST.addAll(itemsRandom); //add n items
			BST.clear();
		}

		midpointTime = System.nanoTime();

		// Run an loop that creates new object to capture the cost of the overhead
		for (long i = 0; i < timesToLoop2; i++) 
		{
			BST.clear();
		}

		stopTime = System.nanoTime();

		// Subtract the cost of running the loop
		// from the cost of running the loop plus the real work.
		// Average it over the number of runs.
		double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop2;
		System.out.print(n + "\t"+ averageTime + "\t");
		
		//Time addAll(add N items) Treeset:
		startTime = System.nanoTime();
		
		for (int i = 0; i < timesToLoop2; i++)
		{
			tSet.addAll(itemsRandom);
			tSet.clear();
		}

		midpointTime = System.nanoTime();

		// Run an loop that creates new object to capture the cost of the overhead
		for (long i = 0; i < timesToLoop2; i++) 
		{
			tSet.clear();
		}

		stopTime = System.nanoTime();

		// Subtract the cost of running the loop
		// from the cost of running the loop plus the real work.
		// Average it over the number of runs.
		averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop2;

		System.out.print("\t"+ averageTime + "\t");
		
		//Time contains BST:
		tSet.addAll(itemsRandom);
		BST.addAll(itemsRandom); 
		int lastItem = itemsRandom.get(n-1); //get last item
		startTime = System.nanoTime();
		
		for (int i = 0; i < timesToLoop; i++)
		{
			BST.contains(lastItem);
		}

		midpointTime = System.nanoTime();

		// Run an loop that creates new object to capture the cost of the overhead
		for (long i = 0; i < timesToLoop; i++) 
		{
		}

		stopTime = System.nanoTime();

		// Subtract the cost of running the loop
		// from the cost of running the loop plus the real work.
		// Average it over the number of runs.
		averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
		System.out.print(n + "\t"+ averageTime + "\t");
		
		//Time contains Treeset:
		startTime = System.nanoTime();
		
		for (int i = 0; i < timesToLoop; i++)
		{
			tSet.contains(lastItem);
		}

		midpointTime = System.nanoTime();

		// Run an loop that creates new object to capture the cost of the overhead
		for (long i = 0; i < timesToLoop; i++) 
		{
		}

		stopTime = System.nanoTime();

		// Subtract the cost of running the loop
		// from the cost of running the loop plus the real work.
		// Average it over the number of runs.
		averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

		System.out.println("\t"+ averageTime);

	}


}
