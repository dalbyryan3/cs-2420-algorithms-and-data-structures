package assignment8;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Test Add Remove and Clear through .dot visualization of the BSTs 
 * @author Andre Watson, and Ryan Dalby
 *
 */
public class BinarySearchTreeAddRemoveClearTest {

	public static void main(String[] args) {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		//add normally/correctly
		//adds a root, adds to either side of root, sorts correctly when adding future children
		ArrayList<Integer> items = new ArrayList<Integer>(Arrays.asList(15, 10, 14, 12, 11, 13, 18, 16, 19, 17));
		b.addAll(items);
		b.writeDot("BST_Test.dot");
		
		b.remove(17); //remove leaf, no sibling
		b.remove(14); //remove inner node, no sibling, has one child
		b.writeDot("BST_Test2.dot");
		
		b.remove(12); //remove inner node, no sibling, has two children
		b.writeDot("BST_Test3.dot");
		
		b.remove(20); //shouldn't remove not in BST
		b.remove(15); //remove root with two children
		b.writeDot("BST_Test4.dot");
		
		BinarySearchTree<Integer> b2 = new BinarySearchTree<Integer>();
		//add normally/correctly
		b2.addAll(items);
		
		b2.remove(18); // remove inner node with sibling has 2 children
		b2.writeDot("BST_Test5.dot");
		
		b2.remove(19); //remove inner node with sibiling has 1 child
		b2.writeDot("BST_Test6.dot");
		
		b2.remove(11); //remove leaf node with sibling
		b2.writeDot("BST_Test7.dot");
		
		ArrayList<Integer> toRemove = new ArrayList<Integer>(Arrays.asList(10,12,13,14,17));
		b2.removeAll(toRemove);
		b2.writeDot("BST_Test8.dot");
		
		b2.remove(15); //remove root with 1 child
		b2.writeDot("BST_Test9.dot");

		b2.remove(16); //remove root with no children
		b2.writeDot("BST_Test10.dot");

		b.clear(); //removes all
		b.writeDot("BST_Test11.dot");
		
		

		


	
		
		

	}

}
