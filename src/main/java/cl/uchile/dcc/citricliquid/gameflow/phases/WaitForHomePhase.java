package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.phases.EndTurnPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.MovingPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.Phase;

public class WaitForHomePhase extends Phase {

    @Override
    public void waitHome() {

    }
    @Override
    public void stayAtHome(){
        toEndTurnPhase();
        gameController.stayAtHome();
    }

    @Override
    public void toEndTurnPhase() {
        changePhase(new EndTurnPhase());
    }

    @Override
    public void toMovingPhase() {
        canMoving();
        changePhase(new MovingPhase());
    }
}
