public class EmptyTile extends Tile
{
    private static final String[] titleLines = new String[0];
    @Override
    public String[] getTitleLines(GameState gameState) { return titleLines; }

    @Override
    public char renderFloor(GameState gameState) { return ' '; }
}
