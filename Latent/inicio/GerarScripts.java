package inicio;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import CFG.C;
import CFG.Node;
import CFG.S;
import CFG.S_S;
import LS_CFG.C_LS;
import LS_CFG.FactoryLS;
import LS_CFG.For_S_LS;
import LS_CFG.S_LS;
import LS_CFG.S_S_LS;
import util.Pair;
import java.util.Random;
import java.util.Scanner;

public class GerarScripts {
	static int tam[] = {0,01000,2000,
			4000,
			6000,
			7000,
			10000,
			10000,
			10000,
			10000};
	
	
	
	
	public static S getS_SRAnd() {
		C_LS c = new  C_LS();
		c.mutation(-1, 20, false);
		
		return new S_LS(c);
		
	}
public static void ler0(List<Node> programs) throws IOException{
		
		
		
		OutputStream os = new FileOutputStream("Scripts.txt"); // nome do arquivo que será escrito
        Writer wr = new OutputStreamWriter(os); // criação de um escritor
        BufferedWriter br = new BufferedWriter(wr);
		
       
        	 for(Node n: programs) {
        		 Node n2= new S_LS(new For_S_LS((S)n));
        		 br.write(n2.translate2());
         		br.newLine();
  
        	
        	
        }
       
	
		
		
		br.close();
	
	
		return ;
}
	
	
	public static List<Pair<Integer,Integer>> combinacao(int n){
		List<Pair<Integer,Integer>> l = new ArrayList<>();
		l.add(new Pair<>(n-1,1));
		l.add(new Pair<>(1,n-1));
		for(int i =1;i<n;i++) {
			for(int j =1;j<n;j++) {
				if(i+j==n) {
					l.add(new Pair<>(i,j));
				}
			}
		}
		return l;
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		FactoryLS f =new FactoryLS();;
		Set<String> all_programs = new HashSet<>();
		List<Node> programs = new ArrayList<>();
		
		while(all_programs.size()<100000) {
			S_LS s = (S_LS) getS_SRAnd();
			for(int i =0;i<9;i++) {
				S_LS l =(S_LS) getS_SRAnd();
				S_LS r = (S_LS) s.Clone( new FactoryLS());
				S_S_LS ss = new S_S_LS(l,r);
				s.setChild(ss);
				
			}
			if (!all_programs.contains(s.translate())) {
				all_programs.add(s.translate());
				//System.out.println(s.translate());
				programs.add(s);
			}
			
		}
		 ler0(programs);
		
	}

}
