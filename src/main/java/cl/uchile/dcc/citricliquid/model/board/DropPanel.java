package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.Player;

public class DropPanel extends AbstractPanel{
    /**
     * Cronstructor de la clase.
     *
     * @param id unico.
     */
    public DropPanel(int id) {
        super(id);
    }

    /**
     * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
     */
    @Override
    public void activatedBy(Player player) {
        player.reduceStarsBy(player.roll() * player.getNormaLevel());
    }
}
