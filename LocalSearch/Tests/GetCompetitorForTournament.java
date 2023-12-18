package Tests;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import AIs.Interpreter;
import CFG.Control;
import LS_CFG.FactoryLS;
import LS_CFG.Node_LS;
import Standard.StrategyTactics;
import ai.mayari;
import ai.abstraction.HeavyRush;
import ai.coac.CoacAI;
import ai.core.AI;
import gui.PhysicalGameStatePanel;
import rts.GameState;
import rts.PhysicalGameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;
import util.Pair;
import java.util.Set;

public class GetCompetitorForTournament {
	static int max=7000;
	static FactoryLS f =new FactoryLS();;
	public static void ler0(String map,String ia,String teste,List<String> list_scripts,Set<String> set_scripts,boolean treinado) throws FileNotFoundException{
			
			set_scripts.add("S;Empty");
			String entrada = "r1/out_"+map+"_"+ia+"_"+teste+".txt";
			if (treinado) {
				entrada = "r1_1/out_"+map+"_"+ia+"_"+teste+".txt";
			}
			Scanner in = new Scanner(new FileReader(entrada));
			String a1="";
			String a2="";
			while (in.hasNextLine()) {
			    String line = in.nextLine();
			    String dados[] = line.split("\t");
			    if(dados[0].equals("Camp")) {
			    	float tempo = Float.parseFloat(dados[1]);
			    	
			    	if( !set_scripts.contains(dados[3])){
			    		set_scripts.add(dados[3]);
			    		list_scripts.add(dados[3]);
			    	}
				    	
			    	
			    }
			}
			
		in.close();
		return ;
	}
	
	
	

	
public static String getMap(String s) {
	
		
		
		if(s.equals("0")) return "./maps/16x16/TwoBasesBarracks16x16.xml";
		if(s.equals("1")) return "maps/24x24/basesWorkers24x24A.xml";
		if(s.equals("2")) return  "maps/32x32/basesWorkers32x32A.xml";
	    if(s.equals("3")) { max =15000;;return "maps/BroodWar/(4)BloodBath.scmB.xml";}
	   
	    
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String[] maps = {"2"};
		String[] ais = {"1","3","0","2"};
		
		
		
		
		
		
		
		
		
		List<String> opponents = new ArrayList<>();
		Set<String> set_scripts = new HashSet<String>();;
		 for(String map :maps) {
			 for(String ai : ais) {
				 for(int i=0;i<10;i++) {
					 
					 List<String> auxs = new ArrayList<>();
					ler0(map,ai,i+"",auxs,set_scripts,false);
					int auxxx = Math.max(0, auxs.size()-5);
					for( int j=auxxx;j<auxs.size();j++) {
						opponents.add(auxs.get(j));
					}
					auxs.clear();
				
				 }
			 }
		 } 
		 System.out.println(opponents.size());
		 
		 OutputStream os = new FileOutputStream("file24.txt"); // nome do arquivo que será escrito
	        Writer wr = new OutputStreamWriter(os); // criação de um escritor
	        BufferedWriter br = new BufferedWriter(wr); // adiciono a um escritor de buffer
	        
	        for(String op :opponents) {
	        	String s =op+"\t"+Control.load(op,f).translate()+"\n";
	        	br.write(s);
	        }
	        br.close();
	    
		 
		 
		 
		 //Node_LS j = (Node_LS) Control.load(opponents.get(test), f);
		
	    

	}



}