package RootFinder.Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {

	
	public static class Term implements Comparable<Term>{
		public final double  coefficient;
		public final double exponent;
		private Term derivative;
		public Term(double coefficient, double exponent){
			this.coefficient = coefficient;
			this.exponent = exponent;
		}
		
		public Term(String s){
			String parseString = s;
			if(parseString.startsWith("-x")) parseString = "-1"+s.substring(1);
			if(parseString.startsWith("x")) parseString = "1"+s;
			
			Pattern p = Pattern.compile("(-?(\\d*\\.)?\\d+)?x(\\^(-?(\\d*\\.)?\\d+))?");
			Matcher m = p.matcher(parseString);
			if(!m.find()){//no x found so it's just a naked number.
				this.coefficient = Double.parseDouble(parseString);
				this.exponent = 0;
			}else{
				this.coefficient = Double.parseDouble(m.group(1));
				this.exponent = Double.parseDouble(m.group(4) == null ? "1.0" : m.group(4));
			}
		}
		
		public Term derivative(){
			if(derivative == null){
				if(this.exponent == 0.0){
					derivative = new Term(0,0);
				}else{
					derivative = new Term(coefficient*exponent, exponent - 1.0);
				}
			}
			return derivative;
		}
		
		public double value(double x){
			return coefficient * Math.pow(x, exponent);
		}
		
		public Term termFromAdding(Term t){
			if(t.exponent != this.exponent) return null;
			return new Term(t.coefficient + this.coefficient, this.exponent);
		}
		
		@Override
		public int compareTo(Term other){
			if(this.exponent == other.exponent) 
				return new Double(this.coefficient).compareTo(other.coefficient);
			else 
				return new Double(this.exponent).compareTo(other.exponent);
		}
		@Override
		public boolean equals(Object other){
			if(!other.getClass().equals(this.getClass())) return false;
			Term otherTerm = (Term)other;
			return this.coefficient == otherTerm.coefficient && 
					this.exponent == otherTerm.exponent;
		}
		@Override
		public String toString(){
			String exponentStr = exponent != 1.0 &&  exponent != 0.0? "^"+exponent : "";
			String xStr = exponent != 0.0? "x" : "";
			return ""+coefficient+xStr+exponentStr;
		}
	}
	
	//terms sorted from highest to lowest.
	public final List<Term> terms;
	private Polynomial derivative;
	
	public Polynomial(List<Term> terms){
		List<Term> sortedTerms = new ArrayList<>(terms);
		Collections.sort(sortedTerms, Comparator.reverseOrder());
		this.terms = Collections.unmodifiableList(sortedTerms);
	}
	
	//negative exponents will fail.
	public Polynomial(String s){
		ArrayList<String> minusStrTerms = new ArrayList<>();		
		for(String curString : s.split("-")){
			if(curString.isEmpty()) continue;
			minusStrTerms.add("-"+curString);
		}
		if(!s.startsWith("-")){
			minusStrTerms.set(0, minusStrTerms.get(0).substring(1));
		}
		List<String> plusAndMinusStrTerms = new ArrayList<>();
		for(String curMinusString : minusStrTerms){
			for( String curString : curMinusString.split("\\+")){
				if(curString.isEmpty()) continue;
				plusAndMinusStrTerms.add(curString);
			}
		}
		List<Term> newTerms = new ArrayList<>();
		for(String curString : plusAndMinusStrTerms){
			newTerms.add(new Term(curString));
		}
		
		List<Term> sortedTerms = new ArrayList<>(newTerms);
		Collections.sort(sortedTerms, Comparator.reverseOrder());
		this.terms = Collections.unmodifiableList(sortedTerms);
	}
	
	public Polynomial derivative(){
		if(derivative != null) return derivative;
		List<Term> derivTerms = new ArrayList<>();
		for(Term curTerm : terms){
			Term curDerivTerm = curTerm.derivative();
			if(curDerivTerm.coefficient != 0.0)
				derivTerms.add(curDerivTerm);
		}
		derivative = new Polynomial(derivTerms);
		return derivative;
	}
	
	public double value(double x){
		double sum = 0;
		for(Term curTerm : terms)
			sum += curTerm.value(x);
		return sum;
	}
	
	public Polynomial polyFromAdding(Term t){
		List<Term> newTerms = new ArrayList<>(terms);
		Optional<Term> toAddTo = terms.stream().filter((x)->{return x.exponent == t.exponent;}).findAny();
		toAddTo.ifPresent( (x)->{newTerms.remove(x);});
		Term newTerm = toAddTo.isPresent() ? t.termFromAdding(toAddTo.get()) : t;
		newTerms.add(newTerm);
		return new Polynomial(newTerms);
	}
	
	@Override
	public boolean equals(Object other){
		if(!other.getClass().equals(this.getClass())) return false;
		Polynomial otherPoly = (Polynomial) other;
		if(this.terms.size() != otherPoly.terms.size()) return false;
		for(int i = 0; i < this.terms.size(); i++){
			if(!this.terms.get(i).equals(otherPoly.terms.get(i))) return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < terms.size(); i++){
			if(i != 0){
				if(terms.get(i).coefficient > 0)
					sb.append("+");
			}
			sb.append(terms.get(i).toString());
		}
		return sb.toString();
	}
	
		
}
