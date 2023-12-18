package Cutoff;

import ai.evaluation.LTD2;
import rts.GameState;

public class CutOffLTD2 implements Cutoff {

	int cont = 0;
	float last_state0=-1235;
	float last_state1=-1235;
	int limite=1000;
	public CutOffLTD2(int limite) {
		// TODO Auto-generated constructor stub
		this.cont = 0;
		this.last_state0=-1235;
		this.last_state1=-1235;
		this.limite=limite;
	}
	
	public boolean stop(GameState gs) {
		LTD2 eva = new LTD2();
		float state0 = eva.base_score(0,gs);
		float state1 = eva.base_score(1,gs);
		if(state0!=this.last_state0 || state1!=this.last_state1) {
			this.cont=0;
		}else {
			cont++;
		}
		this.last_state0=state0;
		this.last_state1=state1;
		if(cont>this.limite)return true;
		return false;
		
		
	}

	@Override
	public void resert() {
		this.last_state0=-1235;
		this.last_state1=-1235;
		
	}
}
