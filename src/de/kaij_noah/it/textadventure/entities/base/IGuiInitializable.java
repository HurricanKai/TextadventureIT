package de.kaij_noah.it.textadventure.entities.base;

import de.kaij_noah.it.textadventure.base.IConsole;
import de.kaij_noah.it.textadventure.base.IMenuManager;

public interface IGuiInitializable
{
    void guiInitialize(IConsole console, IMenuManager menuManager);
}
