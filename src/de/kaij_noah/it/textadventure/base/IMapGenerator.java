package de.kaij_noah.it.textadventure.base;

public interface IMapGenerator
{
    TileTemplate[][][] Generate(int sizeX, int sizeY, int sizeZ);
}
