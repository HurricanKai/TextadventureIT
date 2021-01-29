import java.awt.*;

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
    public void Render(Console console)
    {
        int maxLineLength = 0;
        int spaceWidth = console.getCharWidth(' ');
        String[] lines;

        for (IComponent component : components)
        {
            lines = component.getLines();
            for (int i = 0; i < lines.length; i++)
            {
                maxLineLength = Math.max(console.getStringWidth(lines[i]),maxLineLength);

            }
        }

        int amountSpaces;

        for (IComponent component : components)
        {
            lines = component.getLines();
            for (int i = 0; i < lines.length; i++)
            {
                console.Write(lines[i]);
                int lineWidth = console.getStringWidth(lines[i]);
                amountSpaces = (maxLineLength - lineWidth) / spaceWidth;

                for(int j = 0; j < amountSpaces; j++)
                {
                    console.Write(" ");
                }

                if(lineWidth % 2 == 0)
                {
                    console.Write(" ");
                }

                console.Write(" | ");
                console.NewLine();
            }
        }
    }
}