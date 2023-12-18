package DesignInteligent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CFG.ChildC;
import CFG.Control;
import CFG.Node;
import LS_CFG.FactoryLS;
import LS_CFG.Node_LS;

public class RandomMutation implements Mutation {

	public RandomMutation() {
		;
	}
	
	public void selectNodes(Node_LS node, List<Node_LS> Selects) {
		List<Node_LS> list= new ArrayList<>();;
		node.countNode(Selects);
	
	}
	
	
	@Override
	public List<Node_LS> mutation(Node_LS node, int n) {
		// TODO Auto-generated method stub
		
		
		Random r=new Random();;
		List<Node_LS> individuos= new ArrayList<>();;
		for (int i =0;i<n;i++) {
			Node_LS  n2 = (Node_LS) node.Clone(new FactoryLS());
			List<Node_LS> selects2= new ArrayList<>();;
			this.selectNodes((Node_LS)n2,selects2);
			int custo = r.nextInt(9)+1;
			int no = r.nextInt(selects2.size());
			selects2.get(no).mutation(0, custo, false);
			individuos.add(n2);
			
		}
		
		
		return individuos;
	}
	


	
	
	public static void main(String[] args) throws Exception {
		RandomMutation m =new RandomMutation();
		String s = "S;S_S;S;For_S;S;C;Harvest;4;S;S_S;S;S_S;S;Empty;S;S_S;S;S_S;S;Empty;S;S_S;S;Empty;S;For_S;S;For_S;S;S_S;S;S_S;S;S_S;S;S_S;S;C;Attack;Weakest;S;C;Idle;S;S_S;S;C;MoveAway;S;C;Train;Worker;Down;25;S;C;MoveToUnit;Ally;MostHealthy;S;S_S;S;C;Idle;S;C;Build;Ranged;Up;100;S;S_S;S;S_S;S;Empty;S;For_S;S;S_S;S;S_S;S;S_S;S;If_B_then_S;B;CanAttack;S;C;Idle;S;C;Train;Worker;Down;0;S;S_S;S;C;Build;Barracks;Up;3;S;C;Harvest;100;S;C;Harvest;8;S;Empty;S;For_S;S;For_S;S;C;Train;Worker;Up;7";
		Node n = Control.load(s,new FactoryLS());
		List<Node_LS> selects= new ArrayList<>();;
		
		List<Node_LS> n_muatados=m.mutation((Node_LS) n, 5);
		for (Node_LS j : n_muatados) {
			System.out.println(j.translate());
		}
		
		
		
	}
}
