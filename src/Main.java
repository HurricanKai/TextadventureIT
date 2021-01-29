import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, FontFormatException
    {
        var console = new Console();

        // var map = PrimsGen.GenerateRandomMap(10, 20, new EmptyTileGenerator());
        var renderables = new IRenderable[]
        {
            new DebugDisplay(),
            // new MapRenderer(map),
            new Gui(),
        };
        while(true)
        {
            for (var renderable : renderables)
                renderable.Render(console);

            console.SwapBuffer();

            System.in.read();
            var n = console.ReadLine();
            if (n.equals("exit"))
                break;
        }
    }
}
