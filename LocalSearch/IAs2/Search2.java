package IAs2;

import java.util.List;

import DesignInteligent.Mutation;
import LS_CFG.Node_LS;
import rts.GameState;

public interface Search2 {
	Node_LS run(GameState gs,int max,Node_LS j,Avaliador ava, Mutation m)throws Exception;
	void resert();
}
