package de.kaij_noah.it.textadventure.tile;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.Icon;
import de.kaij_noah.it.textadventure.base.Tile;

public abstract class AnimatedTile extends Tile
{
    private int startIndex = 0;

    public AnimatedTile(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
    }

    protected abstract Icon[] getPossibleTiles();

    protected void setStartIndex(int startIndex)
    {
        this.startIndex = startIndex;
    }

    @Override
    public void onStep(GameState gameState)
    {
        super.onStep(gameState);
        var tiles = getPossibleTiles();
        setAppearance(tiles[((gameState.getTime() / 100) + startIndex) % tiles.length]);
    }
}
