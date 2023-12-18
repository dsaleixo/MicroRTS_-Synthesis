package InterfacePython;

import java.util.List;

import AIs.AStarPathFindingForDSL;
import CFG.Node;
import ai.abstraction.AbstractAction;
import ai.abstraction.AbstractionLayerAI;
import ai.abstraction.pathfinding.PathFinding;
import ai.core.AI;
import ai.core.ParameterSpecification;
import rts.GameState;
import rts.PlayerAction;
import rts.units.Unit;
import rts.units.UnitTypeTable;

public class CoreInterpreter extends AbstractionLayerAI {

	
	public CoreInterpreter(UnitTypeTable a_utt) {
		this(a_utt, new AStarPathFindingForDSL());
		// TODO Auto-generated constructor stub
		
	}



	public CoreInterpreter(UnitTypeTable a_utt, AStarPathFindingForDSL a_pf) {
		super(a_pf);
        reset(a_utt);
	}
	
	public CoreInterpreter(PathFinding a_pf) {
		super(a_pf);
		// TODO Auto-generated constructor stub
	}

	public CoreInterpreter(PathFinding a_pf, int timebudget, int cyclesbudget) {
		super(a_pf, timebudget, cyclesbudget);
		// TODO Auto-generated constructor stub
	}
	
	public void clear() {
		this.actions.clear();
	}
	
	

	@Override
	public PlayerAction getAction(int player, GameState gs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AI clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParameterSpecification> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
