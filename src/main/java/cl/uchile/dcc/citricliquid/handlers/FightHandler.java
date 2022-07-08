package cl.uchile.dcc.citricliquid.handlers;

import cl.uchile.dcc.citricliquid.gameflow.GameController;

import java.beans.PropertyChangeEvent;

public class FightHandler implements Handler{
    private GameController gameController;

    public FightHandler(GameController controller){
        gameController = controller;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if((boolean) evt.getNewValue()){
            gameController.chooseFight();
        }
    }
}
