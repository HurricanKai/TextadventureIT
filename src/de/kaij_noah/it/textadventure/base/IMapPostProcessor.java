package de.kaij_noah.it.textadventure.base;

public interface IMapPostProcessor
{
    TileTemplate[][][] Process(TileTemplate[][][] map);
}
