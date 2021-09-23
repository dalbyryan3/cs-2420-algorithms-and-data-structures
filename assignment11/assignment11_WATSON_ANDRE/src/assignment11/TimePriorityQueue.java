package assignment11;

import java.util.ArrayList;
import java.util.Random;

public class TimePriorityQueue {
	private static Random rand = new Random();

	public static void main(String[] args) {
		for(int i = 500; i <= 10000; i = i + 500)
		{
			System.out.print(i + "\t");
			timeAddN(i);
//			timeFindNMinItems(i);
//			timeDeleteNMinItems(i);
		}
	}
	
	private static void timeAddN(int N)
	{
		int timesToLoop = 100;
		long startTime, midpointTime, stopTime;
		double averageTime;
		ArrayList<Integer> items = new ArrayList<Integer>();
		
		for(int i = 0; i < N; i++)
		{
			items.add(rand.nextInt());
		}

		//Allow thread to stabilize:
		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		
		startTime = System.nanoTime();

		for(int i = 0; i < timesToLoop; i ++)
		{
			PriorityQueue<Integer> test = new PriorityQueue<Integer>();
			for(int j = 0; j < N; j++)
			{
				test.add(items.get(j));
			}
		}
		
		midpointTime = System.nanoTime();
		
		for(int i = 0; i < timesToLoop; i ++)
		{
			PriorityQueue<Integer> test = new PriorityQueue<Integer>();
			for(int j = 0; j < N; j++)
			{
				items.get(j);
			}
		}

		stopTime = System.nanoTime();
		
		// Subtract the cost of running the loop
		// from the cost of running the loop plus the real work.
		// Average it over the number of runs.
		averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop; 
		System.out.println(averageTime + "\t" + (averageTime/N) + "\t");


	}
	
	private static void timeFindNMinItems(int N)
	{
		int timesToLoop = 5000000;
		long startTime, midpointTime, stopTime;
		double averageTime;
		ArrayList<Integer> items = new ArrayList<Integer>();
		
		for(int i = 0; i < N; i++)
		{
			items.add(rand.nextInt());
		}

		//Allow thread to stabilize:
		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		
		startTime = System.nanoTime();

		for(int i = 0; i < timesToLoop; i ++)
		{
			PriorityQueue<Integer> test = new PriorityQueue<Integer>();
			test.add(items.get(0));
			for(int j = 0; j < N; j++)
			{
				test.findMin();
			}
		}
		
		midpointTime = System.nanoTime();
		
		for(int i = 0; i < timesToLoop; i ++)
		{
			PriorityQueue<Integer> test = new PriorityQueue<Integer>();
			test.add(items.get(0));
			for(int j = 0; j < N; j++)
			{
			}
		}

		stopTime = System.nanoTime();
		
		// Subtract the cost of running the loop
		// from the cost of running the loop plus the real work.
		// Average it over the number of runs.
		averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop; 
		System.out.println(averageTime + "\t" + (averageTime/N) + "\t");


		
	}
	
	private static void timeDeleteNMinItems(int N)
	{
		int timesToLoop = 5000;
		long startTime, midpointTime, stopTime;
		double averageTime;
		ArrayList<Integer> items = new ArrayList<Integer>();
		
		for(int i = 0; i < N; i++)
		{
			items.add(rand.nextInt());
		}

		//Allow thread to stabilize:
		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		
		startTime = System.nanoTime();

		for(int i = 0; i < timesToLoop; i ++)
		{
			PriorityQueue<Integer> test = new PriorityQueue<Integer>();
			for(int j = 0; j < N; j++)
			{
				test.add(items.get(j));
				test.deleteMin();
				test.add(items.get(j));
			}
		}
		
		midpointTime = System.nanoTime();
		
		for(int i = 0; i < timesToLoop; i ++)
		{
			PriorityQueue<Integer> test = new PriorityQueue<Integer>();
			for(int j = 0; j < N; j++)
			{
				test.add(items.get(j));
				test.add(items.get(j));
			}
		}

		stopTime = System.nanoTime();
		
		// Subtract the cost of running the loop
		// from the cost of running the loop plus the real work.
		// Average it over the number of runs.
		averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop; 
		System.out.println(averageTime + "\t" + (averageTime/N) + "\t");

	}
	

}
