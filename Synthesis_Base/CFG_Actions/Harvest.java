package CFG_Actions;

import java.util.ArrayList;
import java.util.List;

import AIs.AStarPathFindingForDSL;
import AIs.Interpreter;
import CFG.AlmostTerminal;
import CFG.ChildC;
import CFG.Direction;
import CFG.Factory;
import CFG.N;
import CFG.Node;
import CFG.Type;
import ai.abstraction.AbstractAction;

import rts.GameState;
import rts.PhysicalGameState;
import rts.Player;
import rts.units.Unit;
import util.Pair;

public class Harvest implements ChildC {

	N n;
	boolean used;
	public Harvest() {
		// TODO Auto-generated constructor stub
		this.used=false;
		n = new N();
	}

	
	
	public Harvest(N n) {
		super();
		this.n = n;
		this.used=false;
	}



	public N getN() {
		return n;
	}



	public void setN(N n) {
		this.n = n;
	}



	@Override
	public String translate() {
		// TODO Auto-generated method stub
		return "u.harvest("+n.getN()+")";
	}


	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Harvest";
	
	}
	
	public boolean contador(GameState gs, int player, Interpreter automato) {
		PhysicalGameState pgs = gs.getPhysicalGameState();
	       
        int cont =0;
    	int n_int= Integer.parseInt(n.getValue());
  
    	if(automato.me.ContainsInt("Harvest")) {
    		int n_actully = automato.me.getInt("Harvest");
    		
    		 return  n_actully<n_int; 		
    	}
    	
		 for(Unit u2:pgs.getUnits()) {


	            if (  u2.getPlayer() == player ) {
	     
		           // UnitAction a1 = gs.getActionAssignment(u2).action;
		            AbstractAction a2 = automato.getAbstractAction(u2);
		          
		            
		            if(a2 instanceof ai.abstraction.Harvest) {
		            	cont++;
		            }
	            }

		 }
		 
		 return cont<n_int;
	}
	
	@Override
	public void interpret(GameState gs, int player, Unit u, Interpreter automato) throws Exception {
		
		
		if (automato.me.containblockTable(this.getName()+u.getID())){
			
			return;
		}
		PhysicalGameState pgs = gs.getPhysicalGameState();
		
		
		Player p = gs.getPlayer(player);
		
		//if(!u.getType().canHarvest) 	throw new Exception();
		if(!u.getType().canHarvest) return;
		if(u.getPlayer() == player   && automato.getAbstractAction(u)==null) {
			if(!this.contador(gs, player, automato))return	;
				
				Unit closestBase = null;
	            Unit closestResource = null;
	            int closestDistance = 0;
	            for (Unit u2 : pgs.getUnits()) {
	                if (u2.getType().isResource) {
	                    int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());
	                    if (closestResource == null || d < closestDistance) {
	                        closestResource = u2;
	                        closestDistance = d;
	                    }
	                }
	            }
	            closestDistance = 0;
	            for (Unit u2 : pgs.getUnits()) {
	                if (u2.getType().isStockpile && u2.getPlayer()==p.getID()) {
	                    int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());
	                    if (closestBase == null || d < closestDistance) {
	                        closestBase = u2;
	                        closestDistance = d;
	                    }
	                }
	            }
	            if (closestResource != null && closestBase != null) {
	            	AStarPathFindingForDSL pf = (AStarPathFindingForDSL) automato.pf;
	            	Pair<Integer,Integer> move1 = pf.findPathToPositionInRange2(u, closestResource.getX() + closestResource.getY() * pgs.getWidth(),1,
							gs,automato.me);
	            	Pair<Integer,Integer> move2 = pf.findPathToPositionInRange2(u, closestBase.getX() + closestBase.getY() * pgs.getWidth(),1,
							gs,automato.me);
	            	if(move1!=null || move2!=null ) {
		                automato.harvest(u, closestResource, closestBase);
		                this.used= true;
		                automato.me.plusInt("Harvest",1);
	            	}
	            	else {
	            		automato.me.setblockTable(this.getName()+u.getID());
	            	}
	            }
				
				
			}
			
				
					
		}

	@Override
	public String translateIndentation(int tap) {
		// TODO Auto-generated method stub
		String esp= "";
		for(int i =0; i<tap;i++)esp+="\t";
		return esp + this.translate();
	}

	@Override
	public Node Clone(Factory f) {
		// TODO Auto-generated method stub
		return f.build_Harvest((N) this.n.Clone(f));
	}

	@Override
	public boolean equals(Node n) {
		// TODO Auto-generated method stub
		if (!(n instanceof Harvest)) return false;
		Harvest h = (Harvest)n;
		
		return this.n.equals(n);
	}

	@Override
	public List<ChildC> AllCombinations(Factory f) {
		// TODO Auto-generated method stub
		N n2 = (N) f.build_N();
		
		List<ChildC> l = new ArrayList<>();
		for(AlmostTerminal s3 : n2.AllCombinations(f)) {
			Harvest h = (Harvest) f.build_Harvest((N)s3.Clone(f));
			l.add(h);
		}
	
		return l;
	}

	@Override
	public void resert() {
		// TODO Auto-generated method stub
		this.used=false;
		
	}
	
	@Override
	public boolean clear(Node father,Factory f ) {
		// TODO Auto-generated method stub
		return used;
	}

	@Override
	public void load(List<String> list,Factory f) {
		// TODO Auto-generated method stub
		String s = list.get(0);
		list.remove(0);
		
		
		this.n = (N) f.build_N(s);
	}

	@Override
	public void salve(List<String> list) {
		// TODO Auto-generated method stub
		list.add(this.getName());
		list.add(this.n.getValue());
		
	}
	
	@Override
	public String translate2() {
		// TODO Auto-generated method stub
		return "u.harvest(|"+n.getN()+"|)";
	}

	
}
