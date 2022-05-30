package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
class PanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private IPanel testHomePanel;
  private IPanel testNeutralPanel;
  private IPanel testBonusPanel;
  private IPanel testDropPanel;
  private IPanel testEncounterPanel;
  private IPanel testBossPanel;
  private Player suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new BonusPanel(1);
    testBossPanel = new BossPanel(2);
    testDropPanel = new DropPanel(3);
    testEncounterPanel = new EncounterPanel(4);
    testHomePanel = new HomePanel(5);
    testNeutralPanel = new NeutralPanel(6);
    testSeed = new Random().nextLong();
    suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    assertEquals(1, testBonusPanel.getId());
    assertEquals(2, testBossPanel.getId());
    assertEquals(3, testDropPanel.getId());
    assertEquals(4, testEncounterPanel.getId());
    assertEquals(5, testHomePanel.getId());
    assertEquals(6, testNeutralPanel.getId());

    assertEquals(BonusPanel.class, testBonusPanel.getClass());
    assertEquals(BossPanel.class, testBossPanel.getClass());
    assertEquals(DropPanel.class, testDropPanel.getClass());
    assertEquals(EncounterPanel.class, testEncounterPanel.getClass());
    assertEquals(HomePanel.class, testHomePanel.getClass());
    assertEquals(NeutralPanel.class, testNeutralPanel.getClass());
  }

  @Test
  public void playersTest(){
    assertTrue(testNeutralPanel.getPlayers().isEmpty());
    final var suguri2 = new Player("Suguri2", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);

    testNeutralPanel.addPlayer(suguri);
    assertEquals(1, testNeutralPanel.getPlayers().size());
    testNeutralPanel.addPlayer(suguri2);
    assertEquals(2, testNeutralPanel.getPlayers().size());

    assertTrue(testNeutralPanel.getPlayers().contains(suguri)
            && testNeutralPanel.getPlayers().contains(suguri2));

    testNeutralPanel.removePlayer(suguri2);
    assertEquals(1, testNeutralPanel.getPlayers().size());
    assertEquals(suguri, testNeutralPanel.getPlayers().get(0));

  }
  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    final var expectedPanel1 = new NeutralPanel(20);
    final var expectedPanel2 = new NeutralPanel(21);

    testNeutralPanel.addNextPanel(expectedPanel1);
    assertEquals(1, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    assertEquals(Set.of(expectedPanel1, expectedPanel2),
                 testNeutralPanel.getNextPanels());
  }

  @Test
  public void surroundingPanels(){
    assertNull(testNeutralPanel.getDown());
    assertNull(testNeutralPanel.getLeft());
    assertNull(testNeutralPanel.getUp());
    assertNull(testNeutralPanel.getRight());

    final var expectedDownPanel = new NeutralPanel(100);
    final var expectedLeftPanel = new NeutralPanel(101);
    final var expectedUpPanel = new NeutralPanel(102);
    final var expectedRightPanel = new NeutralPanel(103);

    testNeutralPanel.setDown(expectedDownPanel);
    assertEquals(expectedDownPanel, testNeutralPanel.getDown());

    testNeutralPanel.setLeft(expectedLeftPanel);
    assertEquals(expectedLeftPanel, testNeutralPanel.getLeft());

    testNeutralPanel.setUp(expectedUpPanel);
    assertEquals(expectedUpPanel, testNeutralPanel.getUp());

    testNeutralPanel.setRight(expectedRightPanel);
    assertEquals(expectedRightPanel, testNeutralPanel.getRight());
  }

  @Test
  public void equalsTest(){
    final var neutralPanelCopy = new NeutralPanel(6);
    final var bonusPanel2 = new NeutralPanel(66);

    assertFalse(testNeutralPanel.equals(suguri));
    assertFalse(testNeutralPanel.equals(testDropPanel));
    assertTrue(testNeutralPanel.equals(neutralPanelCopy));
    assertFalse(testBonusPanel.equals(bonusPanel2));

    //-----/
    neutralPanelCopy.addNextPanel(testHomePanel);
    assertFalse(testNeutralPanel.equals(neutralPanelCopy));

    testNeutralPanel.addNextPanel(testHomePanel);
    assertTrue(testNeutralPanel.equals(neutralPanelCopy));
    //-----/
    neutralPanelCopy.setDown(testHomePanel);
    assertFalse(testNeutralPanel.equals(neutralPanelCopy));

    testNeutralPanel.setDown(testHomePanel);
    assertTrue(testNeutralPanel.equals(neutralPanelCopy));
    //-----/
    neutralPanelCopy.setLeft(testBossPanel);
    assertFalse(testNeutralPanel.equals(neutralPanelCopy));

    testNeutralPanel.setLeft(testBossPanel);
    assertTrue(testNeutralPanel.equals(neutralPanelCopy));
    //-----/
    neutralPanelCopy.setUp(testEncounterPanel);
    assertFalse(testNeutralPanel.equals(neutralPanelCopy));

    testNeutralPanel.setUp(testEncounterPanel);
    assertTrue(testNeutralPanel.equals(neutralPanelCopy));
    //-----/
    neutralPanelCopy.setRight(testDropPanel);
    assertFalse(testNeutralPanel.equals(neutralPanelCopy));

    testNeutralPanel.setRight(testDropPanel);
    assertTrue(testNeutralPanel.equals(neutralPanelCopy));
    //----------/

    neutralPanelCopy.addPlayer(suguri);
    assertFalse(testNeutralPanel.equals(neutralPanelCopy));
  }

  @Test
  public void homePanelTest() {
    assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());
    testHomePanel.activatedBy(suguri);
    assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());

    suguri.setCurrentHp(1);
    testHomePanel.activatedBy(suguri);
    assertEquals(2, suguri.getCurrentHp());
  }

  @Test
  public void neutralPanelTest() {
    final var expectedSuguri = suguri.copy();
    testNeutralPanel.activatedBy(suguri);
    assertEquals(expectedSuguri, suguri);
  }

  // region : Consistency tests
  @RepeatedTest(100)
  public void bonusPanelConsistencyTest() {
    int expectedStars = 0;
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testBonusPanel.activatedBy(suguri);
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, suguri.getStars(),
                   "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }

  @RepeatedTest(100)
  public void dropPanelConsistencyTest() {
    int expectedStars = 30;
    suguri.increaseStarsBy(30);
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testDropPanel.activatedBy(suguri);
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, suguri.getStars(),
                   "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }
  // endregion
}