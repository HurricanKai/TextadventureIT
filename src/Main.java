import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, FontFormatException
    {
        var console = new Console();

        var mapWidth = 100;
        var mapHeight = 100;

        var tileGenerator = new EmptyTileGenerator();
        IMapGenerator generator = new BacktrackingMapGenerator();
        var map = generator.Generate(mapWidth, mapHeight, tileGenerator);
        var renderables = new IRenderable[]
        {
            new DebugDisplay(),
            new MapRenderer(map),
        };
        while(true)
        {
            for (var renderable : renderables)
                renderable.Render(console);

            console.SwapBuffer();

            var n = console.ReadLine();
            if (n.equals("exit"))
                break;
        }
    }
}
