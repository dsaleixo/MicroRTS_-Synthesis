package DesignInteligent;

import java.util.List;

import CFG.Node;
import LS_CFG.Node_LS;

public interface Mutation {
	List<Node_LS> mutation(Node_LS node, int n);
	
}
