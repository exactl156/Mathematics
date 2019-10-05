package math2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/*
 * This class is devoted to having elementary properties of matrixes as listed below: 
 * 1. Obtaining values
 * 2. Printing Matrix
 * 3. Having a Determinent Value
 * 4. Having a gaussian Matrix
 * 5. multiplying this matrix with another Matrix
 * 6. multiplying this matrix with another Matrix
 * */
public class Matrix {
	int MatrixRows;
	int MatrixCols;
	double[][] MatrixValues;


	public Matrix() throws NumberFormatException, IOException {
		super();
		//getInfo();
	}


	/*
	 *This method does 3 things. 
	 * Firstly it obtains the number of rows
	 * Secondly it obtains number of columns
	 * Thirdly it obtains matrix values
	 * Primarily this method is aimed at getting information from user
	 * 
	 * */
	public void getInfo() throws NumberFormatException, IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("eneter # of rows:\n");
		MatrixRows=Integer.parseInt(br.readLine());
		System.out.print("eneter # of cols:\n");
		MatrixCols=Integer.parseInt(br.readLine());


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
		//printMatrix();
		//double[][] newValues= {{2,2},{2,2}};
		//transferMatrix(newValues);
		//printMatrix();
		//	System.out.print(MatrixRows*MatrixCols);
	}


	/*
	 * This method is another method of transfering information to this matrix object. it does so by explicitly
	 * passing in an array. Primarily it is aimed at communication between objects already in system. Its 
	 * particularily useful in transfering to matrix already exist without changing data already here.
	 * 
	 * 
	 * */
	public void transferMatrix(double[][] newValues)  
	{
		MatrixValues=newValues;
		MatrixCols=newValues[0].length;
		MatrixRows=newValues.length;
	}


	/*
	 * This method does one thing. It goes through the Matrix Values matrix and prints the values as the matrix is 
	 * stored as.
	 * 
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
	 * this method is aimed at finding out the value of the determinent. it does so 
	 * inefficiently by calculating cofactors and using recursion. A faster method would be to use 
	 * gaussian elimination before multiplying the diagonal. It also returns a double answer for the 
	 * determinent of the matrix. If the matrix does not have a determinent it will return a -1 and a 
	 * statement that this matrix doesn't have a matrix.
	 * 
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
	 * the purpose of this method is to obtain the cofactor of a certain element. it is used in the 
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
		Elimination.printMatrix();
		return null;
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
		System.out.println(Arrays.toString(pivots));
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
	public void subtractRowBfromA(int A,int B,double timesA,double timesB) 
	{
		for(int i=0;i<MatrixCols;i++) 
		{
			MatrixValues[A][i]=timesA*MatrixValues[A][i]-timesB*MatrixValues[B][i];
		}
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
		tr.printMatrix();
		return tr;
	}


	/*
	 * 1. This method's objective is to calculate the cofactor. 
	 * 2. It does so by going through each element and using obtainCofactor method in conjunction with
	 * the determinent method to calculate each element of the cofactor matrix.
	 * 3. Its return type is a matrix
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
		CofatorMatrix.printMatrix();
		return CofatorMatrix;
	}
	
	
	public Matrix Addition(Matrix otherMatrix) throws NumberFormatException, IOException 
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


	public Matrix multiply(Matrix otherMatrix) throws NumberFormatException, IOException 
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
}
