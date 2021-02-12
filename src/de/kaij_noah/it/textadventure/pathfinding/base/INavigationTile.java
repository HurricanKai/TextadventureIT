package de.kaij_noah.it.textadventure.pathfinding.base;

import de.kaij_noah.it.textadventure.math.Vector3I;
import de.kaij_noah.it.textadventure.math.Weighted;

import java.util.Collection;

public interface INavigationTile
{
    Collection<Weighted<Vector3I>> getWeightedChildren();
}
