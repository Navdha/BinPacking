/**
 * Project: JavaGE Library
 * Author:  Loukas Georgiou
 * Date:	15 April 2006
 * 
 * Copyright 2006, 2008 Loukas Georgiou.
 * This file is part of JavaGE (jGE) Library.
 * 
 * jGE Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * jGE Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with jGE Library.  If not, see <http://www.gnu.org/licenses/>.
 */ 

package bangor.aiia.jge.ps;

import bangor.aiia.jge.core.Core;
import bangor.aiia.jge.core.Evaluator;
import bangor.aiia.jge.population.Individual;
import bangor.aiia.jge.population.Population;

/**
 * The class <code>HammnigDistance</code> implements a problem specification
 * of finding a target string. Namely, it compares the Hamming Distance
 * between the phenotypes of the individuals of the population and the target string.<br>
 * The phenotypes must be fixed-length strings.
 * <br><br>
 * In information theory, the Hamming distance between two strings 
 * of equal length is the number of positions for which 
 * the corresponding symbols are different.<br>
 * Put another way, it measures the number of substitutions required 
 * to change one into the other, or the number of errors that 
 * transformed one string into the other.
 * 
 * @author 	Loukas Georgiou
 * @version 1.0, 15/04/06
 * @see 	Evaluator
 * @see 	Population
 * @see 	Individual
 * @see 	Core
 * @since 	JavaGE 0.1
 */
public class DemoD implements Evaluator <String, String> {

private String target = null;
	
	// Flag whether a solution has be found or not
	private boolean solutionFound = false;
	
	/**
	 * Default Constructor. Should not be used.
	 */
	private DemoD() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param target The target string that must be found.
	 */
	public DemoD(String target) {
		this();
		this.target = target;
	}
	
	/**
	 * Evaluates the phenotype (which must be a fixed-length string)
	 * of each individual of the population and assigns the Raw Fitness Value 
	 * according to the Hamming Distance between the phenotype and the target string.<br>
	 * If an individual's phenotype has not the same length with the target string
	 * then the individual is invalid (sets valid to false).<br><br> 
	 * The Raw Fitness is calculated with the following formula:<br>
	 * <code>Raw Fitness = Phenotype Length - Hamming Distance.</code>
	 *  
	 * @param population The population to be evaluated.
	 */
	public void evaluate(Population<String, String> population) {
		
		solutionFound = false;
		int size = population.size();
		int length = target.length();
		Individual<String, String> individual;
		String current = null;
	
		
		// Iterate the population
		for (int i = 0; i < size; i++) {
			
			individual = population.getIndividual(i);
			current = individual.getPhenotype().value();
			System.out.println(current);
			if(current.contains("<")){
				individual.setValid(false);
				System.out.println("false");
			}
			else {
			String[] currently = current.split("\\s");
			int j=0;
			while(j < currently.length)
			{
				ExecPhenotype(currently[j++]);
				
			}
				
			System.out.println();
				// Assign Raw Fitness and set Individual as Valid
				individual.setRawFitnessValue((double) (length));
				individual.setValid(true);
				
				// Check if a solution is found
				
			}
			
		}
		
	}
	
	private void ExecPhenotype(String Pheno) {
		System.out.println("Pheno = " +Pheno);
		if(Pheno.contains("=")){
		String[] vals = Pheno.split("=");
		String[] values=vals[1].split(",");
		if(Pheno.contains("filled")||Pheno.contains("random")){
			int number=Integer.parseInt(values[0]);
			double ignoreVal=Double.parseDouble(values[1]);
			String removeVal=values[2];
			if(Pheno.contains("highest")){
				highest_filled(number, ignoreVal, removeVal);
			}
			else if(Pheno.contains("lowest")){
				lowest_filled(number, ignoreVal, removeVal);
			}
			else{
				random_bin(number, ignoreVal, removeVal);
			}
		}
		else if(Pheno.contains("gap")){
			int number=Integer.parseInt(values[0]);
			String threshold=values[1];
			double ignoreVal=Double.parseDouble(values[2]);
			String removeVal=values[3];
			gap_less_than(number, threshold, ignoreVal, removeVal);
		}
		else if(Pheno.contains("num_of_pieces")){
			int number=Integer.parseInt(values[0]);
			int numpieces=Integer.parseInt(values[1]);
			double ignoreVal=Double.parseDouble(values[2]);
			String removeVal=values[3];
			num_of_pieces(number, numpieces, ignoreVal, removeVal);
		}
	}
		else{
			if(Pheno.contains("best")){
				best_fit_decreasing();
			}
			else if(Pheno.contains("worst")){
				worst_fit_decreasing();
			}
			else if(Pheno.contains("best")){
				first_fit_decreasing();
			}
		}
	}

	private void first_fit_decreasing() {
		
		System.out.println("I am first_fit_decreasing");
	}

	private void worst_fit_decreasing() {
		// TODO Auto-generated method stub
		System.out.println("I am worst_fit_decreasing");
	}

	private void best_fit_decreasing() {
		// TODO Auto-generated method stub
		System.out.println("I am best_fit_decreasing");
	}

	private void gap_less_than(int num, String threshold, double ignore,String remove) {
		System.out.println("I am gap_less_than(" +num +"," +threshold +"," +ignore +"," +remove +")");
		
	}
	
	private void num_of_pieces(int num, int numpieces, double ignore,String remove) {
		System.out.println("I am num_of_pieces(" +num +"," +numpieces +"," +ignore +"," +remove +")");
		
	}

	private void highest_filled(int num, double ignore, String remove) {
		System.out.println("I am highest_filled(" +num +"," +ignore +"," +remove +")");
	}
	
	private void lowest_filled(int num, double ignore, String remove) {
		System.out.println("I am lowest_filled(" +num +"," +ignore +"," +remove +")");
	}
	
	private void random_bin(int num, double ignore, String remove) {
		System.out.println("I am random_bin(" +num +"," +ignore +"," +remove +")");
	}

	/**
	 * Returns true if the solution has been found.<br>
	 * For the Hamming Distance problem a solution is found
	 * when the current population contains an individual with
	 * phenotype equal to the target string.
	 * 
	 * @return True, if the solution has been found.
	 */
	public boolean solutionFound() {
		return solutionFound;
	}

	
	/**
	 * Returns the target string to be found.
	 * 
	 * @return The target string to be found.
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * Sets the target string to be found.
	 * 
	 * @param target The target string to be found.
	 */
	public void setTarget(String target) {
		this.target = target;
	}

}
