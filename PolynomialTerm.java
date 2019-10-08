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
	 * 
	 * */
	public void PrintPolynomialTerm() 
	{
		System.out.print(coeficient+variable+"^"+exponent);
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
}
