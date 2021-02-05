public final class MoveNorthAction implements IAction
{
    public static final MoveNorthAction Instance = new MoveNorthAction();

    @Override
    public String getDescription()
    {
        return "Gehe nach norden";
    }

    @Override
    public void Execute(GameState gameState)
    {
        var pos = gameState.getPosition();
        pos.Y -= 1;
        gameState.setPosition(pos); // not necessary but for the sake of it
    }
}
