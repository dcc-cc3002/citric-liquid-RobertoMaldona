package cl.uchile.dcc.citricliquid.model.board;
import cl.uchile.dcc.citricliquid.model.units.Player;

import java.util.List;
import java.util.Set;

public interface IPanel {
    Set<IPanel> getNextPanels();

    void addNextPanel(IPanel nPanel);

    List<Player> getPlayers();

    void addPlayer(Player player);

    void removePlayer(Player player);

    int getId();

    IPanel getLeft();

    IPanel getRight();

    IPanel getUp();

    IPanel getDown();

    void setLeft(IPanel panelLeft);

    void setRight(IPanel panelRight);

    void setUp(IPanel panelUp);

    void setDown(IPanel panelDown);

    void activatedBy(final Player player);
}
