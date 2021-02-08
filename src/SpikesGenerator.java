import java.util.Random;

public final class SpikesGenerator implements ITileGenerator
{
    @Override
    public Tile Generate(TileTemplate template, int x, int y, int z, int maxx, int maxy, int maxz, Random random)
    {
        return new Spikes(template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth());
    }
}
