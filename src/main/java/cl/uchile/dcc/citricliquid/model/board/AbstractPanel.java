package cl.uchile.dcc.citricliquid.model.board;
import cl.uchile.dcc.citricliquid.model.units.Player;

import java.util.*;

public abstract class AbstractPanel implements IPanel{
    private final int id;
    private final Set<IPanel> nextPanels;
    private final List<Player> Players;
    private IPanel left;
    private IPanel right;
    private IPanel up;
    private IPanel down;

    /**
     * Cronstructor de la clase.
     * @param id unico.
     */
    public AbstractPanel(int id) {
        nextPanels = new HashSet<IPanel>();
        Players = new ArrayList<Player>();
        this.id = id;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @return la variable nextPanels
     */
    public Set<IPanel> getNextPanels() {
        return nextPanels;
    }

    /**
     * Agrega un panel a nextPanels si este no estaba previamente.
     * @param panel
     */
    public void addNextPanel(IPanel panel) {
        if (this.getId()!=panel.getId()) {
            nextPanels.add(panel);

        }
    }

    /**
     * Agrega al Player entregado como parametro a la lista PLayers.
     * @param player
     */
    public void addPlayer(Player player) {
        Players.add(player);

    }

    /**
     * Remueve el jugador del la lista Players del Panel.
     * @param player
     */
    public void removePlayer(Player player) {
        Players.remove(player);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPanel)) return false;
        AbstractPanel that = (AbstractPanel) o;
        return id == that.id &&
                Objects.equals(nextPanels, that.nextPanels) &&
                Objects.equals(Players, that.Players) &&
                Objects.equals(left, that.left) &&
                Objects.equals(right, that.right) &&
                Objects.equals(up, that.up) &&
                Objects.equals(down, that.down);
    }
    /**
     * @return List PLayers.
     */
    public List<Player> getPlayers() {
        return Players;
    }

    /**
     * @return Ipanel left
     */
    public IPanel getLeft() {
        return left;
    }
    /**
     * @return Ipanel right
     */
    public IPanel getRight() {
        return right;
    }
    /**
     * @return Ipanel up
     */
    public IPanel getUp() {
        return up;
    }
    /**
     * @return Ipanel down
     */
    public IPanel getDown() {
        return down;
    }

    /**
     * Setea el Ipanel left con un nuevo Ipanel
     */
    public void setLeft(IPanel left) {
        this.left = left;
    }
    /**
     * Setea el Ipanel left con un nuevo Ipanel
     */
    public void setRight(IPanel right) {
        this.right = right;
    }
    /**
     * Setea el Ipanel left con un nuevo Ipanel
     */
    public void setUp(IPanel up) {
        this.up = up;
    }
    /**
     * Setea el Ipanel left con un nuevo Ipanel
     */
    public void setDown(IPanel down) {
        this.down = down;
    }

    /**
     * Executes the appropriate action to the player according to this panel's type.
     */
    public abstract void activatedBy(final Player player);

}
