package assignment10;

/**
 * Hash function that results in less collisions than BadHashFunctor but still will give a similar value for similar Strings
 * @author Andre Watson and Ryan Dalby
 *
 */
public class MediocreHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		int sum = 0;
		for(char c : item.toCharArray())
		{
			sum += c;
		}
		return sum;
	}

}
