public class OpenRoomMapGenerator implements IMapGenerator
{
    @Override
    public Map Generate(int sizeX, int sizeY, ITileGenerator tileGenerator)
    {
        var tiles = new Tile[sizeX][sizeY];

        for (int x = 0; x < sizeX; x++)
        {
            for (int y = 0; y < sizeY; y++)
            {
                var tile = tileGenerator.Generate(x, y);
                tile.setCanMoveNorth(true);
                tile.setCanMoveSouth(true);
                tile.setCanMoveEast(true);
                tile.setCanMoveWest(true);
                tiles[x][y] = tile;
            }
        }

        return new Map(sizeX, sizeY, tiles);
    }
}
