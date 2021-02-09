package de.kaij_noah.it.textadventure.tile.campfire;

import de.kaij_noah.it.textadventure.base.ITileGenerator;
import de.kaij_noah.it.textadventure.base.Tile;
import de.kaij_noah.it.textadventure.base.TileTemplate;

import java.util.Random;

public final class CampfireGenerator implements ITileGenerator
{
    @Override
    public Tile Generate(TileTemplate template, int x, int y, int z, int maxx, int maxy, int maxz, Random random)
    {
        return new Campfire(random.nextInt(10), template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth());
    }
}
