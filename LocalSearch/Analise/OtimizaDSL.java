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
import Cutoff.CutOffLTD2;
import Cutoff.Cutoff;
import Cutoff.NoCutOff;
import LS_CFG.FactoryLS;
import LS_CFG.Node_LS;
import ai.core.AI;
import ai.evaluation.LTD2;
import gui.PhysicalGameStatePanel;
import rts.GameState;
import rts.PhysicalGameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;

public class OtimizaDSL {
	static int max=7000;
	static FactoryLS f =new FactoryLS();;
	public static List<String> ler0(String map,String ia,String teste) throws FileNotFoundException{
			
		    Set<String> conj= new HashSet<String>();
		    List<String> list= new ArrayList<>();
			String entrada = "./analise/scripts_lento/out_"+map+"_"+ia+"_"+teste+".txt";
			Scanner in = new Scanner(new FileReader(entrada));
			int cont=0;
			while (in.hasNextLine()) {
			    String line = in.nextLine();
				list.add(line)   ;    
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
	
	public static int partida(GameState gs,UnitTypeTable utt, int player, int max_cycle, AI ai1, AI ai2, boolean exibe, Cutoff cutoff) throws Exception {
			
			
			
			ai1.reset(utt);
			ai2.reset(utt);
			GameState gs2 = gs.cloneChangingUTT(utt);
			boolean gameover = false;
			JFrame w=null;
			if(exibe) w = PhysicalGameStatePanel.newVisualizer(gs2,640,640,false,PhysicalGameStatePanel.COLORSCHEME_BLACK);
			boolean itbroke=false ;
			int cont=0;
			int last_state0=-1235;
			int last_state1=-1235;
		    do {
		    	PlayerAction pa1=null;
		    	long ini = System.currentTimeMillis();
		    		try {
		    			pa1 = ai1.getAction(player, gs2);
		    		}catch(Exception e) {
		    			System.out.println("erro1 "+e );
		    			pa1 = new PlayerAction();
		    		}
		    	
		    		PlayerAction pa2=null;
		    		try {
		    			pa2 = ai2.getAction(1-player, gs2);
		    		}catch(Exception e) {
		    			System.out.println("erro1 "+e );
		    			pa2 = new PlayerAction();
		    		}
		    	long paraou = System.currentTimeMillis()-ini;  
		    	
		    	 gs2.issueSafe(pa1);
		    	 gs2.issueSafe(pa2);
		        
		            if(exibe) {
		            	w.repaint();
		            	Thread.sleep(1);
		            }
		            
		            gameover = gs2.cycle();
		          
		            
		            
		
		    } while (!gameover && (gs2.getTime() < max_cycle)&& !cutoff.stop(gs2));
			
		    ;
		    return gs2.getTime();
		
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
		
		List<String> list =ler0(map,id_IA,teste);
		// 21 43 51
	
		for(int player=list.size()-30;player<list.size();player++) {
			Node_LS s1 = (Node_LS) Control.load(list.get(player), f);
			Node_LS s2 = (Node_LS) Control.load(list.get(player), f);
			//String ff ="S;For_S;S;S_S;S;S_S;S;C;Train;Worker;Left;4;S;C;Train;Worker;Down;20;S;S_S;S;For_S;S;For_S;S;S_S;S;For_S;S;C;Harvest;4;S;For_S;S;C;MoveAway;S;S_S;S;C;Attack;Weakest;S;S_S;S;C;MoveToUnit;Enemy;MostHealthy;S;S_S;S;C;MoveToUnit;Enemy;Farthest;S;If_B_then_S_else_S;B;OpponentHasUnitInPlayerRange;S;Empty;S;S_S;S;For_S;S;C;Harvest;20;S;C;Train;Heavy;Right;4";
			// 59
			//Node_LS s1 = (Node_LS) Control.load(ff, f);
			//Node_LS s2 = (Node_LS) Control.load(ff, f);
			//System.out.println(s1.translateIndentation(0));
			AI j0 = new Interpreter(utt,s1);
			AI j1 = new Interpreter(utt,s2 );
			long ini = System.currentTimeMillis();
			int time= partida(gs2,utt, 0,  max, j0, j1, false, new CutOffLTD2(1000));
			long paraou = System.currentTimeMillis()-ini;
			System.out.println(player+"\t"+paraou+"\t"+time+"\t"+1.0*paraou/time+"\t"+s1.translate());
			long ini2 = System.currentTimeMillis();
			s1.clear(s1, f);
			s1.clear(s2, f);
			j0 = new Interpreter(utt,s1);
			j1 = new Interpreter(utt,s2 );
			int time2= partida(gs2,utt, 0,  max, j0, j1, false, new NoCutOff());
			long paraou2 = System.currentTimeMillis()-ini2;
			System.out.println(player+"\t"+paraou2+"\t"+time2+"\t"+1.0*paraou2/time2+"\t"+s1.translate());
			System.out.println((1.0*paraou)/paraou2);
			System.out.println();
		
		}

	}

}
