package de.kaij_noah.it.textadventure.options;

import de.kaij_noah.it.textadventure.gui.Console;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class OptionsMenu
{
    private final Console console;
    private final GameOptions options;
    private boolean isDisplaying = false;
    private boolean isEditingWidth = false;
    private boolean isEditingDepth = false;
    private boolean isEditingHeight = false;
    private boolean isEditingGeneratorKind = false;

    public OptionsMenu(Console console, GameOptions options)
    {
        this.console = console;
        this.options = options;
        console.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            { }

            @Override
            public void keyPressed(KeyEvent e)
            {

                switch (e.getKeyChar())
                {
                    case '1':
                        onPress(0);
                        break;
                    case '2':
                        onPress(1);
                        break;
                    case '3':
                        onPress(2);
                        break;
                    case '4':
                        onPress(3);
                        break;
                    case '5':
                        onPress(4);
                        break;
                    case '6':
                        onPress(5);
                        break;
                    case '7':
                        onPress(6);
                        break;
                    case '8':
                        onPress(7);
                        break;
                    case '9':
                        onPress(8);
                        break;
                    case 'e':
                        isDisplaying = false;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            { }
        });
    }

    private void onPress(int i)
    {
        if (isEditingWidth)
        {
            switch(i)
            {
                case 0:
                    options.setMapWidth(20);
                    break;
                case 1:
                    options.setMapWidth(40);
                    break;
                case 2:
                    options.setMapWidth(80);
                    break;
                case 3:
                    options.setMapWidth(120);
                    break;
            }

            isEditingWidth = false;
        }
        else if (isEditingDepth)
        {
            switch(i)
            {
                case 0:
                    options.setMapDepth(5);
                    break;
                case 1:
                    options.setMapDepth(15);
                    break;
                case 2:
                    options.setMapDepth(30);
                    break;
                case 3:
                    options.setMapDepth(60);
                    break;
            }

            isEditingDepth = false;
        }
        else if (isEditingHeight)
        {
            switch(i)
            {
                case 0:
                    options.setMapHeight(5);
                    break;
                case 1:
                    options.setMapHeight(10);
                    break;
                case 2:
                    options.setMapHeight(20);
                    break;
                case 3:
                    options.setMapHeight(50);
                    break;
            }
            isEditingHeight = false;
        }
        else if (isEditingGeneratorKind)
        {
            switch(i)
            {
                case 0:
                    options.setMapGeneratorKind(MapGeneratorKind.OpenRoom);
                    break;
                case 1:
                    options.setMapGeneratorKind(MapGeneratorKind.Backtracking);
                    break;
            }
            isEditingGeneratorKind = false;
        }
        else
        {
            switch(i)
            {
                case 0:
                    isEditingWidth = true;
                    break;
                case 1:
                    isEditingDepth = true;
                    break;
                case 2:
                    isEditingHeight = true;
                    break;
                case 3:
                    isEditingGeneratorKind = true;
                    break;
                case 4:
                    options.setShouldHidePillars(!options.shouldHidePillars());
                    break;
            }
        }
    }

    public void display()
    {
        isDisplaying = true;
        while(isDisplaying)
        {
            console.write("To change an option, press the number next to it");
            console.newLine();
            console.newLine();

            if (isEditingWidth)
            {
                console.write("1. 20");
                console.newLine();
                console.write("2. 40 (current)");
                console.newLine();
                console.write("3. 80");
                console.newLine();
                console.write("4. 120");
                console.newLine();
            }
            else if (isEditingDepth)
            {
                console.write("1. 5");
                console.newLine();
                console.write("2. 15 (current)");
                console.newLine();
                console.write("3. 30");
                console.newLine();
                console.write("4. 60");
                console.newLine();
            }
            else if (isEditingHeight)
            {
                console.write("1. 5");
                console.newLine();
                console.write("2. 10 (default)");
                console.newLine();
                console.write("3. 20");
                console.newLine();
                console.write("4. 50");
                console.newLine();
            }
            else if (isEditingGeneratorKind)
            {
                console.write("1. OpenRoom");
                console.newLine();
                console.write("2. Backtracking (default)");
                console.newLine();
            }
            else
            {
                console.write("Map Size:");
                console.newLine();
                console.write("1. Width: ");
                console.write(Integer.toString(options.getMapWidth()));
                console.newLine();
                console.write("2. Depth: ");
                console.write(Integer.toString(options.getMapDepth()));
                console.newLine();
                console.write("3. Height (you won't see this in the preview. This is the maximum height the stairs can lead to): ");
                console.write(Integer.toString(options.getMapHeight()));
                console.newLine();
                console.write("4. Generator Kind:");
                switch(options.getMapGeneratorKind())
                {
                    case OpenRoom:
                        console.write("OpenRoom");
                        break;
                    case Backtracking:
                        console.write("Backtracking");
                        break;
                }
                console.newLine();
                console.write("5. Should hide pillars: ");
                if (options.shouldHidePillars())
                    console.write("true");
                else
                    console.write("false");
                console.newLine();
            }

            console.newLine();
            console.write("e: exit");
            console.swapBuffer();
        }
    }
}
