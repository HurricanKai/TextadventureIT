public class HealthComponent implements IComponent
{
    @Override
    public String[] getLines(GameState gameState)
    {
        return new String[] {String.format("Health: %s%%", Math.round(gameState.getHealth() * 100))};
    }
}
