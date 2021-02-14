package de.kaij_noah.it.textadventure.mapgen;

import de.kaij_noah.it.textadventure.base.IMapGenerator;
import de.kaij_noah.it.textadventure.base.TileTemplate;
import de.kaij_noah.it.textadventure.math.Util;
import de.kaij_noah.it.textadventure.math.Vector3I;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public final class BacktrackingMapGenerator implements IMapGenerator
{
    @Override
    public TileTemplate[][][] Generate(int sizeX, int sizeY, int sizeZ)
    {
        var tiles = new TileTemplate[sizeX][sizeY][sizeZ];
        var visited = new ArrayList<Integer>();
        var random = new Random();

        var stack = new Stack<Integer>();
        var current = 0;

        int pathI = 0;
        while (true)
        {
            visited.add(current);

            var x = Util.GetX(current, sizeX, sizeY, sizeZ);
            var y = Util.GetY(current, sizeX, sizeY, sizeZ);
            var z = Util.GetZ(current, sizeX, sizeY, sizeZ);
            if (tiles[x][y][z] == null)
            {
                tiles[x][y][z] = new TileTemplate();
            }

            var tile = tiles[x][y][z];

            // System.out.printf("Walking %s %s %s\n", x, y, z);

            var sameLevelMultiplier = 100;
            var possibleDirs = new ArrayList<Vector3I>();

            if (x - 1 >= 0)
            {
                var t = Util.Index2D(x - 1, y, z, sizeX, sizeY, sizeZ);
                if (!visited.contains(t))
                    for (int i = 0; i < sameLevelMultiplier; i++)
                        possibleDirs.add(new Vector3I(x - 1, y, z));
            }

            if (y - 1 >= 0)
            {
                var t = Util.Index2D(x, y - 1, z, sizeX, sizeY, sizeZ);
                if (!visited.contains(t))
                    for (int i = 0; i < sameLevelMultiplier; i++)
                        possibleDirs.add(new Vector3I(x, y - 1, z));
            }

            if (z - 1 >= 0)
            {
                var t = Util.Index2D(x, y, z - 1, sizeX, sizeY, sizeZ);
                if (!visited.contains(t))
                    possibleDirs.add(new Vector3I(x, y, z - 1));
            }

            if (x + 1 < sizeX)
            {
                var t = Util.Index2D(x + 1, y, z, sizeX, sizeY, sizeZ);
                if (!visited.contains(t))
                    for (int i = 0; i < sameLevelMultiplier; i++)
                        possibleDirs.add(new Vector3I(x + 1, y, z));
            }

            if (y + 1 < sizeY)
            {
                var t = Util.Index2D(x, y + 1, z, sizeX, sizeY, sizeZ);
                if (!visited.contains(t))
                    for (int i = 0; i < sameLevelMultiplier; i++)
                        possibleDirs.add(new Vector3I(x, y + 1, z));
            }

            if (z + 1 < sizeZ)
            {
                var t = Util.Index2D(x, y, z + 1, sizeX, sizeY, sizeZ);
                if (!visited.contains(t))
                    possibleDirs.add(new Vector3I(x, y, z + 1));
            }

            if (possibleDirs.isEmpty())
            {
                if (stack.isEmpty())
                    return tiles;

                current = stack.pop();
                pathI = (pathI + 1) % 9;
                continue;
            }

            var d = possibleDirs.get(random.nextInt(possibleDirs.size()));
            if (tiles[d.X][d.Y][d.Z] == null)
            {
                tiles[d.X][d.Y][d.Z] = new TileTemplate();
            }

            var o = tiles[d.X][d.Y][d.Z];
            if (d.X > x)
            {
                tile.setCanMoveEast(true);
                o.setCanMoveWest(true);
                System.out.println("Branching off east");
            }
            if (d.X < x)
            {
                tile.setCanMoveWest(true);
                o.setCanMoveEast(true);
                System.out.println("Branching off west");
            }
            if (d.Y < y)
            {
                tile.setCanMoveNorth(true);
                o.setCanMoveSouth(true);
                System.out.println("Branching off north");
            }
            if (d.Y > y)
            {
                tile.setCanMoveSouth(true);
                o.setCanMoveNorth(true);
                System.out.println("Branching off south");
            }
            if (d.Z > z)
            {
                tile.setCanMoveUp(true);
                o.setCanMoveDown(true);
                System.out.println("Branching off up");
            }
            if (d.Z < z)
            {
                tile.setCanMoveDown(true);
                o.setCanMoveUp(true);
                System.out.println("Branching off down");
            }


            stack.push(current);
            current = Util.Index2D(d.X, d.Y, d.Z, sizeX, sizeY, sizeZ);
        }
    }
}
