package de.kaij_noah.it.textadventure.tile.spikes;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.Tile;

public final class Spikes extends Tile
{
    private static final String[] titleLines = new String[]{"Oh nein! Eine falle! Du verlierst etwas leben."};

    public Spikes(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
    }

    @Override
    public char renderFloor(GameState gameState)
    {
        return 'Ѕ';
    }

    @Override
    public String[] getTitleLines(GameState gameState)
    {
        return titleLines;
    }

    @Override
    public void onPlayerEnter(GameState gameState)
    {
        gameState.putState("health", ((float) gameState.getState("health")) - .10f);
    }
}
