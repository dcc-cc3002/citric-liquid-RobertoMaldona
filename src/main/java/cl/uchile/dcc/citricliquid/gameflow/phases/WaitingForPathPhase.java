package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.InvalidPhaseTransition;
import cl.uchile.dcc.citricliquid.gameflow.phases.MovingPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.Phase;

public class WaitingForPathPhase extends Phase {

    @Override
    public void waitToChosePath() {

    }

    @Override
    public void toMovingPhase() {
        canMoving();
        changePhase(new MovingPhase());
    }
}
