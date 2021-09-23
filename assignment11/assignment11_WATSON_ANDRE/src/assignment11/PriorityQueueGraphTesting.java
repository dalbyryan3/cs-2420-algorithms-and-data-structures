package assignment11;

import java.util.Random;

public class PriorityQueueGraphTesting {

	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		Random rand = new Random();
		for(int i = 0; i < 40; i++) {
			pq.add(rand.nextInt(1000));
		}
		pq.generateDotFile("BasicRandomAdds");
		
		pq.deleteMin();
		pq.deleteMin();
		pq.add(42);
		pq.add(-20); //guaranteed to be new root
		
		pq.generateDotFile("AddToRoot");
		pq.clear();
		pq.add(1);
		pq.add(2);
		pq.add(3);
		pq.deleteMin();
		pq.generateDotFile("CorrectAddAfterClear");
		
		
	}

}
