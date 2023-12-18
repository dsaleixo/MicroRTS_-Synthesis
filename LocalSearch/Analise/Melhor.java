package Analise;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;

import AIs.Interpreter;
import CFG.Control;
import LS_CFG.FactoryLS;
import LS_CFG.Node_LS;
import ai.mayari;
import ai.abstraction.partialobservability.POLightRush;
import ai.abstraction.partialobservability.POWorkerRush;
import ai.coac.CoacAI;
import ai.core.AI;
import gui.PhysicalGameStatePanel;
import rts.GameState;
import rts.PhysicalGameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;
import util.Pair;

public class Melhor {
	static int max=7000;
	static FactoryLS f =new FactoryLS();;
	
	
	
public static int getMior(float p[],int n) {
	int index=-1;
	float melhor=-1;
	
	for(int i=0;i<n;i++) {
	
		if(melhor<p[i]) {
			index=i;
			melhor = p[i];
		}
	}
	
	return index;
}
	
	
public static Pair<Integer,Integer> lerMelhor(String map,String ia,String teste) throws FileNotFoundException{
		
	    Set<String> conj= new HashSet<String>();
	    List<String> list= new ArrayList<>();
		String entrada = "out_"+map+"_"+ia+"_"+teste+".txt";
		Scanner in = new Scanner(new FileReader("r4/"+entrada));
		
		int n= Integer.parseInt(in.nextLine().split("\t")[1]);
		
		float p1[]= new float[n];
		float p2[]= new float[n];
		for (int i=0;i<n;i++) {
			p1[i]=0;
			p2[i]=0;
		}
		
		while (in.hasNextLine()) {
		    String line = in.nextLine();
		    String dados[] = line.split("\t");
		    if(dados[0].equals("VS")) {
		    	int i = Integer.parseInt(dados[1]);
		    	int j = Integer.parseInt(dados[2]);
		    	float r = Float.parseFloat(dados[3]);
		    	p1[i] +=r;
		    	p2[j] +=1-r;
		    }
		    
		}
		
	in.close();
	

	
	return new Pair<>(getMior(p1,n),getMior(p2,n));
}	
	
	
public static List<String> ler0(String map,String ia,String teste) throws FileNotFoundException{
		
	    Set<String> conj= new HashSet<String>();
	    List<String> list= new ArrayList<>();
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
		    	
		    	
		    	
			    	a1=dados[dados.length-1];
			    	if(!conj.contains(a1)) {
			    		conj.add(a1);
			    		list.add(a1);
			    	}
		    	
		    }
		}
		
	in.close();
	

	return list;
}


public static String getMap(String s) {
	
		if(s.equals("0")) return "maps/24x24/basesWorkers24x24A.xml";
	if(s.equals("1")) return "maps/32x32/basesWorkers32x32A.xml";
    if(s.equals("2")) { max =15000;;return "maps/BroodWar/(4)BloodBath.scmB.xml";}
		return null;
	}
	
public static double partida(GameState gs,UnitTypeTable utt, int player, int max_cycle, AI ai1, AI ai2, boolean exibe) throws Exception {
		
		
		
		ai1.reset(utt);
		ai2.reset(utt);
		GameState gs2 = gs.cloneChangingUTT(utt);
		boolean gameover = false;
		JFrame w=null;
		if(exibe) w = PhysicalGameStatePanel.newVisualizer(gs2,640,640,false,PhysicalGameStatePanel.COLORSCHEME_BLACK);
		boolean itbroke=false ;
		
	    do {
	    	PlayerAction pa1=null;
	    		try {
	    			pa1 = ai1.getAction(player, gs2);
	    		}catch(Exception e) {
	    			pa1 = new PlayerAction();
	    		}
	    	
	    		PlayerAction pa2=null;
	    		try {
	    			pa2 = ai2.getAction(1-player, gs2);
	    		}catch(Exception e) {
	    			pa2 = new PlayerAction();
	    		}
	            
	    	 gs2.issueSafe(pa1);
	    	 gs2.issueSafe(pa2);
	        
	            if(exibe) {
	            	w.repaint();
	            	Thread.sleep(1);
	            }
	            
	            gameover = gs2.cycle();
	           
	            
	
	    } while (!gameover && (gs2.getTime() < max_cycle));
		
	    if (gs2.winner()==player)return 1.0;
	    if (gs2.winner()==-1)return 0.5;
	    return 0;
	
}
	
	
public static AI getAdv(GameState gs,String s,UnitTypeTable utt) throws Exception {
	
	
	if(s.equals("0"))  return new POWorkerRush(utt); //new GuidedRojoA3N(utt);
	if(s.equals("1")) return new POLightRush(utt);
	
	if(s.equals("2")) return new CoacAI(utt);

	
	if(s.equals("3")) return new mayari(utt);
	
	return null;
}


private static double Avalia(GameState gs2, UnitTypeTable utt,Pair<Node_LS, Node_LS> j) throws Exception {
		// TODO Auto-generated method stub
		double r=0;
		AI j0 = new Interpreter(utt,j.m_a.Clone(f) );
		AI j1 = new Interpreter(utt,j.m_b.Clone(f) );
		for(int i =0;i<4;i++) {
			AI adv0 = getAdv(gs2,""+i,utt);
			
			double rL=0;
			for(int rep =0;rep<2;rep++)rL+=partida(gs2,utt,0,max,j0,adv0,false);
			for(int rep =0;rep<2;rep++)rL+=partida(gs2,utt,1,max,j1,adv0,false);
			//System.out.println("VS\t"+i+"\t"+rL);
			r+=rL;
		}
		return r;
	}

	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		for(int map=1;map<2;map++) {
			for(int id_IA=3;id_IA<4;id_IA++) {
				for(int teste=0;teste<5;teste++) {
		
					UnitTypeTable utt = new UnitTypeTable(2);
					String path_map = getMap(""+map);
					PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
					GameState gs2 = new GameState(pgs, utt);
					
					List<String> list = ler0(""+map,""+id_IA,""+teste);
					Pair<Integer,Integer> p =lerMelhor(""+map,""+id_IA,""+teste);
					String j0 = list.get(p.m_a);
					String j1 = list.get(p.m_b);
					
					
					Pair<Node_LS,Node_LS> par =new Pair<>((Node_LS)Control.load(j0,f),(Node_LS)Control.load(j1,f));
					//S;S_S;S;For_S;S;S_S;S;For_S;S;C;Idle;S;If_B_then_S;B;OpponentHasUnitInPlayerRange;
					double r = Avalia(gs2,utt,par);
					System.out.println("Camp\t"+id_IA+"\t"+r);
				}
			}
		}
	}
		
	

}
