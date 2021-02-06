public class Spikes extends Tile
{
    @Override
    public char renderFloor(GameState gameState)
    {
        return 'Ѕ';
    }

    private static final String[] titleLines = new String[] { "Oh nein! Eine falle! Du verlierst etwas leben." };
    @Override
    public String[] getTitleLines(GameState gameState) { return titleLines; }

    @Override
    public void onPlayerEnter(GameState gameState)
    {
        gameState.putState("health", ((float)gameState.getState("health")) - .10f);
    }
}
