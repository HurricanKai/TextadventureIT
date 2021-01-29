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
                    col[y].setCanMoveLeft(true);
                    tiles[x - 1][y].setCanMoveRight(true);
                }

                if (x < (map.getSizeX() - 1) && random.nextDouble() > chance)
                {
                    col[y].setCanMoveRight(true);
                    tiles[x + 1][y].setCanMoveLeft(true);
                }

                if (y > 0 && random.nextDouble() > chance)
                {
                    col[y].setCanMoveUp(true);
                    col[y - 1].setCanMoveDown(true);
                }

                if (y < (map.getSizeY() - 1) && random.nextDouble() > chance)
                {
                    col[y].setCanMoveDown(true);
                    col[y + 1].setCanMoveUp(true);
                }
            }
        }

        return map;
    }
}
