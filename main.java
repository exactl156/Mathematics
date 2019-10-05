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
		sample.getInfo();
		sample2.getInfo();
		//sample.switchTwoRows(0, 1);
		//sample.subtractRowBfromA(0, 1, 1, 1);
		//sample.printMatrix();
		//sample.reorder();
		//int[] sa=sample.pivotArray();
		//sample.GaussianEliminate();
		//sample.Transpose();
		//sample.MatrixOfCofactors();
		//Matrix result=sample.Addition(sample2);
		Matrix result=sample.multiply(sample2);
		result.printMatrix();
		//System.out.print("he");
		System.out.print(sample.Determinent());
	}
}
