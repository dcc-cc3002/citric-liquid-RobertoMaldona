package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.phases.EndTurnPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.Phase;

public class FightPhase extends Phase {

    @Override
    public void toEndTurnPhase() {
        changePhase(new EndTurnPhase());
    }
}
