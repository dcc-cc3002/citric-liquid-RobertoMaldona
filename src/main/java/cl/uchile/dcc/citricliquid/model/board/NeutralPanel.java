package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.Player;

/**
 SubClass of AbstractPanel
 */
public class NeutralPanel extends AbstractPanel{
    /**
     * Constructor de la clase.
     *
     * @param id unico.
     */
    public NeutralPanel(int id) {
        super(id);
    }

    /**
     * Does nothing when the Panel is NeutralPanel
     * @param player
     */
    @Override
    public void activatedBy(Player player) {
        //nada
    }
}
