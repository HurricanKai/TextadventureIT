import java.util.Random;

public final class FatherDaughterQuestGenerator implements ITileGenerator
{
    private int count = 0;

    @Override
    public Tile Generate(int x, int y, int maxx, int maxy, Random random)
    {


        switch (count)
        {
            case 0:
                count++;
                return new FatherQuest();

            case 1:
                count++;
                return new DaughterQuest();

            default:
                return new EmptyTile();
        }
    }
}
