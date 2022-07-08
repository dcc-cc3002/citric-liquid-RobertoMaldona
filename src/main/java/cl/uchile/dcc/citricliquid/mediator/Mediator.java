package cl.uchile.dcc.citricliquid.mediator;

import cl.uchile.dcc.citricliquid.gameflow.GameController;
import cl.uchile.dcc.citricliquid.model.board.*;
import cl.uchile.dcc.citricliquid.model.norma.INormaGoal;
import cl.uchile.dcc.citricliquid.model.units.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mediator class to connect the game'S controller with a test suite.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 2.0-rc.1
 * @since 2.0
 */
public class Mediator {
    private final GameController controller = new GameController();

    // From here on you have to modify the code with calls to the methods of your implementation of
    // the game's controller.
    // You'll have to modify the methods body and replace the <Object> parameters of the mediator
    // classes in the methods to the actual types you have defined.
    // For example:
    //    Change:
    //      public class MediatorPanel<T extends Object>
    //    With:
    //      public class MediatorPanel<T extends Panel>

    //////////////////////////////////////////////////////////////////////////////////////////////////
    // YOU CAN ONLY MODIFY THE SEGMENTS COMMENTED WITH A (!) AND THE GENERIC TYPES AS STATED IN THE
    // PREVIOUS EXAMPLE.
    // *** ANY OTHER CHANGE WILL BE PENALIZED ***
    //////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates a new bonus panel, adds it to the game and returns the created panel inside a
     * mediator.
     */
    public MediatorPanel<IPanel> createBonusPanel(int id) {
        // (!) Change the <Object> or ? parameter with the actual class of the Panel
        // (!) Implement the body of this method
        BonusPanel theCreatedPanel = controller.addBonusPanel(id);
        return new MediatorPanel<>(theCreatedPanel);  // new MediatorPanel<>(theCreatedPanel)
    }

    /**
     * Creates a new boss panel, adds it to the game and returns the created panel inside a mediator.
     */
    public MediatorPanel<IPanel> createBossPanel(int id) {
        // (!) Change the <Object> parameter with the actual class of the Panel
        // (!) Implement the body of this method
        BossPanel theCreatedPanel = controller.addBossPanel(id);
        return new MediatorPanel<>(theCreatedPanel);  // new MediatorPanel<>(theCreatedPanel)
    }

    /**
     * Creates a new drop panel, adds it to the game and returns the created panel inside a mediator.
     */
    public MediatorPanel<IPanel> createDropPanel(int id) {
        // (!) Change the <Object> parameter with the actual class of the Panel
        // (!) Implement the body of this method
        DropPanel theCreatedPanel = controller.addDropPanel(id);
        return new MediatorPanel<>(theCreatedPanel);
    }

    /**
     * Creates a new encounter panel, adds it to the game and returns the created panel inside a
     * mediator.
     */
    public MediatorPanel<IPanel> createEncounterPanel(int id) {
        // (!) Change the <Object> parameter with the actual class of the Panel
        // (!) Implement the body of this method
        EncounterPanel theCreatedPanel = controller.addEncounterPanel(id);
        return new MediatorPanel<>(theCreatedPanel);
    }

    /**
     * Creates a new home panel, adds it to the game and returns the created panel inside a mediator.
     */
    public MediatorPanel<IPanel> createHomePanel(int id) {
        // (!) Change the <Object> parameter with the actual class of the Panel
        // (!) Implement the body of this method
        HomePanel theCreatedPanel = controller.addHomePanel(id);
        return new MediatorPanel<>(theCreatedPanel);
    }

    /**
     * Creates a new neutral panel, adds it to the game and returns the created panel inside a
     * mediator.
     */
    public MediatorPanel<IPanel> createNeutralPanel(int id) {
        // (!) Change the <Object> parameter with the actual class of the Panel
        // (!) Implement the body of this method
        NeutralPanel theCreatedPanel = controller.addNeutralPanel(id);
        return new MediatorPanel<>(theCreatedPanel);
    }

    /**
     * Creates a new Player, locates it in a panel and returns a pair with the created player and it's
     * associated panel, each one in a mediator object.
     */
    public Pair<MediatorPlayer<Player>, MediatorPanel<IPanel>> createPlayer(
            MediatorPanel<IPanel> mediatorPanel, String name, int hitPoints, int attack, int defense,
            int evasion) {
        // (!) Change the <Object> parameter with the actual class of the Player and Panel
        // (!) Implement the body of this method
        Player theCreatedPlayer = controller.addPlayer(name, hitPoints,attack, defense, evasion, mediatorPanel.panel);
        MediatorPlayer<Player> mediatorPlayer = new MediatorPlayer<>(theCreatedPlayer); // new MediatorUnit(theCreatedPlayer)
        return new Pair<>(mediatorPlayer, mediatorPanel);
    }

