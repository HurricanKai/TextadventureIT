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
                    tile.setCanMoveNorth(true);
                    tile.setCanMoveSouth(true);
                    tile.setCanMoveEast(true);
                    tile.setCanMoveWest(true);
                    templates[x][y][z] = tile;
                }
            }
        }

        return templates;
    }
}
