package cl.uchile.dcc.citricliquid.model.units;

public interface IUnit {

    IUnit copy();

    String getName();

    int getMaxHp();

    int getCurrentHp();

    int getAtk();

    int getDef();

    int getEvd();

    int getStars();

    void increaseStarsBy(final int amount);

    void reduceStarsBy(final int amount);

    int rollAttack();

    void defend(int enemyAttack);

    void evade(int enemyAttack);
    
    void winAgainst(IUnit defeatedUnit);
    
    void defeatedByPlayer(Player player);

    void defeatedByWildUnit(WildUnit wildUnit);

    void defeatedByBoss(BossUnit bossUnit);

    void increaseVictoriesToPlayer(Player winPlayer);

    boolean isKO();
}
