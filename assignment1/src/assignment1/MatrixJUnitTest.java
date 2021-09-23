package assignment1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MatrixJUnitTest {

	@Test
	void MatrixMultiplicationIncompatableDim() {
		Matrix M1 = new Matrix(new int[][]
                {{1, 2, 3},
				   {2, 5, 6}});

		Matrix M2 = new Matrix(new int[][]
                {{4, 5},
				   {3, 2}});
		
		assertEquals(null, M1.times(M2));

	}

}
