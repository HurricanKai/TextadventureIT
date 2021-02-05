import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class BacktrackingMapGenerator implements IMapGenerator
{
    private static final boolean DEBUG = false;

    @Override
    public Map Generate(int sizeX, int sizeY, ITileGenerator tileGenerator)
    {
        var tiles = new Tile[sizeX][sizeY];
        var visited = new ArrayList<Integer>();
        var random = new Random();

        for (int x = 0; x < sizeX; x++)
        {
            for (int y = 0; y < sizeY; y++)
            {
                if (!DEBUG)
                tiles[x][y] = tileGenerator.Generate(x, y, sizeX, sizeY, random);
            }
        }

        var stack = new Stack<Integer>();
        var current = 0;

        int pathI = 0;
        while(true)
        {
            visited.add(current);

            var x = Util.GetX(current, sizeX, sizeY);
            var y = Util.GetY(current, sizeX, sizeY);
            if (DEBUG && tiles[x][y] == null)
            {
                char finalPathI = Integer.toString(pathI).charAt(0);
                tiles[x][y] = new Tile()
                {
                    @Override
                    public char renderFloor(GameState gameState)
                    {
                        return finalPathI;
                    }
                };
            }
            var tile = tiles[x][y];

            System.out.printf("Walking %s %s\n", x, y);

            var possibleDirs = new ArrayList<Vector2I>(4);

            if (x - 1 >= 0)
            {
                var t = Util.Index2D(x - 1, y, sizeX, sizeY);
                if (!visited.contains(t))
                    possibleDirs.add(new Vector2I(x - 1, y));
            }

            if (y - 1 >= 0)
            {
                var t = Util.Index2D(x, y - 1, sizeX, sizeY);
                if (!visited.contains(t))
                    possibleDirs.add(new Vector2I(x, y - 1));
            }

            if (x + 1 < sizeX)
            {
                var t = Util.Index2D(x + 1, y, sizeX, sizeY);
                if (!visited.contains(t))
                    possibleDirs.add(new Vector2I(x + 1, y));
            }

            if (y + 1 < sizeY)
            {
                var t = Util.Index2D(x, y + 1, sizeX, sizeY);
                if (!visited.contains(t))
                    possibleDirs.add(new Vector2I(x, y + 1));
            }

            if (possibleDirs.isEmpty())
            {
                if (stack.isEmpty())
                    return new Map(sizeX, sizeY, tiles);

                current = stack.pop();
                pathI = (pathI + 1) % 9;
                continue;
            }

            var d = possibleDirs.get(random.nextInt(possibleDirs.size()));
            if (DEBUG && tiles[d.X][d.Y] == null)
            {
                char finalPathI2 = Integer.toString(pathI).charAt(0);
                tiles[d.X][d.Y] = new Tile()
                {
                    @Override
                    public char renderFloor(GameState gameState)
                    {
                        return finalPathI2;
                    }
                };
            }

            var o = tiles[d.X][d.Y];
            if (d.X > x)
            {
                tile.setCanMoveEast(true);
                o.setCanMoveWest(true);
                System.out.println("Branching off right");
            }
            if (d.X < x)
            {
                tile.setCanMoveWest(true);
                o.setCanMoveEast(true);
                System.out.println("Branching off left");
            }
            if (d.Y < y)
            {
                tile.setCanMoveNorth(true);
                o.setCanMoveSouth(true);
                System.out.println("Branching off up");
            }
            if (d.Y > y)
            {
                tile.setCanMoveSouth(true);
                o.setCanMoveNorth(true);
                System.out.println("Branching off down");
            }

            stack.push(current);
            current = Util.Index2D(d.X, d.Y, sizeX, sizeY);
        }
    }
}
