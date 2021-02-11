package de.kaij_noah.it.textadventure.base;

import java.util.ArrayList;
import java.util.List;

public abstract class Tile
{
    private final boolean canMoveWest;
    private final boolean canMoveEast;
    private final boolean canMoveNorth;
    private final boolean canMoveSouth;
    private char appearance;

    public Tile(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        this.canMoveWest = canMoveWest;
        this.canMoveEast = canMoveEast;
        this.canMoveNorth = canMoveNorth;
        this.canMoveSouth = canMoveSouth;
    }

    public boolean canMoveWest()
    {
        return canMoveWest;
    }

    public boolean canMoveEast()
    {
        return canMoveEast;
    }

    public boolean canMoveNorth()
    {
        return canMoveNorth;
    }

    public boolean canMoveSouth()
    {
        return canMoveSouth;
    }

    public void initialize(GameState gameState)
    {
    }

    public char renderFloor()
    {
        return appearance;
    }

    protected void setAppearance(char appearance)
    {
        this.appearance = appearance;
    }

    public abstract String[] getTitleLines(GameState gameState);

    public IAction[] getPossibleActions()
    {
        var list = new ArrayList<IAction>();
        addToPossibleActions(list);
        return list.toArray(IAction[]::new);
    }

    protected void addToPossibleActions(List<IAction> list)
    {
    }

    public void onEntityEnter(IEntity entity, GameState gameState)
    {

    }

    public void onEntityExit(IEntity entity, GameState gameState)
    {

    }

    public void onStep(GameState gameState)
    {

    }
}

