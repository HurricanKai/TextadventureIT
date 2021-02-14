package de.kaij_noah.it.textadventure.base;

import de.kaij_noah.it.textadventure.math.Vector3I;
import de.kaij_noah.it.textadventure.math.Weighted;
import de.kaij_noah.it.textadventure.pathfinding.base.INavigationMap;
import de.kaij_noah.it.textadventure.pathfinding.base.INavigationTile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public final class Map implements INavigationMap
{
    private final int sizeX;
    private final int sizeY;
    private final int sizeZ;
    private final Tile[][][] tiles;
    private final NavigationTile[][][] navigationTiles;

    public Map(int sizeX, int sizeY, int sizeZ, Tile[][][] tiles)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
        this.tiles = tiles;
        navigationTiles = new NavigationTile[sizeX][sizeY][sizeZ];
    }

    public void initializePathFinding()
    {
        for (int x = 0; x < sizeX; x++)
        {
            var row = navigationTiles[x];
            for (int y = 0; y < sizeY; y++)
            {
                var col = row[y];
                for (int z = 0; z < sizeZ; z++)
                    col[z] = new NavigationTile();
            }
        }

        for (int x = 0; x < sizeX; x++)
        {
            var row = tiles[x];
            for (int y = 0; y < sizeY; y++)
            {
                var col = row[y];
                for (int z = 0; z < sizeZ; z++)
                {
                    var list = new ArrayList<Weighted<Vector3I>>();
                    var tile = col[z];
                    tile.addToReferences(list, new Vector3I(x, y, z));
                    navigationTiles[x][y][z].setChildren(list);
                }
            }
        }
    }

    public Tile[][][] get_tiles()
    {
        return tiles;
    }

    public Tile getTile(int x, int y, int z)
    {
        return tiles[x][y][z];
    }
    public Tile getTile(Vector3I position)
    {
        return tiles[position.X][position.Y][position.Z];
    }

    public int getSizeX()
    {
        return sizeX;
    }

    public int getSizeY()
    {
        return sizeY;
    }

    public int getSizeZ()
    {
        return sizeZ;
    }

    public void onStep(GameState gameState)
    {
        for (var row : tiles)
            for (var col : row)
                for (var tile : col)
                    tile.onStep(gameState);
    }

    public INavigationTile getNavigationTile(int x, int y, int z)
    {
        return navigationTiles[x][y][z];
    }
    public INavigationTile getNavigationTile(Vector3I position)
    {
        return navigationTiles[position.X][position.Y][position.Z];
    }

    private final class NavigationTile implements INavigationTile
    {
        private Collection<Weighted<Vector3I>> children;

        @Override
        public Collection<Weighted<Vector3I>> getWeightedChildren()
        {
            return children;
        }

        public void setChildren(Collection<Weighted<Vector3I>> children)
        {
            this.children = children;
        }
    }
}
