package IAs2;

import java.util.ArrayList;
import java.util.List;

import AIs.Interpreter;
import CFG.Control;
import CFG.Factory;
import CFG.Node;
import DesignInteligent.MainMutattion;
import DesignInteligent.Mutation;
import DesignInteligent.RandomMutation;
import DesignInteligent.TerminalMutation;
import EvaluateGameState.Playout;
import EvaluateGameState.SimplePlayout;
import LS_CFG.Empty_LS;
import LS_CFG.FactoryLS;
import LS_CFG.Node_LS;
import LS_CFG.S_LS;
import ai.core.AI;
import rts.GameState;
import rts.units.UnitTypeTable;

public class Algoritmo1 {

	Search2 sc;
	Avaliador ava;
	long tempo_ini;
	int type_mutation;


	
	
	
	public Algoritmo1(Search2 sc,Avaliador ava, int tm) {
		// TODO Auto-generated constructor stub
		this.ava=ava;
		this.sc = sc;
		this.type_mutation =tm;
		this.tempo_ini =System.currentTimeMillis();
	
	}
	
	
	public Mutation getMutation(boolean b) {
		if(this.type_mutation==0)return new RandomMutation();
		if(this.type_mutation==1) {
			if(b) {
			//	System.out.println("TErminalMutation");
				return new TerminalMutation();
			}
			else {
				//ystem.out.println("Main Mutação");
				return new MainMutattion();
			}
		}
		return null;
	}
	
	
	public void run(GameState gs,int max) throws Exception {
		
		boolean atualizaou=false;
		while(true) {
			
			
			
			Node_LS j = ava.getIndividuo();
			
			Mutation m = this.getMutation(atualizaou);
			boolean SA = !(this.type_mutation==1&&atualizaou);
			Node_LS  c0 = sc.run(gs, max, j, ava,m);

			double r0 = ava.Avalia(gs, max, c0);
			double r1 = ava.Avalia(gs, max, j);
			
			atualizaou=false;
			
			if(r0>r1) {
				ava.update(gs, max, c0);
				long paraou = System.currentTimeMillis()-this.tempo_ini;
				System.out.println("testet parou "+paraou);
				if(paraou/1000>2*3600)atualizaou=true;
			}
			
		}
		
		
	}


	
		
		
}


	


