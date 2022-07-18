package cl.uchile.dcc.citricliquid.gameflow.phases;

import cl.uchile.dcc.citricliquid.gameflow.GameController;
import cl.uchile.dcc.citricliquid.gameflow.IncorrectDirectionException;
import cl.uchile.dcc.citricliquid.gameflow.InvalidActionException;
import cl.uchile.dcc.citricliquid.gameflow.InvalidPhaseTransition;

public class Phase {
    protected GameController gameController;

    /**
     * Set the gameController of this phase
     * @param controller gameController
     */
    public void setGameController(GameController controller){
        gameController = controller;
    }

    /**
     * method that start the turn in the gameController
     * @throws InvalidActionException if the phase actual have not permited throw this method
     */
    public void start() throws InvalidActionException, InvalidPhaseTransition {
        throw new InvalidActionException("You can´t start in this phase");
    }
    /**
     * method that execute the method when the player is KO in the gameController
     * @throws InvalidActionException if the phase actual have not permited throw this method
     */
    public void recover() throws InvalidActionException, InvalidPhaseTransition {
        throw new InvalidActionException("You can´t recover in this phase");
    }

    /**
     * method that execute the method to choose a card to play in the gameController
     * @throws InvalidActionException if the phase actual have not permited throw this method
     */
    public void chooseCardToPlay() throws InvalidActionException{
        throw new InvalidActionException("You can´t choose to play a card in this phase");
    }

    /**
     * method that execute the method to move the owner turn's player in the gameController
     * @throws InvalidActionException if the phase actual have not permited throw this method
     */
    public void move() throws InvalidActionException{
        throw new InvalidActionException("you can´t move in this phase");
    }

    /**
     * method that keep moving the owner turn's player in the gameController
     * @throws InvalidActionException if the phase actual have not permited throw this method
     */
    public void keepMoving() throws InvalidActionException{
        throw new InvalidActionException("You can´t choose between keep moving oo stay in current panel");
    }

    /**
     * method to stay at owner's home in the gameController
     * @throws InvalidActionException if the phase actual have not permitted throw this method
     */
    public void stayAtHome() throws InvalidActionException {
        throw new InvalidActionException("You can´t choose stay at home in current panel");
    }

    public void waitHome() throws InvalidActionException{
        throw new InvalidActionException("You can´t stay in home in this moment");
    }

    public void waitToFight() throws InvalidActionException{
        throw new InvalidActionException("You can´t Fight with other Unit in this moment");
    }

    /**
     * method that finish the turn in the gameController
     * @throws InvalidActionException if the phase actual have not permited throw this method
     */
    public void waitToChosePath() throws InvalidActionException{
        throw new InvalidActionException("You cant choose the path to continue the moving in this phase");
    }

    /**
     * method that finish the turn in the gameController
     * @throws InvalidActionException if the phase actual have not permited throw this method
     */
    public void finishTurn() throws InvalidActionException{
        throw new InvalidActionException("You can´t finish turn in this moment");
    }

    /**
     * method that evade in the gameController
     * @throws InvalidActionException if the phase actual have not permited throw this method
     */
    public void evade() throws InvalidActionException, InvalidPhaseTransition {
        throw new InvalidActionException("You can´t evade in this moment");
    }
    /**
     * method that defense in the gameController
     * @throws InvalidActionException if the phase actual have not permited throw this method
     */
    public void defense() throws InvalidActionException, InvalidPhaseTransition {
        throw new InvalidActionException("You can´t evade in this moment");
    }

    /**
     * method that attack in the gameController
     * @throws InvalidActionException if the phase actual have not permited throw this method
     */
    public void attack() throws InvalidActionException {
        throw new InvalidActionException("You can´t attack in this moment");
    }

    /**
     * method to fight in the gameController
     * @throws InvalidActionException if the phase actual have not permitted throw this method
     */
    public void fight() throws InvalidActionException {
        throw new InvalidActionException("You can´t fight in this moment");
    }

    /**
     * method to set True to field "canOwnerMove" of gameController
     */
    protected void canMoving(){
        gameController.setCanOwnerMove(true);
    }
    /**
     * method to set false to field "canOwnerMove" of gameController
     */
    protected void stopMoving() {
        gameController.stopMove();
    }

