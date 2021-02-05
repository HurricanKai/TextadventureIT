public class SingleTileGenerator implements ITileGenerator
{
    private final Tile tile;

    public SingleTileGenerator(Tile tile)
    {
        this.tile = tile;
    }

    @Override
    public Tile Generate(int x, int y)
    {
        return tile;
    }
}
