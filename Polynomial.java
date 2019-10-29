package math2;

import java.util.ArrayList;
/*
 * This class is devoted to polynomials of the form ax^n+bx^n-1...yx+z. These polynomials have a variety of properties
 * listed below:
 * 		1. Printing polynomial
 * 		2. Extending the polynomial by adding a term
 * 		3. multiplying such polynomials
 * 		4. adding such polynomials
 * 		5. having a derivative
 * 		6. Having a value
 * */
public class Polynomial 
{
	public int numberOfPolynomialTerms;
	public ArrayList<PolynomialTerm> ListOfTerms;

	public Polynomial() 
	{
		this.numberOfPolynomialTerms = 0;
		ListOfTerms = new ArrayList<PolynomialTerm>();
	}


	/*
	 * 1. this method aims to print the entire polynomial
	 * 2. it does so by going through the list of PolynomialTerms and uses the term's print method to print if out.
	 * Alsp the current version adds a plus sign to indicate addition at all times
	 * 3. this method returns nothing.
	 * */
	public void printPolynomial() 
	{
		System.out.print("( ");
		for(int i=0;i<ListOfTerms.size();i++) 
		{
			ListOfTerms.get(i).PrintPolynomialTerm();
			if(i!=ListOfTerms.size()-1) 
			{
				System.out.print("+");
			}
		}
		System.out.print(" )");
	}


	/*
	 * 1. this method aims to add values to this object
	 * 2. it does so by going through the list of PolynomialTerms and determining if polynomial has been added 
	 * by degree. If it has, the polynomialTerm simply increments the polynomialTerm already in list by its
	 * coeficient and returns nothing. If the PolynomialTerm isn't found, the term is added to end of the list
	 * 3. this method returns nothing.
	 * */
	public void addTerm(PolynomialTerm t) 
	{
		for(int i=0;i<ListOfTerms.size();i++) 
		{
			if(ListOfTerms.get(i).exponent==t.exponent) 
			{
				ListOfTerms.get(i).coeficient+=t.coeficient;
				return;
			}
		}
		ListOfTerms.add(t);
		numberOfPolynomialTerms++;
	}


	/*
	 * 1. The purpose of this method is to multiply two Polynomial together
	 * 2. It does so by using a double for loop for each polynomial. It then multiplies them together and uses
	 * the addPolynomial function to add the functions together.
	 * 3. Its return type is a polynomial that is the product of the multiplication
	 * */
	public Polynomial MultiplyPolynomials(Polynomial secondPoly) 
	{
		Polynomial product=new Polynomial();
		PolynomialTerm newTerm;
		for(int i=0;i<ListOfTerms.size();i++) 
		{
			for(int j=0;j<secondPoly.ListOfTerms.size();j++) 
			{
				newTerm = ListOfTerms.get(i).multiplyPolynomialTerm(secondPoly.ListOfTerms.get(j));
				product.addTerm(newTerm);
			}
		}

		return product;
	}


	/*
	 * 1. The purpose of this method is to sum two polynomials together
	 * 2. It does so by first taking the second polynomial and copying all values there. Then it parses 
	 * through current values and adds them individually to the current one
	 * 3. Its return type is a polynomial that is the sum of current and arguments terms.
	 * */
	public Polynomial AddPolynomials(Polynomial secondPoly) 
	{
		Polynomial sum=secondPoly;
		PolynomialTerm newTerm;
		for(int i=0;i<ListOfTerms.size();i++) 
		{
			newTerm=ListOfTerms.get(i);
			sum.addTerm(newTerm);
		}
		return sum;
	}


	/*
	 * 1. This method calculates the derivative of functions as a another polynomial
	 * 2. It does so by calling the deriveative function on each term to obtain another term and then adding
	 * each term to a new polynomial.
	 * 3. It returns a polynomial that is the derivitive of the polynomial called on*/
	public Polynomial DerivativeOfPolynomial() 
	{
		Polynomial Derivative=new Polynomial();
		PolynomialTerm newTerm;
		for(int i=0;i<ListOfTerms.size();i++) 
		{
			newTerm=ListOfTerms.get(i).DerivateOfTerm();
			Derivative.addTerm(newTerm);
		}
		return Derivative;
	}


	/*
	 * 1. This method evaluates the polynomial at a point specfied by the input
	 * 2. For now the method does so by multiplying the coeficient with the Math.power version of point
	 * to the exponent value.
	 * 3. this method returns the value of a double at a point pt
	 * */
	public double ValueOfPolynomialAt(double pt) 
	{
		double value=0;
		for(int i=0;i<ListOfTerms.size();i++) 
		{
			value+=ListOfTerms.get(i).ValueOfPolynomialAt(pt);
		}
		return value;
	}

	
	/*
	 * 1. this method returns the integral of the Polynomial
	 * 2. it does so by using a for loop to go through each term and calls their integral method.
	 * 3. It returns a polynomial
	 * */
	public Polynomial IntegralOfPolynomial()
	{
		Polynomial Integral=new Polynomial();
		PolynomialTerm newTerm;
		for(int i=0;i<ListOfTerms.size();i++) 
		{
			newTerm=ListOfTerms.get(i).IntegralOfTerm();
			Integral.addTerm(newTerm);
		}
		return Integral;
	}
	
	
	/*
	 * 1. this method calculates the root of the polynomial given an intial guess.
	 * 2. it does so by incrementing the exponent and divinding the coeficient by exponent+1
	 * 3. It returns a polynomial term
	 * */
	public double roots(double guess) 
	{
		double x0=guess;
		ArrayList<Double> rootValues=new ArrayList<Double>();
		//f =  The function whose root we are trying to find
		Polynomial	fprime =this.DerivativeOfPolynomial();// %The derivative of f(x)
		double	tolerance = .0000001;// %7 digit accuracy is desired
		double	epsilon = .00000000000001; //%Don't want to divide by a number smaller than this

		int	maxIterations = 20; //%Don't allow the iterations to continue indefinitely
		boolean	haveWeFoundSolution = false; //%Have not converged to a solution yet
		double y,yprime,x1;
		for (int i = 0; i< maxIterations;i++) 
		{

			y = this.ValueOfPolynomialAt(x0);
			yprime = fprime.ValueOfPolynomialAt(x0);

			if(Math.abs(yprime) < epsilon) //%Don't want to divide by too small of a number
				//% denominator is too small
				break; //%Leave the loop

			x1 = x0 - y/yprime; //%Do Newton's computation

			if(Math.abs(x1 - x0) <= tolerance) //%If the result is within the desired tolerance
			{ 
				haveWeFoundSolution = true;				 
				break; //%Done, so leave the loop
			}

			x0 = x1; //%Update x0 to start the process again
		}
		x0=Math.round(x0*1000)/1000;
		return x0;
	}
}
