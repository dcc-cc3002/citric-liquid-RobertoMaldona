package cl.uchile.dcc.citricliquid.model.norma;

import cl.uchile.dcc.citricliquid.model.units.Player;

import java.util.List;

public class WinsNorma implements INormaGoal{
    @Override
    public void checkMe(Player player) {
        List<Integer> winsForLevel= List.of(0,2, 5, 9, 14);
        int currentVictories = player.getVictories();
        if(currentVictories>= winsForLevel.get(player.getNormaLevel() - 1)){
            player.normaClear();
        }
    }
}
