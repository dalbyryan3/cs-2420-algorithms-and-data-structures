package assignment10;

import java.util.ArrayList;
import java.util.Random;

public class compareHashTables {

	private static Random rand = new Random();
	private static int timesToLoop = 100;
	
	
	public static void main(String args[])
	{
		for(int i = 500; i <= 10000; i = i + 500)
		{
			System.out.print(i + "\t");
			compareCollisionResolving(i);
		}


	}
	
	private static void compareCollisionResolving(int hashSize)
	{
		long startTime, midpointTime, stopTime;
		GoodHashFunctor goodFunc = new GoodHashFunctor();
		int collisions = 0;
		int throwaway;
		double averageTime;
		
		ArrayList<String> items = new ArrayList<String>();
		for(int i = 0; i < hashSize/10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				items.add(generateString(j));
			}
		}

		//Allow thread to stabilize:
		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		//Timing code(Quad Probe):
		startTime = System.nanoTime();
		
		for (int i = 0; i < timesToLoop; i++)
		{
			QuadProbeHashTable hashTable = new QuadProbeHashTable(hashSize, goodFunc);
			hashTable.addAll(items);
			collisions = hashTable.getCollisions();
		}

		midpointTime = System.nanoTime();

		// Run an loop that creates new object to capture the cost of the overhead
		for (long i = 0; i < timesToLoop; i++) 
		{
			QuadProbeHashTable hashTable = new QuadProbeHashTable(hashSize, goodFunc);
			throwaway = hashTable.getCollisions();
		}

		stopTime = System.nanoTime();

		// Subtract the cost of running the loop
		// from the cost of running the loop plus the real work.
		// Average it over the number of runs.
		averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop; 
		System.out.print(collisions + "\t" + averageTime + "\t");
		
		//Timing code(Separate Chaining):
		startTime = System.nanoTime();
		
		for (int i = 0; i < timesToLoop; i++)
		{
			ChainingHashTable hashTable = new ChainingHashTable(hashSize, goodFunc);
			hashTable.addAll(items);
			collisions = hashTable.getCollisions();
		}

		midpointTime = System.nanoTime();

		// Run an loop that creates new object to capture the cost of the overhead
		for (long i = 0; i < timesToLoop; i++) 
		{
			ChainingHashTable hashTable = new ChainingHashTable(hashSize, goodFunc);
			throwaway = hashTable.getCollisions();
		}

		stopTime = System.nanoTime();

		// Subtract the cost of running the loop
		// from the cost of running the loop plus the real work.
		// Average it over the number of runs.
		averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop; 
		System.out.println(collisions + "\t" + averageTime + "\t");
	}
	
	private static String generateString(int size)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i++)
		{
			char c = (char)(97+rand.nextInt(25));
			sb.append(c);
		}
		return sb.toString();
	}

}
