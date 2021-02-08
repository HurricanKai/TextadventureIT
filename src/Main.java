import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String[] args) throws IOException, FontFormatException, InterruptedException
    {
        var console = new Console();
        var random = new Random();

        var mapWidth = 40;
        var mapDepth = 15;
        var mapHeight = 5;

        var tileGenerator = new WeightedTileGenerator(new Weighted[]
        {
            new Weighted(100f, new EmptyGenerator()),
            new Weighted(15f, new SpikesGenerator()),
            new Weighted(1f, new TeleporterGenerator()),
            new Weighted(1f, new FatherDaughterQuestGenerator()),
            new Weighted(1f, new TestDialogGenerator()),
            new Weighted(2f, new CampfireGenerator()),
        });
        var postProcessors = new IMapPostProcessor[]
        {

            new AddRandomConnectionMapPostProcessor()
        };
        IMapGenerator generator = new BacktrackingMapGenerator();
        // var generator = new OpenRoomMapGenerator();
        var mapTemplate = generator.Generate(mapWidth, mapDepth, mapHeight);
        for(var processor : postProcessors)
            mapTemplate = processor.Process(mapTemplate);

        var mapTiles = new Tile[mapTemplate.length][mapTemplate[0].length][mapTemplate[0][0].length];

        for (int x = 0; x < mapTiles.length; x++)
        {
            for (int y = 0; y < mapTiles[x].length; y++)
            {
                for (int z = 0; z < mapTiles[x][y].length; z++)
                {
                    if (mapTiles[x][y][z] != null)
                        continue;

                    var template = mapTemplate[x][y][z];
                    if (template.canMoveDown())
                    {
                        mapTiles[x][y][z] = new StairDownTile(template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth());
                        var v = mapTemplate[x][y][z - 1];
                        mapTiles[x][y][z - 1] = new StairUpTile(v.canMoveWest(), v.canMoveEast(), v.canMoveNorth(), v.canMoveSouth());
                    }
                    else if (template.canMoveUp())
                    {
                        mapTiles[x][y][z] = new StairUpTile(template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth());
                        var v = mapTemplate[x][y][z + 1];
                        mapTiles[x][y][z + 1] = new StairDownTile(v.canMoveWest(), v.canMoveEast(), v.canMoveNorth(), v.canMoveSouth());
                    }
                    else
                    {
                        mapTiles[x][y][z] = tileGenerator.Generate(template, x, y, z, mapTiles.length, mapTiles[x].length, mapTiles[x][y].length, random);
                    }
                }
            }
        }
        var map = new Map(mapTiles.length, mapTiles[0].length, mapTiles[0][0].length, mapTiles);

        var gui = new Gui();
        var renderables = new IRenderable[]
        {
            new DebugDisplay(),
            new CompactMapRenderer(map),
            // new SpacedMapRenderer(map),
            new BlankLineRenderer(),
            new TitleLineRenderer(),
            gui,
        };

        var gameState = new GameState(new Vector3I(0, 0, 0), map);
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

            @Override
            public void keyReleased(KeyEvent e)
            {
            }
        });

        for (Tile[][] col : map.get_tiles())
        {
            for(Tile[] row : col)
            {
                row[gameState.getPosition().Z].initialize(gameState);
            }
        }

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
