package math2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ColumnVector extends Matrix {

	public ColumnVector() throws NumberFormatException, IOException {
		// TODO Auto-generated constructor stub
		MatrixCols=1;
	}


	/*
	 * 1. this method is used to obtain vector's information.
	 * 2. it does so by using a buffered reader to read in the number of rows the vector will contain. Then
	 * it uses a double for loop to then reinput the vector values
	 * 3. It returns nothing*/
	@Override
	public void getInfo() throws NumberFormatException, IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\neneter # of rows:\n");
		MatrixRows=getIntegerNumberFromUser(br);
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
	 * 1. This method is another method of transferring information to this matrix object. Primarily it is 
	 * aimed at communication between objects already in system. Its particularily useful in transfering 
	 * to matrix already exist without changing data already here.
	 * 2.it does so by explicitly passing in an array which then use method in suprclass to pass it. It also
	 * rejects values that don't have column vector format by checking the lenght of first row. 
	 * 3. it returns nothing
	 * 
	 * */
	@Override
	public void transferMatrix(double[][] newValues) 
	{
		// TODO Auto-generated method stub
		if(newValues[0].length==1) 
		{
			super.transferMatrix(newValues);
		}
	}


	/*
	 * 1. This method's purpose is to calculate the dot product of two column vectors
	 * 2.it does so by explicitly passing in an array which then use method in suprclass to pass it. It also
	 * rejects values that don't have column vector format by checking the lenght of first row. 
	 * 3. it returns a double value represent the dot product of two Row vectors.
	 * */
	public double ColDot(ColumnVector otherTerm) 
	{
		double sum=0;
		otherTerm.printMatrix();
		
		if(otherTerm.MatrixRows==MatrixRows) 
		{
			for (int i = 0; i < MatrixRows; i++) 
			{
				sum+=otherTerm.MatrixValues[i][0]*MatrixValues[i][0];		
			}
		}
		return sum;	
	}
}
