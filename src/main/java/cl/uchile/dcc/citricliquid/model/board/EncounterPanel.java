package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.Player;

/**
 SubClass of AbstractPanel
 */
public class EncounterPanel extends AbstractPanel{
    /**
     * Cronstructor de la clase.
     *
     * @param id unico.
     */
    public EncounterPanel(int id) {
        super(id);
    }

    /**
     * Does nothing when the Panel is EncounterPanel
     * @param player
     */
    @Override
    public void activatedBy(Player player) {

    }
}
