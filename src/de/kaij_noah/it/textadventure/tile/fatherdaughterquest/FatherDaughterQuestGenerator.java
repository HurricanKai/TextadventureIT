package de.kaij_noah.it.textadventure.tile.fatherdaughterquest;

import de.kaij_noah.it.textadventure.base.ITileGenerator;
import de.kaij_noah.it.textadventure.base.Tile;
import de.kaij_noah.it.textadventure.base.TileTemplate;
import de.kaij_noah.it.textadventure.tile.empty.EmptyTile;

import java.util.Random;

public final class FatherDaughterQuestGenerator implements ITileGenerator
{
    private int count = 0;

    @Override
    public Tile Generate(TileTemplate template, int x, int y, int z, int maxx, int maxy, int maxz, Random random)
    {


        switch (count)
        {
            case 0:
                count++;
                return new FatherQuest(template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth());

            case 1:
                count++;
                return new DaughterQuest(template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth());

            default:
                return new EmptyTile(template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth());
        }
    }
}
