package de.kaij_noah.it.textadventure.mapgen;

import de.kaij_noah.it.textadventure.base.IMapGenerator;
import de.kaij_noah.it.textadventure.base.TileTemplate;

public final class OpenRoomMapGenerator implements IMapGenerator
{
    @Override
    public TileTemplate[][][] Generate(int sizeX, int sizeY, int sizeZ)
    {
        var templates = new TileTemplate[sizeX][sizeY][sizeZ];

        for (int x = 0; x < sizeX; x++)
        {
            for (int y = 0; y < sizeY; y++)
            {
                for (int z = 0; z < sizeZ; z++)
                {
                    var tile = new TileTemplate();
                    tile.setCanMoveNorth(y - 1 >= 0);
                    tile.setCanMoveSouth(y + 1 < sizeY);
                    tile.setCanMoveEast(x + 1 < sizeX);
                    tile.setCanMoveWest(x - 1 >= 0);
                    // eh idk this logic is bugged
                    var v = z % 2;
                    tile.setCanMoveDown(z - 1 >= 0 && x == v && y == v - 1);
                    tile.setCanMoveUp(z + 1 < sizeZ && x == v - 1 && y == v);
                    templates[x][y][z] = tile;
                }
            }
        }

        return templates;
    }
}
