package Cutoff;

import rts.GameState;

public interface Cutoff {
	boolean stop(GameState gs);
	void resert();
}
