package cl.uchile.dcc.citricliquid.gameflow;

public class Phase {
    private GameController gameController;

    public void setGameController(GameController controller){
        gameController = controller;
    }

    public void start() throws InvalidActionException {
        throw new InvalidActionException("You can´t start in this phase");
    }

    public void recover() throws InvalidActionException{
        throw new InvalidActionException("You can´t recover in this phase");
    }

    public void chooseCardToPlay() throws InvalidActionException{
        throw new InvalidActionException("You can´t choose to play a card in this phase");
    }

    public void move() throws InvalidActionException{
        throw new InvalidActionException("you can´t move in this phase");
    }

    public void keepMoving() throws InvalidActionException{
        throw new InvalidActionException("You can´t choose between keep moving oo stay in current panel");
    }

    public void waitHome() throws InvalidActionException{
        throw new InvalidActionException("You can´t stay in home in this moment");
    }

    public void waitToFight() throws InvalidActionException{
        throw new InvalidActionException("You can´t Fight with other Unit in this moment");
    }

    public void waitToChosePath() throws InvalidActionException{
        throw new InvalidActionException("You cant choose the path to continue the moving in this phase");
    }

    public void finishTurn() throws InvalidActionException{
        throw new InvalidActionException("You can´t finish turn in this moment");
    }

}
