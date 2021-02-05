public class Gui implements IRenderable
{
    private final IComponent[] components;

    public Gui()
    {
        components = new IComponent[]
                {
                    new TestComponent()
                };
    }

    @Override
    public void Render(Console console, GameState gameState)
    {
        console.NewLine();
        int maxLineLength = 0;
        int spaceWidth = console.getCharWidth(' ');
        var actions = gameState.getTile().getPossibleActions();


        for (IComponent component : components)
        {
            String[] lines = component.getLines();
            for (int i = 0; i < lines.length; i++)
            {
                maxLineLength = Math.max(console.getStringWidth(lines[i]),maxLineLength);

            }
        }

        int actionCount = 0;
        int componentCount = 0;
        int lineCount = 0;

        while(true)
        {
            int linesLength = 0;
            int lineWidth = 0;
            if(componentCount < components.length)
            {
                String[] lines = components[componentCount].getLines();
                linesLength = lines.length;
                lineWidth = console.getStringWidth(lines[lineCount]);

                console.Write(lines[lineCount]);
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

            console.Write(" | " + (actionCount + 1) +": ");


            if(actionCount < actions.length)
            {
                console.Write(actions[actionCount].getDescription());
                actionCount++;
            }


            console.NewLine();



            if(lineCount >= linesLength)
            {
                componentCount++;
                lineCount = 0;
            }

            if(actionCount >= actions.length && componentCount >= components.length)
            {
                break;
            }
        }
    }
}