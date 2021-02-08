import java.util.Random;

public final class CampfireGenerator implements ITileGenerator
{
    @Override
    public Tile Generate(int x, int y, int maxx, int maxy, Random random)
    {
        return new Campfire(random.nextInt(10));
    }
}
