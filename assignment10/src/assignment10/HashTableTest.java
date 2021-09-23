package assignment10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * JUnit test file for both QuadProbeHashTable and ChainingHashTable
 * @author Andre Watson and Ryan Dalby
 *
 */
class HashTableTest {

	@Test
	void quadProbeAdd() {
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(16, medFunc);
		assertEquals(true, test.add("item"));
		assertEquals(false, test.add("item"));		
	}
	
	@Test
	void quadProbeAddAll() { //Tests addAll, rehash, and probing
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(3, medFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		assertEquals(true, test.addAll(collection));
		assertEquals(false, test.addAll(collection));
		collection.add("ab");
		assertEquals(true, test.addAll(collection));
	}
	
	@Test
	void quadProbeClear() { 
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(3, medFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		test.addAll(collection);
		test.clear();
		assertEquals(0, test.size());
		assertEquals(false, test.contains("a"));
	}
	
	@Test
	void quadProbeContains() { 
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(3, medFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		test.addAll(collection);
		assertEquals(true, test.contains("z"));
		assertEquals(false, test.contains("zz"));
	}
	
	@Test
	void quadProbeContainsAll() { 
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(3, medFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		test.addAll(collection);
		assertEquals(true, test.containsAll(collection));
		collection.remove(0);
		assertEquals(true, test.containsAll(collection));
		collection.add("zz");
		assertEquals(false, test.containsAll(collection));
	}
	
	@Test
	void quadProbeIsEmpty() { 
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(3, medFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		test.addAll(collection);
		assertEquals(false, test.isEmpty());
		test.clear();
		assertEquals(true, test.isEmpty());
	}
	
	@Test
	void quadProbeSize() { 
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(3, medFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		assertEquals(0, test.size());
		test.addAll(collection);
		assertEquals(26, test.size());
		test.clear();
		assertEquals(0, test.size());
	}
	
	@Test
	void quadProbeAddAllWithBadHash() { //Tests addAll, rehash, and probing
		BadHashFunctor badFunc = new BadHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(3, badFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}

		assertEquals(true, test.addAll(collection));
		assertEquals(false, test.addAll(collection));
		collection.add("ab");
		assertEquals(true, test.addAll(collection));
	}
	
	@Test
	void quadProbeAddAllWithGoodHash() { //Tests addAll, rehash, and probing
		GoodHashFunctor goodFunc = new GoodHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(3, goodFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		assertEquals(true, test.addAll(collection));
		assertEquals(false, test.addAll(collection));
		collection.add("ab");
		assertEquals(true, test.addAll(collection));
	}
	
	
	//Now testing ChainingHashTable
	
	@Test
	void chainingAdd() {
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		ChainingHashTable test = new ChainingHashTable(16, medFunc);
		assertEquals(true, test.add("item"));
		assertEquals(false, test.add("item"));		
	}
	
	@Test
	void chainingAddAll() { //Tests addAll, rehash, and probing
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		ChainingHashTable test = new ChainingHashTable(3, medFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		assertEquals(true, test.addAll(collection));
		assertEquals(false, test.addAll(collection));
		collection.add("ab");
		assertEquals(true, test.addAll(collection));
	}
	
	@Test
	void chainingClear() { 
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		ChainingHashTable test = new ChainingHashTable(3, medFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		test.addAll(collection);
		test.clear();
		assertEquals(0, test.size());
		assertEquals(false, test.contains("a"));
	}
	
	@Test
	void chainingContains() { 
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		ChainingHashTable test = new ChainingHashTable(3, medFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		test.addAll(collection);
		assertEquals(true, test.contains("z"));
		assertEquals(false, test.contains("zz"));
	}
	
	@Test
	void chainingContainsAll() { 
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		ChainingHashTable test = new ChainingHashTable(3, medFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		test.addAll(collection);
		assertEquals(true, test.containsAll(collection));
		collection.remove(0);
		assertEquals(true, test.containsAll(collection));
		collection.add("zz");
		assertEquals(false, test.containsAll(collection));
	}
	
	@Test
	void chainingIsEmpty() { 
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		ChainingHashTable test = new ChainingHashTable(3, medFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		test.addAll(collection);
		assertEquals(false, test.isEmpty());
		test.clear();
		assertEquals(true, test.isEmpty());
	}
	
	@Test
	void chainingSize() { 
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		ChainingHashTable test = new ChainingHashTable(3, medFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		assertEquals(0, test.size());
		test.addAll(collection);
		assertEquals(26, test.size());
		test.clear();
		assertEquals(0, test.size());
	}
	
	@Test
	void chainingAddAllWithBadHash() { //Tests addAll, rehash, and probing
		BadHashFunctor badFunc = new BadHashFunctor();
		ChainingHashTable test = new ChainingHashTable(3, badFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		assertEquals(true, test.addAll(collection));
		assertEquals(false, test.addAll(collection));
		collection.add("ab");
		assertEquals(true, test.addAll(collection));
	}
	
	@Test
	void chainingAddAllWithGoodHash() { //Tests addAll, rehash, and probing
		GoodHashFunctor goodFunc = new GoodHashFunctor();
		ChainingHashTable test = new ChainingHashTable(3, goodFunc);
		ArrayList<String> collection = new ArrayList<String>();
		for(int i = 0; i < 26; i++)
		{
			collection.add(String.valueOf((char)(i + 97)));
		}
		assertEquals(true, test.addAll(collection));
		assertEquals(false, test.addAll(collection));
		collection.add("ab");
		assertEquals(true, test.addAll(collection));
	}

}
