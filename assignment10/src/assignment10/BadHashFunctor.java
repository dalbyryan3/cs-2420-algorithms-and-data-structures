package assignment10;

/**
 * Returns a hash value that will result in lots of collisions
 * @author Andre Watson and Ryan Dalby
 *
 */
public class BadHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		return item.length();
	}

}
