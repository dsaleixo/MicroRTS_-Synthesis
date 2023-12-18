package IAs2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import AIs.Interpreter;
import CFG.Control;
import CFG.Factory;
import DesignInteligent.Mutation;
import EvaluateGameState.Playout;
import EvaluateGameState.SimplePlayout;
import LS_CFG.FactoryLS;
import LS_CFG.Node_LS;
import ai.core.AI;
import rts.GameState;
import rts.units.UnitTypeTable;

public class SA implements Search2 {
	
	int tempo_limite;
	double T0_inicial;
	double alpha_inicial;
	double beta_inicial;
	double T0;
	double alpha;
	double beta;
	boolean limpa;

	Factory f;
	Random r;
	
	public SA( int tempo,double T0, double alpha, double beta, boolean limpa) {
		// TODO Auto-generated constructor stub
		System.out.println("Busca SA");
		
		this.tempo_limite=tempo;
		this.limpa = limpa;
		this.T0_inicial = T0;
		this.alpha_inicial= alpha;
		this.beta_inicial = beta;
		f = new FactoryLS();
		r =new Random();
	}

	
	
	
	@Override
	public Node_LS run(GameState gs, int max, Node_LS best,Avaliador ava, Mutation m,boolean SA) throws Exception {
		// TODO Auto-generated method stub
		this.resert();
		double v = ava.Avalia(gs, max, best);
		long Tini = System.currentTimeMillis();
		long tempo = System.currentTimeMillis()-Tini;
	
		int cont=0;
		Node_LS local= (Node_LS) best.Clone(f);
		double v_local= v;
		int cont_mutacao=0;
		while( (tempo*1.0)/1000.0 <tempo_limite && !ava.criterioParada(v)) {
			double T = this.T0/(1+cont*this.alpha);
			Node_LS melhor_vizinho = null ;
			double v_vizinho = -111111;
			List<Node_LS> vizinhos = m.mutation(local, 2);
			for(Node_LS aux : vizinhos) {
			
				long initt = System.currentTimeMillis();
				double v2 = ava.Avalia(gs, max, aux);
				long paraou = System.currentTimeMillis()-initt;
				
				//this.coac.Avalia(gs, max, aux);
				cont_mutacao+=1;
				if(v_vizinho<v2) {
					if(this.limpa)aux.clear(null, f);
					melhor_vizinho = (Node_LS) aux.Clone(f);
					v_vizinho=v2;	
				}
				System.out.println("munt\t"+paraou+"\t"+v2+"\t"+Control.salve(aux));
				tempo = System.currentTimeMillis()-Tini;
				if((tempo*1.0)/1000.0 >tempo_limite || ava.criterioParada(v_vizinho))break;
		
			}

			if(accept(v_local,v_vizinho,T)&& SA) {
				
				local=(Node_LS) melhor_vizinho.Clone(f);
				v_local=v_vizinho;
				
			}
			//System.out.println(v_vizinho.m_b+"   t2\t"+melhor_vizinho.translate());
			tempo = System.currentTimeMillis()-Tini;
			
			
			if(v<v_vizinho) {
				System.out.println("melhorou "+v+"<"+v_vizinho);
				best = (Node_LS) melhor_vizinho.Clone(f);
				v_local=v_vizinho;
				v= v_vizinho;		
			
			}
			
			cont++;
			
		}
		tempo = System.currentTimeMillis()-Tini;
		System.out.println("nMunt\t"+tempo/1000+"\t"+cont_mutacao);
		return best;
	}

	private boolean accept(double v, double v_vizinho, double t) {
		// TODO Auto-generated method stub
		double aux2 = Math.exp(this.beta*((v_vizinho - v)/t));
		
		aux2 = Math.min(1,aux2);
		if(r.nextFloat()<aux2)return true;
		
		
		return false;
	}


	@Override
	public void resert() {
		// TODO Auto-generated method stub
		this.T0 = this.T0_inicial;
		this.alpha = this.alpha_inicial;
		this.beta = this.beta_inicial;
		
	}

}
