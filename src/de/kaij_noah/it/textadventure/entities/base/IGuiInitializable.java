package de.kaij_noah.it.textadventure.entities.base;

import de.kaij_noah.it.textadventure.base.IConsole;
import de.kaij_noah.it.textadventure.base.IEntity;
import de.kaij_noah.it.textadventure.base.IMenuManager;

public interface IGuiInitializable extends IEntity
{
    void guiInitialize(IConsole console, IMenuManager menuManager);
}
