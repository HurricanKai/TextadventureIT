import java.util.Random;

public interface ITileGenerator
{
    Tile Generate(int x, int y, int maxx, int maxy, Random random);
}
