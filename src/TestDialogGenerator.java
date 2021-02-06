import java.util.Random;

public final class TestDialogGenerator implements ITileGenerator
{
    @Override
    public Tile Generate(int x, int y, int maxx, int maxy, Random random)
    {
        return new TestDialogTile();
    }
}
