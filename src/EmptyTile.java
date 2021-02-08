public class EmptyTile extends Tile
{
    private static final String[] titleLines = new String[0];

    public EmptyTile(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
    }

    @Override
    public String[] getTitleLines(GameState gameState) { return titleLines; }

    @Override
    public char renderFloor(GameState gameState) { return ' '; }
}
