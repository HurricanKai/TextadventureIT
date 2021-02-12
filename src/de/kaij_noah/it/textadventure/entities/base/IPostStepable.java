package de.kaij_noah.it.textadventure.entities.base;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IEntity;

public interface IPostStepable extends IEntity
{
    void postStep(GameState gameState);
}
