public class Gui implements IRenderable
{
    private final IDisplay[] displays;

    public Gui()
    {
        displays = new IDisplay[]
                {
                    new HealthDisplay()
                };
    }

    @Override
    public void Render(Console console, GameState gameState)
    {
        int maxLineLength = 0;
        int spaceWidth = console.getCharWidth(' ');
        var actions = gameState.getTile().getPossibleActions(gameState);

        for (IDisplay display : displays)
        {
            String[] lines = display.getLines(gameState);
            for (int i = 0; i < lines.length; i++)
            {
                maxLineLength = Math.max(console.getStringWidth(lines[i]),maxLineLength);

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
                console.Write(+ (actionIndex + 1) + ": ");
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
        switch(c)
        {
            case '1':
                gameState.getTile().getPossibleActions(gameState)[0].Execute(gameState);
                break;
            case '2':
                gameState.getTile().getPossibleActions(gameState)[1].Execute(gameState);
                break;
            case '3':
                gameState.getTile().getPossibleActions(gameState)[2].Execute(gameState);
                break;
            case '4':
                gameState.getTile().getPossibleActions(gameState)[3].Execute(gameState);
                break;
            case '5':
                gameState.getTile().getPossibleActions(gameState)[4].Execute(gameState);
                break;
            case '6':
                gameState.getTile().getPossibleActions(gameState)[5].Execute(gameState);
                break;
            case '7':
                gameState.getTile().getPossibleActions(gameState)[6].Execute(gameState);
                break;
            case '8':
                gameState.getTile().getPossibleActions(gameState)[7].Execute(gameState);
                break;
            case '9':
                gameState.getTile().getPossibleActions(gameState)[8].Execute(gameState);
                break;
        }
    }
}