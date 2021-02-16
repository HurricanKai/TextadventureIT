package de.kaij_noah.it.textadventure.entities.base;

import de.kaij_noah.it.textadventure.base.IEntity;
import de.kaij_noah.it.textadventure.options.GameOptions;

public interface IOptionsInitializable extends IEntity
{
    void optionsInitialize(GameOptions options);
}
