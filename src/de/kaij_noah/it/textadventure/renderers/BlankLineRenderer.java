package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IConsole;
import de.kaij_noah.it.textadventure.base.IRenderer;

public final class BlankLineRenderer implements IRenderer
{
    @Override
    public void render(IConsole console, GameState gameState)
    {
        console.newLine();
    }
}
