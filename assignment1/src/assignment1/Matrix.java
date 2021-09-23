package assignment1;

public class Matrix {
	private int numRows;
	private int numColumns;
	private int data[][];
	
	// Constructor with data for new matrix (automatically determines dimensions)
	public Matrix(int d[][])
	{
		numRows = d.length; // d.length is the number of 1D arrays in the 2D array
		if(numRows == 0)
			numColumns = 0;
		else
			numColumns = d[0].length; // d[0] is the first 1D array
		data = new int[numRows][numColumns]; // create a new matrix to hold the data
		// copy the data over
		for(int i=0; i < numRows; i++) 
			for(int j=0; j < numColumns; j++)
				data[i][j] = d[i][j];
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object o)
	{
		if(!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
			return false;
		Matrix m = (Matrix)o; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		if(!(m.numRows == this.numRows && m.numColumns == this.numColumns)) //both the number of rows and cols of compared object must be equal to proceed
		{
			return false;
		}
		
		for(int r = 0; r < this.numRows; r++) //matrix sizes are the same so we check elements
		{
			for(int c = 0; c < this.numColumns; c++)
			{
				if(m.data[r][c] != this.data[r][c]) //check if elements are not same 
				{
					return false;
				}
			}
		}
		return true; //After checking all the ways a matrix could not be equal to the other we get here and conclude it is the same
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String matrixString = "";
		for(int r = 0; r < numRows; r++) //we will iterate over elements in our matrix
		{
			for(int c = 0; c < numColumns; c++)
			{
				matrixString = String.format("%s%d ", matrixString, data[r][c]); //formats our string
			}
			matrixString = String.format("%s\n", matrixString); //goes to next line in string to correspond with next row
		}
		return matrixString; 
	}
	
	public Matrix times(Matrix m)
	{
		//this object is the matrix on the left of mult sign(matrix1) and the arg object is on the right (matrix2)
		if(!(this.numColumns == m.numRows)) //means you cannot multiply
		{
			return null;
		}
		Matrix product = new Matrix(new int[this.numRows][m.numColumns]);//creates new matrix of size m1xn2 (if matrix 1 is m1xn1 and matrix 2 is m2xn2 and n1 = m2)
		int sum = 0; //sum for adding values of our multiplied row by column
		for(int i = 0; i < product.numRows; i++) //iterate over dimensions of product matrix
		{
			for(int j = 0; j < product.numColumns; j++)
			{
				sum = 0;

				//this following loop is so we can sum our multiplied corresponding values of row i in our first matrix and column j in our second matrix
				//it will iterate the number of time of the size of the cols of matrix 1 or size of rows in matrix 2 which are equal to be multiplicable.
				for(int k = 0; k < this.numColumns; k++)//will iterate over values matrix1's rows and over values of matrix2's columns
				{
					sum += this.data[i][k] * m.data[k][j]; //multiplies corresponding values and adds to sum
				}
				product.data[i][j] = sum; //will place our sum in corresponding spot
			}
		}
		return product; 
	}
	
	public Matrix plus(Matrix m)
	{
		if(!(m.numRows == this.numRows && m.numColumns == this.numColumns)) //both the number of rows and cols of compared object must be equal to proceed
		{
			return null;
		}
		Matrix sum = new Matrix(new int[this.numRows][this.numColumns]);
		for(int r = 0; r < this.numRows; r++) //we will iterate over elements in our matrix
		{
			for(int c = 0; c < this.numColumns; c++)
			{
				sum.data[r][c] = this.data[r][c] + m.data[r][c]; //sum corresponding elements
			}
		}
		
		return sum; 
	}
}
