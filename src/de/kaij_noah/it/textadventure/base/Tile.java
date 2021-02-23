package de.kaij_noah.it.textadventure.base;

import de.kaij_noah.it.textadventure.math.Vector3I;
import de.kaij_noah.it.textadventure.math.Weighted;

import java.util.ArrayList;
import java.util.List;

public abstract class Tile
{
    private final boolean canMoveWest;
    private final boolean canMoveEast;
    private final boolean canMoveNorth;
    private final boolean canMoveSouth;
    private Icon appearance;
    private int iconSize;

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
        iconSize = gameState.getOptions().getIconSize();
    }

    public Icon renderFloor()
    {
        return appearance;
    }

    protected void setAppearance(Icon appearance)
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

    public void addToReferences(List<Weighted<Vector3I>> references, Vector3I ownPosition)
    {
        if (canMoveWest())
        {
            var pos = ownPosition.copy();
            pos.X--;
            references.add(new Weighted<>(1.0, pos));
        }
        if (canMoveEast())
        {
            var pos = ownPosition.copy();
            pos.X++;
            references.add(new Weighted<>(1.0, pos));
        }
        if (canMoveNorth())
        {
            var pos = ownPosition.copy();
            pos.Y--;
            references.add(new Weighted<>(1.0, pos));
        }
        if (canMoveSouth())
        {
            var pos = ownPosition.copy();
            pos.Y++;
            references.add(new Weighted<>(1.0, pos));
        }
    }
}

