package de.kaij_noah.it.textadventure.tile;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.Tile;

public abstract class AnimatedTile extends Tile
{
    private int startIndex = 0;

    public AnimatedTile(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
    }

    protected abstract char[] getPossibleTiles();

    protected void setStartIndex(int startIndex)
    {
        this.startIndex = startIndex;
    }

    @Override
    public void onStep(GameState gameState)
    {
        super.onStep(gameState);
        var tiles = getPossibleTiles();
        setAppearance(tiles[(gameState.getTime() + startIndex) % tiles.length]);
    }
}
