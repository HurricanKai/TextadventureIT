public class Map {
    private final int sizeX;
    private final int sizeY;
    private Tile[][] tiles;

    public Map(int sizeX, int sizeY, Tile[][] tiles) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.tiles = tiles;
    }

    public Tile[][] get_tiles() {
        return tiles;
    }

    public void set_tiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public Tile get_tile(int x, int y) {
        return tiles[x][y];
    }

    public void set_tile(int x, int y, Tile tile) {
        tiles[x][y] = tile;
    }

    public int getSizeX()
    {
        return sizeX;
    }

    public int getSizeY()
    {
        return sizeY;
    }
}
