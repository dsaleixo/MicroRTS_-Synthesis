package Analise;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import CFG.Control;
import LS_CFG.Node_LS;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.UnitTypeTable;
import util.Pair;

public class NumeroAtualizacao {

	public static List<String> ler0(String map,String ia,String teste,float limite) throws FileNotFoundException{
		
		
		String entrada = "out_"+map+"_"+ia+"_"+teste+".txt";
		Scanner in = new Scanner(new FileReader("r1/"+entrada));
		String a1="";
		String a2="";
		String a3="";
		int cont=0;
		while (in.hasNextLine()) {
		    String line = in.nextLine();
		    String dados[] = line.split("\t");
		    if(dados[0].equals("Camp")) {
		    	float tempo = Float.parseFloat(dados[1]);
		    	
		    	if(tempo<limite ) {
		    	
			    	a1=dados[1];
			    	a2=dados[2];
			    	a3=dados[3];
			    	if(a3.equals("")) {
			    		a3=dados[4];
			    	}
		    	}else {
		    		break;
		    	}
		    }if(dados[0].equals("atualizou")) {
		    	float tempo = Float.parseFloat(dados[2]);
		    	
		    	if(tempo<limite &&tempo >limite -4*3600 ) {
		    	
			    	cont++;
		    }
		}
		}
	in.close();
	List<String> list= new ArrayList<>();
	list.add(a1);
	list.add(a2);
	list.add(a3);
	list.add(""+cont);
	return list;
}
	
	public static void grava(String map,String id_IA,String teste) throws IOException {
		OutputStream os = new FileOutputStream("r2/out_"+map+"_"+id_IA+"_"+teste+".txt"); // nome do arquivo que será escrito
        Writer wr = new OutputStreamWriter(os); // criação de um escritor
        BufferedWriter br = new BufferedWriter(wr);
	
		
		for(int i =1;i<=19;i++) {
			float limite=  i*3600*4;
			
			
			
			List<String> p = ler0(map,id_IA,teste,limite);
			
			br.write(limite+"\t"+p.get(1)+"\t"+p.get(2)+"\t"+p.get(3));
			br.newLine();
			
			
			
		}
		
		br.close();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		for(int map=0;map<3;map++) {
			for(int id_IA=0;id_IA<4;id_IA++) {
				for(int teste=0;teste<10;teste++) {
					grava(""+map,""+id_IA,""+teste);
					System.out.println(""+map+ " "+id_IA+" "+teste);
				}
			}
		}
		
	
		
	

	}

}
