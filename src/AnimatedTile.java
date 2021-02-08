public abstract class AnimatedTile extends Tile
{
    private int startIndex = 0;

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
