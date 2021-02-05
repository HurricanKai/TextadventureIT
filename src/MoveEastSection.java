public final class MoveEastSection implements IAction
{
    public static final MoveEastSection Instance = new MoveEastSection();

    @Override
    public String getDescription()
    {
        return "Gehe nach osten";
    }

    @Override
    public void Execute(GameState gameState)
    {
        var pos = gameState.getPosition();
        pos.X += 1;
        gameState.setPosition(pos); // not necessary but for the sake of it
    }
}
