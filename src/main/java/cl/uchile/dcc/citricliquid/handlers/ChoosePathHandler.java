package cl.uchile.dcc.citricliquid.handlers;

import cl.uchile.dcc.citricliquid.gameflow.GameController;

import java.beans.PropertyChangeEvent;

public class ChoosePathHandler implements Handler{
    private GameController gameController;

    public ChoosePathHandler(GameController controller){
        gameController = controller;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if((boolean) evt.getNewValue()){
            gameController.moreThan1Path();
        }
    }
}
