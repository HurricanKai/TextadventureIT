public class SpacedMapRenderer extends MapRendererBase
{
    private char[] chars;

    public SpacedMapRenderer(Map map)
    {
        super(map);
    }

    @Override
    public void Render(Console console, GameState gameState)
    {
        var playerPosition = gameState.getPosition();
        var playerTilePositionX = playerPosition.X * 3;
        var playerTilePositionY = playerPosition.Y * 3;
        // render map non-overlapping
        for (int y = 0; y < (map.getSizeY() * 3); y++)
        {
            chars = new char[map.getSizeX() * 3];
            var tiley = y / 3;
            var tileoffsety = y % 3;
            for (int x = 0; x < chars.length; x++)
            {
                var tilex = x / 3;
                var tileoffsetx = x % 3;

                chars[x] = getTileOffsetChar(map.get_tile(tilex, tiley), tileoffsetx, tileoffsety, gameState);
            }
            if (y == playerTilePositionY + 1)
                chars[playerTilePositionX + 1] = '#';
            console.Write(chars);
            console.NewLine();
        }
    }
}
