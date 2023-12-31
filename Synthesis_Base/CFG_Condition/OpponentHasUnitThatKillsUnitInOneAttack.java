package CFG_Condition;

import java.util.ArrayList;
import java.util.List;

import AIs.Interpreter;
import CFG.ChildB;
import CFG.Factory;
import CFG.Node;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.Unit;

public class OpponentHasUnitThatKillsUnitInOneAttack implements ChildB {

	boolean value;
	public OpponentHasUnitThatKillsUnitInOneAttack() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String translate() {
		// TODO Auto-generated method stub
		return "u.OpponentHasUnitThatKillsUnitInOneAttack()";
	}

	@Override
	public void interpret(GameState gs, int player, Unit u, Interpreter automato) throws Exception {
		// TODO Auto-generated method stub
		PhysicalGameState pgs = gs.getPhysicalGameState();
		
		
		if(automato.me.ContainsBool(this.getName())) {
			this.value = automato.me.getBool(this.getName());
    		return;
    		
    	}
		automato.me.setBool(this.getName(), false);
		
		
		
		value = false;
		
		for (Unit u2 : pgs.getUnits()) {

            if (u2.getPlayer() >= 0 && u2.getPlayer() != player) {

                int damage = u2.getMaxDamage();
                int HP = u.getHitPoints();

                if (damage >= HP) {
                	automato.me.setBool(this.getName(), true);
                	value= true;
                	return;
                }

            }

        }
		

	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "OpponentHasUnitThatKillsUnitInOneAttack";
	}

	@Override
	public String translateIndentation(int tap) {
		// TODO Auto-generated method stub
		return this.translate();
	}

	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public Node Clone(Factory f) {
		// TODO Auto-generated method stub
		return f.build_OpponentHasUnitThatKillsUnitInOneAttack();
	}

	@Override
	public boolean equals(Node n) {
		// TODO Auto-generated method stub
		if (!(n instanceof OpponentHasUnitThatKillsUnitInOneAttack)) return false;
		return true;
	}

	@Override
	public List<ChildB> AllCombinations(Factory f) {
		// TODO Auto-generated method stub
		OpponentHasUnitThatKillsUnitInOneAttack aux = (OpponentHasUnitThatKillsUnitInOneAttack) f.build_OpponentHasUnitThatKillsUnitInOneAttack();
		List<ChildB> l = new ArrayList<>();
		l.add(aux);
		return l;
	}

	@Override
	public void resert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean clear(Node father,Factory f) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void load(List<String> list,Factory f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void salve(List<String> list) {
		// TODO Auto-generated method stub
		list.add(this.getName());
	}
	
}
