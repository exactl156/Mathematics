package math2;

import java.util.ArrayList;

public class Polynomial 
{
int numberOfPolynomialTerms;
ArrayList<PolynomialTerm> ListOfTerms;

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
	for(int i=0;i<ListOfTerms.size();i++) 
	{
		ListOfTerms.get(i).PrintPolynomialTerm();
		System.out.print("+");
	}
}
public void addTerm(PolynomialTerm t) 
{
	for(int i=0;i<ListOfTerms.size();i++) 
	{
		if(ListOfTerms.get(i).exponent==t.exponent) 
		{
			ListOfTerms.get(i).coeficient+=t.coeficient;
			return;
		}
//		System.out.print("+");
	}
	ListOfTerms.add(t);
}

}
