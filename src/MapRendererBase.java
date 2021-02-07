public abstract class MapRendererBase implements IRenderable
{
    protected final Map map;
    public MapRendererBase(Map map)
    {
        this.map = map;
    }

    // private static final char RightBlock =  '▐';
    // private static final char LeftBlock =   '▌';
    // private static final char UpBlock =     '▀';
    // private static final char DownBlock =   '▄';
    private static final char RightBlock =  '█';
    private static final char LeftBlock =   '█';
    private static final char UpBlock =     '█';
    private static final char DownBlock =   '█';
    private static final char Empty =       ' ';

    protected char getTileOffsetChar(Tile tile, int tileoffsetx, int tileoffsety, GameState gameState)
    {
        if (tileoffsetx == 0 && tileoffsety == 0
                || tileoffsetx == 2 && tileoffsety == 0
                || tileoffsetx == 0 && tileoffsety == 2
                || tileoffsetx == 2 && tileoffsety == 2)
        {
            return '█';
        }

        else if (tileoffsetx == 2 && tileoffsety == 1)
            return tile.canMoveEast() ? Empty : RightBlock;

        else if (tileoffsetx == 0 && tileoffsety == 1)
            return tile.canMoveWest() ? Empty : LeftBlock;

        else if (tileoffsetx == 1 && tileoffsety == 0)
            return tile.canMoveNorth() ? Empty : UpBlock;

        else if (tileoffsetx == 1 && tileoffsety == 2)
            return tile.canMoveSouth() ? Empty : DownBlock;

        else if (tileoffsetx == 1 && tileoffsety == 1)
            return tile.renderFloor(gameState);

        throw null;
    }
}
