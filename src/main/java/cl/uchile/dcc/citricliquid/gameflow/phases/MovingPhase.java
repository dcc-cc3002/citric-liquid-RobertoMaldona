package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.InvalidPhaseTransition;

public class MovingPhase extends Phase {

    @Override
    public void move() throws InvalidPhaseTransition {
        gameController.moveStart();
    }

    @Override
    public void keepMoving() throws InvalidPhaseTransition {
        gameController.keepMoving();
    }

    @Override
    public void toWaitingFightPhase() {
        changePhase(new WaitingFightPhase());
    }

    @Override
    public void toWaitingForHomePhase() {
        changePhase(new WaitForHomePhase());
    }

    @Override
    public void toWaitingForPath() {
        changePhase(new WaitingForPathPhase());
    }

    @Override
    public void toEndTurnPhase() {
        changePhase(new EndTurnPhase());
    }
}
