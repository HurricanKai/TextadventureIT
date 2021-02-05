import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gui implements IRenderable
{
    private final IComponent[] components;

    public Gui()
    {
        components = new IComponent[]
                {
                    new HealthComponent()
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
            String[] lines = component.getLines(gameState);
            for (int i = 0; i < lines.length; i++)
            {
                maxLineLength = Math.max(console.getStringWidth(lines[i]),maxLineLength);

            }
        }

        int actionCount = 0;
        int componentCount = 0;
        int lineCount = 0;

        do
        {
            int linesLength = 0;
            int lineWidth = 0;
            if (componentCount < components.length)
            {
                String[] lines = components[componentCount].getLines(gameState);
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

            console.Write(" | ");


            if (actionCount < actions.length)
            {
                console.Write(+ (actionCount + 1) + ": ");
                console.Write(actions[actionCount].getDescription());
                actionCount++;
            }


            console.NewLine();

            lineCount++;
            if (lineCount >= linesLength)
            {
                componentCount++;
                lineCount = 0;
            }

        } while (actionCount < actions.length || componentCount < components.length);
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