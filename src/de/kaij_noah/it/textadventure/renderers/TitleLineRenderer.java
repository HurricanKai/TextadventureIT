package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IRenderer;
import de.kaij_noah.it.textadventure.gui.Console;

public final class TitleLineRenderer implements IRenderer
{
    @Override
    public void Render(Console console, GameState gameState)
    {
        var titleLines = gameState.getTile().getTitleLines(gameState);

        for (var line : titleLines)
        {
            console.Write(line);
            console.NewLine();
            console.NewLine();
        }
    }
}
