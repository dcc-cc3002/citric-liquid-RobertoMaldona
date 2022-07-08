package cl.uchile.dcc.citricliquid.model.gameflow;

import cl.uchile.dcc.citricliquid.gameflow.GameController;
import cl.uchile.dcc.citricliquid.model.board.IPanel;
import cl.uchile.dcc.citricliquid.model.units.BossUnit;
import cl.uchile.dcc.citricliquid.model.units.Player;
import cl.uchile.dcc.citricliquid.model.units.WildUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhasesTest {

    private final GameController gameController = new GameController();
    private final Random random = new Random();

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
    }

    @Test
    public void startTest(){
        gameController.
    }


}
