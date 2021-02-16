package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IConsole;
import de.kaij_noah.it.textadventure.base.Icon;
import de.kaij_noah.it.textadventure.base.Map;
import de.kaij_noah.it.textadventure.options.GameOptions;

import java.util.stream.Collectors;

public final class ZoomedCompactMapRenderer extends MapRendererBase
{
    private char[] chars;

    public ZoomedCompactMapRenderer(Map map, GameOptions options)
    {
        super(map, options);
        chars = new char[viewRangeX * zoomFactorX * 3 * options.getIconSize() + 1];
    }

    private final int zoomFactorX = 1;
    private final int zoomFactorY = 1;
    private final int viewRangeX = 5 * zoomFactorX;
    private final int viewRangeY = 5 * zoomFactorY;
    @Override
    public void Render(IConsole console, GameState gameState)
    {
        chars = new char[viewRangeX * zoomFactorX * 3 * gameState.getOptions().getIconSize() + 1];
        var iconSize = gameState.getOptions().getIconSize();
        var halfIconSize = iconSize / 2;
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
                for (int icony = 0; icony < halfIconSize; icony++)
                {
                    for (int tilex = minTileX; tilex < maxTileX; tilex++)
                    {
                        // we only want to draw the sub-tile above the actual tile if the current tile is row 0 (since that is the left wall)
                        for (int tileoffsetx = tilex == 0 ? 0 : 1; tileoffsetx <= 2; tileoffsetx++)
                        {
                            var icon = getTileOffsetIcon(map, tilex, tiley, z, tileoffsetx, tileoffsety, gameState);
                            for (int iconx = 0; iconx < iconSize; iconx++)
                            {
                                var c = icon.getCharAt(iconx, icony);
                                for (int zoomx = 0; zoomx < zoomFactorX; zoomx++)
                                {
                                    chars[(((tilex - minTileX) * 2 + tileoffsetx) * iconSize + iconx) * zoomFactorX + zoomx] = c;
                                }
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

                                var icon = entity.render();
                                if (icon == null)
                                    icon = Icon.DebugIcon;

                                for (int iconx = 0; iconx < iconSize; iconx++)
                                {
                                    for (int zoomx = 0; zoomx < zoomFactorX; zoomx++)
                                    {
                                        chars[(((entity.getPosition().X - minTileX) * 2 + 1) * iconSize + iconx) * zoomFactorX + zoomx] = icon.getCharAt(iconx, icony);
                                    }
                                }
                            }
                        }
                    }

                    for (int zoomy = 0; zoomy < zoomFactorY; zoomy++)
                    {
                        console.write(chars);
                        console.newLine();
                    }
                }
            }
        }
    }
}
