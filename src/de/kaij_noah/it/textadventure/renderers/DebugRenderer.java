package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IConsole;
import de.kaij_noah.it.textadventure.base.IRenderer;

public final class DebugRenderer implements IRenderer
{
    private int frameCount = 0;

    @Override
    public void Render(IConsole console, GameState gameState)
    {
        console.Write(String.format("Frame: %s\n", frameCount++));
    }
}
