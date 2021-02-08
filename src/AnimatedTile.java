public abstract class AnimatedTile extends Tile
{
    private int startIndex = 0;

    public AnimatedTile(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
    }

    protected abstract char[] getPossibleTiles();

    protected void setStartIndex(int startIndex)
    {
        this.startIndex = startIndex;
    }

    @Override
    public char renderFloor(GameState gameState)
    {
        var tiles = getPossibleTiles();
        return tiles[(gameState.getTimeStep() + startIndex) % tiles.length];
    }
}
