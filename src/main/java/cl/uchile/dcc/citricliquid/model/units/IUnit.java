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

}
