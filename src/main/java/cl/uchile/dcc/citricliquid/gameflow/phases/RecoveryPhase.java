package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.InvalidPhaseTransition;
import cl.uchile.dcc.citricliquid.gameflow.phases.EndTurnPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.Phase;

public class RecoveryPhase extends Phase {

    @Override
    public void recover() throws InvalidPhaseTransition {
        gameController.recover();
    }

    @Override
    public void toEndTurnPhase(){
        changePhase(new EndTurnPhase());
    }

    @Override
    public void toStartPhase(){
        changePhase(new StartPhase());
    }
}
