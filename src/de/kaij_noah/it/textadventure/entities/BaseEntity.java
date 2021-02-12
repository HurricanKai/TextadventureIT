package de.kaij_noah.it.textadventure.entities;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IConsole;
import de.kaij_noah.it.textadventure.base.IEntity;
import de.kaij_noah.it.textadventure.base.Map;
import de.kaij_noah.it.textadventure.entities.base.IPostStepable;
import de.kaij_noah.it.textadventure.entities.base.IPreStepable;
import de.kaij_noah.it.textadventure.math.Vector3I;

public abstract class BaseEntity implements IEntity, IPreStepable
{
    private Vector3I oldPosition;
    private Vector3I newPosition;

    public BaseEntity(Vector3I position)
    {
        this.oldPosition = position;
        this.newPosition = position;
    }

    @Override
    public Vector3I getPosition()
    {
        return oldPosition.copy();
    }

    @Override
    public void setPosition(Vector3I position)
    {
        this.newPosition = position;
    }

    @Override
    public void onStep(GameState gameState)
    {
    }

    @Override
    public void preStep(GameState gameState)
    {
        if (oldPosition != newPosition)
        {
            gameState.getMap().getTile(oldPosition).onEntityExit(this, gameState);
            gameState.getMap().getTile(newPosition).onEntityEnter(this, gameState);
            oldPosition = newPosition;
        }
    }
}
