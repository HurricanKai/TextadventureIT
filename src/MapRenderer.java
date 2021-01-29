public class MapRenderer implements IRenderable
{
    private final Map map;
    private char[] chars;

    public MapRenderer(Map map)
    {
        this.map = map;
    }

    @Override
    public void Render(Console console)
    {
        // render map
        for (int y = 0; y < (map.getSizeY() * 3); y++)
        {
            chars = new char[map.getSizeX() * 3];
            MapToChars(map, y, chars);
            console.Write(chars);
            console.NewLine();
        }
    }

    // private static final char RightBlock =  '▐';
    // private static final char LeftBlock =   '▌';
    // private static final char UpBlock =     '▀';
    // private static final char DownBlock =   '▄';
    private static final char RightBlock =  '█';
    private static final char LeftBlock =   '█';
    private static final char UpBlock =     '█';
    private static final char DownBlock =   '█';
    private static final char Empty =       '░';
    private void MapToChars(Map map, int y, char[] target)
    {
        var tiley = y / 3;
        var tileoffsety = y % 3;
        for (int x = 0; x < target.length; x++)
        {
            var tilex = x / 3;
            var tileoffsetx = x % 3;

            if (tileoffsetx == 0 && tileoffsety == 0
                    || tileoffsetx == 2 && tileoffsety == 0
                    || tileoffsetx == 0 && tileoffsety == 2
                    || tileoffsetx == 2 && tileoffsety == 2)
            {
                target[x] = '█';
            } else
            {
                var tile = map.get_tile(tilex, tiley);

                if (tileoffsetx == 2 && tileoffsety == 1)
                    target[x] = tile.canMoveRight() ? Empty : RightBlock;

                else if (tileoffsetx == 0 && tileoffsety == 1)
                    target[x] = tile.canMoveLeft() ? Empty : LeftBlock;

                else if (tileoffsetx == 1 && tileoffsety == 0)
                    target[x] = tile.canMoveUp() ? Empty : UpBlock;

                else if (tileoffsetx == 1 && tileoffsety == 2)
                    target[x] = tile.canMoveDown() ? Empty : DownBlock;

                else if (tileoffsetx == 1 && tileoffsety == 1)
                    target[x] = tile.renderFloor();
            }
        }
    }
}
