package cl.uchile.dcc.citricliquid.model.gameflow;

import cl.uchile.dcc.citricliquid.gameflow.GameController;
import cl.uchile.dcc.citricliquid.gameflow.phases.MovingPhase;
import cl.uchile.dcc.citricliquid.gameflow.phases.WaitForHomePhase;
import cl.uchile.dcc.citricliquid.model.board.*;
import cl.uchile.dcc.citricliquid.model.norma.StarNorma;
import cl.uchile.dcc.citricliquid.model.norma.WinsNorma;
import cl.uchile.dcc.citricliquid.model.units.BossUnit;
import cl.uchile.dcc.citricliquid.model.units.Player;
import cl.uchile.dcc.citricliquid.model.units.WildUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    private final GameController gameController = new GameController();
    private final Random random = new Random();

    private final List<Player> playersTest = new ArrayList<>();
    private final List<IPanel> panelsTest = new ArrayList<>();
    private final List<WildUnit> wildUnitsTest = new ArrayList<>();
    private final List<BossUnit> bossUnitsTest = new ArrayList<>();
    private Player Suguri, SuguriController;
    private Player QP, QPController;
    private Player Yuki, YukiController;
    private Player Kai, KaiController;
    private WildUnit wildUnit1;
    private BossUnit bossUnit1;
    private IPanel bonusPnl, bossPnl, dropPnl, encounterPnl, neutralPnl;
    private IPanel homePnl, homePnl1, homePnl2, homePnl3, homePnl4;

    @BeforeEach
    public void setUp(){
        Suguri = new Player("Suguri", 4, 1, 4, 1);
        QP = new Player("QP", 7, 7, 3, 7);
        Yuki = new Player("Yuki", 2,3,4,5);
        Kai = new Player("Kai", 4,7,2,4);

        wildUnit1 = new WildUnit("tigre", 3,2,1,10);
        bossUnit1 = new BossUnit("Shrek", 50, 5, 4, 1);

        bonusPnl = new BonusPanel(1);
        bossPnl = new BossPanel(2);
        dropPnl = new DropPanel(3);
        encounterPnl = new EncounterPanel(4);
        homePnl = new HomePanel(5);
        neutralPnl = new NeutralPanel(6);
    }

    @Test
    public void createPanelsTest(){
        BonusPanel bonusPnlController = gameController.addBonusPanel(1);
        assertEquals(bonusPnl, bonusPnlController);

        BossPanel bossPnlController = gameController.addBossPanel(2);
        assertEquals(bossPnl,bossPnlController);

        DropPanel dropPnlController = gameController.addDropPanel(3);
        assertEquals(dropPnl, dropPnlController);

        EncounterPanel encounterPnlController = gameController.addEncounterPanel(4);
        assertEquals(encounterPnl, encounterPnlController);

        HomePanel homePnlController = gameController.addHomePanel(5);
        assertEquals(homePnl, homePnlController);

        NeutralPanel neutralPnlController = gameController.addNeutralPanel(6);
        assertEquals(neutralPnl, neutralPnlController);
    }

    @Test
    public void createUnitsTest(){
        homePnl1 = gameController.addHomePanel(10);
        SuguriController = gameController.addPlayer(Suguri.getName(), Suguri.getMaxHp(), Suguri.getAtk(), Suguri.getDef(),
                Suguri.getEvd(),homePnl1);
        assertEquals(SuguriController, Suguri);

        homePnl2 = gameController.addHomePanel(11);
        Player QPController = gameController.addPlayer(QP.getName(), QP.getMaxHp(), QP.getAtk(), QP.getDef(),
                QP.getEvd(),homePnl2);
        assertEquals(QPController, QP);

        WildUnit wildUnitController = gameController.addWildUnit(wildUnit1.getName(), wildUnit1.getMaxHp(), wildUnit1.getAtk(),
                wildUnit1.getDef(), wildUnit1.getEvd());
        assertEquals(wildUnit1, wildUnitController);

        BossUnit bossUnitController = gameController.addBossUnit(bossUnit1.getName(), bossUnit1.getMaxHp(), bossUnit1.getAtk(), bossUnit1.getDef(),
                bossUnit1.getEvd());
        assertEquals(bossUnit1, bossUnitController);
    }

    @Test
    public void assignHomePanelTest(){
        assertSame(null, Suguri.getHomePanel());

        gameController.setHomePanel(Suguri, homePnl);

        assertSame(homePnl, Suguri.getHomePanel());
    }

    @Test
    public void assignNextPanels(){
        homePnl1 = gameController.addHomePanel(10);
        assertTrue(homePnl1.getNextPanels().isEmpty());

        gameController.setNextPanel(homePnl1, dropPnl);
        assertEquals(1, homePnl1.getNextPanels().size());
        assertTrue(homePnl1.getNextPanels().contains(dropPnl));
        gameController.setNextPanel(homePnl1, encounterPnl);
        assertEquals(2, homePnl1.getNextPanels().size());
        assertTrue(homePnl1.getNextPanels().contains(encounterPnl));
        Set<IPanel> expected = new HashSet<>();
        expected.add(dropPnl);
        expected.add(encounterPnl);

        assertEquals(expected, gameController.getNextPanels(homePnl1));


    }

    @Test
    public void getBoard() {
        assertTrue(gameController.getPanels().isEmpty());

        panelsTest.add(gameController.addBossPanel(20));
        panelsTest.add(gameController.addBonusPanel(21));
        panelsTest.add(gameController.addDropPanel(22));
        panelsTest.add(gameController.addEncounterPanel(23));
        panelsTest.add(gameController.addNeutralPanel(24));
        panelsTest.add(gameController.addHomePanel(25));

        assertEquals(Set.copyOf(panelsTest), gameController.getPanels());
    }

    private void addPlayersToController(){
        homePnl1 = gameController.addHomePanel(20);
        SuguriController = gameController.addPlayer(Suguri.getName(), Suguri.getMaxHp(), Suguri.getAtk(), Suguri.getDef(),
                Suguri.getEvd(), homePnl1);
        homePnl2 = gameController.addHomePanel(21);
        QPController = gameController.addPlayer(QP.getName(), QP.getMaxHp(), QP.getAtk(), QP.getDef(),
                QP.getEvd(), homePnl2);
        homePnl3 = gameController.addHomePanel(22);
        YukiController = gameController.addPlayer(Yuki.getName(), Yuki.getMaxHp(), Yuki.getAtk(), Yuki.getDef(),
                Yuki.getEvd(), homePnl3);
        homePnl4 = gameController.addHomePanel(23);
        KaiController = gameController.addPlayer(Kai.getName(), Kai.getMaxHp(), Kai.getAtk(), Kai.getDef(),
                Kai.getEvd(), homePnl4);
        gameController.resetOwnerTurn();
    }

    /**
     * Test method getOwnerTurn to get the owner turn's player,
     * Test method finishTurn to finish the turn the current owner turn and change the owner turn to the next player, and
     * Test method getChapter to get the current chapter of the game.
     */
    @Test
    public void turns_getChapter(){
        addPlayersToController();
        assertSame(SuguriController, gameController.getOwnerTurn());

        gameController.finishTurn();

        assertSame(QPController, gameController.getOwnerTurn());

        gameController.finishTurn();

        assertSame(YukiController, gameController.getOwnerTurn());

        gameController.finishTurn();

        assertSame(KaiController, gameController.getOwnerTurn());

        gameController.finishTurn();
        assertEquals(2, gameController.getChapter());
        assertSame(SuguriController, gameController.getOwnerTurn());
    }

    @Test
    public void setNormaTest(){
        addPlayersToController();
        assertSame(gameController.getOwnerTurn().getNormaGoal().getClass(), StarNorma.class);

        gameController.setNormaGoal(new WinsNorma());
        assertSame(gameController.getOwnerTurn().getNormaGoal().getClass(), WinsNorma.class);

        gameController.setNormaGoal(new StarNorma());
        assertSame(gameController.getOwnerTurn().getNormaGoal().getClass(), StarNorma.class);
    }

    @Test
    public void iWonTest(){
        addPlayersToController();

        /* Revisamos que el juego no haya acabado ni que haya un jugador que sea el ganador */
        assertFalse(gameController.isGameOver());
        assertSame(null, gameController.getWinner());

        /* Norma del jugador dueño del turno es aumentada en 1 llegando a nivel 2 */
        gameController.getOwnerTurn().normaClear();
        /* Revisamos que el juego no haya acabado ni que haya un jugador que sea el ganador */
        assertFalse(gameController.isGameOver());
        assertSame(null, gameController.getWinner());

        /* Norma del jugador dueño del turno es aumentada en 1 llegando a nivel 3 */
        gameController.getOwnerTurn().normaClear();
        /* Revisamos que el juego no haya acabado ni que haya un jugador que sea el ganador */
        assertFalse(gameController.isGameOver());
        assertSame(null, gameController.getWinner());

        /* Norma del jugador dueño del turno es aumentada en 1 llegando a nivel 4 */
        gameController.getOwnerTurn().normaClear();
        /* Revisamos que el juego no haya acabado ni que haya un jugador que sea el ganador */
        assertFalse(gameController.isGameOver());
        assertSame(null, gameController.getWinner());

        /* Norma del jugador dueño del turno es aumentada en 1 llegando a nivel 5 */
        gameController.getOwnerTurn().normaClear();
        /* Revisamos que el juego no haya acabado ni que haya un jugador que sea el ganador */
        assertFalse(gameController.isGameOver());
        assertSame(null, gameController.getWinner());

        /* Norma del jugador dueño del turno es aumentada en 1 llegando a nivel 6
         * Por lo tanto este jugador ha ganado
        */
        gameController.getOwnerTurn().normaClear();

        /* Revisamos que el juego haya acabado y que el jugador que era dueño del turno sea el ganador */
        assertTrue(gameController.isGameOver());
        assertSame(gameController.getOwnerTurn(), gameController.getWinner());
    }

    /**
     * Test does a normacheck and norma clear when a player lands on a HomePanel
     */
    @Test
    public void onHomePanelTest(){
        addPlayersToController();
        neutralPnl = gameController.addNeutralPanel(33);
        homePnl = gameController.addHomePanel(34);
        gameController.setNextPanel(homePnl1,homePnl);
        gameController.setPlayerPanel(gameController.getOwnerTurn(), neutralPnl);
        assertEquals(0, gameController.getOwnerTurn().getStars());

        gameController.setPlayerPanel(gameController.getOwnerTurn(), homePnl);
        assertEquals(1, gameController.getOwnerTurn().getNormaLevel());
    }

    @RepeatedTest(25)
    public void stopMovingAtHomeTest() {
        /*
        Configuraciones iniciales, aderimos un neutralPanel y a este le añadimos el HomePanel de SuguriController como siguiente.
        Luego, a este último le añadimos otro panel como siguiente.
         */
        addPlayersToController();
        neutralPnl = gameController.addNeutralPanel(7);
        encounterPnl = gameController.addEncounterPanel(77);
        gameController.setNextPanel(neutralPnl, homePnl1);
        gameController.setNextPanel(homePnl1, encounterPnl);

        /*
        Posicionamos a SuguriController en neutralPnl(que tiene al home Panel de SuguriController como siguiente), y
        seteamos la phase del gameController con una MovingPhase para poder ejecutar MoveStart y que aplique
        todos sus efectos correctamente.
         */
        gameController.setPlayerPanel(SuguriController, neutralPnl);
        gameController.setPhase(new MovingPhase());

        gameController.moveStart();
        /*
        Nos aseguramos que SuguriController no haya avanzado más allá de su Home Panel.
         */
        assertTrue(homePnl1.getPlayers().contains(SuguriController));
    }

    @RepeatedTest(25)
    public void stopMovingForMeetPlayersTest() {
        /*
        Configuraciones iniciales, aderimos un neutralPanel y a este le añadimos el HomePanel de otro jugador como siguiente.
        Luego, a este último le añadimos otro panel cualquiera como siguiente.
         */
        addPlayersToController();
        neutralPnl = gameController.addNeutralPanel(7);
        encounterPnl = gameController.addEncounterPanel(77);
        IPanel homePnlAnotherPlayer = List.of(homePnl2, homePnl3, homePnl4).get(random.nextInt(3));
        gameController.setNextPanel(neutralPnl, homePnlAnotherPlayer);
        gameController.setNextPanel(homePnlAnotherPlayer, encounterPnl);

        /*
        homePnlAnotherPlayer debería contener otro jugador.
         */
        assertFalse(homePnlAnotherPlayer.getPlayers().isEmpty());
        /*
        Posicionamos a SuguriController en neutralPnl(que tiene homePnlAnotherPlayer como siguiente), y
        seteamos la phase del gameController con una MovingPhase para poder ejecutar MoveStart y que aplique
        todos sus efectos correctamente.
         */
        gameController.setPlayerPanel(SuguriController, neutralPnl);
        gameController.setPhase(new MovingPhase());

        gameController.moveStart();

        /*
        Nos aseguramos que SuguriController no haya avanzado más allá de homePnlAnotherPlayer.
         */
        assertTrue(homePnlAnotherPlayer.getPlayers().contains(SuguriController));
        assertEquals(2, homePnlAnotherPlayer.getPlayers().size());
    }

    @RepeatedTest(25)
    public void stopMovingForChoosePathTest() {
        /*
        Configuraciones iniciales, aderimos un neutralPanel y a este le añadimos un panel con más de un panel como siguiente.
         */
        addPlayersToController();
        neutralPnl = gameController.addNeutralPanel(7);
        encounterPnl = gameController.addEncounterPanel(77);
        dropPnl = gameController.addDropPanel(777);
        gameController.setNextPanel(neutralPnl, encounterPnl);
        gameController.setNextPanel(encounterPnl, dropPnl);
        IPanel homePnlPlayer = List.of(homePnl1, homePnl2, homePnl3, homePnl4).get(random.nextInt(4));
        gameController.setNextPanel(encounterPnl, homePnlPlayer);

        /*
        Posicionamos a SuguriController en neutralPnl, y
        seteamos la phase del gameController con una MovingPhase para poder ejecutar MoveStart y que aplique
        todos sus efectos correctamente.
         */
        gameController.setPlayerPanel(SuguriController, neutralPnl);
        gameController.setPhase(new MovingPhase());

        gameController.moveStart();

        /*
        Nos aseguramos que SuguriController no haya avanzado más allá del segundo panel(encounterPnl).
         */
        assertTrue(encounterPnl.getPlayers().contains(SuguriController));
    }



}
