<!-- 1.0.3-b1 -->
# 99.7% Citric Liquid

Base code for CC3002's *99.7% Citric Juice* Project.

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by 
[Fruitbat Factory](https://fruitbatfactory.com).

The first Part consist in create the model of the game. It's:
Board, Units, and normas.

***Panel:***
En este modelo, existe una clase abstracta llamada AbstractPanel que define el funcionamiento general
de un panel, implementando también la interfaz IPanel que contiene todos los métodos de la clase, y contiene el método abstracto "activatedBy" que 
se define en cada panel en especifico. 

***Units:***
There are 3 types of units: BossUnit, WildUnit and Player. They have both variables and methods in common
which are defined in the abstract class AbstractUnit, such as life and attack variables, constructor and methods such as
roll, among others. Therefore, each unit class extends AbstractUnit. There is also an interface
implements in AbstractUnit with all the methods that this class has.


Player, has specific variables such as normaGoal, victories, actualPanel and home; In addition to methods for
the new variables and others like normaCheck that use double-dispatch for their implementation. 

***Normas:***
There are 2 types of norms: StarNorma and WinsNorma. These implement the INormaGoal interface, which only defines
a method, CheckMe that receives a player and It decides if the goal selected by the player was achieved or not by calling
Player's normaClear if that's the case.

**TESTS:**
There are several tests, creating a class to test the panels, another for AbstractUnits and one in
specific to Player. In each of these, the aim is to test each of the methods created, creating tests to
each or groups of related methods.


The project always seeks to follow the Solid Principles, in addition to being made to accept changes and extensions.
Each functionality has its own method and all the methods in Abstract Unit and AbstractPanel apply to their subclasses.