public class Spikes extends Tile
{
    @Override
    public char renderFloor(GameState gameState)
    {
        return 'А';
    }

    private static final String[] titleLines = new String[] { "Oh nein! Eine falle! Du verlierst etwas leben." };
    @Override
    public String[] getTitleLines(GameState gameState) { return titleLines; }

    @Override
    public void onPlayerEnter(GameState gameState)
    {
        gameState.setHealth(gameState.getHealth() - .10f);
    }
}
