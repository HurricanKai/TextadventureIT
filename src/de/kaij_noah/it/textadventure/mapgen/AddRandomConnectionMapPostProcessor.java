package de.kaij_noah.it.textadventure.mapgen;

import de.kaij_noah.it.textadventure.base.IMapPostProcessor;
import de.kaij_noah.it.textadventure.base.TileTemplate;

import java.util.Random;

public final class AddRandomConnectionMapPostProcessor implements IMapPostProcessor
{
    @Override
    public TileTemplate[][][] Process(TileTemplate[][][] tiles)
    {
        var random = new Random();

        for (int x = 0; x < tiles.length; x++)
        {
            var col = tiles[x];
            for (int y = 0; y < col.length; y++)
            {
                var row = col[y];
                for (int z = 0; z < row.length; z++)
                {
                    var chance = 1.0 - .10; // 20%
                    if (x > 0 && random.nextDouble() > chance)
                    {
                        row[z].setCanMoveWest(true);
                        tiles[x - 1][y][z].setCanMoveEast(true);
                    }

                    if (x < tiles.length - 1 && random.nextDouble() > chance)
                    {
                        row[z].setCanMoveEast(true);
                        tiles[x + 1][y][z].setCanMoveWest(true);
                    }

                    if (y > 0 && random.nextDouble() > chance)
                    {
                        row[z].setCanMoveNorth(true);
                        col[y - 1][z].setCanMoveSouth(true);
                    }

                    if (y < row.length - 1 && random.nextDouble() > chance)
                    {
                        row[z].setCanMoveSouth(true);
                        col[y + 1][z].setCanMoveNorth(true);
                    }
                }
            }
        }

        return tiles;
    }
}
