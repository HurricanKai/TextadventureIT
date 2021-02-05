public final class MoveWestAction implements IAction
{
    public static final MoveWestAction Instance = new MoveWestAction();

    @Override
    public String getDescription()
    {
        return "Gehe nach westen";
    }

    @Override
    public void Execute(GameState gameState)
    {
        var pos = gameState.getPosition();
        pos.X -= 1;
        gameState.setPosition(pos); // not necessary but for the sake of it
    }
}
