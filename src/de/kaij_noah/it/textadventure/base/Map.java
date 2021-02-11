package de.kaij_noah.it.textadventure.base;

import de.kaij_noah.it.textadventure.math.Vector3I;

public final class Map
{
    private final int sizeX;
    private final int sizeY;
    private final int sizeZ;
    private Tile[][][] tiles;

    public Map(int sizeX, int sizeY, int sizeZ, Tile[][][] tiles)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
        this.tiles = tiles;
    }

    public Tile[][][] get_tiles()
    {
        return tiles;
    }

    public void set_tiles(Tile[][][] tiles)
    {
        this.tiles = tiles;
    }

    public Tile get_tile(int x, int y, int z)
    {
        return tiles[x][y][z];
    }
    public Tile get_tile(Vector3I position)
    {
        return tiles[position.X][position.Y][position.Z];
    }

    public void set_tile(int x, int y, int z, Tile tile)
    {
        tiles[x][y][z] = tile;
    }
    public void set_tile(Vector3I position, Tile tile)
    {
        tiles[position.X][position.Y][position.Z] = tile;
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
}
