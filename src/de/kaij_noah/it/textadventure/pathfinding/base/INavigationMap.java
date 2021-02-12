package de.kaij_noah.it.textadventure.pathfinding.base;

import de.kaij_noah.it.textadventure.math.Vector3I;

public interface INavigationMap
{
    int getSizeX();
    int getSizeY();
    int getSizeZ();
    INavigationTile getNavigationTile(Vector3I position);
    INavigationTile getNavigationTile(int x, int y, int z);
}
