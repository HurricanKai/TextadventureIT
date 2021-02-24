package de.kaij_noah.it.textadventure.entities;

import de.kaij_noah.it.textadventure.menudisplays.HealthMenuDisplay;
import de.kaij_noah.it.textadventure.base.IConsole;
import de.kaij_noah.it.textadventure.base.IMenuManager;
import de.kaij_noah.it.textadventure.base.Icon;
import de.kaij_noah.it.textadventure.base.IconCache;
import de.kaij_noah.it.textadventure.entities.base.IGuiInitializable;
import de.kaij_noah.it.textadventure.entities.base.IHealthEntity;
import de.kaij_noah.it.textadventure.entities.base.IOptionsInitializable;
import de.kaij_noah.it.textadventure.math.Vector3I;
import de.kaij_noah.it.textadventure.options.GameOptions;

public final class PlayerEntity extends BaseEntity implements IGuiInitializable, IHealthEntity, IOptionsInitializable
{
    private float health = 1f;
    private Icon icon;

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
    public Icon render()
    {
        return icon;
    }

    @Override
    public void optionsInitialize(GameOptions options)
    {
        icon = IconCache.Instance.getIcon("Player");
    }
}
