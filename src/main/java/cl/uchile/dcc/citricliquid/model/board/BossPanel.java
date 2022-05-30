package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.Player;

public class BossPanel extends AbstractPanel{
    /**
     * Cronstructor de la clase.
     *
     * @param id unico.
     */
    public BossPanel(int id) {
        super(id);
    }

    /**
     * Does nothing when the Panel is BossPanel
     * @param player
     */
    @Override
    public void activatedBy(Player player) {

    }
}
