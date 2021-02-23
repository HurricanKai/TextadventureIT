package de.kaij_noah.it.textadventure.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public final class IconCache
{
    public static final IconCache Instance = new IconCache();

    private final HashMap<String, Icon> cache = new HashMap<>();
    private final HashMap<String, Icon[]> animatedCache = new HashMap<>();
    private IconCache()
    {

    }

    public Icon getIcon(String name)
    {
        if (cache.containsKey(name))
            return cache.get(name);

        Icon res;
        try
        {
            res = Icon.loadFromText(Files.readString(Path.of("./resources/Icons/" + name)), Icon.DefaultPallet);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            res = Icon.DebugIcon;
        }

        cache.put(name, res);
        return res;
    }

    public Icon[] getAnimatedIcon(String name)
    {
        if (animatedCache.containsKey(name))
            return animatedCache.get(name);

        Icon[] res;
        try
        {
            res = Icon.loadAnimatedFromText(Files.readString(Path.of("./resources/Icons/" + name)), Icon.DefaultPallet);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            res = new Icon[] { Icon.DebugIcon };
        }

        animatedCache.put(name, res);
        return res;
    }
}
