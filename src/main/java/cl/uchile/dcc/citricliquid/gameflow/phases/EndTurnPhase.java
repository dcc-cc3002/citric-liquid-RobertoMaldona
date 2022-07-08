package cl.uchile.dcc.citricliquid.gameflow.phases;

public class EndTurnPhase extends Phase {

    @Override
    public void finishTurn(){
        gameController.finishTurn();
    }

    @Override
    public void toStartPhase(){
        changePhase(new StartPhase());
    }
}
