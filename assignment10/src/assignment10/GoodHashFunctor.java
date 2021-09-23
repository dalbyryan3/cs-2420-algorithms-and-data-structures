package assignment10;

/**
 * Hash function that performs well, an implementation of Java's hashing function for Strings.
 * @author Andre Watson and Ryan Dalby
 *
 */
public class GoodHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		//Implementing Java's hashing algorithm for Strings
		int sum = 0;
		for(int i = 0; i < item.length(); i++)
		{
			sum = sum * 31 + item.charAt(i);
		}
		return sum;
	}

}
