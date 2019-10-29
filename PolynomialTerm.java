package math2;
/*
 * this class is devoted to describing the most simple polynomial term Ax^B. and its properties 
 * associated with that. Currently there are two known properties.
 * 1. We can multiply polynomials
 * 2. We can add polynomials
 * 
 * */
public class PolynomialTerm {

	double exponent;
	String variable; 
	double coeficient;
	
	
	public PolynomialTerm( double coeficient, String variable,double exponent) 
	{
		this.exponent=exponent;
		this.variable=variable;
		this.coeficient=coeficient;
	}
	
	
	/*
	 * 1.This method simply aims to print out the polynomial in this object
	 * 2. It does so by printing coeficient+variable+"^"+exponent using the system
	 * 3. It returns nothing
	 * */
	public void PrintPolynomialTerm() 
	{
		if(exponent>0) 
		{
			System.out.print("("+coeficient+variable+"^"+exponent+")");
		}
		else 
		{
			System.out.print("("+coeficient+")");
		}
	}
	
	
	/*
	 * 1. this method multiplies polynomials of the same variable together. Currently it has no checks
	 * on whether they are same variable
	 * 2. It does so by returning a new polynomial with coeficients multiplied and exponents added.
	 * 3. Its return type is polynomial.
	 * */
	public PolynomialTerm multiplyPolynomialTerm(PolynomialTerm another) 
	{
		return new PolynomialTerm(coeficient*another.coeficient,variable,exponent+another.exponent);
	}
	
	
	 /* 1. this method adds polynomials of the same variable together. Currently it has no checks
	 * on whether they are same variable or they are of the same order/degree
	 * 2. It does so by returning a new Polynomial with coeficients multiplied and exponents added.
	 * 3. Its return type is polynomial.
	 * */
	public PolynomialTerm AddPolynomialTerm(PolynomialTerm another) 
	{
		return new PolynomialTerm(coeficient+another.coeficient,variable,exponent);
	}
	
	
	/*
	 * 1. This method returns the derivative of a polynomial term
	 * 2. It does so by returning a polynoimal with coeficient multiplied by exponent in coeficient place of 
	 * intialization and a decrement of 1 in exponent
	 * 3. It returns a new term thats the derivative of the term.
	 * */
	public PolynomialTerm DerivateOfTerm() 
	{
		if(exponent>0) 
		{
			return new PolynomialTerm(coeficient*exponent,variable,exponent-1);
		}
		else 
		{
			return new PolynomialTerm(0,variable,0);
		}
	}
	
	/*
	 * 1. This method evaluates the function at a point specfied by the input
	 * 2. For now the method does so by multiplying the coeficient with the Math.power version of point
	 * to the exponent value.
	 * 3. this method returns the value of a double at a point pt
	 * */
	public double ValueOfPolynomialAt(double pt) 
	{
		return coeficient*Math.pow(pt, exponent);
	}
	
	
	/*
	 * 1. this method returns the integral of the term
	 * 2. it does so by incrementing the exponent and divinding the coeficient by exponent+1
	 * 3. It returns a polynomial term
	 * */
	public PolynomialTerm IntegralOfTerm() 
	{
			return new PolynomialTerm(coeficient/(exponent+1),variable,exponent+1);
	}
}
