package cl.uchile.dcc.citricliquid.model.gameflow;

import cl.uchile.dcc.citricliquid.gameflow.GameController;
import cl.uchile.dcc.citricliquid.gameflow.IncorrectDirectionException;
import cl.uchile.dcc.citricliquid.gameflow.InvalidActionException;
import cl.uchile.dcc.citricliquid.gameflow.InvalidPhaseTransition;
import cl.uchile.dcc.citricliquid.gameflow.phases.*;
import cl.uchile.dcc.citricliquid.model.board.IPanel;
import cl.uchile.dcc.citricliquid.model.units.BossUnit;
import cl.uchile.dcc.citricliquid.model.units.Player;
import cl.uchile.dcc.citricliquid.model.units.WildUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhasesTest {

    private final GameController gameController = new GameController();
    private final Random random = new Random();
    private long seed;

    private final List<Player> playersTest = new ArrayList<>();
    private final List<IPanel> panelsTest = new ArrayList<>();
    private final List<WildUnit> wildUnitsTest = new ArrayList<>();
    private final List<BossUnit> bossUnitsTest = new ArrayList<>();
    private Player Suguri;
    private Player QP;
    private Player Yuki;
    private Player Kai;
    private WildUnit chicken, roboBall, seagull;
    private BossUnit storeManager, shifuRobot, flyingCastle;
    private IPanel bonusPnl, bossPnl, dropPnl, encounterPnl;
    private IPanel homePnl, homePnl1, homePnl2, homePnl3, homePnl4;
    private IPanel neutralPnl1, neutralPnl2, neutralPnl3, neutralPnl4, neutralPnl5, neutralPnl6, neutralPnl7, neutralPnl8;

    @BeforeEach
    public void setUp(){
        homePnl1 = gameController.addHomePanel(20);
        Suguri = gameController.addPlayer("Suguri", 4, 1, 4, 1, homePnl1);
        homePnl2 = gameController.addHomePanel(21);
        QP = gameController.addPlayer("QP", 7, 7, 3, 7, homePnl2);
        homePnl3 = gameController.addHomePanel(22);
        Yuki = gameController.addPlayer("Yuki", 2,3,4,5, homePnl3);
        homePnl4 = gameController.addHomePanel(23);
        Kai = gameController.addPlayer("Kai", 4,7,2,4, homePnl4);

        neutralPnl1= gameController.addNeutralPanel(1);
        neutralPnl2= gameController.addNeutralPanel(2);
        neutralPnl3= gameController.addNeutralPanel(3);
        neutralPnl4= gameController.addNeutralPanel(4);
        neutralPnl5= gameController.addNeutralPanel(5);
        neutralPnl6= gameController.addNeutralPanel(6);
        neutralPnl7= gameController.addNeutralPanel(7);
        neutralPnl8= gameController.addNeutralPanel(8);

        seed= new Random().nextLong();
    }

    @RepeatedTest(24)
    public void startAndRecoveryTest(){
        gameController.setNextPanel(homePnl1, neutralPnl1);
        gameController.setNextPanel(neutralPnl1, neutralPnl2);
        gameController.setNextPanel(neutralPnl2, neutralPnl3);
        gameController.setNextPanel(neutralPnl3, neutralPnl4);
        gameController.setNextPanel(neutralPnl4, neutralPnl5);
        gameController.setNextPanel(neutralPnl5, neutralPnl6);
        gameController.StartGame();

        assertSame(gameController.getPhase().getClass(), StartPhase.class);
        assertEquals(Suguri, gameController.getOwnerTurn());

        gameController.getOwnerTurn().setCurrentHp(0);

        gameController.getOwnerTurn().setSeed(seed);
        random.setSeed(seed);
        int dice = random.nextInt(6)+1;

        gameController.turnStart();
        if(dice >= 7-gameController.getChapter()){
            assertEquals(Suguri.getMaxHp(), Suguri.getCurrentHp());
            assertEquals(Suguri, gameController.getOwnerTurn());
        }else {
            assertEquals(QP, gameController.getOwnerTurn());
        }
        assertSame(gameController.getPhase().getClass(), StartPhase.class);
    }


    @RepeatedTest(24)
    public void movePhaseTest(){
        gameController.setNextPanel(homePnl1, neutralPnl1);
        gameController.setNextPanel(neutralPnl1, neutralPnl2);
        gameController.setNextPanel(neutralPnl2, neutralPnl3);
        gameController.setNextPanel(neutralPnl3, neutralPnl4);
        gameController.setNextPanel(neutralPnl4, neutralPnl5);
        gameController.setNextPanel(neutralPnl5, neutralPnl6);
        gameController.StartGame();


        gameController.getOwnerTurn().setSeed(seed);
        random.setSeed(seed);
        int dice = random.nextInt(6)+1;

        gameController.turnStart();
        if(dice==1){
            assertEquals(neutralPnl1, Suguri.getActualPanel());
        }else if(dice==2){
            assertEquals(neutralPnl2, Suguri.getActualPanel());
        }else if(dice==3){
            assertEquals(neutralPnl3, Suguri.getActualPanel());
        }else if(dice==4){
            assertEquals(neutralPnl4, Suguri.getActualPanel());
        }else if(dice==5){
            assertEquals(neutralPnl5, Suguri.getActualPanel());
        }else if(dice==6){
            assertEquals(neutralPnl6, Suguri.getActualPanel());
        }
        assertSame(StartPhase.class, gameController.getPhase().getClass());
    }

    @RepeatedTest(24)
    public void WaitForHomePhaseTest(){
        gameController.setNextPanel(neutralPnl1, homePnl1);
        gameController.setNextPanel(homePnl1, neutralPnl2);
        gameController.setNextPanel(neutralPnl2, neutralPnl3);
        gameController.setNextPanel(neutralPnl3, neutralPnl4);
        gameController.setNextPanel(neutralPnl4, neutralPnl5);
        gameController.setNextPanel(neutralPnl5, neutralPnl6);
        gameController.StartGame();
        gameController.setPlayerPanel(Suguri, neutralPnl1);

        gameController.getOwnerTurn().setSeed(seed);
        random.setSeed(seed);
        int dice = random.nextInt(6)+1;
        gameController.turnStart();
        if(dice==1){
            assertEquals(homePnl1, Suguri.getActualPanel());
        }else {
            assertSame(WaitForHomePhase.class, gameController.getPhase().getClass());
            if(random.nextBoolean()){
                gameController.tryToStayAtHome();
                assertEquals(homePnl1, Suguri.getActualPanel());
                assertSame(StartPhase.class, gameController.getPhase().getClass());
                assertEquals(QP, gameController.getOwnerTurn());
            }else {
                gameController.tryKeepMoving();
                if(dice==2){
                    assertEquals(neutralPnl2, Suguri.getActualPanel());
                }else if(dice==3){
                    assertEquals(neutralPnl3, Suguri.getActualPanel());
                }else if(dice==4){
                    assertEquals(neutralPnl4, Suguri.getActualPanel());
                }else if(dice==5){
                    assertEquals(neutralPnl5, Suguri.getActualPanel());
                }else if(dice==6){
                    assertEquals(neutralPnl6, Suguri.getActualPanel());
                }
                assertEquals(QP, gameController.getOwnerTurn());
                assertSame(StartPhase.class, gameController.getPhase().getClass());
            }
        }
    }

    @RepeatedTest(25)
    public void waitForPathTest(){
        gameController.setNextPanel(homePnl1, neutralPnl1);
        gameController.setNextPanel(neutralPnl1, neutralPnl2);
        gameController.setNextPanel(neutralPnl1, neutralPnl7);
        gameController.setNextPanel(neutralPnl2, neutralPnl3);
        gameController.setNextPanel(neutralPnl3, neutralPnl2);
        gameController.StartGame();

        /*
        homePnl1 --> neutralPnl1 --> neutralPnl7
                          |
                          |
                          v
                     neutralPnl2  <---> neutralPnl3
         */

        gameController.getOwnerTurn().setSeed(seed);
        random.setSeed(seed);
        int dice = random.nextInt(6)+1;
        gameController.turnStart();

        if(dice==1){
            assertSame(StartPhase.class, gameController.getPhase().getClass());
            assertEquals(QP, gameController.getOwnerTurn());
        }else {
            assertSame(WaitingForPathPhase.class, gameController.getPhase().getClass());
            int options = random.nextInt(4);
            if (options == 0) {
                gameController.setUpPanel(neutralPnl1, neutralPnl2);
                gameController.tryToMoveUp();
            } else if (options == 1) {
                gameController.setDownPanel(neutralPnl1, neutralPnl2);
                gameController.tryToMoveDown();
            } else if (options == 2) {
                gameController.setLeftPanel(neutralPnl1, neutralPnl2);
                gameController.tryToMoveLeft();
            } else {
                gameController.setRightPanel(neutralPnl1, neutralPnl2);
                gameController.tryToMoveRight();
            }
            assertTrue(Suguri.getActualPanel().equals(neutralPnl2) || Suguri.getActualPanel().equals(neutralPnl3));
            assertEquals(QP, gameController.getOwnerTurn());
            assertSame(StartPhase.class, gameController.getPhase().getClass());
        }
    }

    @RepeatedTest(36)
    public void waitingForFight_and_FightPhaseTest(){
        gameController.setNextPanel(homePnl1, neutralPnl1);
        gameController.setNextPanel(neutralPnl1, neutralPnl2);
        gameController.setNextPanel(neutralPnl2, neutralPnl3);
        gameController.setNextPanel(neutralPnl3, neutralPnl4);
        gameController.setNextPanel(neutralPnl4, neutralPnl5);
        gameController.setNextPanel(neutralPnl5, neutralPnl6);
        gameController.StartGame();

        gameController.setPlayerPanel(Yuki, neutralPnl1);

        gameController.getOwnerTurn().setSeed(seed);
        random.setSeed(seed);
        Yuki.setSeed(seed);
        int diceSuguri1 = random.nextInt(6)+1;
        int diceSuguri2 = random.nextInt(6)+1;
        int diceSuguri3 = random.nextInt(6)+1;
        Random random2 = new Random();
        random2.setSeed(seed);
        int diceYuki = random2.nextInt(6)+1;
        int diceYuki2 = random2.nextInt(6)+1;

        gameController.turnStart();
        assertSame(WaitingFightPhase.class, gameController.getPhase().getClass());
        gameController.tryToFight();

        if(random2.nextBoolean()){
            assertSame(FightPhase.class, gameController.getPhase().getClass());
            gameController.tryToDefense();
            if( (Suguri.getAtk()+diceSuguri2-diceYuki-Yuki.getDef()) >= Yuki.getMaxHp()){
                assertTrue(Yuki.isKO());
                assertSame(StartPhase.class, gameController.getPhase().getClass());
                assertEquals(QP, gameController.getOwnerTurn());
            }else {
                assertSame(FightPhase.class, gameController.getPhase().getClass());
                if(random2.nextBoolean()){
                    gameController.tryToDefense();
                    if( (Yuki.getAtk()+diceYuki2-diceSuguri3-Suguri.getDef()) >= Suguri.getMaxHp()){
                        assertTrue(Suguri.isKO());
                    }
                }else {
                    gameController.tryToEvade();
                    if(Yuki.getAtk()+diceYuki2 >= Suguri.getEvd()+diceSuguri3) {
                        if (Yuki.getAtk() + diceYuki2 >= Suguri.getMaxHp()) {
                            assertTrue(Suguri.isKO());
                        }else {
                            assertTrue(Suguri.getMaxHp()>Suguri.getCurrentHp());
                        }
                    }else {
                        assertEquals(Suguri.getMaxHp(), Suguri.getCurrentHp());
                    }
                }
            }
        }else {
            assertSame(FightPhase.class, gameController.getPhase().getClass());
            gameController.tryToEvade();
            if(Suguri.getAtk()+diceSuguri2 >= Yuki.getEvd()+diceYuki){
                if(Suguri.getAtk()+diceSuguri2>= Yuki.getMaxHp()){
                    assertTrue(Yuki.isKO());
                }else {
                    assertSame(WaitingFightPhase.class, gameController.getPhase().getClass());
                    if(random2.nextBoolean()){
                        gameController.tryToDefense();
                        if( (Yuki.getAtk()+diceYuki2-diceSuguri3-Suguri.getDef()) >= Suguri.getMaxHp()){
                            assertTrue(Suguri.isKO());
                        }
                    }else {
                        gameController.tryToEvade();
                        if(Yuki.getAtk()+diceYuki2 >= Suguri.getEvd()+diceSuguri3) {
                            if (Yuki.getAtk() + diceYuki2 >= Suguri.getMaxHp()) {
                                assertTrue(Suguri.isKO());
                            }else {
                                assertTrue(Suguri.getMaxHp()>Suguri.getCurrentHp());
                            }
                        }else {
                            assertEquals(Suguri.getMaxHp(), Suguri.getCurrentHp());
                        }
                    }
                }
                assertSame(StartPhase.class, gameController.getPhase().getClass());
                assertEquals(QP, gameController.getOwnerTurn());
            }else {
                assertSame(FightPhase.class, gameController.getPhase().getClass());
                if(random2.nextBoolean()){
                    gameController.tryToDefense();
                    if( (Yuki.getAtk()+diceYuki2-diceSuguri3- Suguri.getDef()) >= Suguri.getMaxHp()){
                        assertTrue(Suguri.isKO());
                    }else {
                        assertTrue(Suguri.getMaxHp()>Suguri.getCurrentHp());
                    }
                }else {
                    gameController.tryToEvade();
                    if (Yuki.getAtk() + diceYuki2 >= Suguri.getEvd() + diceSuguri3) {
                        if (Yuki.getAtk() + diceYuki2 >= Suguri.getMaxHp()) {
                            assertTrue(Suguri.isKO());
                        }else {
                            assertTrue(Suguri.getMaxHp()>Suguri.getCurrentHp());
                        }

                    }else{
                        assertEquals(Suguri.getMaxHp(), Suguri.getCurrentHp());
                    }
                }

            }
        }
        assertSame(StartPhase.class, gameController.getPhase().getClass());
        assertEquals(QP, gameController.getOwnerTurn());
    }

    @RepeatedTest(12)
    public void endTurnPhaseTest(){
        gameController.setNextPanel(neutralPnl1, homePnl1);
        gameController.setNextPanel(homePnl1, neutralPnl2);
        gameController.setNextPanel(neutralPnl2, neutralPnl3);
        gameController.setNextPanel(neutralPnl3, neutralPnl4);
        gameController.setNextPanel(neutralPnl4, neutralPnl5);
        gameController.setNextPanel(neutralPnl5, neutralPnl6);
        gameController.StartGame();

        gameController.setPlayerPanel(Suguri, neutralPnl1);
        gameController.setPlayerPanel(Kai, neutralPnl3);

        gameController.getOwnerTurn().setSeed(seed);
        random.setSeed(seed);
        int dice = random.nextInt(6)+1;

        gameController.turnStart();

        if(dice>1){
            assertEquals(Suguri, gameController.getOwnerTurn());
            assertSame(WaitForHomePhase.class, gameController.getPhase().getClass());
            gameController.tryKeepMoving();
            assertNotEquals(homePnl1, Suguri.getActualPanel());
            if(dice>=3){
                assertEquals(Suguri, gameController.getOwnerTurn());
                assertSame(WaitingFightPhase.class, gameController.getPhase().getClass());
                gameController.tryKeepMoving();
                if(dice>3){
                    assertNotEquals(neutralPnl3, Suguri.getActualPanel());
                }
            }
            assertEquals(QP, gameController.getOwnerTurn());
            assertSame(StartPhase.class, gameController.getPhase().getClass());
        }
    }


    @Test
    public void exceptionsTest(){
        Phase phase = new Phase();
        Phase startPhase = new StartPhase();
        Phase movingPhase = new MovingPhase();
        int n_exceptions = 0;

        try {
            phase.toStartPhase();
        } catch (InvalidPhaseTransition invalidPhaseTransition) {
            n_exceptions++;
        }
        try {
            movingPhase.toMovingPhase();
        } catch (InvalidPhaseTransition invalidPhaseTransition) {
            n_exceptions++;
        }
        try {
            phase.toRecoveryPhase();
        } catch (InvalidPhaseTransition invalidPhaseTransition) {
            n_exceptions++;
        }
        try {
            startPhase.toWaitingForHomePhase();
        } catch (InvalidPhaseTransition invalidPhaseTransition) {
            n_exceptions++;
        }
        try {
            phase.toWaitingFightPhase();
        } catch (InvalidPhaseTransition invalidPhaseTransition) {
            n_exceptions++;
        }
        try {
            startPhase.toWaitingForPath();
        } catch (InvalidPhaseTransition invalidPhaseTransition) {
            n_exceptions++;
        }
        try {
            phase.toFightPhase();
        } catch (InvalidPhaseTransition invalidPhaseTransition) {
            n_exceptions++;
        }
        try {
            startPhase.toEndTurnPhase();
        } catch (InvalidPhaseTransition invalidPhaseTransition) {
            n_exceptions++;
        }

        assertEquals(8, n_exceptions);

        try{
            phase.start();
        } catch (InvalidPhaseTransition ignored) {
            ;
        } catch (InvalidActionException e) {
            n_exceptions++;
        }

        try{
            phase.recover();
        } catch (InvalidPhaseTransition ignored) {
            ;
        } catch (InvalidActionException e) {
            n_exceptions++;
        }

        try{
            phase.move();
        } catch (InvalidActionException e) {
            n_exceptions++;
        } catch (InvalidPhaseTransition ignored) {
            ;
        }

        try{
            phase.keepMoving();
        } catch (InvalidActionException e) {
            n_exceptions++;
        } catch (InvalidPhaseTransition ignored) {
            ;
        }

        try{
            phase.stayAtHome();
        } catch (InvalidActionException e) {
            n_exceptions++;
        }

        try{
            phase.finishTurn();
        } catch (InvalidActionException e) {
            n_exceptions++;
        }

        try{
            phase.evade();
        } catch (InvalidPhaseTransition ignored) {
            ;
        } catch (InvalidActionException e) {
            n_exceptions++;
        }

        try{
            phase.defense();
        } catch (InvalidPhaseTransition ignored) {
            ;
        } catch (InvalidActionException e) {
            n_exceptions++;
        }

        try{
            phase.fight();
        } catch (InvalidActionException e) {
            n_exceptions++;
        }

        assertEquals(17, n_exceptions);

        try{
            startPhase.moveRight();
        } catch (InvalidActionException e) {
            n_exceptions++;
        } catch (InvalidPhaseTransition | IncorrectDirectionException ignored) {
            ;
        }

        try{
            startPhase.moveLeft();
        } catch (InvalidActionException e) {
            n_exceptions++;
        } catch (InvalidPhaseTransition | IncorrectDirectionException ignored) {
            ;
        }

        try{
            startPhase.moveUp();
        } catch (InvalidActionException e) {
            n_exceptions++;
        } catch (InvalidPhaseTransition | IncorrectDirectionException ignored) {
            ;
        }

        try{
            startPhase.moveDown();
        } catch (InvalidActionException e) {
            n_exceptions++;
        } catch (InvalidPhaseTransition | IncorrectDirectionException ignored) {
            ;
        }

        assertEquals(21, n_exceptions);
    }

    @Test
    public void try_methods_controllerTest(){
        gameController.StartGame();
        gameController.try_endTurn();
        assertEquals(Suguri, gameController.getOwnerTurn());
        gameController.try_startMove();
        gameController.tryToMoveRight();
        gameController.tryToMoveLeft();
        gameController.tryToMoveUp();
        gameController.tryToMoveDown();
        assertEquals(homePnl1, Suguri.getActualPanel());

        gameController.tryToFight();
        gameController.tryToEvade();
        gameController.tryToDefense();
        assertEquals(Suguri.getMaxHp(), Suguri.getCurrentHp());
        gameController.setPlayerPanel(Suguri, neutralPnl1);
        gameController.tryToStayAtHome();
        assertEquals(neutralPnl1, Suguri.getActualPanel());

        gameController.setPhase(new EndTurnPhase());
        gameController.turnStart();
        gameController.try_recover();
        gameController.tryKeepMoving();
        assertSame(EndTurnPhase.class, gameController.getPhase().getClass());

        gameController.moreThan1Path();
        gameController.onHomePanel();
        gameController.chooseFight();
        assertSame(EndTurnPhase.class, gameController.getPhase().getClass());

    }

    @Test
    public void moveIncorrectDirectionTest(){
        gameController.setNextPanel(homePnl1, neutralPnl1);
        gameController.setNextPanel(neutralPnl1, neutralPnl2);
        gameController.setNextPanel(neutralPnl1, neutralPnl7);
        gameController.setNextPanel(neutralPnl2, neutralPnl3);
        gameController.setNextPanel(neutralPnl3, neutralPnl2);
        gameController.StartGame();

        gameController.getOwnerTurn().setSeed(seed);
        random.setSeed(seed);
        int dice = random.nextInt(6)+1;
        gameController.turnStart();

        if(dice>1){
            gameController.tryToMoveUp();
            assertSame(WaitingForPathPhase.class, gameController.getPhase().getClass());
            assertEquals(neutralPnl1, Suguri.getActualPanel());

            gameController.tryToMoveDown();
            assertSame(WaitingForPathPhase.class, gameController.getPhase().getClass());
            assertEquals(neutralPnl1, Suguri.getActualPanel());

            gameController.tryToMoveLeft();
            assertSame(WaitingForPathPhase.class, gameController.getPhase().getClass());
            assertEquals(neutralPnl1, Suguri.getActualPanel());

            gameController.tryToMoveRight();
            assertSame(WaitingForPathPhase.class, gameController.getPhase().getClass());
            assertEquals(neutralPnl1, Suguri.getActualPanel());
        }
    }

}
