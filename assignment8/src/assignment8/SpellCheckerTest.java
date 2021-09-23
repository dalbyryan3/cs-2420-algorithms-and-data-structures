package assignment8;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests methods for SpellChecker not directly tested in the Demo
 * @author Andre Watson, Ryan Dalby
 *
 */
class SpellCheckerTest {

	@Test
	void testAddToDictionary() {
		SpellChecker sc = new SpellChecker(new File("dictionary.txt"));
		sc.addToDictionary("bst");
		List<String> misspelled = new ArrayList<String>();
		misspelled.add("danny");
		assertEquals(misspelled, sc.spellCheck(new File("good_luck.txt")));
	}
	@Test
	void testRemoveFromDictionary() {
		SpellChecker sc = new SpellChecker(new File("dictionary.txt"));
		sc.removeFromDictionary("there");
		List<String> misspelled = new ArrayList<String>();
		misspelled.add("there");
		assertEquals(misspelled, sc.spellCheck(new File("hello_world.txt")));
		
	}
	//Spell check is tested in demo

}
