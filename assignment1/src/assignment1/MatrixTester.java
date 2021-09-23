/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment1;

public class MatrixTester {
	public static void main(String[] args)
	{			
		Matrix M1 = new Matrix(new int[][]
		                       {{1, 2, 3},
							   {2, 5, 6}});
		
		Matrix M2 = new Matrix(new int[][]
		                       {{4, 5},
							   {3, 2},
							   {1, 1}});
		
		Matrix M3 = new Matrix(new int[][] 
								{{1,2,3}, 
								{4,5,6}});
		
		
		Matrix M4 = new Matrix(new int[][] 
								{{7,8,9}, 
								{10,11,12}});
		

		// this is the known correct result of multiplying M1 by M2
		Matrix referenceMultiply = new Matrix(new int[][]
		                                      {{13, 12},
								              {29, 26}});
		
		//known correct result of adding M3 and M4
		Matrix refrenceAddition = new Matrix(new int[][] 
				{{8,10,12},
				{14,16,18}});

		
		
		/* 
		 * Note that none of the tests below will be correct until you have implemented all methods.
		 * This is just one example of a test, you must write more tests and cover all cases.
		 */
		
		// exercise your toString method
		String m1Expected = "1 2 3 \n2 5 6 \n";
		String m1Result = M1.toString();
		System.out.println("M1 is:\n" + m1Result);
		if(!m1Expected.equals(m1Result))
			System.out.println("toString Error.\nGot:\n" + m1Result + "\nExpected:\n" + m1Expected);
		
		//test .equals true and false cases
		//test true case
		boolean boolM1M1 = M1.equals(M1);
		System.out.println("M1 equals M1: " + boolM1M1);
		//test false case of two matrices where dimensions differ
		boolean boolM1M2 = M1.equals(M2);
		System.out.println("M1 equals M2: " + boolM1M2);
		//test false case of two matrices where dimensions are equal
		boolean boolM1M3 = M1.equals(M3);
		System.out.println("M1 equals M3: " + boolM1M3);
		//test false case of a non matrix and a matrix
		String other = "";
		boolean boolM1Other = M1.equals(other);
		System.out.println("M1 equals a String: " + boolM1Other);
		System.out.println(); //newline
		
		// get the matrix computed by your times method
		Matrix computedMultiply = M1.times(M2);
		
		// exercises your toString method as well
		System.out.println("Computed result for M1 * M2:\n" + computedMultiply); 
		
		// exercises your .equals method, and makes sure that your computed result is the same as the known correct result
		if(!referenceMultiply.equals(computedMultiply)) 
			System.out.println("equals error (M1 * M2 not equal to expected result)");
		
		// will test times with incorrect sizes
		Matrix failComputedMultiply = M1.times(M3);
		
		//will test toString with a null Matrix
		System.out.println("Computed result for M1 * M3:\n" + failComputedMultiply +"\n"); 

		//will check if failComputedMultiply is null
		if(!(failComputedMultiply == null))
		{
			System.out.println("incompatable dimension error for multiplication M1 * M3 should return null");
		}
		
		//test add method
		Matrix computedAddition = M3.plus(M4);
		
		//will make sure addition is correct and also test the toString which is implicitly called
		System.out.println("Computed result for M3 + M4:\n" + computedAddition);
		
		//will test equals method to make sure M3 + M4 is correct
		if(!refrenceAddition.equals(computedAddition))
		{
			System.out.println("equals error (M3 + M4 not equal to expected result)");
		}
		
		// will test add with incorrect sizes
		Matrix failComputedAddition = M1.plus(M2);
		
		//will test toString with a null Matrix
		System.out.println("Computed result for M1 + M2:\n" + failComputedAddition +"\n"); 
		
		//will check if failComputedMultiply is null
		if(!(failComputedAddition == null))
		{
			System.out.println("incompatable dimension error for addition M1 * M3 should return null");
		}
		
		
	}
}
