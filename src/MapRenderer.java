public class MapRenderer implements IRenderable
{
    private final Map map;
    private char[] chars;

    public MapRenderer(Map map)
    {
        this.map = map;
        chars = new char[10 * 3];
    }

    @Override
    public void Render(Console console)
    {
        // render map
        for (int y = (map.getYSize() * 3); y > 0; y--)
        {
            chars = new char[map.getXSize() * 3];
            MapToChars(map, 0, y - 1, map.getXSize() * 3, y, chars);
            console.Write(chars);
            console.NewLine();
        }
    }

    private static final char RightBlock =  '▐';
    private static final char LeftBlock =   '▌';
    private static final char UpBlock =     '▀';
    private static final char DownBlock =   '▄';
    private static final char Empty =       '░';
    private void MapToChars(Map map, int minx, int miny, int maxx, int maxy, char[] target)
    {
        for (int x = minx; x < maxx; x++)
        {
            var tilex = x / 3;
            var tileoffsetx = x % 3;
            for (int y = miny; y < maxy; y++)
            {
                var tiley = y / 3;
                var tileoffsety = y % 3;
                int i = Util.Index2D(x - minx, y - miny, maxx, maxy);

                if (tileoffsetx == 0 && tileoffsety == 0
                        || tileoffsetx == 2 && tileoffsety == 0
                        || tileoffsetx == 0 && tileoffsety == 2
                        || tileoffsetx == 2 && tileoffsety == 2)
                {
                    target[i] = '█';
                }
                else
                {
                    var tile = map.get_tile(tilex, tiley);

                    if (tileoffsetx == 2 && tileoffsety == 1)
                        target[i] = tile.canMoveRight() ? Empty : RightBlock;

                    else if (tileoffsetx == 0 && tileoffsety == 1)
                        target[i] = tile.canMoveLeft() ? Empty : LeftBlock;

                    else if (tileoffsetx == 1 && tileoffsety == 2)
                        target[i] = tile.canMoveUp() ? Empty : UpBlock;

                    else if (tileoffsetx == 1 && tileoffsety == 0)
                        target[i] = tile.canMoveDown() ? Empty : DownBlock;

                    else if (tileoffsetx == 1 && tileoffsety == 1)
                        target[i] = tile.renderFloor();
                }
            }
        }
    }
}
