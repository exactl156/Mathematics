package math2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//		System.out.print("HHere");
		//	Equation test = new Equation("yo=r");
		/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    String[] input = br.readLine().split(" ");
		    //System.out.print(input[0]);
		    Equation test = new Equation(input[0]);*/


		PolynomialTest();
		System.out.print("\n-------------------------------------------------");
		MatrixTest();
		System.out.print("\n-------------------------------------------------");
		ColVectorTest();
		System.out.print("\n-------------------------------------------------");
		RowVectorTest();
	}


	/*1. This method is aimed for testing Matrix calculations
	 * 2. THe method does so based on configuration I want.
	 * 3. It returns nothing
	 * */
	public static void MatrixTest() throws NumberFormatException, IOException 
	{
		Matrix sample = new Matrix();
		Matrix sample2 = new Matrix();
		/*sample.getInfo();
		sample.printMatrix();
		Matrix sample2 = new Matrix();
		sample2.getInfo();
		sample2.printMatrix();
		
		Matrix Result = new Matrix();
		Result=sample.multiply(sample2).multiply(sample.inverse());
		Result.printMatrix();*/
		
		System.out.print("\nMatrixTest\nDemoing get info\n");
		sample = new Matrix();
		sample.getInfo();
		sample.printMatrix();
		System.out.print(sample.Determinent());


		System.out.print("\nDemoing transfer matrix\n");
		double[][] ArrayMatrixValues= {{1, 2, 3},{4, 5, 6},{7, 8, 9}};
		sample2 = new Matrix();
		sample2.transferMatrix(ArrayMatrixValues);
		sample2.printMatrix();


		System.out.print("\nDemoing switch two rows matrix\n");
		sample.switchTwoRows(0, 1);
		sample.printMatrix();


		System.out.print("\nDemoing subtract two rows matrix\n");
		sample.subtractRowBfromA(0, 1, 1, 1);
		sample.printMatrix();


		System.out.print("\nReordering matrix rows\n");
		Matrix result=sample.reorder();
		result.printMatrix();
		int[] sa=sample.pivotArray();


		System.out.print("\nGaussian Eliminating Part of Matrix\n");
		Matrix result2=sample.GaussianEliminate();
		result2.printMatrix();

		System.out.print("\nTransposing Matrix\n");
		Matrix result3=sample.Transpose();
		result3.printMatrix();

		System.out.print("\nGetting Matrix of cofactors\n");
		Matrix result4=sample.MatrixOfCofactors();
		result4.printMatrix();

		System.out.print("\nAdding two matrixes\n");
		Matrix result5=sample.Addition(sample2);
		if(result5!=null)
		result5.printMatrix();

		System.out.print("\nMultiplying two matrixes\n");
		Matrix result6=sample.multiply(sample2);
		if(result6!=null)result6.printMatrix();


		System.out.print("\nInverse\n");
		Matrix inv=sample.inverse();//.multiply(sample2);
		inv.printMatrix();

		System.out.print("\nIdentity\n");
		Matrix Identity=inv.multiply(sample);
		Identity.printMatrix();

		/*	System.out.print("\nvector\n");
		Matrix sample3 = new Matrix();
		sample3.getInfo();
		inv.multiply(sample3).printMatrix();*/
		System.out.print("\nPrinting Determinent\n");
		sample.printMatrix();
		System.out.print(sample.Determinent());
		
	}


	/*
	 * 1.This method is aimed at testing polynomial properties
	 * 2. Currently it does the follow to Polynomials
	 * 		#Multiplies two Polynomials
	 * 		#Sums two Polynomials
	 * 		#multiplies two Polynomials
	 * 		#differentiates two Polynomials
	 * 		#evaluates two Polynomials
	 * 3. It returns nothing.
	 * */
	public static void PolynomialTest() 
	{
		System.out.print("\nPolynomial test");
		System.out.print("\nFirst poly\n");
		PolynomialTerm polyterm = new PolynomialTerm(4,"x",6);
		PolynomialTerm polyterm2 = new PolynomialTerm(2,"x",6);
		PolynomialTerm polyterm3 = new PolynomialTerm(2,"x",5);
		Polynomial polynomial= new Polynomial();
		polynomial.addTerm(polyterm);
		polynomial.addTerm(polyterm2);
		polynomial.addTerm(polyterm3);
		polynomial.printPolynomial();

		System.out.print("\n\n\nSecond poly\n");
		PolynomialTerm polyterm4 = new PolynomialTerm(3,"x",5);
		PolynomialTerm polyterm5 = new PolynomialTerm(2,"x",4);
		PolynomialTerm polyterm6 = new PolynomialTerm(1,"x",0);
		Polynomial polynomial2= new Polynomial();
		polynomial2.addTerm(polyterm4);
		polynomial2.addTerm(polyterm5);
		polynomial2.addTerm(polyterm6);
		polynomial2.printPolynomial();

		System.out.print("\n\n\nthird poly\n");
		Polynomial polynomial3= polynomial.MultiplyPolynomials(polynomial2);
		polynomial3.printPolynomial();

		System.out.print("\n\n\nfourth poly\n");
		Polynomial polynomial4= polynomial.AddPolynomials(polynomial2);
		polynomial4.printPolynomial();

		System.out.print("\n\n\nfifth poly\n");
		Polynomial polynomial5= polynomial4.DerivativeOfPolynomial();
		polynomial5.printPolynomial();

		System.out.print("\n\nValue of fifth poly at 2\n");
		System.out.print(polynomial5.ValueOfPolynomialAt(.5));

		System.out.print("\n\n\nsixth poly\n");
		Polynomial polynomial6= polynomial.IntegralOfPolynomial();
		polynomial6.printPolynomial();

		System.out.print("\nroots of a polynomial\n");
		PolynomialTerm polyterm7 = new PolynomialTerm(1,"x",2);
		PolynomialTerm polyterm8 = new PolynomialTerm(-4,"x",0);
		Polynomial polynomial7=new Polynomial();
		polynomial7.addTerm(polyterm7);
		polynomial7.addTerm(polyterm8);
		System.out.print(polynomial7.roots(56));
	}


	public static void ColVectorTest() throws NumberFormatException, IOException 
	{
		System.out.print("\nTesting Column Vector\n");
		System.out.print("\nInput Col Vector\n");
		ColumnVector x = new ColumnVector();
		x.getInfo();

		System.out.print("\nOutput Col Vector\n");
		x.printMatrix();

		System.out.print("\n Demoing Transfer Function\n");
		double[][] newVals={{3},{4},{5}};
		ColumnVector y = new ColumnVector();
		y.transferMatrix(newVals);
		y.printMatrix();

		System.out.print("\n Demoing Col Multiplying by matrix\n");
		Matrix Changer = new Matrix();
		Changer.getInfo();
		Changer.multiply(x).printMatrix();

		System.out.print("\nDemoing Col Dot product\n");

		System.out.print("Dot:"+x.ColDot(y)+"\n");


	}
	public static void RowVectorTest() throws NumberFormatException, IOException 
	{
		// TODO Auto-generated method stub
		System.out.print("\nTesting Row Vector\n");
		System.out.print("\nInput Row Vector\n");
		RowVector x = new RowVector();
		x.getInfo();

		System.out.print("\nOutput Row Vector\n");
		x.printMatrix();

		System.out.print("\nDemoing Transfer Function\n");
		double[][] newVals={{3,4,5,6}};
		RowVector y = new RowVector();
		y.transferMatrix(newVals);
		y.printMatrix();

		System.out.print("\nDemoing Row Multiplying by matrix\n");
		Matrix Changer = new Matrix();
		Changer.getInfo();
		x.multiply(Changer).printMatrix();

		System.out.print("\nDemoing Row Dot product\n");

		System.out.print("Dot:"+x.RowDot(y)+"\n");
	}
}

