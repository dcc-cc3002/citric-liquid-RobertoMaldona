package cl.uchile.dcc.citricliquid.model.units;

import java.util.Random;

public abstract class AbstractUnit implements IUnit {
    private final String name;
    private final int maxHp;
    private final int atk;
    private final int def;
    private final int evd;
    private int currentHp;
    private int stars;
    private final Random random;

    /**
     * Constructor base of the units.
     *  @param hp
     *     the initial (and max) hit points of the character.
     * @param atk
     *     the base damage the character does.
     * @param def
 *     the base defense of the character.
     * @param evd
     */
    public AbstractUnit(String name, int hp, int atk, int def, int evd){
        this.name = name;
        this.maxHp = hp;
        this.currentHp = hp;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
        this.random = new Random();
        stars = 0;
    }

    /**
     * Returns the character's max hit points.
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Returns the current character's attack points.
     */
    public int getAtk() {
        return atk;
    }

    /**
     * Returns the current character's defense points.
     */
    public int getDef() {
        return def;
    }

    /**
     * Returns the current character's evasion points.
     */
    public int getEvd() {
        return evd;
    }


    /**
     * Returns the current hit points of the character.
     */
    public int getCurrentHp() {
        return currentHp;
    }

    /**
     * Sets the current character's hit points.
     *
     * <p>The character's hit points have a constraint to always be between 0 and maxHP, both
     * inclusive.
     */
    public void setCurrentHp(final int newHp) {
        this.currentHp = Math.max(Math.min(newHp, maxHp), 0);
    }

    /**
     * Increases this unit's star count by an amount.
     */
    public void increaseStarsBy(final int amount) {
        stars += amount;
    }

    /**
     * Reduces this unit's star count by a given amount.
     *
     * The star count will must always be greater or equal to 0
     */
    public void reduceStarsBy(final int amount) {
        stars = Math.max(0, stars - amount);
    }

    /**
     * Returns this player's star count.
     */
    public int getStars() {
        return stars;
    }

    /**
     * method that advice if the Unit is KO
     */
    public boolean isK_O(){
        if(getCurrentHp()==0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Set's the seed for this player's random number generator.
     *
     * <p>The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */
    public void setSeed(final long seed) {
        random.setSeed(seed);
    }

    /**
     * Returns a uniformly distributed random value in [1, 6].
     */
    public int roll() {
        return random.nextInt(6) + 1;
    }
    /**
     * Returns the character's name.
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractUnit)) {
            return false;
        }
        final AbstractUnit unit = (AbstractUnit) o;
        return getAtk() == unit.getAtk() &&
                getDef() == unit.getDef() &&
                getEvd() == unit.getEvd() &&
                getStars() == unit.getStars() &&
                getCurrentHp() == unit.getCurrentHp() &&
                getName().equals(unit.getName());
    }

    public abstract IUnit copy();

    /**
     * calculate the attack
     * @return rollAttack + unit's attack
     */
    public int rollAttack(){
        return (roll()+atk);
    }

    /**
     * do the action of "evade" an attack.
     * @param enemyAttack the amount of the enemy's attack
     */
    public void evade(int enemyAttack){
        int evade = roll()+evd;
        if(evade<=enemyAttack){
            setCurrentHp(currentHp-enemyAttack);
        }
    }

    /**
     * do the action of "defense" an attack
     * @param enemyAttack the amount of the enemy's attack
     */
    public void defend(int enemyAttack){
        int defense = roll()+def;
        int amountATK = Math.max(1, enemyAttack-defense);
        setCurrentHp(currentHp-amountATK);
    }

    /**
     * When a Unit wins to another unit, ejecute the method defeatedBy[Unit] according to the specific unit that wins.
     * @param defeatedUnit the defeated unit.
     */
    public abstract void winAgainst(IUnit defeatedUnit);

    /**
     * gives half of the stars to the player who has defeated him, and increase the victories
     * @param winPlayer
     */
    public void defeatedByPlayer(Player winPlayer){
        int starsToPlayer = getStars();
        reduceStarsBy(starsToPlayer);
        winPlayer.increaseStarsBy(starsToPlayer);
    }

    @Override
    public void defeatedByWildUnit(WildUnit wildUnit) {
    }

    @Override
    public void defeatedByBoss(BossUnit bossUnit) {
    }

    /**
     * Increases the parameter victories of the winPlayer according to specific unit that is defeated.
     * @param winPlayer the Player that increases his victories
     */
    @Override
    public abstract void increaseVictoriesToPlayer(Player winPlayer);

    /**
     * @return true if the unit is KO, false if not.
     */
    public boolean isKO(){
        return getCurrentHp() == 0;
    }
}
