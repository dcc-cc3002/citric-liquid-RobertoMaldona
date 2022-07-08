package cl.uchile.dcc.citricliquid.gameflow;

import cl.uchile.dcc.citricliquid.gameflow.phases.*;
import cl.uchile.dcc.citricliquid.handlers.ChoosePathHandler;
import cl.uchile.dcc.citricliquid.handlers.FightHandler;
import cl.uchile.dcc.citricliquid.handlers.HomePanelHandler;
import cl.uchile.dcc.citricliquid.handlers.NormaCheckHandler;
import cl.uchile.dcc.citricliquid.model.board.*;
import cl.uchile.dcc.citricliquid.model.norma.INormaGoal;
import cl.uchile.dcc.citricliquid.model.units.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameController {
    private Phase phase_actual;
    private final List<Player> list_players = new ArrayList<>();
    private final List<IPanel> board = new ArrayList<>();
    private Player ownerTurn;
    private int nturn;
    private int chapter;
    private int remainingSteps;
    private Player winner = null;
    private boolean gameOver = false;
    private boolean canOwnerMove = false;

    private final NormaCheckHandler normaCheckHandler = new NormaCheckHandler(this);
    private final HomePanelHandler homePanelHandler = new HomePanelHandler(this);
    private final FightHandler fightHandler = new FightHandler(this);
    private final ChoosePathHandler choosePathHandler = new ChoosePathHandler(this);

    public GameController(){
        chapter = 1;
        phase_actual = new Phase();
        nturn = 1;
    }

    /**
     * Set the phase actual of the GameController
     * @param phase phase
     */
    public void setPhase(@NotNull Phase phase) {
        phase_actual = phase;
        phase.setGameController(this);
    }

    /**
     * Create a new player,
     * so add it to list_players and set the home panel of the player.
     * @param name player's name
     * @param hp   HP points
     * @param atk  atk points
     * @param def  defense points
     * @param evd  evasion points
     * @param panel the panel that will be set like the player's homePanel
     */
    public Player addPlayer(String name, int hp, int atk, int def, int evd, @NotNull IPanel panel){
        Player player = new Player(name, hp, atk, def, evd);
        list_players.add(player);
        panel.addPlayer(player);
        setHomePanel(player, panel);
        player.setActualPanel(panel);
        return player;
    }

    /**
     * Method that sets the HomePanel in a player
     * @param player the player
     * @param newHome new HomePanel
     */
    public void setHomePanel(Player player, IPanel newHome) {
        player.setHomePanel(newHome);
    }

    /**
     * Set the new Panel where the player is, so remove the player from the panel where it was
     * and add it to the new panel.
     * @param player the player
     * @param panel  the new panel
     */
    public void setPlayerPanel(Player player, IPanel panel) {
        player.getActualPanel().removePlayer(player);
        panel.addPlayer(player);
        player.setActualPanel(panel);
    }

    /**
     * Create a BossUnit
     * @param name BossUnit's name
     * @param hp   HP points
     * @param atk  atk points
     * @param def  defense points
     * @param evd  evasion points
     * @return BossUnit
     */
    public BossUnit addBossUnit(String name, int hp, int atk, int def, int evd) {
        return new BossUnit(name, hp, atk, def, evd);
    }

    /**
     * Create a WildUnit
     * @param name WildUnit's name
     * @param hp   HP points
     * @param atk  atk points
     * @param def  defense points
     * @param evd  evasion points
     * @return WildUnit the created Unit
     */
    public WildUnit addWildUnit(String name, int hp, int atk, int def, int evd) {
        return new WildUnit(name, hp, atk, def, evd);
    }

    /**
     * Method that return the list of players
     * @return listOfPlayers
     */
    public List<Player> getListPlayers() {
        return list_players;
    }

    /**
     * Method that create a new Bonus Panel and add it to the list of board.
     * @param id the id of the new Panel
     * @return new Bonus Panel
     */
    public BonusPanel addBonusPanel(int id) {
        BonusPanel bonusPanel = new BonusPanel(id);
        board.add(bonusPanel);
        return bonusPanel;
    }

    /**
     * Method that create a new Boss Panel
     * and add it to the list of All the Panels
     * @param id the id of the new Panel
     * @return new Boss Panel
     */
    public BossPanel addBossPanel(int id) {
        BossPanel bossPanel = new BossPanel(id);
        board.add(bossPanel);
        return bossPanel;
    }

    /**
     * Method that create a new Drop Panel and add it to the list of board.
     * @param id the id of the new Panel
     * @return new Drop Panel
     */
    public DropPanel addDropPanel(int id) {
        DropPanel dropPanel = new DropPanel(id);
        board.add(dropPanel);
        return dropPanel;
    }

    /**
     * Method that create a new Encounter Panel and add it to the list of board.
     * @param id the id of the new Panel
     * @return new Encounter Panel
     */
    public EncounterPanel addEncounterPanel(int id) {
        EncounterPanel encounterPanel = new EncounterPanel(id);
        board.add(encounterPanel);
        return encounterPanel;
    }

    /**
     * Method that create a new Encounter Panel and add it to the list of board.
     * @param id the id of the new Panel
     * @return new Home Panel
     */
    public HomePanel addHomePanel(int id) {
        HomePanel NP = new HomePanel(id);
        board.add(NP);
        return NP;
    }

    /**
     * Method that create a new Encounter Panel and add it to the list of board.
     * @param id the id of the new Panel
     * @return new Neutral Panel
     */
    public NeutralPanel addNeutralPanel(int id) {
        NeutralPanel neutralPanel = new NeutralPanel(id);
        board.add(neutralPanel);
        return neutralPanel;
    }

    /**
     * Return a list with all the panel next to xpanel
     * @param xpanel the panel
     * @return list with the next panel of actual
     */
    public Set<IPanel> getNextPanels(IPanel xpanel) {
        return xpanel.getNextPanels();
    }

    /**
     * Method that set that newpanel is next to actual
     * @param actual actual panel
     * @param newpanel panel next to actual
     */
    public void setNextPanel(@NotNull IPanel actual, IPanel newpanel) {
        actual.addNextPanel(newpanel);
    }

    /**
     * Method that returns a list with all the panels created in the controller
     * @return copy of board, it is all the panels.
     */
    public Set<IPanel> getPanels() {
        return Set.copyOf(board);
    }

    /**
     * Method to activate the owner turn's current panel
     */
    public void activatePanel(){
        ownerTurn.getActualPanel().activatedBy(ownerTurn);
    }
    /**
     * @return the chapter actual.
     */
    public int getChapter(){
        return chapter;
    }

    /**
     * @return the player that is the owner of turn.
     */
    public Player getOwnerTurn() {
        return ownerTurn;
    }

    /**
     * Set the normaGoal of the Player that have the turn
     * @param goal new NormaGoal
     */
    public void setNormaGoal(INormaGoal goal) {
        getOwnerTurn().setNormaGoal(goal);
    }

    /**
     * Set nturn, and chapter if it's necessary, when a player ends his turn.
     * Change ownerTurn too, to the next owner of the turn.
     */
    public void finishTurn(){
        activatePanel();
        if(nturn==4){
            chapter++;
            nturn=1;
        }else {
            nturn++;
        }
        resetOwnerTurn();
    }

    /**
     * Method to assign the Player to ownerTurn.
     */
    public void resetOwnerTurn(){
        ownerTurn = getListPlayers().get(nturn-1);
        ownerTurn.addNormaLevelHandler(normaCheckHandler);

    }
    /**
     * Set the field canOwnerMove
     * @param bool that represent if the player turn's owner can move.
     */
    public void setCanOwnerMove(boolean bool){
        canOwnerMove = bool;
    }

    /**
     * Method that stop the moving of the player turn's owner.
     */
    public void stopMove(){
        setCanOwnerMove(false);
    }
    /**
     * Method that is used when the observer detect that the actual panel's ownerTurn have more than one next panel.
     * So try to change the actual phase to a WaitingForPath phase.
     */
    public void moreThan1Path()  {
        if(remainingSteps>0){
            stopMove();
            try {
                phase_actual.toWaitingForPath();
            } catch (InvalidPhaseTransition invalidPhaseTransition) {
                invalidPhaseTransition.printStackTrace();
            }
        }
    }

    /**
     * Method that is used when the observer detect that the actual panel's ownerTurn have more than one Player.
     * So try to change the actual phase to a WaitingFight phase.
     */
    public void chooseFight() {
        stopMove();
        try {
            phase_actual.toWaitingFightPhase();
        } catch (InvalidPhaseTransition invalidPhaseTransition) {
            invalidPhaseTransition.printStackTrace();
        }
    }

    /**
     * Method that is used when the observer detect that the actual panel's ownerTurn is his HomePanel.
     * So try to change the actual phase to a WaitingForHome phase.
     */
    public void onHomePanel() {
        if(remainingSteps>0){
            stopMove();
            try {
                phase_actual.toWaitingForHomePhase();
            } catch (InvalidPhaseTransition invalidPhaseTransition) {
                invalidPhaseTransition.printStackTrace();
            }
        }

    }

    /**
     * Method that is used when the observer detect that the norma level's ownerTurn has been incremented.
     * So check if the player own of the turn win or not.
     */
    public void newNormaLevel() {
        if(ownerTurn.getNormaLevel()==6){
            winner= ownerTurn;
            gameOver = true;
        }
    }

    /**
     * Return the player winner of the game or null if there's no winner yet.
     * @return winner
     */
    @Nullable
    public Player getWinner() {
        return winner;
    }

    /**
     * Say if the game has finished or not
     * @return true or false
     */
    public boolean isGameOver(){
        return gameOver;
    }

    /**
     * The owner turn move at the start of his turn
     */
    public void moveStart() {
        ownerTurn.addAtHomePanelHandler(homePanelHandler);
        ownerTurn.addMoreThanOnePlayerHandler(fightHandler);
        ownerTurn.addMoreTanOnePathHandler(choosePathHandler);

        remainingSteps = ownerTurn.roll();
        setCanOwnerMove(true);
        while (remainingSteps>0 && canOwnerMove){
            IPanel nextPanel= getNextPanels(ownerTurn.getActualPanel()).iterator().next();
            remainingSteps--;
            setPlayerPanel(ownerTurn, nextPanel);
        }
        stopMove();
    }

    /**
     * @return the GameController's Actual Phase.
     */
    public Phase getPhase() {
        return phase_actual;
    }

    /**
     * Method to start a turn.
     */
    public void turnStart() {
        try {
            phase_actual.start();
        } catch (InvalidPhaseTransition | InvalidActionException invalidPhaseTransition) {
            invalidPhaseTransition.printStackTrace();
        }
    }

    /**
     * Method to ejecute a turn when the player is KO in recover Phase.
     */
    public void try_recover(){
        try {
            phase_actual.recover();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to start to move
     */
    public void try_startMove(){
        try {
            phase_actual.move();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }


    public void recover() {
        int dice = getOwnerTurn().roll();
        if(dice<(7-chapter)){
            try_endTurn();
        } else {
            try {
                phase_actual.toStartPhase();
            } catch (InvalidPhaseTransition invalidPhaseTransition) {
                invalidPhaseTransition.printStackTrace();
            }
        }
    }
}
