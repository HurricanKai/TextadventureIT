public class MapRenderer implements IRenderable
{
    private final Map map;
    private char[] chars;

    public MapRenderer(Map map)
    {
        this.map = map;
    }

    @Override
    public void Render(Console console, GameState gameState)
    {
        // render map non-overlapping
        // for (int y = 0; y < (map.getSizeY() * 3); y++)
        // {
        //     chars = new char[map.getSizeX() * 3];
        //     MapToCharsNonOverlapping(map, y, chars, gameState);
        //     if (y == playerTilePositionY + 1)
        //         chars[playerTilePositionX + 1] = '#';
        //     console.Write(chars);
        //     console.NewLine();
        // }

        DrawOverlapping(map, gameState, console);
    }

    // private static final char RightBlock =  '▐';
    // private static final char LeftBlock =   '▌';
    // private static final char UpBlock =     '▀';
    // private static final char DownBlock =   '▄';
    private static final char RightBlock =  '█';
    private static final char LeftBlock =   '█';
    private static final char UpBlock =     '█';
    private static final char DownBlock =   '█';
    private static final char Empty =       ' ';
    private void MapToCharsNonOverlapping(Map map, int y, char[] target, GameState gameState)
    {
        var tiley = y / 3;
        var tileoffsety = y % 3;
        for (int x = 0; x < target.length; x++)
        {
            var tilex = x / 3;
            var tileoffsetx = x % 3;


            target[x] = getTileOffsetChar(map.get_tile(tilex, tiley), tileoffsetx, tileoffsety, gameState);
        }
    }

    private void DrawOverlapping(Map map, GameState gameState, Console console)
    {
        var playerPosition = gameState.getPosition();
        var playerTilePositionX = playerPosition.X * 3;
        var playerTilePositionY = playerPosition.Y * 3;

        for (int tiley = 0; tiley < map.getSizeY(); tiley++)
        {
            for (int tileoffsety = tiley == 0 ? 0 : 1; tileoffsety <= 2; tileoffsety++)
            {
                chars = new char[map.getSizeX() * 2 + 1];

                for (int tilex = 0; tilex < map.getSizeX(); tilex++)
                {
                    for (int tileoffsetx = tilex == 0 ? 0 : 1; tileoffsetx <= 2; tileoffsetx++)
                    {
                        chars[tilex * 2 + tileoffsetx] = getTileOffsetChar(map.get_tile(tilex, tiley), tileoffsetx, tileoffsety, gameState);
                    }
                }

                if (tiley == playerTilePositionY && tileoffsety == 1)
                    chars[playerTilePositionX + 1] = '#';
                console.Write(chars);
                console.NewLine();
            }
        }
    }

    private char getTileOffsetChar(Tile tile, int tileoffsetx, int tileoffsety, GameState gameState)
    {
        if (tileoffsetx == 0 && tileoffsety == 0
                || tileoffsetx == 2 && tileoffsety == 0
                || tileoffsetx == 0 && tileoffsety == 2
                || tileoffsetx == 2 && tileoffsety == 2)
        {
            return '█';
        }

        else if (tileoffsetx == 2 && tileoffsety == 1)
            return tile.canMoveEast() ? Empty : RightBlock;

        else if (tileoffsetx == 0 && tileoffsety == 1)
            return tile.canMoveWest() ? Empty : LeftBlock;

        else if (tileoffsetx == 1 && tileoffsety == 0)
            return tile.canMoveNorth() ? Empty : UpBlock;

        else if (tileoffsetx == 1 && tileoffsety == 2)
            return tile.canMoveSouth() ? Empty : DownBlock;

        else if (tileoffsetx == 1 && tileoffsety == 1)
            return tile.renderFloor(gameState);

        throw null;
    }
}
