package de.kaij_noah.it.textadventure;

import de.kaij_noah.it.textadventure.base.*;
import de.kaij_noah.it.textadventure.entities.EntityManager;
import de.kaij_noah.it.textadventure.entities.PlayerEntity;
import de.kaij_noah.it.textadventure.entities.SimpleHomingEntity;
import de.kaij_noah.it.textadventure.entities.base.IGuiInitializable;
import de.kaij_noah.it.textadventure.entities.base.IMapInitializable;
import de.kaij_noah.it.textadventure.gui.Console;
import de.kaij_noah.it.textadventure.mapgen.AddRandomConnectionMapPostProcessor;
import de.kaij_noah.it.textadventure.math.Vector3I;
import de.kaij_noah.it.textadventure.math.Weighted;
import de.kaij_noah.it.textadventure.mapgen.WeightedTileGenerator;
import de.kaij_noah.it.textadventure.options.GameOptions;
import de.kaij_noah.it.textadventure.options.OptionsMenu;
import de.kaij_noah.it.textadventure.renderers.*;
import de.kaij_noah.it.textadventure.tile.campfire.CampfireGenerator;
import de.kaij_noah.it.textadventure.tile.empty.EmptyGenerator;
import de.kaij_noah.it.textadventure.tile.fatherdaughterquest.FatherDaughterQuestGenerator;
import de.kaij_noah.it.textadventure.tile.special.StairDownTile;
import de.kaij_noah.it.textadventure.tile.special.StairUpTile;
import de.kaij_noah.it.textadventure.tile.spikes.SpikesGenerator;
import de.kaij_noah.it.textadventure.tile.teleporter.TeleporterGenerator;
import de.kaij_noah.it.textadventure.tile.testdialog.TestDialogGenerator;

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
        var options = new GameOptions();

        // var optionMenu = new OptionsMenu(console, options);
        // optionMenu.display();

        var postProcessors = new IMapPostProcessor[]
                {
                        new AddRandomConnectionMapPostProcessor()
                };

        IMapGenerator generator;
        generator = options.getMapGenerator();

        Map map;
        IRenderer mapRenderer;
        MenuRenderer gui;
        GameState gameState;
        TileTemplate[][][] mapTemplate;
        Tile[][][] mapTiles;
        EntityManager entityManager;
        PlayerEntity playerEntity;
        {
            var start = System.currentTimeMillis();
            console.newLine();
            console.newLine();
            console.newLine();
            console.write("Generating Map...");
            console.swapBuffer();

            mapTemplate = generator.Generate(options.getMapWidth(), options.getMapDepth(), options.getMapHeight());
            for (var processor : postProcessors)
                mapTemplate = processor.Process(mapTemplate);

            mapTiles = new Tile[mapTemplate.length][mapTemplate[0].length][mapTemplate[0][0].length];

            map = new Map(mapTiles.length, mapTiles[0].length, mapTiles[0][0].length, mapTiles);
            mapRenderer = new ZoomedCompactMapRenderer(map);

            entityManager = new EntityManager();
            playerEntity = entityManager.addEntity(new PlayerEntity());

            gui = new MenuRenderer(map, playerEntity);
            gameState = new GameState(playerEntity, map, entityManager, options);

            generateTiles(random, mapTemplate, mapTiles, new EmptyGenerator());
            mapRenderer.Render(console, gameState);
            console.write(String.format("Took %sms", System.currentTimeMillis() - start));
            System.out.printf("Map gen Took %sms\n", System.currentTimeMillis() - start);
        }

        {
            var start = System.currentTimeMillis();
            console.newLine();
            console.newLine();
            console.newLine();
            console.write("Populating Map...");
            console.swapBuffer();

            var tileGenerator = new WeightedTileGenerator(new Weighted[]
                    {
                            new Weighted<ITileGenerator>(100f, new EmptyGenerator()),
                            new Weighted<ITileGenerator>(15f, new SpikesGenerator()),
                            new Weighted<ITileGenerator>(1f, new TeleporterGenerator()),
                            new Weighted<ITileGenerator>(1f, new FatherDaughterQuestGenerator()),
                            new Weighted<ITileGenerator>(1f, new TestDialogGenerator()),
                            new Weighted<ITileGenerator>(2f, new CampfireGenerator()),
                    });
            generateTiles(random, mapTemplate, mapTiles, tileGenerator);
            mapRenderer.Render(console, gameState);

            console.write(String.format("Took %sms", System.currentTimeMillis() - start));
            System.out.printf("Map pop Took %sms\n", System.currentTimeMillis() - start);
        }

        {
            var start = System.currentTimeMillis();
            console.newLine();
            console.newLine();
            console.newLine();
            console.write("Generating Pathfinding overlay...");
            console.swapBuffer();
            map.initializePathFinding();
            console.write(String.format("Took %sms", System.currentTimeMillis() - start));
            System.out.printf("Path ov Took %sms\n", System.currentTimeMillis() - start);
        }

        {
            var start = System.currentTimeMillis();
            console.newLine();
            console.newLine();
            console.newLine();
            console.write("Generating Quests and Enemies...");
            console.swapBuffer();

            // do stuff

            console.write(String.format("Took %sms", System.currentTimeMillis() - start));
            System.out.printf("QE Took %sms\n", System.currentTimeMillis() - start);
        }

        var renderables = new IRenderer[]
                {
                        new DebugRenderer(),
                        mapRenderer,
                        new BlankLineRenderer(),
                        new TitleLineRenderer(),
                        gui,
                };

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
                var pos = playerEntity.getPosition();
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_UP:
                        if (map.getTile(pos).canMoveNorth() && pos.Y > 0)
                        {
                            pos.Y -= 1;
                            playerEntity.setPosition(pos);
                        }
                        break;

                    case KeyEvent.VK_DOWN:
                        if (map.getTile(pos).canMoveSouth() && pos.Y < gameState.getMap().getSizeY() - 1)
                        {
                            pos.Y += 1;
                            playerEntity.setPosition(pos);
                        }
                        break;

                    case KeyEvent.VK_LEFT:
                        if (map.getTile(pos).canMoveWest() && pos.X > 0)
                        {
                            pos.X -= 1;
                            playerEntity.setPosition(pos);
                        }
                        break;

                    case KeyEvent.VK_RIGHT:
                        if (map.getTile(pos).canMoveEast() && pos.X < gameState.getMap().getSizeX() - 1)
                        {
                            pos.X += 1;
                            playerEntity.setPosition(pos);
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
            for (Tile[] row : col)
            {
                for (Tile t : row)
                    t.initialize(gameState);
            }
        }

        var h = entityManager.addEntity(new SimpleHomingEntity(new Vector3I(options.getMapWidth() - 1, options.getMapDepth() - 1, options.getMapHeight() - 1)));
        entityManager.getAll(IMapInitializable.class).forEach(v -> v.mapInitialize(map));
        entityManager.getAll(IGuiInitializable.class).forEach(v -> v.guiInitialize(console, gui));

        while (true)
        {
            for (var renderable : renderables)
                renderable.Render(console, gameState);

            console.swapBuffer();
            gameState.incrementTime();
            gameState.onStep();
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }

    private static void generateTiles(Random random, TileTemplate[][][] mapTemplate, Tile[][][] mapTiles, ITileGenerator tileGenerator)
    {
        for (int x = 0; x < mapTiles.length; x++)
        {
            for (int y = 0; y < mapTiles[x].length; y++)
            {
                for (int z = 0; z < mapTiles[x][y].length; z++)
                {
                    var template = mapTemplate[x][y][z];
                    if (template.canMoveDown())
                    {
                        mapTiles[x][y][z] = new StairDownTile(template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth());
                        var v = mapTemplate[x][y][z - 1];
                        mapTiles[x][y][z - 1] = new StairUpTile(v.canMoveWest(), v.canMoveEast(), v.canMoveNorth(), v.canMoveSouth());
                    } else if (template.canMoveUp())
                    {
                        mapTiles[x][y][z] = new StairUpTile(template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth());
                        var v = mapTemplate[x][y][z + 1];
                        mapTiles[x][y][z + 1] = new StairDownTile(v.canMoveWest(), v.canMoveEast(), v.canMoveNorth(), v.canMoveSouth());
                    } else
                    {
                        mapTiles[x][y][z] = tileGenerator.Generate(template, x, y, z, mapTiles.length, mapTiles[x].length, mapTiles[x][y].length, random);
                    }
                }
            }
        }
    }
}
