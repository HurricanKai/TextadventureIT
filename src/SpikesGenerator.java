import java.util.Random;

public final class SpikesGenerator implements ITileGenerator
{
    @Override
    public Tile Generate(int x, int y, int maxx, int maxy, Random random)
    {
        return new Spikes();
    }
}
