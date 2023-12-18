package DesignInteligent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CFG.Control;
import CFG.For_S;
import CFG.Node;
import LS_CFG.C_LS;
import LS_CFG.FactoryLS;
import LS_CFG.For_S_LS;
import LS_CFG.Node_LS;
import LS_CFG.S_LS;
import LS_CFG.S_S_LS;

public class LastComandMutation implements Mutation {

	public LastComandMutation() {
		// TODO Auto-generated constructor stub
	}

	public void selectNodes(Node_LS node, List<Node_LS> Selects) {
		List<Node_LS> list= new ArrayList<>();;
		
		node.countNode(list);
		for(Node_LS aux : list){
			if(aux instanceof  For_S ) {
				Selects.add(aux);
			}
		}
		for(int i=Selects.size()-1;i>=0;i--) {
			boolean b=false;
			for(int j = 0;j<Selects.size();j++) {
				if(i==j)continue;
				String a =Selects.get(i).translate();
				String c = Selects.get(j).translate();
				if(c.indexOf(a)>=0) {
					b=true;
				
					break;
				}
			}
			if(b) {
				Selects.remove(i);
			}
				
		}
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
		;if(selects2.size()==0)return individuos;
			For_S_LS fs =(For_S_LS) selects2.get(selects2.size()-1);
			
			C_LS c = new C_LS();
			c.mutation(0, 15, false);
			S_LS s = new S_LS(c);
			S_S_LS ss = new S_S_LS(fs.getChild(),s);
			S_LS s2 = new  S_LS(ss);
			fs.setChild(s2);
			individuos.add(n2);
		}
		return individuos;
	}

	public static void main(String[] args) {
		LastComandMutation m =new LastComandMutation();
		String s = "S;S_S;S;For_S;S;C;Harvest;4;S;S_S;S;S_S;S;Empty;S;S_S;S;S_S;S;Empty;S;S_S;S;Empty;S;For_S;S;For_S;S;S_S;S;S_S;S;S_S;S;S_S;S;C;Attack;Weakest;S;C;Idle;S;S_S;S;C;MoveAway;S;C;Train;Worker;Down;25;S;C;MoveToUnit;Ally;MostHealthy;S;S_S;S;C;Idle;S;C;Build;Ranged;Up;100;S;S_S;S;S_S;S;Empty;S;For_S;S;S_S;S;S_S;S;S_S;S;If_B_then_S;B;CanAttack;S;C;Idle;S;C;Train;Worker;Down;0;S;S_S;S;C;Build;Barracks;Up;3;S;C;Harvest;100;S;C;Harvest;8;S;Empty;S;For_S;S;For_S;S;C;Train;Worker;Up;7";
		Node n = Control.load(s,new FactoryLS());
		System.out.println(n.translateIndentation(0));
		System.out.println();
		System.out.println("-----------------------------------------------");
		List<Node_LS> n_muatados=m.mutation((Node_LS) n, 5);
		for (Node_LS j : n_muatados) {
			System.out.println(j.translateIndentation(0));
			System.out.println();
			System.out.println("-----------------------------------------------");
		}
		
	
		
	

	}
}
