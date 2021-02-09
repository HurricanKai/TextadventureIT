package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IRenderer;
import de.kaij_noah.it.textadventure.gui.Console;

public final class DebugRenderer implements IRenderer
{
    private int frameCount = 0;

    @Override
    public void Render(Console console, GameState gameState)
    {
        console.Write(String.format("Frame: %s\n", frameCount++));
    }
}
