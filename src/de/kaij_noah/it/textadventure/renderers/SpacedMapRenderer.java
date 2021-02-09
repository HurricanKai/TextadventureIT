package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.Map;
import de.kaij_noah.it.textadventure.gui.Console;

public final class SpacedMapRenderer extends MapRendererBase
{
    private final char[] chars;

    public SpacedMapRenderer(Map map)
    {
        super(map);
        chars = new char[map.getSizeX() * 3];
    }

    @Override
    public void Render(Console console, GameState gameState)
    {
        var playerPosition = gameState.getPosition();
        var playerTilePositionX = playerPosition.X * 3;
        var playerTilePositionY = playerPosition.Y * 3;
        var z = playerPosition.Z;
        // render map non-overlapping
        for (int y = 0; y < (map.getSizeY() * 3); y++)
        {
            var tiley = y / 3;
            var tileoffsety = y % 3;
            for (int x = 0; x < chars.length; x++)
            {
                var tilex = x / 3;
                var tileoffsetx = x % 3;

                chars[x] = getTileOffsetChar(map.get_tile(tilex, tiley, z), tileoffsetx, tileoffsety, gameState);
            }
            if (y == playerTilePositionY + 1)
                chars[playerTilePositionX + 1] = '#';
            console.Write(chars);
            console.NewLine();
        }
    }
}