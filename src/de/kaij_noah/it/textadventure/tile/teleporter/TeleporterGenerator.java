package de.kaij_noah.it.textadventure.tile.teleporter;

import de.kaij_noah.it.textadventure.base.ITileGenerator;
import de.kaij_noah.it.textadventure.base.Tile;
import de.kaij_noah.it.textadventure.base.TileTemplate;
import de.kaij_noah.it.textadventure.math.Vector3I;

import java.util.Random;

public final class TeleporterGenerator implements ITileGenerator
{

    @Override
    public Tile Generate(TileTemplate template, int x, int y, int z, int maxx, int maxy, int maxz, Random random)
    {
        var range = random.nextDouble() * 30; // NOTE: this range is a bit difficult to get right
        // issue is, if the range is too big, hits will always be out of bounds,
        // leading to MOST teleports going to the edges. -> undesirable

        var targetx = (int) Math.round(x + random.nextDouble() * range * 2 - range);
        var targety = (int) Math.round(y + random.nextDouble() * range * 2 - range);
        targetx = Math.max(0, Math.min(targetx - 1, maxx));
        targety = Math.max(0, Math.min(targety - 1, maxy));

        String directionString;
        if (targety > y)
        {
            if (targetx > x)
            {
                directionString = "southeast";
            } else if (targetx < x)
            {
                directionString = "southwest";
            } else
            {
                directionString = "south";
            }
        } else if (targety < y)
        {
            if (targetx > x)
            {
                directionString = "northeast";
            } else if (targetx < x)
            {
                directionString = "northwest";
            } else
            {
                directionString = "north";
            }
        } else
        {
            if (targetx > x)
            {
                directionString = "east";
            } else if (targetx < x)
            {
                directionString = "west";
            } else
            {
                return Generate(template, x, y, z, maxx, maxy, maxz, random); // this is very unlikely
            }
        }

        return new Teleporter(new Vector3I(targetx, targety, z), directionString, template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth());
    }
}
