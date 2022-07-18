package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.InvalidPhaseTransition;
import cl.uchile.dcc.citricliquid.gameflow.phases.ChooseCardPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.Phase;
import cl.uchile.dcc.citricliquid.gameflow.phases.RecoveryPhase;

public class StartPhase extends Phase {

    @Override
    public void start() {
        if(gameController.getOwnerTurn().isK_O()){
            toRecoveryPhase();
            gameController.try_recover();
        } else {
            gameController.starsAtStart();
            toMovingPhase();
            gameController.try_startMove();
        }
    }

    @Override
    public void toChooseCardPhase() {
        changePhase(new ChooseCardPhase());
    }

    @Override
    public void toRecoveryPhase() {
        changePhase(new RecoveryPhase());
    }
    @Override
    public void toMovingPhase() {
        changePhase(new MovingPhase());
    }
}
