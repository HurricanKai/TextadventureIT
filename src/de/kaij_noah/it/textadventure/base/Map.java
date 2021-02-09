package de.kaij_noah.it.textadventure.base;

import de.kaij_noah.it.textadventure.base.Tile;

public class Map {
    private final int sizeX;
    private final int sizeY;
    private final int sizeZ;
    private Tile[][][] tiles;

    public Map(int sizeX, int sizeY, int sizeZ, Tile[][][] tiles) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
        this.tiles = tiles;
    }

    public Tile[][][] get_tiles() {
        return tiles;
    }

    public void set_tiles(Tile[][][] tiles) {
        this.tiles = tiles;
    }

    public Tile get_tile(int x, int y, int z) {
        return tiles[x][y][z];
    }

    public void set_tile(int x, int y, int z, Tile tile) {
        tiles[x][y][z] = tile;
    }

    public int getSizeX()
    {
        return sizeX;
    }

    public int getSizeY()
    {
        return sizeY;
    }

    public int getSizeZ() { return sizeZ; }
}
