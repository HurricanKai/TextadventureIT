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
        var p = gameState.getPlayerEntity().getPosition();
        var x = p.X;
        var y = p.Y;
        var z = p.Z;
        console.write(String.format("Frame: %s | Pos: %s %s %s\n", frameCount++, x, y, z));
    }
}
