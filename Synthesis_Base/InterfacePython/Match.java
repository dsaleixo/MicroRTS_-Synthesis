package InterfacePython;

import javax.swing.JFrame;

import ai.abstraction.LightRush;
import ai.abstraction.WorkerRush;
import ai.core.AI;
import gui.PhysicalGameStatePanel;
import rts.GameState;
import rts.PhysicalGameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;

public class Match {
	String path_map;
	PhysicalGameState pgs;
	GameState gs2;
	UnitTypeTable utt;
	boolean show_scream;
	JFrame w=null;
	public Match(String map, boolean show_scream) throws Exception {
		// TODO Auto-generated constructor stub
		this.path_map=map;
	
		this.utt = new UnitTypeTable(2);
		this.pgs = PhysicalGameState.load(this.path_map, this.utt);
		this.gs2 = new GameState(this.pgs, this.utt);
		this.show_scream = show_scream;
		if(show_scream) this.w = PhysicalGameStatePanel.newVisualizer(gs2,640,640,false,PhysicalGameStatePanel.COLORSCHEME_BLACK);
		
	}
	
	public GameState getGS() {
		return this.gs2;
	}
	
	public UnitTypeTable getUTT() {
		return this.utt;
	}
	
	public void SetActions(PlayerAction pa) {
		this.gs2.issueSafe(pa);
	}
	public boolean run() throws InterruptedException {
		if(this.show_scream) {
        	this.w.repaint();
        	Thread.sleep(2);
        	
        }
		boolean gameover = this.gs2.cycle();
		
		return gameover;
	}
	public void  resert() {
		this.gs2 = new GameState(this.pgs, this.utt);
	}

	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Match match = new Match("maps/24x24/basesWorkers24x24A.xml", true);

		AI ai2 = new WorkerRush(match.getUTT());
		 boolean gameover;
		AI ai1 = new LightRush(match.getUTT());
		
		do {
        	PlayerAction pa1 = ai1.getAction(0, match.getGS());
                
            PlayerAction pa2 = ai2.getAction(1,match.getGS());
                
            match.SetActions(pa1);
            match.SetActions(pa2);
            
            gameover = match.run();
                
              
                

        } while (!gameover && (match.getGS().getTime() <= 7000)); 
		
		System.out.println("vencencedor "+match.getGS().winner());
	}

}
