package RootFinder.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import dailyprogrammer.RootFinder.Core.Polynomial;
import dailyprogrammer.RootFinder.Core.Polynomial.Term;

public class PolynomialTest {

	@Test
	public void termFromString() {
		String s = "3x^2";
		Term t = new Term(s);
		assertTrue(t.exponent == 2);
		assertTrue(t.coefficient == 3);
	}
	
	@Test
	public void termFromStringNeg() {
		String s = "-3x^-2";
		Term t = new Term(s);
		assertTrue(t.exponent == -2);
		assertTrue(t.coefficient == -3);
	}
	@Test
	public void termFromStringFloat() {
		String s = "-3.1x^-2.1";
		Term t = new Term(s);
		assertTrue(t.exponent == -2.1);
		assertTrue(t.coefficient == -3.1);
	}
	
	@Test
	public void termFromStringNoCoeffiecient() {
		String s = "-3.1x^-2.1";
		Term t = new Term(s);
		assertTrue(t.exponent == -2.1);
		assertTrue(t.coefficient == -3.1);
	}
	
	@Test
	public void termFromStringNoCo() {
		String s = "x^2";
		Term t = new Term(s);
		assertTrue(t.exponent == 2);
		assertTrue(t.coefficient == 1);
	}

	@Test
	public void termFromStringNoCoNoExp() {
		String s = "x";
		Term t = new Term(s);
		assertTrue(t.exponent == 1.0);
		assertTrue(t.coefficient == 1.0);
	}
	
	@Test
	public void termFromStringNoX() {
		String s = "4";
		Term t = new Term(s);
		assertTrue(t.exponent == 0.0);
		assertTrue(t.coefficient == 4.0);
	}
	
	
	@Test
	public void polyFromString() {
		String s = "3x^2-2x-1";

		Polynomial p = new Polynomial(s);
		
		Term t2 = p.terms.get(0);
		Term t1 = p.terms.get(1);
		Term t0 = p.terms.get(2);

		assertTrue(t2.exponent == 2);
		assertTrue(t2.coefficient == 3);
		
		assertTrue(t1.exponent == 1);
		assertTrue(t1.coefficient == -2);
		
		assertTrue(t0.exponent == 0);
		assertTrue(t0.coefficient == -1);
	}
	
	@Test
	public void polyFromStringNeg() {
		String s = "-3x^2-2x-1";

		Polynomial p = new Polynomial(s);
		
		Term t2 = p.terms.get(0);
		Term t1 = p.terms.get(1);
		Term t0 = p.terms.get(2);

		assertTrue(t2.exponent == 2);
		assertTrue(t2.coefficient == -3);
		
		assertTrue(t1.exponent == 1);
		assertTrue(t1.coefficient == -2);
		
		assertTrue(t0.exponent == 0);
		assertTrue(t0.coefficient == -1);
	}
	
	@Test
	public void polyFromStringLonger() {
		String s = "x^4-3x^2-2x+1";

		Polynomial p = new Polynomial(s);
		
		Term t3 = p.terms.get(0);
		Term t2 = p.terms.get(1);
		Term t1 = p.terms.get(2);
		Term t0 = p.terms.get(3);

		
		assertTrue(t3.exponent == 4);
		assertTrue(t3.coefficient == 1);
		
		assertTrue(t2.exponent == 2);
		assertTrue(t2.coefficient == -3);
		
		assertTrue(t1.exponent == 1);
		assertTrue(t1.coefficient == -2);
		
		assertTrue(t0.exponent == 0);
		assertTrue(t0.coefficient == 1);
	}
	
	
	@Test
	public void termDeriv() {
		String s = "5x^4";
		Term t = new Term(s);
		Term deriv = t.derivative();
		assertTrue(deriv.coefficient == 20.0);
		assertTrue(deriv.exponent == 3.0);
	}
	
