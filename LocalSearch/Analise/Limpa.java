package Analise;

import java.io.BufferedWriter;
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

public class Limpa {

	public static void ler0(String map,String ia,String teste) throws IOException{
		
		
		String entrada = "out_"+map+"_"+ia+"_"+teste+".txt";
		Scanner in = new Scanner(new FileReader("r3/"+entrada));
		String ant="";
		OutputStream os = new FileOutputStream("r33/out_"+map+"_"+ia+"_"+teste+".txt"); // nome do arquivo que será escrito
        Writer wr = new OutputStreamWriter(os); // criação de um escritor
        BufferedWriter br = new BufferedWriter(wr);
		int cont=0;
		while (in.hasNextLine()) {
		    String line = in.nextLine();
		    String dados[] = line.split("\t");
		    if(dados[0].equals("Camp")) {
		    	float tempo = Float.parseFloat(ant.split("\t")[1]);
		    	float r = Float.parseFloat(dados[2]);
		   
			    System.out.println(tempo+"\t"+r);
			    br.write(tempo+"\t"+r);
				br.newLine();
		    	
		    }
		    ant= line;
		}
		br.close();
		in.close();
	
		return ;
}
	
	public static void grava(String map,String id_IA,String teste) throws IOException {	
			ler0(map,id_IA,teste);

	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		for(int map=0;map<3;map++) {
			for(int id_IA=0;id_IA<4;id_IA++) {
				for(int teste=0;teste<10;teste++) {
					grava(""+map,""+id_IA,""+teste);
				}
			}
		}
		
	
		
	

	}

}
