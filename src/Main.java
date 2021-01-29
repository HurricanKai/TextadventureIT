import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, FontFormatException
    {
        var console = new Console();

        var mapWidth = 40;
        var mapHeight = 15;

        var tileGenerator = new WeightedTileGenerator(new Weighted[]
        {
            new Weighted(100f, new EmptyTileGenerator())
        });
        var postProcessors = new IMapPostProcessor[]
        {
            new AddRandomConnectionMapPostProcessor()
        };
        IMapGenerator generator = new BacktrackingMapGenerator();
        // var generator = new OpenRoomMapGenerator();
        var map = generator.Generate(mapWidth, mapHeight, tileGenerator);
        for(var processor : postProcessors)
            map = processor.Process(map);


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
