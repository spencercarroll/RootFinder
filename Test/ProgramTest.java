package RootFinder.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import dailyprogrammer.RootFinder.Core.AllRootsFinder;

public class ProgramTest {

	private static final double error = 0.00000001;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private PrintStream origOut;
	private PrintStream origErr;
	
	@Before
	public void setUpOutStreams() {
		origOut = System.out;
		origErr = System.err;
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	public void resetOutStreams(){
		System.setOut(origOut);
		System.setErr(origErr);
	}
	
	@Test
	public void formalInput1() {
		String input = "4x^2-11x-3\n";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		
		AllRootsFinder.main(null);
		String actual = outContent.toString();
		String expected = "-0.25 or 3.0\n";
		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void formalInput2() {
		String input = "4x-8";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		
		AllRootsFinder.main(null);
		String actual = outContent.toString();
		String expected = "2.0\n";
		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void formalInput3() {
		String input = "x^4-2x^3+7x^2-16x+4";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		
		AllRootsFinder.main(null);
		String actual = outContent.toString();
		String expected = "0.28249374733529076 or 2.0\n";
		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void formalInput4() {
		String input = "x^2-7x+6";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		
		AllRootsFinder.main(null);
		String actual = outContent.toString();
		String expected = "1.0 or 6.0\n";
		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void multipleInteriorRoots1() {
		String input = "x^7-14x^5+49x^3-36x";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		
		AllRootsFinder.main(null);
		String actual = outContent.toString();
		String expected = "-3.0 or -2.0 or -1.0 or 0.0 or 1.0 or 2.0 or 3.0\n";
		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void nakedCube() {
		String input = "x^3";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		
		AllRootsFinder.main(null);
		String actualStr = outContent.toString();
		double actual = Double.parseDouble(actualStr);
		
		double expected = 0.0;
		assertEquals(actual, expected, error);
	}

}
