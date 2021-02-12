package de.kaij_noah.it.textadventure.pathfinding.base;

import de.kaij_noah.it.textadventure.math.Vector3I;

import java.util.Iterator;

public abstract class PathFinder implements Iterator<Vector3I>
{
    protected final INavigationMap map;

    public PathFinder(INavigationMap map)
    {
        this.map = map;
    }

    public abstract void beginCalculation(Vector3I start, Vector3I goal);

    public abstract void notifyCostChange(Vector3I endEdge);
}
