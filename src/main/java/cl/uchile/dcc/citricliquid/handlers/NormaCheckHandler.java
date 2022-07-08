package cl.uchile.dcc.citricliquid.handlers;

import cl.uchile.dcc.citricliquid.gameflow.GameController;

import java.beans.PropertyChangeEvent;

public class NormaCheckHandler implements Handler{
    private GameController gameController;

    public NormaCheckHandler(GameController controller){
        gameController = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        gameController.newNormaLevel();
    }
}
