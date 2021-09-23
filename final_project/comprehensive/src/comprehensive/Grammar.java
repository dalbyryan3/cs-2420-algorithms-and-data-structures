package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


/**
 * Primary Grammar class that will store a passed grammar and generate random strings from that grammar
 * @author Andre Watson and Ryan Dalby
 *
 */
public class Grammar {
	private HashMap<String, ArrayList<String>> backing;
	private Random rand;
	
	public Grammar(String filename)
	{
		rand = new Random();
		backing = new HashMap<String, ArrayList<String>>();
		File f = new File(filename);
		parseGFile(f);
		
	}
	
	/**
	 * Parses a grammar file, storing the grammar in a hashmap
	 * @param f The grammar file to be parsed 
	 */
	private void parseGFile(File f)
	{
		
		try {
			Scanner file = new Scanner(f);
			while(file.hasNextLine())
			{
				if(file.nextLine().equals("{"))
				{
					String key = file.nextLine(); //We know that after a { the next line is guaranteed to be a non-terminal being defined
					ArrayList<String> values = new ArrayList<String>();
					String nextLine = file.nextLine();//we are guaranteed to have at least one production rule per non-terminal 
					while(!nextLine.equals("}"))
					{
						values.add(nextLine);
						nextLine = file.nextLine();
					}
					backing.put(key, values); //each non-terminal(which is our key) is guaranteed to be unique
				}
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Generates a random phrase that matches the grammar
	 * @return String that represents a phrase with randomly chosen productions
	 */
	public String generateRandomPhrase()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(randomPick("<start>"));
		int currentIndex = 0; //This will keep track of how far in the String we are so we don't have to reanalyze the whole String each time
		while(currentIndex != sb.length()) //looping though all of start production
		{
			if(sb.charAt(currentIndex) == '<') //we found a non-terminal
			{
				int innerIndex = currentIndex;
				while(sb.charAt(innerIndex) != '>') //will key of non-terminal
				{
					innerIndex++;
				}
				String key = sb.substring(currentIndex, innerIndex+1); //+1 due to definition of end index by sb.substring
				
				sb.replace(currentIndex, innerIndex+1, randomPick(key)); //replaces non-terminal with one of its productions 
			}
			else
			{
				currentIndex++; //if current character is not part of a non-terminal we can advance
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * @param key String non-terminal of which a random production will be chosen
	 * @return random production associated with key
	 */
	private String randomPick(String key)
	{
		ArrayList<String> values = backing.get(key);
		return values.get(rand.nextInt(values.size())); //by using random makes sure each production has an equal chance of being chosen
	}

}
