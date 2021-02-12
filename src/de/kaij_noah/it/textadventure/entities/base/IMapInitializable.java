package de.kaij_noah.it.textadventure.entities.base;

import de.kaij_noah.it.textadventure.base.IEntity;
import de.kaij_noah.it.textadventure.base.Map;

public interface IMapInitializable extends IEntity
{
    void mapInitialize(Map map);
}
