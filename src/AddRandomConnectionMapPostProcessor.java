import java.util.Random;

public class AddRandomConnectionMapPostProcessor implements IMapPostProcessor
{
    @Override
    public Map Process(Map map)
    {
        var tiles = map.get_tiles();
        var random = new Random();

        for (int x = 0; x < tiles.length; x++)
        {
            var col = tiles[x];
            for (int y = 0; y < col.length; y++)
            {
                var chance = 1.0 - .10; // 20%
                if (x > 0 && random.nextDouble() > chance)
                {
                    col[y].setCanMoveWest(true);
                    tiles[x - 1][y].setCanMoveEast(true);
                }

                if (x < (map.getSizeX() - 1) && random.nextDouble() > chance)
                {
                    col[y].setCanMoveEast(true);
                    tiles[x + 1][y].setCanMoveWest(true);
                }

                if (y > 0 && random.nextDouble() > chance)
                {
                    col[y].setCanMoveNorth(true);
                    col[y - 1].setCanMoveSouth(true);
                }

                if (y < (map.getSizeY() - 1) && random.nextDouble() > chance)
                {
                    col[y].setCanMoveSouth(true);
                    col[y + 1].setCanMoveNorth(true);
                }
            }
        }

        return map;
    }
}
