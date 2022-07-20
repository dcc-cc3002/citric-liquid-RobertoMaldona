package cl.uchile.dcc.citricliquid.gameflow.phases;

public class StartPhase extends Phase {

    @Override
    public void start() {
        if(gameController.getOwnerTurn().isKO()){
            toRecoveryPhase();
            gameController.try_recover();
        } else {
            gameController.starsAtStart();
            toMovingPhase();
            gameController.try_startMove();
        }
    }

    @Override
    public void toRecoveryPhase() {
        changePhase(new RecoveryPhase());
    }
    @Override
    public void toMovingPhase() {
        changePhase(new MovingPhase());
    }
}
