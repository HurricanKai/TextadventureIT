package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IConsole;
import de.kaij_noah.it.textadventure.base.IRenderer;

public final class TitleLineRenderer implements IRenderer
{
    @Override
    public void Render(IConsole console, GameState gameState)
    {
        var titleLines = gameState.getMap().getTile(gameState.getPlayerEntity().getPosition()).getTitleLines(gameState);

        for (var line : titleLines)
        {
            console.write(line);
            console.newLine();
            console.newLine();
        }
    }
}
