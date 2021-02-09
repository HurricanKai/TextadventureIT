package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.MenuDisplays.HealthMenuDisplay;
import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IConsole;
import de.kaij_noah.it.textadventure.base.IMenuDisplay;
import de.kaij_noah.it.textadventure.base.IRenderer;

public final class MenuRenderer implements IRenderer
{
    private final IMenuDisplay[] displays;

    public MenuRenderer()
    {
        displays = new IMenuDisplay[]
                {
                        new HealthMenuDisplay()
                };
    }

    @Override
    public void Render(IConsole console, GameState gameState)
    {
        int maxLineLength = 0;
        int spaceWidth = console.getCharWidth(' ');
        var actions = gameState.getTile().getPossibleActions(gameState);

        for (IMenuDisplay display : displays)
        {
            String[] lines = display.getLines(gameState);
            for (int i = 0; i < lines.length; i++)
            {
                maxLineLength = Math.max(console.getStringWidth(lines[i]), maxLineLength);

            }
        }

        int actionIndex = 0;
        int displayIndex = 0;
        int lineIndex = 0;

        do
        {
            int linesLength = 0;
            int lineWidth = 0;
            if (displayIndex < displays.length)
            {
                String[] lines = displays[displayIndex].getLines(gameState);
                linesLength = lines.length;

                if (lines.length > 0)
                {
                    lineWidth = console.getStringWidth(lines[lineIndex]);
                    console.Write(lines[lineIndex]);
                }
            }
            int amountSpaces = (maxLineLength - lineWidth) / spaceWidth;

            for (int j = 0; j < amountSpaces; j++)
            {
                console.Write(" ");
            }

            if (lineWidth % 2 == 0)
            {
                console.Write(" ");
            }

            console.Write(" | ");


            if (actionIndex < actions.length)
            {
                console.Write(+(actionIndex + 1) + ": ");
                console.Write(actions[actionIndex].getDescription());
                actionIndex++;
            }

            console.NewLine();

            lineIndex++;
            if (lineIndex >= linesLength)
            {
                displayIndex++;
                lineIndex = 0;
            }

        } while (actionIndex < actions.length || displayIndex < displays.length);
    }

    public void keyTyped(char c, GameState gameState)
    {
        var possibleActions = gameState.getTile().getPossibleActions(gameState);
        int index = -1;
        switch (c)
        {
            case '1':
                index = 0;
                break;
            case '2':
                index = 1;
                break;
            case '3':
                index = 2;
                break;
            case '4':
                index = 3;
                break;
            case '5':
                index = 4;
                break;
            case '6':
                index = 5;
                break;
            case '7':
                index = 6;
                break;
            case '8':
                index = 7;
                break;
            case '9':
                index = 8;
                break;
        }
        if (index < 0 || index > possibleActions.length)
            return;
        possibleActions[index].Execute(gameState);
    }
}