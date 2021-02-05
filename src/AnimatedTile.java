public abstract class AnimatedTile extends Tile
{
    protected abstract char[] getPossibleTiles();

    @Override
    public char renderFloor(GameState gameState)
    {
        var tiles = getPossibleTiles();
        return tiles[gameState.getTimeStep() % tiles.length];
    }
}
