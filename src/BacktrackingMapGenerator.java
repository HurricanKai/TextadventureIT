import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class BacktrackingMapGenerator implements IMapGenerator
{
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
                tiles[x][y] = tileGenerator.Generate(x, y);
            }
        }

        var stack = new Stack<Integer>();
        var current = 0;

        while(true)
        {
            visited.add(current);

            var x = Util.GetX(current, sizeX, sizeY);
            var y = Util.GetY(current, sizeX, sizeY);
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
                continue;
            }

            var d = possibleDirs.get(random.nextInt(possibleDirs.size()));
            var o = tiles[d.X][d.Y];
            if (d.X > x)
            {
                tile.setCanMoveRight(true);
                o.setCanMoveLeft(true);
                System.out.println("Branching off right");
            }
            if (d.X < x)
            {
                tile.setCanMoveLeft(true);
                o.setCanMoveRight(true);
                System.out.println("Branching off left");
            }
            if (d.Y < y)
            {
                tile.setCanMoveUp(true);
                o.setCanMoveDown(true);
                System.out.println("Branching off up");
            }
            if (d.Y > y)
            {
                tile.setCanMoveDown(true);
                o.setCanMoveUp(true);
                System.out.println("Branching off down");
            }

            stack.push(current);
            current = Util.Index2D(d.X, d.Y, sizeX, sizeY);
        }
    }
}
