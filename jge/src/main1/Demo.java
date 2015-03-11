package main1;

import java.util.Vector;

import bangor.aiia.jge.bnf.BNFGrammar;
import bangor.aiia.jge.bnf.BNFParser;
import bangor.aiia.jge.bnf.InvalidBNFException;
import bangor.aiia.jge.core.GrammaticalEvolution;
import bangor.aiia.jge.population.Individual;
import bangor.aiia.jge.ps.DemoD;
import bangor.aiia.jge.ps.HammingDistance;
import bangor.aiia.jge.util.ConfigurationSettings;
import bangor.aiia.jge.util.LogFile;

public class Demo {
	static Vector<String> rules = null;
	 static String bnf1=null;
	 static String rootPath = ConfigurationSettings.getInstance().getRootPath() + "/bnf/";

	public static void hdExperiment() throws InvalidBNFException { 
	bnf1 = BNFParser.loadBNFGrammar(rootPath + "HDGrammar11.bnf");
	Individual<String, String> solution = null;
	String target = "111000111000101010101010101010"; 
	LogFile log = null;
	DemoD hd = new DemoD(target);
	BNFGrammar bnf = new BNFGrammar(bnf1); 
	System.out.println(bnf);
	GrammaticalEvolution ea = new GrammaticalEvolution(bnf, hd, 10, 8, 20, 40);
	ea.setCrossoverRate(0.9);
	ea.setMutationRate(0.01);
	ea.setDuplicationRate(0.01);
	ea.setPruningRate(0.01);
	ea.setMaxGenerations(100);
	ea.setLogger(log);
	solution = ea.run();
	// Also the following information can be retrieved:
	//Number_of_Generations_created = ea.lastRunGenerations(); 
	/*double Sol_Fitness_Value = solution.rawFitness();
	System.out.println("Solution's raw fitness is: " +Sol_Fitness_Value);*/
	String Sol_phenotype = solution.getPhenotype().value();
	System.out.println("Solution's phenotype: " +Sol_phenotype);
	//return solution;
	}
	public static void main(String[] args) throws Exception
	{
		//Individual<String, String> solutionM = hdExperiment();
		hdExperiment();
		//System.out.println("Solution is: " +solutionM);
		
	}
}
