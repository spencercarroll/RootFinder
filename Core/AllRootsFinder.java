package RootFinder.Core;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class AllRootsFinder {
	private static final double smallestStep = 0.00000001;
	private static final double stepTowardsInfinity = 1;
	
	
	public static List<Double> findRoots(Polynomial p){
		if(p.terms.get(0).exponent == 1.0){
			return new ArrayList<Double>(Arrays.asList(LinearRootFinder.findRoot(p)));
		}
		List<Double> vertices = findRoots(p.derivative());
		List<Double> roots = new ArrayList<>();
		if(p.terms.get(0).exponent % 2 != 0 && vertices.size() == 0){
			//start the search anywhere!
			return new ArrayList<Double>(Arrays.asList(NewtonRootFinder.findRoot(p, 0)));
		}
		roots.add(NewtonRootFinder.findRoot(p, vertices.get(0) - stepTowardsInfinity));
		roots.add(NewtonRootFinder.findRoot(p, vertices.get(vertices.size()-1) + stepTowardsInfinity));
		
		//zip vertices.tail vertices.init sure would be handy here
		for(int i = 1; i < vertices.size(); i++){
			double x0 = vertices.get(i-1);
			double x1 = vertices.get(i);
			if(((p.value(x0) < 0) && (p.value(x1) > 0)) || ((p.value(x1) < 0) && (p.value(x0) > 0))){
				Polynomial inflectionPoly = p.derivative().derivative();
				List<Double> inflectionPoints = findRoots(inflectionPoly);
				inflectionPoints.removeIf((x) -> {return x < x0 || x > x1;});
				double inflectionPoint = inflectionPoints.get(0);
				double inflectionSlope = p.derivative().value(inflectionPoint);
				double start = inflectionPoint;
				if(p.value(inflectionPoint) < 0){//need to head upwards
					start = inflectionSlope > 0 ? start + smallestStep : start - smallestStep;
				}else{//need to head downwards
					start = inflectionSlope > 0 ? start - smallestStep : start + smallestStep;
				}
				roots.add(NewtonRootFinder.findRoot(p, start));
			}
		}
		Collections.sort(roots);
		List<Double> returnList = new ArrayList<>();
		
		returnList.add(roots.get(0));
		for(int i = 1; i < roots.size(); i++){
			Double prevRoot = roots.get(i-1);
			Double curRoot = roots.get(i);
			if(Math.abs(prevRoot - curRoot) > smallestStep)
				returnList.add(curRoot);
		}
		return returnList;
	}
	
	
	
	public static void main(String[] args) {
		String line = linesFromStream(System.in).get(0);
		Polynomial p = new Polynomial(line);
		List<Double> roots = findRoots(p);
		for(int i = 1; i < roots.size(); i++){
			Double prev = roots.get(i-1);
			System.out.print(prev+" or ");
		}
		System.out.println(""+roots.get(roots.size()-1));
	}

	
	private static ArrayList<String> linesFromStream(InputStream stream){
		ArrayList<String> lines = new ArrayList<>();
		Scanner scanner = new Scanner(stream);
		while(scanner.hasNextLine()){
			lines.add(scanner.nextLine());
		}
		scanner.close();
		return lines;
	}

}
