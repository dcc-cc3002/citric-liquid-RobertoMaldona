package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.InvalidPhaseTransition;
import cl.uchile.dcc.citricliquid.gameflow.phases.ChooseCardPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.Phase;
import cl.uchile.dcc.citricliquid.gameflow.phases.RecoveryPhase;

public class StartPhase extends Phase {

    @Override
    public void start() throws InvalidPhaseTransition {
        if(gameController.getOwnerTurn().isK_O()){
            toRecoveryPhase();
            gameController.try_recover();
        } else {
            toMovingPhase();
            gameController.try_startMove();
        }
    }

    @Override
    public void toChooseCardPhase() throws InvalidPhaseTransition {
        changePhase(new ChooseCardPhase());
    }

    @Override
    public void toRecoveryPhase() throws InvalidPhaseTransition {
        changePhase(new RecoveryPhase());
    }
    @Override
    public void toMovingPhase() {
        changePhase(new MovingPhase());
    }
}
