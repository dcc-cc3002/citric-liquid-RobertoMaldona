package cl.uchile.dcc.citricliquid.gameflow.phases;

public class MovingPhase extends Phase {

    @Override
    public void move(){
        gameController.moveStart();
    }

    @Override
    public void keepMoving(){
        gameController.keepMoving();
    }

    @Override
    public void toWaitingFightPhase() {
        changePhase(new WaitingFightPhase());
    }

    @Override
    public void toWaitingForHomePhase() {
        changePhase(new WaitForHomePhase());
    }

    @Override
    public void toWaitingForPath() {
        changePhase(new WaitingForPathPhase());
    }

    @Override
    public void toEndTurnPhase() {
        changePhase(new EndTurnPhase());
    }
}
