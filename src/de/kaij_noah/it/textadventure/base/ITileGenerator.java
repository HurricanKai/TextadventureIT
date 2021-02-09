package de.kaij_noah.it.textadventure.base;

import java.util.Random;

public interface ITileGenerator
{
    Tile Generate(TileTemplate template, int x, int y, int z, int maxx, int maxy, int maxz, Random random);
}
