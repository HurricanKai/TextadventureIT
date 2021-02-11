package de.kaij_noah.it.textadventure.entities;

import de.kaij_noah.it.textadventure.MenuDisplays.HealthMenuDisplay;
import de.kaij_noah.it.textadventure.base.IConsole;
import de.kaij_noah.it.textadventure.base.IMenuDisplay;
import de.kaij_noah.it.textadventure.base.IMenuManager;
import de.kaij_noah.it.textadventure.entities.base.IGuiInitializable;
import de.kaij_noah.it.textadventure.math.Vector3I;

public final class PlayerEntity extends BaseEntity implements IGuiInitializable
{
    private float health = 1f;

    public PlayerEntity()
    {
        super(new Vector3I(0, 0, 0));
    }

    public float getHealth()
    {
        return health;
    }

    public void setHealth(float health)
    {
        this.health = health;
    }

    @Override
    public void guiInitialize(IConsole console, IMenuManager menuManager)
    {
        menuManager.add(new HealthMenuDisplay(this));
    }

    @Override
    public boolean hasDied()
    {
        return health <= 0f;
    }

    @Override
    public char render()
    {
        return '#';
    }
}
