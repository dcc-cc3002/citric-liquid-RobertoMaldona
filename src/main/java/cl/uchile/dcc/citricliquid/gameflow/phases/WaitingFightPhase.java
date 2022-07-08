package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.phases.FightPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.MovingPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.Phase;

public class WaitingFightPhase extends Phase {

    @Override
    public void waitToFight() {

    }

    @Override
    public void toFightPhase() {
        changePhase(new FightPhase());
    }

    @Override
    public void toMovingPhase() {
        canMoving();
        changePhase(new MovingPhase());
    }
}
