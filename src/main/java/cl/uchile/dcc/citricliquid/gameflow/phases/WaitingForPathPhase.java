package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.IncorrectDirectionException;
import cl.uchile.dcc.citricliquid.gameflow.InvalidPhaseTransition;
import cl.uchile.dcc.citricliquid.gameflow.phases.MovingPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.Phase;

public class WaitingForPathPhase extends Phase {


    @Override
    public void moveUp() throws IncorrectDirectionException, InvalidPhaseTransition {
        gameController.moveUp();
    }
    @Override
    public void moveDown() throws IncorrectDirectionException, InvalidPhaseTransition {
        gameController.moveDown();
    }
    @Override
    public void moveLeft() throws IncorrectDirectionException, InvalidPhaseTransition {
        gameController.moveLeft();
    }
    @Override
    public void moveRight() throws IncorrectDirectionException, InvalidPhaseTransition {
        gameController.moveRight();
    }

    @Override
    public void toMovingPhase() {
        canMoving();
        changePhase(new MovingPhase());
    }
}
