package de.kaij_noah.it.textadventure.tile.spikes;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IEntity;
import de.kaij_noah.it.textadventure.base.Tile;
import de.kaij_noah.it.textadventure.entities.base.IHealthEntity;

public final class Spikes extends Tile
{
    private static final String[] titleLines = new String[]{"Oh nein! Eine falle! Du verlierst etwas leben."};

    public Spikes(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
        setAppearance('Ѕ');
    }

    @Override
    public String[] getTitleLines(GameState gameState)
    {
        return titleLines;
    }

    @Override
    public void onEntityEnter(IEntity entity, GameState gameState)
    {
        if (entity instanceof IHealthEntity)
        {
            var healthEntity = (IHealthEntity)entity;
            healthEntity.setHealth(healthEntity.getHealth() - .10f);
        }
    }
}
