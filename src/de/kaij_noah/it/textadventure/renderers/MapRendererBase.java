package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IRenderer;
import de.kaij_noah.it.textadventure.base.Map;
import de.kaij_noah.it.textadventure.base.Tile;

public abstract class MapRendererBase implements IRenderer
{
    private static final char Block = '█';
    private static final char RightBlock = Block;
    private static final char LeftBlock = Block;
    private static final char UpBlock = Block;
    private static final char DownBlock = Block;
    private static final char Empty = ' ';
    protected final Map map;
    public MapRendererBase(Map map)
    {
        this.map = map;
    }

    protected char getTileOffsetChar(Map map, int tilex, int tiley, int tilez, int tileoffsetx, int tileoffsety, GameState gameState)
    {
        var tile = map.getTile(tilex, tiley, tilez);

        // note, the below COULD be solved via maths. (tileoffsetx - 1; tileoffsety--; then go onto folding all the + 1s)
        // but I believe it's clearer this way. Code duplication is bad and all, but clarity > compactness
        if (tileoffsetx == 0 && tileoffsety == 0) // top left
        {
            // basically, if this "pillar" would be ringed by floor, make it empty.
            if (gameState.getOptions().shouldHidePillars() && tile.canMoveNorth() && tile.canMoveWest())
            {
                var diagonal = map.getOptionalTile(tilex - 1, tiley - 1, tilez);
                if (diagonal == null || (diagonal.canMoveSouth() && diagonal.canMoveEast()))
                {
                    // as we assume symmetry in canMove methods, left & up can be assumed to behave the same way.
                    return Empty;
                }
            }

            return Block;
        }
        // see first case
        else if (tileoffsetx == 2 && tileoffsety == 0) // top right
        {
            if (gameState.getOptions().shouldHidePillars() && tile.canMoveNorth() && tile.canMoveEast())
            {
                var diagonal = map.getOptionalTile(tilex - 1, tiley + 1, tilez);
                if (diagonal == null || (diagonal.canMoveSouth() && diagonal.canMoveWest()))
                {
                    return Empty;
                }
            }

            return Block;
        }
        // see first case
        else if (tileoffsetx == 0 && tileoffsety == 2) // bottom left
        {
            if (gameState.getOptions().shouldHidePillars() && tile.canMoveSouth() && tile.canMoveWest())
            {
                var diagonal = map.getOptionalTile(tilex + 1, tiley - 1, tilez);
                if (diagonal == null || (diagonal.canMoveNorth() && diagonal.canMoveEast()))
                {
                    return Empty;
                }
            }

            return Block;
        }
        // see first case
        else if (tileoffsetx == 2 && tileoffsety == 2) // bottom right
        {
            if (gameState.getOptions().shouldHidePillars() && tile.canMoveSouth() && tile.canMoveEast())
            {
                var diagonal = map.getOptionalTile(tilex + 1, tiley + 1, tilez);
                if (diagonal == null || (diagonal.canMoveNorth() && diagonal.canMoveWest()))
                {
                    return Empty;
                }
            }

            return Block;
        }

        // end of "pillars"
        else if (tileoffsetx == 2 && tileoffsety == 1) // right
        {
            return tile.canMoveEast() ? Empty : RightBlock;
        }
        else if (tileoffsetx == 0 && tileoffsety == 1) // left
        {
            return tile.canMoveWest() ? Empty : LeftBlock;
        }
        else if (tileoffsetx == 1 && tileoffsety == 0) // up
        {
            return tile.canMoveNorth() ? Empty : UpBlock;
        }
        else if (tileoffsetx == 1 && tileoffsety == 2) // down
        {
            return tile.canMoveSouth() ? Empty : DownBlock;
        }
        else if (tileoffsetx == 1 && tileoffsety == 1) // center
        {
            return tile.renderFloor();
        }

        throw null;
    }
}
