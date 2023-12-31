package EvaluateGameState;

import CFG.Factory;
import CFG.Node;
import ai.core.AI;
import rts.GameState;
import rts.units.UnitTypeTable;
import util.Pair;

public interface Playout {
	//Pair<Double,CabocoDagua2> run(GameState gs,int player,int max_cycle,AI ai1,AI ai2,boolean exibe) throws Exception;

	Pair<Double, CabocoDagua2> run(GameState gs, int player, int max_cycle, AI ai1, AI ai2,
			boolean exibe) throws Exception;
}
