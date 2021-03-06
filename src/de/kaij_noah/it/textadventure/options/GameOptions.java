package de.kaij_noah.it.textadventure.options;

import de.kaij_noah.it.textadventure.base.IMapGenerator;
import de.kaij_noah.it.textadventure.mapgen.BacktrackingMapGenerator;
import de.kaij_noah.it.textadventure.mapgen.OpenRoomMapGenerator;

public final class GameOptions
{
    private boolean shouldHidePillars = true;
    private MapGeneratorKind mapGeneratorKind = MapGeneratorKind.Backtracking;
    private int mapWidth = 120;
    private int mapDepth = 120;
    private int mapHeight = 10;
    private int iconSize = 32;

    public boolean shouldHidePillars()
    {
        return shouldHidePillars;
    }

    public void setShouldHidePillars(boolean value)
    {
        shouldHidePillars = value;
    }

    public MapGeneratorKind getMapGeneratorKind()
    {
        return mapGeneratorKind;
    }

    public void setMapGeneratorKind(MapGeneratorKind mapGeneratorKind)
    {
        this.mapGeneratorKind = mapGeneratorKind;
    }

    public int getMapWidth()
    {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth)
    {
        this.mapWidth = mapWidth;
    }

    public int getMapDepth()
    {
        return mapDepth;
    }

    public void setMapDepth(int mapDepth)
    {
        this.mapDepth = mapDepth;
    }

    public int getMapHeight()
    {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight)
    {
        this.mapHeight = mapHeight;
    }

    public IMapGenerator getMapGenerator()
    {
        IMapGenerator generator;
        switch(getMapGeneratorKind())
        {
            case OpenRoom:
                generator = new OpenRoomMapGenerator();
                break;
            default:
                generator = new BacktrackingMapGenerator();
                break;
        }
        return generator;
    }

    public int getIconSize()
    {
        return iconSize;
    }

    public void setIconSize(int iconSize)
    {
        this.iconSize = iconSize;
    }
}
