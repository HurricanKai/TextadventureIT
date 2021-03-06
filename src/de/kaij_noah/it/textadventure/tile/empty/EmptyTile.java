package de.kaij_noah.it.textadventure.tile.empty;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IconCache;
import de.kaij_noah.it.textadventure.base.Tile;

public class EmptyTile extends Tile
{
    private static final String[] titleLines = new String[0];

    public EmptyTile(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
    }

    @Override
    public void initialize(GameState gameState)
    {
        super.initialize(gameState);
        setAppearance(IconCache.Instance.getIcon("Floor"));
    }

    @Override
    public String[] getTitleLines(GameState gameState)
    {
        return titleLines;
    }
}
