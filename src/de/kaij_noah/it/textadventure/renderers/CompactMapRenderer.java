package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IConsole;
import de.kaij_noah.it.textadventure.base.Map;

import java.util.stream.Collectors;

public final class CompactMapRenderer extends MapRendererBase
{
    private final char[] chars;

    public CompactMapRenderer(Map map)
    {
        super(map);
        chars = new char[map.getSizeX() * 2 + 1];
    }

    @Override
    public void Render(IConsole console, GameState gameState)
    {
        var playerPosition = gameState.getPlayerEntity().getPosition();
        var rowGroups = gameState.getEntityManager().getAll().filter(b -> b.getPosition().Z == playerPosition.Z).collect(Collectors.groupingBy(b -> b.getPosition().Y));

        for (int tiley = 0; tiley < map.getSizeY(); tiley++)
        {
            // we only want to draw the sub-tile above the actual tile if the current tile is col 0 (since that is the upper wall)
            for (int tileoffsety = tiley == 0 ? 0 : 1; tileoffsety <= 2; tileoffsety++)
            {
                for (int tilex = 0; tilex < map.getSizeX(); tilex++)
                {
                    // we only want to draw the sub-tile above the actual tile if the current tile is row 0 (since that is the left wall)
                    for (int tileoffsetx = tilex == 0 ? 0 : 1; tileoffsetx <= 2; tileoffsetx++)
                    {
                        chars[tilex * 2 + tileoffsetx] = getTileOffsetChar(map.getTile(tilex, tiley, playerPosition.Z), tileoffsetx, tileoffsety, gameState);
                    }
                }

                if (tileoffsety == 1)
                {
                    var entities = rowGroups.get(tiley);
                    if (entities != null)
                    {
                        for (var entity : entities)
                            chars[entity.getPosition().X * 2 + 1] = entity.render();
                    }
                }
                console.Write(chars);
                console.NewLine();
            }
        }
    }
}
