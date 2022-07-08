package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.phases.Phase;

public class ChooseCardPhase extends Phase {

    @Override
    public void chooseCardToPlay(){

    }

    @Override
    public void toMovingPhase(){
        changePhase(new MovingPhase());
    }
}
