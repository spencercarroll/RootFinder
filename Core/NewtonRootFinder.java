package RootFinder.Core;

import java.util.Arrays;

import RootFinder.Core.Polynomial.Term;
public class NewtonRootFinder {
	private static final int timesToIterate = 50;

	
	
	public static double findRoot(Polynomial p, double start){
	
		for(int i = 0; i < timesToIterate; i++){
			double m = p.derivative().value(start);
			double b = p.value(start) - m * start;
			Polynomial linearEquation = new Polynomial(
					Arrays.asList(new Term[]{new Term(m,1), new Term(b,0) }));
			start = LinearRootFinder.findRoot(linearEquation);
		}
		return start;
	}
	
}
