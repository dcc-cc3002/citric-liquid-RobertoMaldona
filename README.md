<!-- 1.0.3-b1 -->
# 99.7% Citric Liquid

Base code for CC3002's *99.7% Citric Juice* Project.

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by 
[Fruitbat Factory](https://fruitbatfactory.com).

The first Part consist in create the model of the game. It's:
Board, Units, and normas.

##**Model:**

***Panel:***
In this model, there is an abstract class called AbstractPanel that defines the general operation
of a panel, also implementing the IPanel interface that contains all the methods of the class, and contains the abstract method "activatedBy" that
is defined in each specific panel.

***Units:***
There are 3 types of units: BossUnit, WildUnit and Player. They have both variables and methods in common
which are defined in the abstract class AbstractUnit, such as life and attack variables, constructor and methods such as
roll, among others. Therefore, each unit class extends AbstractUnit. There is also an interface
implements in AbstractUnit with all the methods that this class has.


Player, has specific variables such as normaGoal, victories, actualPanel and home; In addition to methods for
the new variables and others like normaCheck that use double-dispatch for their implementation. 

To define the behavior of battles between these, there are defend and evade methods, which simulate
the action. Also, there is the winAgainst(IUnit) method that using the double-dispatch technique defines what
it is done when a unit defeats another depending on the type of unit (Player, Boss or WildUnit) that are involved,
calling the method defeatedBy-Unit- based on the type of Unit that won.


***Normas:***
There are 2 types of norms: StarNorma and WinsNorma. These implement the INormaGoal interface, which only defines
a method, CheckMe that receives a player and It decides if the goal selected by the player was achieved or not by calling
Player's normaClear if that's the case.


##Game Flow:

The game flow is composed of a controller (GameController) and a set of Phases, using
as the State Pattern design pattern for the latter.

The GameController has all the methods
necessary to execute a game, and within its variables we find a list of players,
list of panels that will function as the board, a phase which changes at each stage of a turn,
among others. It also has 4 handlers, since it works as an observer for the players
when they are on their turn, noting when they land on a panel with more than one path following,
in your home panel, in a panel with more than one player, or these increase from the norm;
sending the signal to the gameController. The latter was modeled following the Pattern Design of
Observer.

For each action of a turn, the gameController has a method named with the suffix
of try which calls the same method that you want to execute but from the phase. This is only going to do
executed if the game is the expected phase to perform the action, otherwise it will throw an exception
since the phase does not allow it to be carried out.

There are 3 types of exceptions: InvalidActionException, when the phase does not allow a method to be performed
associated with a turn action; InvalidPhaseTransition, when the phase is not allowed to change
of the controller from phase A to phase B; and InvalidDirectionException, when a direction is chosen
that there is no time to choose which way to go.

###**TESTS:**
There are several tests, creating a class to test the panels, another for AbstractUnits and one in
specific to Player. In each of these, the aim is to test each of the methods created, creating tests to
each or groups of related methods.

In the gameflow package there are 2 test classes, one to test the GameController methods with everything
what this class is expected to be able to do, and another for the phases and flow of the game where
they test the phases together with the gameController simulating the stages of a turn and game. To mention
that random tests are done for when there is decision making and/or a result is expected given the method
roll() of the IUnits.

The project always seeks to follow the Solid Principles, in addition to being made to accept changes and extensions.
Each functionality has its own method and all the methods in Abstract Unit and AbstractPanel apply to their subclasses.