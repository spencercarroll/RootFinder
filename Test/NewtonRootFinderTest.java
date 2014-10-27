package RootFinder.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import dailyprogrammer.RootFinder.Core.NewtonRootFinder;
import dailyprogrammer.RootFinder.Core.Polynomial;

public class NewtonRootFinderTest {

	@Test
	public void normal() {
		Polynomial p = new Polynomial("x^2-1");
		double root1 = NewtonRootFinder.findRoot(p, -0.001);
		double root2 = NewtonRootFinder.findRoot(p, 0.001);
		assertTrue(root1 == -1.0);
		assertTrue(root2 == 1.0);
	}
	
	@Test
	public void normalLonger() {
		Polynomial p = new Polynomial("2x^3-3x^2-5x+6");
		double root1 = NewtonRootFinder.findRoot(p, 4);
		assertTrue(root1 == 2.0);
	}
	
	@Test//This is a test based on the way findRoots finds roots between interior vertices.
	public void justPastAvgVertexSlopePoint() {
		Polynomial p = new Polynomial("2x^3-3x^2-5x+6");
		double avgSlopePoint = 0.5;
		double root1 = NewtonRootFinder.findRoot(p, avgSlopePoint+0.001);
		assertTrue(root1 == 1.0);
	}
	
	@Test
	public void linear() {
		Polynomial p = new Polynomial("x-1");
		double root1 = NewtonRootFinder.findRoot(p, 10);
		assertTrue(root1 == 1.0);
	}
	
	@Test//a weak link in newton's method.
	public void hardToFind() {
		Polynomial p = new Polynomial("x^2");
		double root1 = NewtonRootFinder.findRoot(p, 10);
		assertEquals(root1, 0.0, 0.00000001);
	}
	
	

}
