package RootFinder.Test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dailyprogrammer.RootFinder.Core.AllRootsFinder;
import dailyprogrammer.RootFinder.Core.Polynomial;

public class AllRootsFinderTest {

	private static final double error = 0.00000001;
	@Test
	public void findRootsLinear() {
		Polynomial p = new Polynomial("x-2");
		List<Double> roots = AllRootsFinder.findRoots(p);
		assertTrue(roots.size() == 1 && roots.get(0) == 2.0);		
	}
	
	
	@Test
	public void findRootsQuadratic() {
		Polynomial p = new Polynomial("x^2-3x+2");
		List<Double> roots = AllRootsFinder.findRoots(p);
		assertTrue(roots.size() == 2);
		assertEquals(roots.get(0), 1.0, error);
		assertEquals(roots.get(1), 2.0, error);
	}
	
	@Test
	public void findRootsCubic() {
		Polynomial p = new Polynomial("2x^3-3x^2-5x+6");
		List<Double> roots = AllRootsFinder.findRoots(p);
		assertTrue(roots.size() == 3);
		assertEquals(roots.get(0), -1.5, error);
		assertEquals(roots.get(1), 1.0, error);
		assertEquals(roots.get(2), 2.0, error);
	}
	
	@Test
	public void findRootNakedCube() {
		Polynomial p = new Polynomial("x^3");
		List<Double> roots = AllRootsFinder.findRoots(p);
		assertTrue(roots.size() == 1);
		assertEquals(roots.get(0), 0.0, error);
	}
	
	@Test
	public void findRootNakedCubeOffset() {
		Polynomial p = new Polynomial("x^3+1");
		List<Double> roots = AllRootsFinder.findRoots(p);
		assertTrue(roots.size() == 1);
		assertEquals(roots.get(0), -1.0, error);
	}
	
	@Test
	public void formalInput1() {
		Polynomial p = new Polynomial("4x^2-11x-3");
		List<Double> roots = AllRootsFinder.findRoots(p);
		assertTrue(roots.size() == 2);
		assertEquals(roots.get(0), -0.25, error);
		assertEquals(roots.get(1), 3, error);
	}
	
	
	@Test
	public void formalInput2() {
		Polynomial p = new Polynomial("4x-8");
		List<Double> roots = AllRootsFinder.findRoots(p);
		assertTrue(roots.size() == 1);
		assertEquals(roots.get(0), 2.0, error);
	}
	
	@Test
	public void formalInput3() {
		Polynomial p = new Polynomial("x^4-2x^3+7x^2-16x+4");
		List<Double> roots = AllRootsFinder.findRoots(p);
		assertTrue(roots.size() == 2);
		assertEquals(roots.get(0), 0.28249374733529076, error);
		assertEquals(roots.get(1), 2.0, error);
	}
	
	@Test
	public void formalInput4() {
		Polynomial p = new Polynomial("x^2-7x+6");
		List<Double> roots = AllRootsFinder.findRoots(p);
		assertTrue(roots.size() == 2);
		assertEquals(roots.get(0), 1.0, error);
		assertEquals(roots.get(1), 6.0, error);
	}
	
	
	@Test
	public void funkyInput1() {
		Polynomial p = new Polynomial("2x^5+4x^4+3x^3+2x^2+4x-20");
		List<Double> roots = AllRootsFinder.findRoots(p);
		assertTrue(roots.size() == 1);
		assertEquals(roots.get(0), 1.101979375255, error);
	}
	
	
	@Test
	public void multipleInteriorRoots1() {
		Polynomial p = new Polynomial("x^7-14x^5+49x^3-36x");
		List<Double> roots = AllRootsFinder.findRoots(p);
		assertTrue(roots.size() == 7);
		assertEquals(roots.get(0), -3.0, error);
		assertEquals(roots.get(1), -2.0, error);
		assertEquals(roots.get(2), -1.0, error);
		assertEquals(roots.get(3), 0.0, error);
		assertEquals(roots.get(4), 1.0, error);
		assertEquals(roots.get(5), 2.0, error);
		assertEquals(roots.get(6), 3.0, error);
	}
	
	
	@Test
	public void multipleInteriorRoots2() {
		double oneF = (1.0/14.0);
		String s = ""+oneF+"x^4+"+oneF+"x^3-"+13.0*oneF+"x^2-"+oneF+"x+"+(6.0/7.0);
		Polynomial p = new Polynomial(s);
		List<Double> roots = AllRootsFinder.findRoots(p);
		assertTrue(roots.size() == 4);
		assertEquals(roots.get(0), -4.0, error);
		assertEquals(roots.get(1), -1.0, error);
		assertEquals(roots.get(2), 1.0, error);
		assertEquals(roots.get(3), 3.0, error);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
