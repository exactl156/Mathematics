package math2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


/*
 * This class is devoted to having elementary properties of matrixes as listed below: 
 * 1. Obtaining values
 * 2. Printing Matrix
 * 3. Having a Determinent Value
 * 4. Having a gaussian Matrix
 * 5. Multiplying this matrix with another Matrix
 * 6. Adding this matrix with another Matrix
 * 7. Scaling the matrix
 * 8. Having an inverse
 * */
public class Matrix {
protected	int MatrixRows;
protected	int MatrixCols;
protected	double[][] MatrixValues;


	public Matrix() throws NumberFormatException, IOException {
		super();
		//getInfo();
	}


	/*
	 * 1.This method does 3 things. 
	 * 		Firstly it obtains the number of rows
	 * 		Secondly it obtains number of columns
	 * 		Thirdly it obtains matrix values
	 * Primarily this method is aimed at getting information from user
	 * 2. This method does so by intially asking for a value of rows. After recieving the value, it asks for
	 * a number of columns. After recieving those two numbers, it recieves the matrix value by using a for 
	 * loop to recieve the number of values inputted
	 * */
	public void getInfo() throws NumberFormatException, IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\neneter # of rows:\n");
		MatrixRows=getIntegerNumberFromUser(br);
		System.out.print("\neneter # of cols:\n");
		MatrixCols=getIntegerNumberFromUser(br);
		MatrixValues= new double[MatrixRows][MatrixCols];
		System.out.print("eneter values:\n");
		for(int i=0;i<MatrixRows;i++) 
		{
			String[] input = br.readLine().split(" ");
			//System.out.print(input[0]+"\n");
			for(int j=0;j<MatrixCols;j++) 
			{
				//			System.out.print(input[j]+"\n");
				MatrixValues[i][j]=Double.parseDouble(input[j]);
			}
		}


	}


	/*
	 * 1. the purpose of this method is to get numeric Integer input from the user.
	 * 2. the method does so by using a try catch block if user enters an illegal value it continues till
	 * it returns a Integer value
	 * 3. it returns the Integer value a user has entered.
	 * */
	public int getIntegerNumberFromUser(BufferedReader in) 
	{
		while (true) { // we end the loop by a return, not by a condition.
			try 
			{
				return Integer.parseInt(in.readLine());
			} catch (IOException ex) 
			{
				System.err.println("could not acquire next line from system input: " + ex.getMessage());
			} catch (NumberFormatException ex) 
			{
				System.err.println("could not convert input string: " + ex.getMessage());
			}
		}
	}


	/*
	 * 1. This method is another method of transferring information to this matrix object. Primarily it is 
	 * aimed at communication between objects already in system. Its particularily useful in transfering 
	 * to matrix already exist without changing data already here.
	 * 2.it does so by explicitly passing in an array. 
	 * 3. it returns nothing
	 * 
	 * */
	public void transferMatrix(double[][] newValues)  
	{

		MatrixCols=newValues[0].length;
		MatrixRows=newValues.length;
		MatrixValues=new double[MatrixRows][MatrixCols];
		for(int i=0;i<MatrixRows;i++) 
		{
			for(int j=0;j<MatrixCols;j++) 
			{
				MatrixValues[i][j]=newValues[i][j];
			}
		}
	}


	/*
	 * 1.This method does one thing. It goes through the Matrix Values matrix and prints the values as the matrix is 
	 * stored as.
	 * 2. It does so by using a double for loop to access all the values in the array
	 * 3. It returns nothing
	 * 
	 * */
	public void printMatrix()
	{
		for(int i=0;i<MatrixRows;i++) 
		{
			for(int j=0;j<MatrixCols;j++) 
			{
				System.out.print(MatrixValues[i][j]+" ");
			}
			System.out.print("\n");
		}
	}


	/*
	 * 1.this method is aimed at finding out the value of the determinant. 
	 * 2.it does so inefficiently by calculating cofactors and using recursion. A faster method would be 
	 * to use gaussian elimination before multiplying the diagonal. 
	 * 3. It also returns a double answer for the determinent of the matrix. If the matrix does not have 
	 * a determinent it will return a -1 and a statement that this matrix doesn't have a matrix.
	 * 
	 * */
	public double Determinent() throws NumberFormatException, IOException 
	{
		if(MatrixRows!=MatrixCols) 
		{
			System.out.print("determinent not available");
			return -1;
		}
		if(MatrixRows==2&&MatrixCols==2) 
		{
			return MatrixValues[0][0]*MatrixValues[1][1]-MatrixValues[0][1]*MatrixValues[1][0];
		}
		double sum=0;
		Matrix co;
		for(int i=0;i<MatrixRows;i++) 
		{
			co= obtainCofactorOfElement(0,i);
			sum+=MatrixValues[0][i]*co.Determinent()*Math.pow(-1, i);
		}
		return sum;
	}


	/*
	 * 1. The purpose of the method is to calculate the determinant using the gauassian reduced matrix
	 * 2. It does so by multiplying row values of the diagonal
	 * 3. The function returns the double value of the determinent.
	 * 
	 * */
	public double Determinent2() throws NumberFormatException, IOException 
	{
		if(MatrixRows!=MatrixCols) 
		{
			System.out.print("determinent not available");
			return -1;
		}
		double det=1;
		Matrix rowreduced=this.GaussianEliminate();
		for(int i=0;i<MatrixRows;i++) 
		{
			det*=rowreduced.MatrixValues[i][i];
		}
		return det;
	}


	/*
	 * 1.the purpose of this method is to obtain the cofactor of a certain element. it is used in the 
	 * current version of the determinent for calculations
	 * 
	 * 
	 * */
	public Matrix obtainCofactorOfElement(int rowIndex,int columnIndex) throws NumberFormatException, IOException 
	{
		double[][] cofactorValues= new double[MatrixRows-1][MatrixCols-1];
		double[] temp= new double[(MatrixRows-1)*(MatrixCols-1)];
		int pt=0;
		for(int i=0;i<MatrixRows;i++) 
		{
			if(i==rowIndex)continue;
			for(int j=0;j<MatrixCols;j++) 
			{
				if(j==columnIndex)continue;
				temp[pt]=MatrixValues[i][j];
				pt++;
			}
		}

		for(int i=0;i<MatrixRows-1;i++) 
		{
			for(int j=0;j<MatrixCols-1;j++) 
			{
				cofactorValues[i][j]=temp[i*(MatrixRows-1)+j];
			}
		}

		Matrix cofactor = new Matrix();
		cofactor.transferMatrix(cofactorValues);
		return cofactor;
	}


	/*
	 * 1. This method is for finding the Reduced row Echolon form of a matrix
	 * 2. it does so by first creating a copy from the transfer matrix method above. It then uses a for loop
	 * on the rows of the matrix to make sure that it obatins maximum number of pivots possible. In the for
	 * loop it reorders the rows using the reorder method to make sure its first element is nonzero. It then
	 * obtains the current nonzero positions in each row by using pivots. It then goes through a second
	 * for loop where it subtracts all the rows below it. It repeats the process for each row.
	 * 3. It returns a matrix in a echlon form
	 * */
	public Matrix GaussianEliminate() throws NumberFormatException, IOException 
	{
		Matrix Elimination = new Matrix();

		Elimination.transferMatrix(MatrixValues);
		int[] pivots;
		for(int i=0;i<MatrixRows;i++ ) 
		{
			Elimination.reorder();
			pivots= Elimination.pivotArray();
			for(int j=i+1;j<MatrixRows;j++ ) 
			{
				if(pivots[j]!=MatrixCols) 
				{
					Elimination.subtractRowBfromA(j, i,1 ,Elimination.MatrixValues[j][pivots[i]]/Elimination.MatrixValues[i][pivots[i]]);
				}
			}
			/*System.out.print("iteration"+i+"\n");
			Elimination.printMatrix();*/
		}
		//Elimination.printMatrix();
		return Elimination;
	}


	/*
	 * 1. This method reorders the rows of a matrix It uses the pivots moethods to reorder them according to the
	 * lenght of the pivots. This way we can get a triangular matrix
	 * 2. It does so by using pivots method to find the first nonzero element. After doing so, it uses the
	 * SwitchTwoRows method to switch the two rows using a bubblesort algorithem.
	 * 
	 * */
	public Matrix reorder() throws NumberFormatException, IOException 
	{
		Matrix Elimination = new Matrix();
		Elimination.transferMatrix(MatrixValues);
		int[] pivots= Elimination.pivotArray();
		for(int i=0;i<MatrixRows;i++ ) 
		{
			for(int j=0;j<MatrixRows;j++ ) 
			{
				if(pivots[i]<pivots[j]) 
				{
					int temp=pivots[i];
					pivots[i]=pivots[j];
					pivots[j]=temp;
					Elimination.switchTwoRows(i, j);
				}
			}
		}
		//Elimination.printMatrix();
		//System.out.println(Arrays.toString(pivots));
		return Elimination;
	}


	/*
	 * This method find the pivots of the array. It does so by going through a loop and attempting to find
	 * first element of each row that is non zero.
	 * 
	 * 
	 * 
	 * */
	public int[] pivotArray() 
	{
		int[] pivots= new int[MatrixRows];
		for(int i=0;i<MatrixRows;i++ ) 
		{
			for(int j=0;j<MatrixCols;j++ ) 
			{
				if(MatrixValues[i][j]!=0) 
				{
					pivots[i]=j;
					break;
				}
				if(j==MatrixCols-1) 
				{
					pivots[i]=MatrixCols;
					break;
				}
			}	
		}
		//	System.out.println(Arrays.toString(pivots));
		return pivots;
	}


	/*
	 * 1. This method's objective is to subtract row A from row B of the matrix. It also decides how many times
	 * it does so via other parameters
	 * 2. It does so by looping through the row subtracted from and subtracting the correspond value in the row
	 * below it.
	 * 3. The method returns void as it only modifies values inside of corresponding matrix.
	 * 
	 * */
	public void subtractRowBfromA(int A,int B,double timesA,double timesB) throws NumberFormatException, IOException 
	{
		/*Matrix Subtract=new Matrix();
		Subtract.transferMatrix(MatrixValues);*/
		for(int i=0;i<MatrixCols;i++) 
		{
			MatrixValues[A][i]=timesA*MatrixValues[A][i]-timesB*MatrixValues[B][i];
		}
		//return Subtract;
	}


	/*
	 * 1. This method's objective is to switch two rows in a matrix
	 * 2. It does so by looping through corresponding rows and switching them by use of a temporary variable
	 * 3. The method returns void as it only modifies values inside of corresponding matrix.
	 * 
	 * */
	public void switchTwoRows(int A,int B) 
	{
		for(int i=0;i<MatrixCols;i++) 
		{
			double twmp=MatrixValues[A][i];
			MatrixValues[A][i]=MatrixValues[B][i];
			MatrixValues[B][i]=twmp;
		}
	}


	/*
	 * 1. The main objective of this method is to transpose the matrix contained above. 
	 * 2. It does so by literally running through a for loop that adds the correponding matrix 
	 * into existence
	 * 3. Its return type is a Matrix
	 * 
	 * */
	public Matrix Transpose() throws NumberFormatException, IOException 
	{
		Matrix tr = new Matrix();
		double[][] transposeArray= new double[MatrixCols][MatrixRows];
		for(int i=0;i<MatrixRows;i++) 
		{
			for(int j=0;j<MatrixCols;j++) 
			{
				transposeArray[j][i]=MatrixValues[i][j];
			}
		}
		tr.transferMatrix(transposeArray);
		//tr.printMatrix();
		return tr;
	}


	/*
	 * 1. This method's objective is to calculate the cofactor. 
	 * 2. It does so by going through each element and using obtainCofactor method in conjunction with
	 * the determinent method to calculate each element of the cofactor matrix.
	 * 3. Its return type is a matrix
	 *  
	 *  */
	public Matrix MatrixOfCofactors() throws NumberFormatException, IOException 
	{
		Matrix CofatorMatrix = new Matrix();
		double[][] CofactorDeterminentValues=new double[MatrixRows][MatrixCols];
		for(int i=0;i<MatrixRows;i++) 
		{
			for(int j=0;j<MatrixCols;j++) 
			{
				CofactorDeterminentValues[i][j]=this.obtainCofactorOfElement(i, j).Determinent()*Math.pow(-1, i+j);
			}
		}
		CofatorMatrix.transferMatrix(CofactorDeterminentValues);
		/*System.out.print("matrix oc cofactors\n");
		CofatorMatrix.printMatrix();*/
		return CofatorMatrix;
	}

	/*
	 * 1. this method simply adds together matrixes.
	 * 2. it does so by iterating through both matrixes with a for loop and forming another array to 
	 * store as a matrix
	 * 3. This method returns another matrix
	 * 
	 * */
	public Matrix Addition(Matrix otherMatrix) throws NumberFormatException, IOException 
	{
		if(MatrixRows==otherMatrix.MatrixRows&&MatrixCols==otherMatrix.MatrixCols) 
		{
			Matrix sum = new Matrix();
			double[][] sumValues=new double[MatrixRows][MatrixCols];
			for(int i=0;i<MatrixRows;i++) 
			{
				for(int j=0;j<MatrixCols;j++) 
				{
					sumValues[i][j]=MatrixValues[i][j]+otherMatrix.MatrixValues[i][j];
				}
			}
			sum.transferMatrix(sumValues);
			return sum;
		}
		else 
		{
			System.out.print("This method is not applicable to the input matrix because the dimensions don't match. "
					+ "As a result we are returning the null matrix.");
			return null;
		}
	}


	/*
	 * 1. this method simply multiplies together matrixes AB. where A is
	 * 2. it does so by iterating through both matrixes with three for loops and forming another array to 
	 * store as a matrix. Two for loops are for traversing through row of first matrix and column of other. 
	 * the third is for loop is for multiplying the column by row.
	 * 3. This method returns another matrix*/
	public Matrix multiply(Matrix otherMatrix) throws NumberFormatException, IOException 
	{
		if(MatrixCols==otherMatrix.MatrixRows) 
		{
			Matrix mult = new Matrix();
			double[][] multValues=new double[MatrixRows][otherMatrix.MatrixCols];
			for(int i=0;i<MatrixRows;i++) 
			{
				for(int j=0;j<otherMatrix.MatrixCols;j++) 
				{
					multValues[i][j]=0;
					for(int k=0;k<MatrixCols;k++) 
					{
						multValues[i][j]+=MatrixValues[i][k]*otherMatrix.MatrixValues[k][j];
					}
				}
			}
			mult.transferMatrix(multValues);
			return mult;
		}
		else 
		{
			System.out.print("This method is not applicable to the input matrix because the dimensions don't match. "
					+ "As a result we are returning the null matrix.");
			return null;
		}
	}


	/*
	 * 1. this method simply scales the current matrix by a factor x.
	 * 2. it does so by iterating through the matrixes with a for loop and forming another array with scaled 
	 * values to store as a matrix
	 * 3. This method returns another matrix*/
	public Matrix scale(double d) throws NumberFormatException, IOException 
	{
		Matrix sum = new Matrix();
		double[][] sumValues=new double[MatrixRows][MatrixCols];
		for(int i=0;i<MatrixRows;i++) 
		{
			for(int j=0;j<MatrixCols;j++) 
			{
				sumValues[i][j]=MatrixValues[i][j]*d;
			}
		}
		sum.transferMatrix(sumValues);
		return sum;
	}


	/*
	 * 1. this method calculates the inverse of a matrix
	 * 2. It does so by finding the matrix of cofactors, transposing it and scaling by a factor of 1/det
	 * of the matrix
	 * 3. It return the inverse of the matrix
	 * 
	 * */
	public Matrix inverse() throws NumberFormatException, IOException 
	{
		if(MatrixCols==MatrixRows) 
		{
			Matrix Inverse= new Matrix();
			Inverse=this.MatrixOfCofactors().Transpose().scale(1/this.Determinent());
			System.out.print("The inverse is\n");
			return Inverse;
		}
		else 
		{
			System.out.print("This method is not applicable to the input matrix because the dimensions don't match. "
					+ "As a result we are returning the null matrix.");
			return null;
		}

	}

	/*
	 * 1.This method calculates the rounded matrix of the current matrix values
	 * 2. It does so by first multiply each number by a 1000 and then rounding using Math .round.
	 * It then retrieves the previous 3 digits by dividing by 1000
	 * 3. It does not return anything. 
	 * */
	public void RoundMatrix() 
	{
		for(int i=0;i<MatrixRows;i++) 
		{
			for(int j=0;j<MatrixCols;j++) 
			{
				MatrixValues[i][j]=Math.round(MatrixValues[i][j]*1000)/1000;
			}
		}
	}
}
