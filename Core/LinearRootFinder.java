package RootFinder.Core;

public class LinearRootFinder {
	public static double findRoot(Polynomial p){
		if(p.terms.size()==1){
			return 0.0;
		}
		return -1*(p.terms.get(1).coefficient / p.terms.get(0).coefficient);
	}
}
