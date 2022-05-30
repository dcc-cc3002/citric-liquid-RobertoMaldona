package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.Player;

/**
 SubClass of AbstractPanel
 */
public class HomePanel extends AbstractPanel{
    /**
     * Cronstructor de la clase.
     *
     * @param id unico.
     */
    public HomePanel(int id) {
        super(id);
    }

    /**
     * Restores a player's HP in 1.
     */
    @Override
    public void activatedBy(Player player) {
        player.setCurrentHp(player.getCurrentHp() + 1);
    }
}
