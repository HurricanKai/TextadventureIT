package de.kaij_noah.it.textadventure.MenuDisplays;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IMenuDisplay;

public class HealthMenuDisplay implements IMenuDisplay
{
    @Override
    public String[] getLines(GameState gameState)
    {
        return new String[] {String.format("Health: %s%%", Math.round(((float)gameState.getState("health")) * 100))};
    }
}
