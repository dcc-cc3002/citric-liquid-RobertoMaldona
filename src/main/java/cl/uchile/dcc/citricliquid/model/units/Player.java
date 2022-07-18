package cl.uchile.dcc.citricliquid.model.units;

import cl.uchile.dcc.citricliquid.model.board.IPanel;
import cl.uchile.dcc.citricliquid.model.norma.INormaGoal;
import cl.uchile.dcc.citricliquid.model.norma.StarNorma;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
  private INormaGoal normaGoal;
  private int victories;
  private IPanel home = null;
  private IPanel actualPanel = null;

  private final PropertyChangeSupport normaCheckNotification= new PropertyChangeSupport(this);
  private final PropertyChangeSupport atHomeNotification= new PropertyChangeSupport(this);
  private final PropertyChangeSupport moreThanOnePlayer= new PropertyChangeSupport(this);
  private final PropertyChangeSupport pathToChooseNotification= new PropertyChangeSupport(this);

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
    normaGoal = new StarNorma();
  }

  /**
   * Returns the current norma level.
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Set the panel Actual
   * @param newActualPanel
   */
  public void setActualPanel(IPanel newActualPanel) {
    actualPanel = newActualPanel;

    if(this.getHomePanel().getId()==actualPanel.getId()) {
      atHomeNotification.firePropertyChange("Estoy en mi HomePanel", false,true);
    }

    else if(actualPanel.getPlayers().size()>1) {
      moreThanOnePlayer.firePropertyChange("Más de un jugador en el panel actual",
              false,true);
    }

    else if(actualPanel.getNextPanels().size()>1) {
      pathToChooseNotification.firePropertyChange("Tengo que elegir un camino",
              false,true);
    }
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
  public IPanel getHomePanel() {
    return home;
  }

  /**
   * Set the panel that will be the player's home
   * @param home
   */
  public void setHomePanel(IPanel home) {
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
    normaCheckNotification.firePropertyChange("Aumento de Norma", normaLevel-1, normaLevel);
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
   * Increases the Player's Field victories
   */
  public void increaseVictoriesBy(int amount){
    setVictories(victories+amount);
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

  /**
   * Method that adds a listener in normaCheckNotification
   * @param Listener new Listener
   */
  public void addNormaLevelHandler(PropertyChangeListener Listener){
    normaCheckNotification.addPropertyChangeListener(Listener);
  }

  /**
   * Method that adds a listener in atHomeNotification
   * @param Listener new Listener
   */
  public void addAtHomePanelHandler(PropertyChangeListener Listener){
    atHomeNotification.addPropertyChangeListener(Listener);
  }

  /**
   * Method that adds a listener in moreThanOnePlayer
   * @param Listener new Listener
   */
  public void addMoreThanOnePlayerHandler(PropertyChangeListener Listener){
    moreThanOnePlayer.addPropertyChangeListener(Listener);
  }

  /**
   * Method that adds a listener in pathToChooseNotification
   * @param Listener new Listener
   */
  public void addMoreTanOnePathHandler(PropertyChangeListener Listener){
    pathToChooseNotification.addPropertyChangeListener(Listener);
  }

  @Override
  public void winAgainst(IUnit defeatedUnit){
    defeatedUnit.defeatedByPlayer(this);
    defeatedUnit.increaseVictoriesToPlayer(this);
  }

  @Override
  public void increaseVictoriesToPlayer(Player winPlayer){
    winPlayer.increaseVictoriesBy(2);
  }

  @Override
  public void defeatedByPlayer(Player winPlayer){
    int starsToPlayer = ((int) Math.floor(getStars()*0.5));
    reduceStarsBy(starsToPlayer);
    winPlayer.increaseStarsBy(starsToPlayer);
  }

  @Override
  public void defeatedByBoss(BossUnit bossUnit) {
    int starsToBoss = ((int) Math.floor(getStars()*0.5));
    reduceStarsBy(starsToBoss);
    bossUnit.increaseStarsBy(starsToBoss);
  }

  @Override
  public void defeatedByWildUnit(WildUnit wildUnit) {
    int starsToWildUnit = ((int) Math.floor(getStars()*0.5));
    reduceStarsBy(starsToWildUnit);
    wildUnit.increaseStarsBy(starsToWildUnit);
  }
}
