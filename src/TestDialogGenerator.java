import java.util.Random;

public final class TestDialogGenerator implements ITileGenerator
{
    @Override
    public Tile Generate(TileTemplate template, int x, int y, int z, int maxx, int maxy, int maxz, Random random)
    {
        return new TestDialogTile(template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth());
    }
}
