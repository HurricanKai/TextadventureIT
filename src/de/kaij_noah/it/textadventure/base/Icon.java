package de.kaij_noah.it.textadventure.base;

import java.util.*;

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
        return loadFromLines(lines, 0, lines.length, pallet);
    }

    public static Icon loadFromLines(String[] lines, int start, int end, Dictionary<Character, Character> pallet)
    {
        var height = end - start;
        var width = lines[start].length() / 2;
        var icon = new Icon(width, height);
        for (int y = start; y < end; y++)
        {
            for (int x = 0; x < width; x++)
            {
                icon.setCharAt(x, y - start, pallet.get(lines[y].charAt(x * 2)));
            }
        }

        return icon;
    }

    public static Icon[] loadAnimatedFromText(String text, Dictionary<Character, Character> pallet)
    {
        var icons = new ArrayList<Icon>();
        var lines = text.split("\n");
        var last = 0;
        for (int i = 0; i < lines.length; i++)
        {
            if (lines[i].startsWith(";Frame"))
            {
                var diff = i - last;
                if (diff > 0)
                {
                    icons.add(loadFromLines(lines, last + 1, i, pallet));
                }

                last = i;
            }
        }
        icons.add(loadFromLines(lines, last + 1, lines.length, pallet));

        var v = new Icon[icons.size()];
        icons.toArray(v);
        return v;
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