    /**
     * method to change the gameController's phase.
     * @param phase new phase to set
     */
    public void changePhase(Phase phase){
        gameController.setPhase(phase);
    }

    /**
     * Method to set the next phase to StartPhase
     * @throws InvalidPhaseTransition if the phase actual can't change to StartPhase
     */
    public void toStartPhase() throws InvalidPhaseTransition {
        throw new InvalidPhaseTransition("You can´t move to Start Phase in this phase");
    }

    /**
     * Method to set the next phase to RecoveryPhase
     * @throws InvalidPhaseTransition if the phase actual can't change to RecoveryPhase
     */
    public void toRecoveryPhase() throws InvalidPhaseTransition {
        throw new InvalidPhaseTransition("You can´t move to Recovery Phase in this phase");
    }
    /**
     * Method to set the next phase to ChooseCardPhase
     * @throws InvalidPhaseTransition if the phase actual can't change to ChooseCardPhase
     */
    public void toChooseCardPhase() throws InvalidPhaseTransition{
        throw new InvalidPhaseTransition("You can´t move to ChooseCard Phase in this phase");
    }
    /**
     * Method to set the next phase to MovingPhase
     * @throws InvalidPhaseTransition if the phase actual can't change to MovingPhase
     */
    public void toMovingPhase() throws InvalidPhaseTransition{
        throw new InvalidPhaseTransition("You can´t move to Moving Phase in this phase");
    }
    /**
     * Method to set the next phase to WaitingFightPhase
     * @throws InvalidPhaseTransition if the phase actual can't change to WaitingFightPhase
     */
    public void toWaitingFightPhase() throws InvalidPhaseTransition{
        throw new InvalidPhaseTransition("You can´t move to WaitingFight Phase in this phase");
    }
    /**
     * Method to set the next phase to WaitingForHomePhase
     * @throws InvalidPhaseTransition if the phase actual can't change to WaitingForHomePhase
     */
    public void toWaitingForHomePhase() throws InvalidPhaseTransition{
        throw new InvalidPhaseTransition("You can´t move to WaitingForHome Phase in this phase");
    }
    /**
     * Method to set the next phase to WaitingForPath
     * @throws InvalidPhaseTransition if the phase actual can't change to WaitingForPath
     */
    public void toWaitingForPath() throws InvalidPhaseTransition{
        throw new InvalidPhaseTransition("You can´t move to WaitingForPath Phase in this phase");
    }
    /**
     * Method to set the next phase to FightPhase
     * @throws InvalidPhaseTransition if the phase actual can't change to FightPhase
     */
    public void toFightPhase() throws InvalidPhaseTransition{
        throw new InvalidPhaseTransition("You can´t move to Fight Phase in this phase");
    }
    /**
     * Method to set the next phase to EndTurnPhase
     * @throws InvalidPhaseTransition if the phase actual can't change to EndTurnPhase
     */
    public void toEndTurnPhase() throws InvalidPhaseTransition{
        throw new InvalidPhaseTransition("You can´t move to EndTurnPhase Phase in this phase");
    }

    /**
     * method to choose move to up in the gameController
     * @throws InvalidActionException if the phase actual have not permitted throw this method
     */
    public void moveUp() throws InvalidActionException, IncorrectDirectionException, InvalidPhaseTransition {
        throw new InvalidActionException("You can´t choose move to up in this moment");
    }
    /**
     * method to choose move to down in the gameController
     * @throws InvalidActionException if the phase actual have not permitted throw this method
     */
    public void moveDown() throws InvalidActionException, IncorrectDirectionException, InvalidPhaseTransition {
        throw new InvalidActionException("You can´t choose move to down in this moment");
    }
    /**
     * method to choose move to left in the gameController
     * @throws InvalidActionException if the phase actual have not permitted throw this method
     */
    public void moveLeft() throws InvalidActionException, IncorrectDirectionException, InvalidPhaseTransition {
        throw new InvalidActionException("You can´t choose move to left in this moment");
    }
    /**
     * method to choose move to right in the gameController
     * @throws InvalidActionException if the phase actual have not permitted throw this method
     */
    public void moveRight() throws InvalidActionException, IncorrectDirectionException, InvalidPhaseTransition {
        throw new InvalidActionException("You can´t choose move to right in this moment");
    }
}
