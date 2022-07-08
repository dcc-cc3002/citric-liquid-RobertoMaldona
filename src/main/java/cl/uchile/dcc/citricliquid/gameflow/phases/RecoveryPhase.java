package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.phases.EndTurnPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.Phase;

public class RecoveryPhase extends Phase {

    @Override
    public void recover(){
        gameController.recover();
    }

    @Override
    public void toEndTurnPhase(){
        changePhase(new EndTurnPhase());
    }
}