    /**
     * Creates a new wild unit, adds it to the game and returns the created unit.
     */
    public MediatorWildUnit<WildUnit> createWildUnit(String name, int hitPoints, int attack, int defense,
                                                     int evasion) {
        // (!) Change the <Object> parameter with the actual class of the Wild unit
        // (!) Implement the body of this method
        WildUnit theCreatedUnit = controller.addWildUnit(name, hitPoints, attack, defense, evasion);
        MediatorWildUnit<WildUnit> mediatorUnit = new MediatorWildUnit<>(theCreatedUnit); // new MediatorWildUnit(theCreatedUnit)
        return mediatorUnit;
    }

    /**
     * Creates a new wild unit, adds it to the game and returns the created unit.
     */
    public MediatorBoss<BossUnit> createBossUnit(String name, int hitPoints, int attack, int defense,
                                          int evasion) {
        // (!) Change the <Object> parameter with the actual class of the Boss unit
        // (!) Implement the body of this method
        BossUnit theCreatedUnit = controller.addBossUnit(name, hitPoints, attack, defense, evasion);
        MediatorBoss<BossUnit> MediatorBoss = new MediatorBoss<>(theCreatedUnit); // new MediatorUnit(theCreatedUnit)
        return MediatorBoss;
    }

    /**
     * Adds a new next panel to another and returns the first one wrapped in a mediator.
     */
    public MediatorPanel<IPanel> setNextPanel(MediatorPanel<IPanel> origin, MediatorPanel<?> target) {
        // (!) Implement the body of this method
        controller.setNextPanel(origin.panel, target.panel);
        return origin;
    }

    /**
     * Returns a list with all the panels in the game wrapped inside a mediator object.
     */
    public List<MediatorPanel<IPanel>> getPanels() {
        var panels = new ArrayList<MediatorPanel<IPanel>>();
        // (!) Uncomment this
        for (var panel : controller.getPanels()) {
          panels.add(new MediatorPanel<>(panel));
        }
        return panels;
    }

    /**
     * Returns the winner of the game or {@code null} if there's no winner yet.
     */
    @Contract(pure = true)
    @Nullable
    public MediatorPlayer<Player> getWinner() {
        // (!) Implement this method
        Player winner = controller.getWinner();
        if(winner!=null){
            return new MediatorPlayer<>(winner);
        }
        return null;
    }

    /**
     * Makes the turn owner move a random number of panels and returns the turn owner and the panel it
     * landed each one wrapped in a mediator.
     */
    public Pair<MediatorPlayer<Player>, MediatorPanel<IPanel>> movePlayer() {
        // (!) Implement this method
        controller.moveStart();
        MediatorPlayer<Player> mediatorPlayer = new MediatorPlayer<>(controller.getOwnerTurn());
        MediatorPanel<IPanel> mediatorPanel = new MediatorPanel<>(controller.getOwnerTurn().getActualPanel());
        Pair<MediatorPlayer<Player>, MediatorPanel<IPanel>> resultingPair = new Pair<>(mediatorPlayer, mediatorPanel);
        return resultingPair;
    }

    /**
     * Sets the norma goal of the turn owner and returns the player wrapped in a mediator.
     */
    public MediatorPlayer<Player> setNormaGoal(INormaGoal goal) {
        // (!) Implement this
        controller.setNormaGoal(goal);
        return new MediatorPlayer<>(controller.getOwnerTurn());
    }


    /**
     * Sets a player's home panel.
     */
    public void setPlayerHome(final MediatorPlayer<Player> player, final MediatorPanel<IPanel> homePanel) {
        controller.setHomePanel(player.unit, homePanel.panel);
    }

    /**
     * Returns the current chapter.
     */
    public int getChapter() {
        // (!) Implement this
        return controller.getChapter();
    }

    public MediatorPlayer<Player> getTurnOwner() {
        // (!) Implement this
        MediatorPlayer<Player> turnOwner = new MediatorPlayer<>(controller.getOwnerTurn()); // new MediatorUnit(theTurnOwner)
        return turnOwner;
    }

    /**
     * Ends the current player's turn
     */
    public void endTurn() {
        // (!) Implement this
        controller.finishTurn();
    }

    /**
     * Utility method to create a player based on another.
     */
    public Pair<MediatorPlayer<Player>, MediatorPanel<IPanel>> createPlayer(
            final MediatorPanel<IPanel> panel, final @NotNull MediatorPlayer<Player> player) {
        return createPlayer(panel, player.getName(), player.getMaxHP(), player.getAtk(),
                player.getDef(), player.getEvd());
    }


