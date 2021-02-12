package de.kaij_noah.it.textadventure.entities.base;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IEntity;

public interface IPreStepable extends IEntity
{
    void preStep(GameState gameState);
}
