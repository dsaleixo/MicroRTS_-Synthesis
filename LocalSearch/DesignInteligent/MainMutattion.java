package DesignInteligent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CFG.Control;
import CFG.Node;
import LS_CFG.FactoryLS;
import LS_CFG.Node_LS;

public class MainMutattion implements Mutation {
	List<Mutation> mutations = new ArrayList<>();
	List<Integer> prob_mutation = new ArrayList<>();
	public MainMutattion() {
		// TODO Auto-generated constructor stub
		mutations.add(new RandomMutation());
		mutations.add(new TerminalMutation());
		mutations.add(new FirstComandMutation());
		mutations.add(new LastComandMutation());
		prob_mutation.add(1);
		prob_mutation.add(1);
		prob_mutation.add(1);
		prob_mutation.add(1);

	}
	
	
	@Override
	public List<Node_LS> mutation(Node_LS node, int n) {
		// TODO Auto-generated method stub
		int cont =0;
		for(int i =0;i<prob_mutation.size();i++) {
			cont+=prob_mutation.get(i);
		}
		
		
		Random r=new Random();;
		int mutation = r.nextInt(cont);
		int cont2 =0;
		
		for(int i =0;i<prob_mutation.size();i++) {
			cont2+=prob_mutation.get(i);
			if(mutation<cont2) {
			
				return mutations.get(i).mutation(node, n);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainMutattion m =new MainMutattion();
		String s = "S;S_S;S;For_S;S;C;Harvest;4;S;S_S;S;S_S;S;Empty;S;S_S;S;S_S;S;Empty;S;S_S;S;Empty;S;For_S;S;For_S;S;S_S;S;S_S;S;S_S;S;S_S;S;C;Attack;Weakest;S;C;Idle;S;S_S;S;C;MoveAway;S;C;Train;Worker;Down;25;S;C;MoveToUnit;Ally;MostHealthy;S;S_S;S;C;Idle;S;C;Build;Ranged;Up;100;S;S_S;S;S_S;S;Empty;S;For_S;S;S_S;S;S_S;S;S_S;S;If_B_then_S;B;CanAttack;S;C;Idle;S;C;Train;Worker;Down;0;S;S_S;S;C;Build;Barracks;Up;3;S;C;Harvest;100;S;C;Harvest;8;S;Empty;S;For_S;S;For_S;S;C;Train;Worker;Up;7";
		Node n = Control.load(s,new FactoryLS());
		for(int i =0;i<10;i++) {
		List<Node_LS> n_muatados=m.mutation((Node_LS) n, 5);
			for (Node_LS j : n_muatados) {
				System.out.println(j.translate());
			}
		}
	}

}
