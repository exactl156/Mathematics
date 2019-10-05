package math2;

public class Equation {
public String original;
public String lhs;
public String rhs;
public Equation(String x) 
{
	original = x;
	//System.out.print(x);
	split();
}
public void split() 
{
	int equaltoSign = original.indexOf("=");
	lhs=original.substring(0, equaltoSign);
	rhs=original.substring(equaltoSign+1, original.length());
	//System.out.print(rhs);
}
}
