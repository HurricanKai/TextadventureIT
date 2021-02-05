import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String[] args) throws IOException, FontFormatException, InterruptedException
    {
        var console = new Console();

        var mapWidth = 40;
        var mapHeight = 15;

        var tileGenerator = new WeightedTileGenerator(new Weighted[]
        {
            new Weighted(80f, new SingleTileGenerator(new EmptyTile())),
            new Weighted(20f, new SingleTileGenerator(new Spikes())),
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

        var gui = new Gui();
        var renderables = new IRenderable[]
        {
            new DebugDisplay(),
            new MapRenderer(map),
            gui,
        };

        var gameState = new GameState(new Vector2I(0, 0), map);
        console.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                gui.keyTyped(e.getKeyChar(), gameState);
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                var pos = gameState.getPosition();
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_UP:
                        if (gameState.getTile().canMoveNorth() && pos.Y > 0)
                        {
                            pos.Y -= 1;
                            gameState.setPosition(pos);
                        }
                        break;

                    case KeyEvent.VK_DOWN:
                        if (gameState.getTile().canMoveSouth() && pos.Y < gameState.getMap().getSizeY() - 1)
                        {
                            pos.Y += 1;
                            gameState.setPosition(pos);
                        }
                        break;

                    case KeyEvent.VK_LEFT:
                        if (gameState.getTile().canMoveWest() && pos.X > 0)
                        {
                            pos.X -= 1;
                            gameState.setPosition(pos);
                        }
                        break;

                    case KeyEvent.VK_RIGHT:
                        if (gameState.getTile().canMoveEast() && pos.X < gameState.getMap().getSizeX() - 1)
                        {
                            pos.X += 1;
                            gameState.setPosition(pos);
                        }
                        break;
                }
            }
        });
        while(true)
        {
            for (var renderable : renderables)
                renderable.Render(console, gameState);

            console.SwapBuffer();
            gameState.incrementTimeStep();
            gameState.onStep();
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
