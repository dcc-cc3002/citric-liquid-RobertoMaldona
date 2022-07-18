package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.InvalidActionException;
import cl.uchile.dcc.citricliquid.gameflow.InvalidPhaseTransition;
import cl.uchile.dcc.citricliquid.gameflow.phases.EndTurnPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.Phase;

public class FightPhase extends Phase {

    @Override
    public void attack(){

    }

    @Override
    public void evade() throws InvalidPhaseTransition, InvalidActionException {
        gameController.evade();
    }

    @Override
    public void defense() throws InvalidPhaseTransition, InvalidActionException {
        gameController.defense();
    }

    @Override
    public void fight(){
        gameController.startFight();
    }

    @Override
    public void toEndTurnPhase() {
        changePhase(new EndTurnPhase());
    }
}
