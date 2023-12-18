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
import ai.core.AI;
import gui.PhysicalGameStatePanel;
import rts.GameState;
import rts.PhysicalGameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;
import util.Pair;

public class Camp {
	static int max=7000;
	static FactoryLS f =new FactoryLS();;
	
public static List<String> ler0(String map,String ia,String teste,float limite) throws FileNotFoundException{
		
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
		    	
		    	if(tempo<limite ) {
		    	
			    	a1=dados[dados.length-1];
			    	if(!conj.contains(a1)) {
			    		conj.add(a1);
			    		list.add(a1);
			    	}
		    	}else {
		    		break;
		    	}
		    }
		}
		
	in.close();
	

	return list;
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

public static String getMap(String s) {
	
	
	if(s.equals("0")) return "maps/8x8/basesWorkers8x8A.xml";
	if(s.equals("1")) return "maps/24x24/basesWorkers24x24A.xml";
	if(s.equals("2")) return "maps/32x32/basesWorkers32x32A.xml";
    if(s.equals("3")) { max =15000;;return "maps/BroodWar/(4)BloodBath.scmB.xml";}
		return null;
	}
	
private static double avalia(GameState gs2, UnitTypeTable utt, Node_LS j,Node_LS adv) throws Exception {
	// TODO Auto-generated method stub
	double r=0;
	AI j0 = new Interpreter(utt,j.Clone(f) );
	AI j1 = new Interpreter(utt,adv.Clone(f) );
	
		
		
	return partida(gs2,utt,0,max,j0,j1,false);
	
}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String map = args[0];
		String teste = args[2];
		
		String id_IA = args[1];
		
		
		UnitTypeTable utt = new UnitTypeTable(2);
		String path_map = getMap(args[0]);
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		GameState gs2 = new GameState(pgs, utt);
		
		
		List<String> list=ler0(map,id_IA,teste,3*24*3600.0f);
		int n = list.size();
		System.out.println("n\t"+n);
		for(int i=n-50;i<n;i++) {
			for(int j=n-50;j<n;j++) {
				Node_LS j0 =(Node_LS) Control.load(list.get(i),f);
				Node_LS j1 =(Node_LS) Control.load(list.get(j),f);
				double r = avalia(gs2,utt,j0,j1);
				System.out.println("VS\t"+i+"\t"+j+"\t"+r);
			}
		}
		System.out.println("FIM");

	}
		
	

}
