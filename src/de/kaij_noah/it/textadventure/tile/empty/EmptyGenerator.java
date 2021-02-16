package de.kaij_noah.it.textadventure.tile.empty;

import de.kaij_noah.it.textadventure.base.ITileGenerator;
import de.kaij_noah.it.textadventure.base.Tile;
import de.kaij_noah.it.textadventure.base.TileTemplate;

import java.util.Random;

public final class EmptyGenerator implements ITileGenerator
{
    @Override
    public Tile Generate(TileTemplate template, int x, int y, int z, int maxx, int maxy, int maxz, Random random)
    {
        return new EmptyTile(template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth());
    }
}
