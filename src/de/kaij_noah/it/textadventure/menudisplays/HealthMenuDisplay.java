package de.kaij_noah.it.textadventure.menudisplays;

import de.kaij_noah.it.textadventure.base.IMenuDisplay;
import de.kaij_noah.it.textadventure.entities.PlayerEntity;

public final class HealthMenuDisplay implements IMenuDisplay
{
    private final PlayerEntity playerEntity;

    public HealthMenuDisplay(PlayerEntity playerEntity)
    {
        this.playerEntity = playerEntity;
    }

    @Override
    public String[] getLines()
    {
        return new String[]{String.format("Health: %s%%", Math.round(playerEntity.getHealth() * 100))};
    }
}
