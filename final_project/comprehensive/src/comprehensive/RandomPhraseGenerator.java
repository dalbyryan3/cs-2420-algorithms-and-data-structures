package comprehensive;

/**
 * Main program to run to pass in a grammar file and generate a specified number of random phrases.
 * @author Andre Watson and Ryan Dalby
 *
 */
public class RandomPhraseGenerator {

	public static void main(String[] args)
	{
		Grammar g = new Grammar(args[0]);
		
		for(int i = 0; i < Integer.parseInt(args[1]); i++)
		{
			System.out.println(g.generateRandomPhrase());
		}
	}
}