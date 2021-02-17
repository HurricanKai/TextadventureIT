package de.kaij_noah.it.textadventure.base;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

public final class Icon
{
    public static final Icon DebugIcon = createCheckerboardPattern();

    private static Icon createCheckerboardPattern()
    {
        // NOTE: THIS WILL BREAK EVERYTHING WHEN NON-32 ICON SIZE IS CHOSEN
        var v = new Icon(32, 16);

        for (int x = 0; x < 32; x++)
        {
            for (int y = 0; y < 16; y++)
            {
                char c;
                if (x % 2 == y % 2)
                    c = '█';
                else
                    c = ' ';

                v.setCharAt(x, y, c);
            }
        }

        return v;
    }

    public static final Dictionary<Character, Character> DefaultPallet = new Hashtable<>()
    {{
        put('0', ' ');
        put('1', '░');
        put('2', '▒');
        put('3', '▓');
        put('4', '█');
    }};

    public static Icon loadFromText(String text, Dictionary<Character, Character> pallet)
    {
        var lines = text.split("\n");
        var height = lines.length;
        var width = lines[0].length() / 2;
        var icon = new Icon(width, height);
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                icon.setCharAt(x, y, pallet.get(lines[y].charAt(x * 2)));
            }
        }

        return icon;
    }

    private final char[] chars;
    private final int width;
    private final int height;

    public Icon(int width, int height)
    {
        if (width == 0 || height == 0)
        {
            throw new IllegalArgumentException();
        }

        this.width = width;
        this.height = height;
        chars = new char[width * height];
    }

    public static Icon createFromSingle(char c, int width, int height)
    {
        var v = new Icon(width, height);
        v.fill(c);
        return v;
    }

    public char getCharAt(int x, int y)
    {
        return chars[width * y + x];
    }

    public void setCharAt(int x, int y, char c)
    {
        chars[width * y + x] = c;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void fill(char appearance)
    {
        Arrays.fill(chars, appearance);
    }
}
