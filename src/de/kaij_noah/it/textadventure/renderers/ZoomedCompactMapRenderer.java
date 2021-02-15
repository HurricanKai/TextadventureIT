package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IConsole;
import de.kaij_noah.it.textadventure.base.Map;

import java.util.stream.Collectors;

public final class ZoomedCompactMapRenderer extends MapRendererBase
{
    private final char[] chars;

    public ZoomedCompactMapRenderer(Map map)
    {
        super(map);
        chars = new char[viewRangeX * zoomFactorX * 3 + 1];
    }

    private final int zoomFactorX = 2;
    private final int zoomFactorY = 2;
    private final int viewRangeX = 32;
    private final int viewRangeY = 8;
    @Override
    public void Render(IConsole console, GameState gameState)
    {
        var playerPosition = gameState.getPlayerEntity().getPosition();
        var rowGroups = gameState.getEntityManager().getAll().filter(b -> b.getPosition().Z == playerPosition.Z).collect(Collectors.groupingBy(b -> b.getPosition().Y));
        var z =  playerPosition.Z;

        var halfRangeX = viewRangeX / 2;
        var halfRangeY = viewRangeY / 2;

        var minTileY = Math.max(0, playerPosition.Y - halfRangeY);
        var maxTileY = Math.min(gameState.getMap().getSizeY(), playerPosition.Y + halfRangeY);
        var minTileX = Math.max(0, playerPosition.X - halfRangeX);
        var maxTileX = Math.min(gameState.getMap().getSizeX(), playerPosition.X + halfRangeX);

        for (int tiley = minTileY; tiley < maxTileY; tiley++)
        {
            // we only want to draw the sub-tile above the actual tile if the current tile is col 0 (since that is the upper wall)
            for (int tileoffsety = tiley == 0 ? 0 : 1; tileoffsety <= 2; tileoffsety++)
            {
                int i = 0;
                for (int tilex = minTileX; tilex < maxTileX; tilex++)
                {
                    // we only want to draw the sub-tile above the actual tile if the current tile is row 0 (since that is the left wall)
                    for (int tileoffsetx = tilex == 0 ? 0 : 1; tileoffsetx <= 2; tileoffsetx++)
                    {
                        var c = getTileOffsetChar(map, tilex, tiley, z, tileoffsetx, tileoffsety, gameState);
                        for (int zoomx = 0; zoomx < zoomFactorX; zoomx++)
                        {
                            chars[((tilex - minTileX) * 2 + tileoffsetx) * zoomFactorX + zoomx] = c;
                            i++;
                        }
                    }
                }

                if (tileoffsety == 1)
                {
                    var entities = rowGroups.get(tiley);
                    if (entities != null)
                    {
                        for (var entity : entities)
                        {
                            // if (entityNotOnScreen)
                            if (entity.getPosition().X < minTileX || entity.getPosition().X > maxTileX
                            || entity.getPosition().Y < minTileY || entity.getPosition().Y > maxTileY)
                                continue;

                            for (int zoomx = 0; zoomx < zoomFactorX; zoomx++)
                            {
                                chars[((entity.getPosition().X - minTileX) * 2 + 1) * zoomFactorX + zoomx] = entity.render();
                            }
                        }
                    }
                }

                for (int zoomy = 0; zoomy < zoomFactorY; zoomy++)
                {
                    console.write(chars);
                    console.newLine();
                }
                System.out.println(i);
            }
        }
    }
}
