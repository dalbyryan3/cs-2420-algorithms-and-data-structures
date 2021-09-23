package assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;


public class AnagramTester {

	private static Random rand;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    long startTime, midpointTime, stopTime;
	    rand = new Random();
		
		// Reads a text file with a single word per line, returns them as an array of Strings
		String[] words = readFile("sample_word_list.txt");
		
		//Allow thread to stabilize
	    startTime = System.nanoTime();
	    while (System.nanoTime() - startTime < 1000000000) { // empty block
	    }
	    
	    //Test
	    int timesToLoop = 25; 
	    int problemSize = 1000;
	    int wordSize = 6;
	    String[] sArray = new String[problemSize];
	    for(int i = 0; i < problemSize; i++)
	    {
	    	sArray[i] = randomString(wordSize);
	    }

	    startTime = System.nanoTime();
	    
	    //Execute code to examine
	    for (int i = 0; i < timesToLoop; i++) {   
	    	AnagramUtil.getLargestAnagramGroup(sArray);
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
//		        + " nanoseconds to determine if two words of size " + problemSize + " are anagrams");
	    System.out.println("It takes exactly " + averageTime
		        + " to find largest anagram group of problem size: " + problemSize);

	    
		
	}

	
	// Create a random string [a-z] of specified length
	public static String randomString(int length)
	{
		String retval = "";
		for(int i = 0; i < length; i++)
		{
			// ASCII values a-z are contiguous (26 characters)
			retval += (char)('a' + (rand.nextInt(26)));
		}
		return retval;
	}
	
	
	// Reads words from a file (assumed to contain one word per line)
	// Returns the words as an array of strings.
	public static String[] readFile(String filename)
	{
		ArrayList<String> results = new ArrayList<String>();
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while(input.ready())
			{
				results.add(input.readLine());
			}
			input.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		String[] retval = new String[1];
		return results.toArray(retval);
	}
	
}
