package RootFinder.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import dailyprogrammer.RootFinder.Core.LinearRootFinder;
import dailyprogrammer.RootFinder.Core.Polynomial;

public class LinearRootFinderTest {

	@Test
	public void normal1() {
		Polynomial p = new Polynomial("2x+1");
		double root = LinearRootFinder.findRoot(p);
		assertTrue(root == -0.5);
	}
	
	@Test
	public void negative() {
		Polynomial p = new Polynomial("-3x+1");
		double root = LinearRootFinder.findRoot(p);
		assertTrue(root == (1.0/3.0));
	}

}
