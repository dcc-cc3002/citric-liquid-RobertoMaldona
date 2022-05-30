package cl.uchile.dcc.citricliquid.model.units;

import cl.uchile.dcc.citricliquid.model.board.HomePanel;
import cl.uchile.dcc.citricliquid.model.board.IPanel;
import cl.uchile.dcc.citricliquid.model.norma.INormaGoal;
import cl.uchile.dcc.citricliquid.model.norma.StarNorma;
import cl.uchile.dcc.citricliquid.model.norma.WinsNorma;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class PlayerTest {
    private final static String PLAYER_NAME = "Suguri";
    private Player suguri;
    private HomePanel homePanel;
    private INormaGoal winsNorma;
    private INormaGoal starNorma;

    @BeforeEach
    public void setUp() {
        homePanel = new HomePanel(1);
        suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
        winsNorma = new WinsNorma();
        starNorma = new StarNorma();
    }

    @Test
    public void normaClearTest() {
        suguri.normaClear();
        Assertions.assertEquals(2, suguri.getNormaLevel());
    }

    @RepeatedTest(100)
    public void normaClearConsistencyTest() {
        final long testSeed = new Random().nextLong();
        // We're gonna test for 0 to 5 norma clears
        final int iterations = Math.abs(new Random(testSeed).nextInt(6));
        final int expectedNorma = suguri.getNormaLevel() + iterations;
        for (int it = 0; it < iterations; it++) {
            suguri.normaClear();
        }
        Assertions.assertEquals(expectedNorma, suguri.getNormaLevel(),
                "Test failed with random seed: " + testSeed);
    }

    @Test
    public void panelsTest(){
        suguri.setHomePanel(homePanel);
        Assertions.assertEquals(homePanel, suguri.getHomePanel());

        suguri.setActualPanel(homePanel);
        Assertions.assertEquals(homePanel, suguri.getActualPanel());
    }

    @Test
    public void normaGoalsTest(){
        suguri.setNormaGoal(starNorma);
        Assertions.assertEquals(starNorma, suguri.getNormaGoal());

        suguri.normaCheck();
        Assertions.assertEquals(1, suguri.getNormaLevel());

        suguri.increaseStarsBy(10);
        suguri.normaCheck();
        Assertions.assertEquals(2, suguri.getNormaLevel());

        suguri.setNormaGoal(winsNorma);
        Assertions.assertEquals(winsNorma, suguri.getNormaGoal());

        suguri.normaCheck();
        Assertions.assertEquals(2, suguri.getNormaLevel());

        suguri.setVictories(4);
        suguri.normaCheck();
        Assertions.assertEquals(3, suguri.getNormaLevel());

    }
}