    /**
     * Mediator class to wrap panels into a format compliant to that of the mediator.
     *
     * @param <T>
     *     The panel's class.
     */
    public static class MediatorPanel<T extends IPanel> {
        private final T panel;

        public MediatorPanel(T panel) {
            this.panel = panel;
        }

        /**
         * Returns this panel's next panels wrapped in a MediatorPanel object.
         */
        public List<MediatorPanel<IPanel>> getNextPanels() {
            var nextPanels = new ArrayList<MediatorPanel<IPanel>>();
            for (var nextPanel : panel.getNextPanels()) {
                nextPanels.add(new MediatorPanel<>(nextPanel));
            }
            return nextPanels;
        }

        /**
         * Returns the players located in this panel wrapped in a mediator.
         */
        @Contract(pure = true)
        @NotNull
        public List<MediatorPlayer<Player>> getPlayers() {
            var players = new ArrayList<MediatorPlayer<Player>>();
            // (!) Uncomment this lines
            for (var player : panel.getPlayers()) {
                players.add(new MediatorPlayer<>(player));
            }
            return players;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final MediatorPanel<?> that = (MediatorPanel<?>) o;
            return panel.equals(that.panel);
        }

        @Override
        public int hashCode() {
            return Objects.hash(panel);
        }
    }

    /**
     * Mediator class to wrap units into a format compliant to that of the mediator.
     *
     * @param <T>
     *     The unit's class.
     */
    public static class MediatorUnit<T extends IUnit> {
        protected T unit;

        public MediatorUnit() {
            throw new UnsupportedOperationException("This constructor is present just as a placeholder.");
        }

        public MediatorUnit(T unit) {
            this.unit = unit;
        }

        public T getUnit() {
            return unit;
        }

        // (!) Implement the getters to call the unit's methods.
        public String getName() {
            return unit.getName();
        }

        public int getMaxHP() {
            return unit.getMaxHp();
        }

        public int getAtk() {
            return unit.getAtk();
        }

        public int getDef() {
            return unit.getDef();
        }

        public int getEvd() {
            return unit.getEvd();
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final MediatorUnit<?> that = (MediatorUnit<?>) o;
            return unit.equals(that.getUnit());
        }

        @Override
        public int hashCode() {
            return Objects.hash(unit);
        }
    }

    /**
     * Mediator class to wrap players into a format compliant to that of the mediator.
     *
     * @param <T>
     *     The player's class.
     */
    public static class MediatorPlayer<T extends Player> extends MediatorUnit<T> {
        // (!) Replace extends Object with the actual class for the units
        //  For example: <T extends Player>
        public MediatorPlayer(String name, int hitPoints, int attack, int defense, int evasion) {
            unit = (T) new Player(name, hitPoints, attack, defense, evasion);
        }

        public MediatorPlayer(T player){
            super(player);
        }

        /**
         * Returns the amount of stars of the player.
         */
        public int getStars() {
            // (!) Implement this
            return unit.getStars();
        }

        /**
         * Returns the player's norma goal.
         */
        public INormaGoal getNormaGoal() {
            // (!) Implement this
            return unit.getNormaGoal();
        }

        /**
         * Returns the player's norma level.
         */
        public int getNormaLevel() {
            // (!) Implement this
            return unit.getNormaLevel();
        }
    }

    /**
     * Mediator class to wrap wild units into a format compliant to that of the mediator.
     *
     * @param <T>
     *     The unit's class.
     */
    public static class MediatorWildUnit<T extends WildUnit> extends MediatorUnit<T> {
        public MediatorWildUnit(String name, int hitPoints, int attack, int defense, int evasion) {
            unit = (T) new WildUnit(name, hitPoints, attack, defense, evasion);
        }

        public MediatorWildUnit(T wildUnit){
            super(wildUnit);
        }
    }

    /**
     * Mediator class to wrap bosses into a format compliant to that of the mediator.
     *
     * @param <T>
     *     The boss' class.
     */
    public static class MediatorBoss<T extends BossUnit> extends MediatorUnit<T> {

        public MediatorBoss(String name, int hitPoints, int attack, int defense, int evasion) {
            unit = (T) new BossUnit(name, hitPoints, attack, defense, evasion);
        }

        public MediatorBoss(T bossUnit){
            super(bossUnit);
        }
    }

    /**
     * Utility class that represents an heterogeneous pair of objects.
     */
    public static class Pair<T1, T2> {
        private final T1 first;
        private final T2 second;

        public Pair(final T1 first, final T2 second) {
            this.first = first;
            this.second = second;
        }

        public T1 getFirst() {
            return first;
        }

        public T2 getSecond() {
            return second;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) &&
                    Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public String toString() {
            return "(" + first.toString() + ", " + second.toString() + ")";
        }
    }
}
