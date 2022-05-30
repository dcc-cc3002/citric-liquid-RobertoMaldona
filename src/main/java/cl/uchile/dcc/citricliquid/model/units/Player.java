package cl.uchile.dcc.citricliquid.model.units;

import cl.uchile.dcc.citricliquid.model.board.HomePanel;
import cl.uchile.dcc.citricliquid.model.board.IPanel;
import cl.uchile.dcc.citricliquid.model.norma.INormaGoal;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public class Player extends AbstractUnit {
  private int normaLevel;
  private INormaGoal normaGoal = null;
  private int victories;
  private HomePanel home = null;
  private IPanel actualPanel = null;

  /**
   * Creates a new player.
   *
   * @param name
   *     the character's name.
   * @param hp
   *     the initial (and max) hit points of the character.
   * @param atk
   *     the base damage the character does.
   * @param def
   *     the base defense of the character.
   * @param evd
   *     the base evasion of the character.
   */
  public Player(final String name, final int hp, final int atk, final int def,
                final int evd) {
    super(name, hp, atk, def, evd);
    normaLevel = 1;
    victories = 0;

  }

  /**
   * Returns the current norma level.
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Set the panel Actual
   * @param actualPanel
   */
  public void setActualPanel(IPanel actualPanel) {
    this.actualPanel = actualPanel;
  }

  /**
   * @return panel Actual
   */
  public IPanel getActualPanel(){
    return actualPanel;
  }

  /**
   * @return the player's home panel
   */
  public HomePanel getHomePanel() {
    return home;
  }

  /**
   * Set the panel that will be the player's home
   * @param home
   */
  public void setHomePanel(HomePanel home) {
    this.home = home;
  }

  /**
   * Set the player's norma goal to the level
   * @param normaGoal
   */
  public void setNormaGoal(INormaGoal normaGoal) {
    this.normaGoal = normaGoal;
  }

  /**
   * @return the actual norma goal of the player
   */
  public INormaGoal getNormaGoal() {
    return normaGoal;
  }
  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   */
  public void normaClear() {
    normaLevel++;
  }

  /**
   * Set the player's victories.
   * @param victories
   */
  public void setVictories(int victories) {
    this.victories = victories;
  }

  /**
   * @return the amount of the victories of the player
   */
  public int getVictories() {
    return victories;
  }

  /**
   * Call to checkMe, norma's method, that decides if the goal selected by the player
   * was achieve or not
   */
  public void normaCheck(){
    normaGoal.checkMe(this);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractUnit)) {
      return false;
    }
    final Player player = (Player) o;
    return getMaxHp() == player.getMaxHp()
           && getAtk() == player.getAtk()
           && getDef() == player.getDef()
           && getEvd() == player.getEvd()
           && getNormaLevel() == player.getNormaLevel()
           && getStars() == player.getStars()
           && getCurrentHp() == player.getCurrentHp()
           && getName().equals(player.getName());
  }



  /**
   * Returns a copy of this character.
   */
  @Override
  public Player copy() {
    return new Player(getName(), getMaxHp(), getAtk(), getDef(), getEvd());
  }
}
