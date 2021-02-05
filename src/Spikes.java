public class Spikes extends Tile
{
    @Override
    public char renderFloor(GameState gameState)
    {
        return 'А';
    }

    @Override
    public void onPlayerEnter(GameState gameState)
    {
        gameState.setHealth(gameState.getHealth() - .10f);
    }
}