	@Test
	public void termDerivNoCo() {
		String s = "x^4";
		Term t = new Term(s);
		Term deriv = t.derivative();
		assertTrue(deriv.coefficient == 4.0);
		assertTrue(deriv.exponent == 3.0);
	}
	
	@Test
	public void termDerivNeg() {
		String s = "-1x^-4";
		Term t = new Term(s);
		Term deriv = t.derivative();
		assertTrue(deriv.coefficient == 4.0);
		assertTrue(deriv.exponent == -5.0);
	}
	
	@Test
	public void termDerivNoEx() {
		String s = "-2x";
		Term t = new Term(s);
		Term deriv = t.derivative();
		assertTrue(deriv.coefficient == -2);
		assertTrue(deriv.exponent == 0.0);
	}
	
	@Test
	public void termDerivZero() {
		String s = "-2";
		Term t = new Term(s);
		Term deriv = t.derivative();
		assertTrue(deriv.coefficient == 0.0);
		assertTrue(deriv.exponent == 0.0);
		assertTrue(deriv.value(10) == 0.0);

	}
	
	@Test
	public void polyDeriv(){
		String s = "3x^2-2x-1";
		Polynomial p = new Polynomial(s);
		Polynomial deriv = p.derivative();
		
		Term t1 = deriv.terms.get(0);
		Term t0 = deriv.terms.get(1);
		assertTrue(deriv.terms.size() == 2);

		assertTrue(t1.exponent == 1);
		assertTrue(t1.coefficient == 6);
		
		assertTrue(t0.exponent == 0);
		assertTrue(t0.coefficient == -2);
	
	}
	
	@Test
	public void polyDerivLonger(){
		String s = "x^4-3x^2-2x+1";

		Polynomial p = new Polynomial(s);
		Polynomial deriv = p.derivative();
		
		Term t2 = deriv.terms.get(0);
		Term t1 = deriv.terms.get(1);
		Term t0 = deriv.terms.get(2);
		assertTrue(deriv.terms.size() == 3);

		assertTrue(t2.exponent == 3);
		assertTrue(t2.coefficient == 4);
		
		assertTrue(t1.exponent == 1);
		assertTrue(t1.coefficient == -6);
		
		assertTrue(t0.exponent == 0);
		assertTrue(t0.coefficient == -2);
	}
	
	@Test
	public void termFromAdding(){
		Term orig = new Term("8");
		Term toAdd = new Term("7");

		
		Term actual = orig.termFromAdding(toAdd);
		Term expected = new Term("15");
		assertTrue(actual.equals(expected));

	}
	
	@Test
	public void termFromAddingNegAndExp(){
		Term orig = new Term("8x^2");
		Term toAdd = new Term("-2x^2");
		
		Term actual = orig.termFromAdding(toAdd);
		Term expected = new Term("6x^2");
		
		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void polyFromAddingTerm(){
		
		String s = "3x^2-2x-1";
		Polynomial p = new Polynomial(s);
		
		Term toAdd = new Term("-2x^2");
		
		Polynomial actual = p.polyFromAdding(toAdd);
		Polynomial expected = new Polynomial("x^2-2x-1");
		
		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void polyFromAddingTermMultiple(){
		
		String s = "3x^4-2x-1";
		Polynomial p = new Polynomial(s);
		
		Term toAdd1 = new Term("x^4");
		Term toAdd2 = new Term("3x");
		Term toAdd3 = new Term("2x^2");

		Polynomial actual = p.polyFromAdding(toAdd1).polyFromAdding(toAdd2).polyFromAdding(toAdd3);
		Polynomial expected = new Polynomial("4x^4+2x^2+x-1");
		assertTrue(actual.equals(expected));
	}
	
	
	@Test
	public void toStringTest(){
		String s = "3x^4-2x-1";
		Polynomial p = new Polynomial(s);
		
		String expected = "3.0x^4.0-2.0x-1.0";
		String found = p.toString();
		assertTrue(expected.equals(found));
	}
	
	
	
	
}
