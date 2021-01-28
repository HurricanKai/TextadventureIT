public class Map {
    private final int xSize;
    private final int ySize;
    private Tile[][] _tiles;

    public Map(int xSize, int ySize, Tile[][] _tiles) {
        this.xSize = xSize;
        this.ySize = ySize;
        this._tiles = _tiles;
    }

    public Tile[][] get_tiles() {
        return _tiles;
    }

    public void set_tiles(Tile[][] tiles) {
        this._tiles = tiles;
    }

    public Tile get_tile(int x, int y) {
        return _tiles[x][y];
    }

    public void set_tile(int x, int y, Tile tile) {
        _tiles[x][y] = tile;
    }

    public int getXSize()
    {
        return xSize;
    }

    public int getYSize()
    {
        return ySize;
    }
}
