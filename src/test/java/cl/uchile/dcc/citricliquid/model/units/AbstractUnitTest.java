package cl.uchile.dcc.citricliquid.model.units;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * Test suite for the players of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class AbstractUnitTest {
  private final static String PLAYER_NAME = "Suguri";
  private Player suguri;
  private final static String Wild_Name = "Wolf";
  private WildUnit wolf;
  private final static String Boss_Name = "Shrek";
  private BossUnit shrek;

  @BeforeEach
  public void setUp() {
    suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    wolf = new WildUnit(Wild_Name, 2, 2, -3, 10);
    shrek = new BossUnit(Boss_Name, 100, 5, -10, 1);
  }

  @Test
  public void constructorTest() {
     assertEquals(PLAYER_NAME, suguri.getName());
     assertEquals(4, suguri.getCurrentHp());
     assertEquals(4, suguri.getMaxHp());
     assertEquals(1, suguri.getAtk());
     assertEquals(-1, suguri.getDef());
     assertEquals(2, suguri.getEvd());

     assertEquals(0, suguri.getStars());
     assertEquals(1, suguri.getNormaLevel());
  }

  @Test
  public void testEquals() {
    final var o = new Object();

    Assertions.assertNotEquals(suguri, o);
    Assertions.assertEquals(suguri, suguri);
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    Assertions.assertEquals(expectedSuguri, suguri);
    final var messi = new Player("Messi", 100, 90, 1, 99);
    assertNotEquals(messi, suguri);

    assertNotEquals(wolf, o);
    assertEquals(wolf, wolf);
    final var expectedWolf = new WildUnit(Wild_Name, 2, 2, -3, 10);
    assertEquals(wolf, expectedWolf);

    assertNotEquals(shrek, o);
    assertEquals(shrek, shrek);
    final var expectedShrek = new BossUnit(Boss_Name, 100, 5, -10, 1);
    assertEquals(shrek, expectedShrek);
  }

  @Test
  public void starsTest(){
    assertEquals(0, shrek.getStars());

    shrek.increaseStarsBy(3);
    assertEquals(3, shrek.getStars());

    shrek.reduceStarsBy(2);
    assertEquals(1, shrek.getStars());

    shrek.reduceStarsBy(10);
    assertEquals(0, shrek.getStars());


  }
  @Test
  public void hitPointsTest() {
    Assertions.assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());
    suguri.setCurrentHp(2);
    Assertions.assertEquals(2, suguri.getCurrentHp());
    suguri.setCurrentHp(-1);
    Assertions.assertEquals(0, suguri.getCurrentHp());
    suguri.setCurrentHp(5);
    Assertions.assertEquals(4, suguri.getCurrentHp());
  }


  @Test
  public void copyTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    final var actualSuguri = suguri.copy();
    // Checks that the copied player have the same parameters as the original
    Assertions.assertEquals(expectedSuguri, actualSuguri);
    // Checks that the copied player doesn't reference the same object
    Assertions.assertNotSame(expectedSuguri, actualSuguri);

    final var expectedWolf = new WildUnit(Wild_Name, 2, 2, -3, 10);
    final var actualWolf = wolf.copy();
    // Checks that the copied player have the same parameters as the original
    Assertions.assertEquals(expectedWolf, actualWolf);
    // Checks that the copied player doesn't reference the same object
    Assertions.assertNotSame(expectedWolf, actualWolf);

    final var expectedShrek = new BossUnit(Boss_Name, 100, 5, -10, 1);
    final var actualShrek = shrek.copy();
    // Checks that the copied player have the same parameters as the original
    Assertions.assertEquals(expectedShrek, actualShrek);
    // Checks that the copied player doesn't reference the same object
    Assertions.assertNotSame(expectedShrek, actualShrek);
  }

  // region : consistency tests
  @RepeatedTest(100)
  public void hitPointsConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
    final int testHP = new Random(testSeed).nextInt(4 * suguri.getMaxHp() + 1)
                       - 2 * suguri.getMaxHp();
    suguri.setCurrentHp(testHP);
    Assertions.assertTrue(0 <= suguri.getCurrentHp()
                          && suguri.getCurrentHp() <= suguri.getMaxHp(),
                          suguri.getCurrentHp() + "is not a valid HP value"
                          + System.lineSeparator() + "Test failed with random seed: "
                          + testSeed);
  }


  @RepeatedTest(100)
  public void rollConsistencyTest() {
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final int roll = suguri.roll();
    Assertions.assertTrue(roll >= 1 && roll <= 6,
                          roll + "is not in [1, 6]" + System.lineSeparator()
                          + "Test failed with random seed: " + testSeed);
  }

  @Test
  public void defeatedTest(){
    wolf.increaseStarsBy(10); //Wild unit
    shrek.increaseStarsBy(10); //Boss

    suguri.winAgainst(wolf);
    assertEquals(10, suguri.getStars());
    assertEquals(1,suguri.getVictories());

    suguri.winAgainst(shrek);
    assertEquals(20, suguri.getStars());
    assertEquals(4,suguri.getVictories());

    shrek.winAgainst(suguri);
    assertEquals(10, suguri.getStars());
    assertEquals(10, shrek.getStars());

    wolf.winAgainst(suguri);
    assertEquals(5, suguri.getStars());
    assertEquals(5, wolf.getStars());

  }

}
