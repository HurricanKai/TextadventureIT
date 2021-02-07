public class CompactMapRenderer extends MapRendererBase
{
    private char[] chars;

    public CompactMapRenderer(Map map)
    {
        super(map);
    }

    @Override
    public void Render(Console console, GameState gameState)
    {
        var playerPosition = gameState.getPosition();
        var playerTilePositionX = playerPosition.X * 3;
        var playerTilePositionY = playerPosition.Y * 3;

        for (int tiley = 0; tiley < map.getSizeY(); tiley++)
        {
            // we only want to draw the sub-tile above the actual tile if the current tile is col 0 (since that is the upper wall)
            for (int tileoffsety = tiley == 0 ? 0 : 1; tileoffsety <= 2; tileoffsety++)
            {
                chars = new char[map.getSizeX() * 2 + 1];

                for (int tilex = 0; tilex < map.getSizeX(); tilex++)
                {
                    // we only want to draw the sub-tile above the actual tile if the current tile is row 0 (since that is the left wall)
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
}
