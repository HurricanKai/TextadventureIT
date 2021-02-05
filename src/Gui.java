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
        console.NewLine();
        int maxLineLength = 0;
        int spaceWidth = console.getCharWidth(' ');
        var actions = gameState.getTile().getPossibleActions();


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
                lineWidth = console.getStringWidth(lines[lineIndex]);

                console.Write(lines[lineIndex]);
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

    public void keyTyped(char c, GameState state)
    {
        switch(c)
        {
            case '1':
                state.getTile().getPossibleActions()[0].Execute(state);
                break;
            case '2':
                state.getTile().getPossibleActions()[1].Execute(state);
                break;
            case '3':
                state.getTile().getPossibleActions()[2].Execute(state);
                break;
            case '4':
                state.getTile().getPossibleActions()[3].Execute(state);
                break;
            case '5':
                state.getTile().getPossibleActions()[4].Execute(state);
                break;
            case '6':
                state.getTile().getPossibleActions()[5].Execute(state);
                break;
            case '7':
                state.getTile().getPossibleActions()[6].Execute(state);
                break;
            case '8':
                state.getTile().getPossibleActions()[7].Execute(state);
                break;
            case '9':
                state.getTile().getPossibleActions()[8].Execute(state);
                break;
        }
    }
}