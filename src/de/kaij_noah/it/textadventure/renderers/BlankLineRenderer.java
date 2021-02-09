package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IRenderer;
import de.kaij_noah.it.textadventure.gui.Console;

public final class BlankLineRenderer implements IRenderer
{
    @Override
    public void Render(Console console, GameState gameState)
    {
        console.NewLine();
    }
}
