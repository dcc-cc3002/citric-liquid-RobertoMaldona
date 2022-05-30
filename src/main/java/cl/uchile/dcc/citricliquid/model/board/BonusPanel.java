package cl.uchile.dcc.citricliquid.model.board;
import cl.uchile.dcc.citricliquid.model.units.Player;

/**
 SubClass of AbstractPanel
 */
public class BonusPanel extends AbstractPanel{
    /**
     * Cronstructor de la clase.
     *
     * @param id unico.
     */
    public BonusPanel(int id) {
        super(id);
    }

    /**
     * Increase the player's star count by the D6 roll multiplied by the maximum between the player's
     * norma level and three.
     */
    @Override
    public void activatedBy(Player player) {
        player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
    }
}
