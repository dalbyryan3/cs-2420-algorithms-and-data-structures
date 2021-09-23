package comprehensive;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class TimeGrammar {
	static Random rand = new Random();
	public static void main(String args[])
	{
//		//Time N phrases
		generateGrammarFile("Test", 1, 1, 1);
		for(int i = 0; i <= 800; i = i + 50)
		{
			timeNphrases(i);
		}
		
//		//Time N number of non terminals
//		for(int i = 50; i <= 800; i = i + 50)
//		{
//			timeNnonTerminals(i);
//		}
		
//		//Time N productions per non terminal
//		for(int i = 50; i <= 800; i = i + 50)
//		{
//			timeNprodPerNonTerm(i);
//		}
		
//		//Time N non terminals per production rule
//		for(int i = 50; i <= 1000; i = i + 50)
//		{
//			timeNnonTermPerProdRule(i);
//		}
		
	}
	
	private static void timeNnonTermPerProdRule(int N) {
		long midpointTime, stopTime;
		double averageTime;
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		
		generateGrammarFile("Test", 1, 1, N);
		Grammar g = new Grammar("Test");
		
		startTime = System.nanoTime();
		g.generateRandomPhrase();
		stopTime = System.nanoTime();
		
		averageTime = (stopTime - startTime); 
		System.out.println(N + "\t" + averageTime + "\t");		
	}

	private static void timeNprodPerNonTerm(int N) {
		long midpointTime, stopTime;
		double averageTime;
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		
		generateGrammarFile("Test", 1, N, 1);
		Grammar g = new Grammar("Test");
		
		startTime = System.nanoTime();
		g.generateRandomPhrase();
		stopTime = System.nanoTime();
		
		averageTime = (stopTime - startTime); 
		System.out.println(N + "\t" + averageTime + "\t");		
	}

	private static void timeNnonTerminals(int N) {
		long midpointTime, stopTime;
		double averageTime;
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		
		generateGrammarFile("Test", N, 1, 1);
		Grammar g = new Grammar("Test");
		
		startTime = System.nanoTime();
		g.generateRandomPhrase();
		stopTime = System.nanoTime();
		
		averageTime = (stopTime - startTime); 
		System.out.println(N + "\t" + averageTime + "\t");
		
	}

	private static void timeNphrases(int N) {
		long midpointTime, stopTime;
		double averageTime;
		int timesToLoop = 100;
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		
		Grammar g = new Grammar("Test");
		
		startTime = System.nanoTime();
		for(int j = 0; j < timesToLoop; j++)
		{
			for(int i = 0; i < N; i++)
			{
				g.generateRandomPhrase();
			}
		}
		midpointTime = System.nanoTime();
		for(int j = 0; j < timesToLoop; j++)
		{
			for(int i = 0; i < N; i++)
			{
			}
		}
		stopTime = System.nanoTime();
		
		averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))/timesToLoop; 
		System.out.println(N + "\t" + averageTime + "\t");
		
	}

	public static void generateGrammarFile(String filename, int numNonTerminals, int numProductionsPerNonTerminal, int numNonTerminalsPerProduction)
	{
		File f = new File(filename);
		if(f.exists())
		{
			f.delete();
		}
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			FileWriter fw = new FileWriter(f);
			BufferedWriter bf = new BufferedWriter(fw);
			writeGrammar(bf, numNonTerminals, numProductionsPerNonTerminal, numNonTerminalsPerProduction);
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	private static void writeGrammar(BufferedWriter bf, int numNonTerminals, int numProductionsPerNonTerminal, int numNonTerminalsPerProduction) throws IOException {
		bf.write("{\n");
		HashMap<String, String> h = new HashMap<String, String>();
		LinkedList<String> q = new LinkedList<String>();
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < numNonTerminals; i++)
		{
			String s = generateString(10);
			while(h.containsValue(s))
			{
				s = generateString(10);
			}
			h.put(s,s);
			q.add("<" + s + ">");
		}
		bf.write("<start>\n");
		String currentNonTerm;
		for(int i = 0; i < numProductionsPerNonTerminal; i++)
		{
			for(int j = 0; j < numNonTerminalsPerProduction; j++)
			{
				currentNonTerm = q.get(rand.nextInt(q.size()));
				sb.append(currentNonTerm);
				sb.append(" ");
			}
			sb.append(generateString(10)); // will append a string regardless if a number of non terminals per production are specified
			sb.append("\n");
			bf.write(sb.toString());
			sb.setLength(0); //clears string builder
		}
		bf.write("}\n");
		bf.write(createProductionString(q, numProductionsPerNonTerminal, numNonTerminalsPerProduction));
		
	}
	
	private static String createProductionString(LinkedList<String> q, int numProductionsPerNonTerminal, int numNonTerminalsPerProduction)
	{
		StringBuilder sb = new StringBuilder();	
		while(!q.isEmpty())
		{
			sb.append("{\n");
			
			String currentNonTerm = q.poll();
			sb.append(currentNonTerm);
			sb.append("\n");
			

			for(int i = 0; i < numProductionsPerNonTerminal; i++)
			{
				if(!q.isEmpty())
				{
					for(int j = 0; j < numNonTerminalsPerProduction; j++)
					{
						sb.append(q.get(rand.nextInt(q.size())));
						sb.append(" ");					
					}
				}
				else
				{
					sb.append(generateString(10));
				}
				sb.append("\n");
			}
			sb.append("}\n");
		}
		return sb.toString();
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
