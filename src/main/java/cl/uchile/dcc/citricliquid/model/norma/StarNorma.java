package cl.uchile.dcc.citricliquid.model.norma;

import cl.uchile.dcc.citricliquid.model.units.Player;

import java.util.List;

public class StarNorma implements INormaGoal{
    @Override
    public void checkMe(Player player) {
        List<Integer> starForLevel= List.of(10,30, 70, 120, 200);
        int starsCurrent = player.getStars();
        if(starsCurrent >= starForLevel.get(player.getNormaLevel() -1)){
            player.normaClear();
        }
    }

    @Override
    public void reciveStarsAtStart(int stars, Player ownerTurn) {
        ownerTurn.increaseStarsBy(stars);
    }
}
