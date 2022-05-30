package cl.uchile.dcc.citricliquid.model.norma;

import cl.uchile.dcc.citricliquid.model.units.Player;

public interface INormaGoal {
    /**
     * Decides if the goal selected by the player
     * was achieve or not
     * @param player
     */
    void checkMe(Player player);
}
