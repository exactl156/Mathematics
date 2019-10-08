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
		Matrix sample = new Matrix();
		Matrix sample2 = new Matrix();
		//sample.getInfo();
		//sample2.getInfo();
		//sample.switchTwoRows(0, 1);
		//sample.subtractRowBfromA(0, 1, 1, 1);
		//sample.printMatrix();
		//Matrix result=sample.reorder();
		//int[] sa=sample.pivotArray();
		//Matrix result=sample.GaussianEliminate();
		//Matrix result=sample.Transpose();
		//Matrix result=sample.MatrixOfCofactors();
		//Matrix result=sample.Addition(sample2);
		//Matrix result=sample.multiply(sample2);
		//result.printMatrix();
		//System.out.print("he");
		//sample.inverse().multiply(sample2).printMatrix();
		//System.out.print(sample.Determinent());
		
		
		PolynomialTerm poly = new PolynomialTerm(4.5,"x",6);
		PolynomialTerm poly2 = new PolynomialTerm(2,"x",6);
		PolynomialTerm poly3 = new PolynomialTerm(2,"x",7);
		//poly.multiplyPolynomial(poly2).PrintPolynomial();
		//poly.AddPolynomial(poly2).PrintPolynomial();
		//poly.PrintPolynomial();
		Polynomial x = new Polynomial();
		x.addTerm(poly);
		x.addTerm(poly2);
		x.addTerm(poly3);
		x.printPolynomial();
	}
}
