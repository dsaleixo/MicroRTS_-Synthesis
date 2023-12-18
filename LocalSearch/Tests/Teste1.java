package Tests;

import java.util.Random;

import CFG.Factory;

import EvaluateGameState.FabicaDeNovidade;
import EvaluateGameState.FabricaNov0;
import EvaluateGameState.Playout;
import EvaluateGameState.SimplePlayout;
import IAs.Busca2Nivel;
import IAs.CorrendoAtrasRabo;
import IAs.CorrendoAtrasRabo2;
import IAs.FictionsPlayTeste;
import IAs.SABasic;
import IAs.Search;
import IAs.SelfPlayNovidade;
import IAs.SelfPlayTeste;
import IAs2.Algoritmo1;
import IAs2.AvaliaCoac;
import IAs2.Avaliador;
import IAs2.AvaliadorPadrao;
import IAs2.B2N;
import IAs2.B2N2;
import IAs2.CCBasica;
import IAs2.CCBasicaMutacao;
import IAs2.HC;
import IAs2.SA;
import TwoLevelSearch.Aleatorio;
import TwoLevelSearch.BehavioralCloning;
import TwoLevelSearch.Level1;
import TwoLevelSearch.Level2;
import TwoLevelSearch.NaiveSampling;
import TwoLevelSearch.TwoLevelsearch;
import ai.coac.CoacAI;
import ai.core.AI;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.UnitTypeTable;

public class Teste1 {
	static int max=6000;
	static  int tempo=2000;
	static int troca =24;
	public Teste1() {
		// TODO Auto-generated constructor stub
	}

	public static String getMap(String s) {
	
		if(s.equals("0")) return "maps/24x24/basesWorkers24x24A.xml";
		if(s.equals("1")) return "maps/32x32/basesWorkers32x32A.xml";
	    if(s.equals("2")) {troca=24;tempo=5000; max =15000;;return "maps/BroodWar/(4)BloodBath.scmB.xml";}
	   return null;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UnitTypeTable utt = new UnitTypeTable();
		String path_map = getMap(args[0]);
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		GameState gs2 = new GameState(pgs, utt);
		
		

		System.out.println(path_map+" novo teste indo pro fim,final msm,sem erros, sem apredizagem");
		
		
		
		AvaliaCoac coac = new AvaliaCoac();
		TwoLevelsearch se =null;
		
		
		if(args[1].equals("0")) {
			Algoritmo1 se2 = new Algoritmo1(new HC(tempo), new IAs2.CS_Default(),0);
			se2.run(gs2, max);
		}
				
		if(args[1].equals("1")) {
			Algoritmo1 se2 = new Algoritmo1(new HC(tempo), new AvaliadorPadrao(1000,null),0);
			se2.run(gs2, max);
		}
		
		if(args[1].equals("2")) {
			Algoritmo1 se2 = new Algoritmo1(new HC(tempo),new IAs2.CS_Default(),1);
			se2.run(gs2, max);
		}
				
		if(args[1].equals("3")) {
			Algoritmo1 se2 = new Algoritmo1(new HC(tempo), new AvaliadorPadrao(1000,null),1);
			se2.run(gs2, max);
		}
		
				
		
		
		
		
	}

}
