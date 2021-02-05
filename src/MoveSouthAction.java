public final class MoveSouthAction implements IAction
{
    public static final MoveSouthAction Instance = new MoveSouthAction();

    @Override
    public String getDescription()
    {
        return "Gehe nach süden";
    }

    @Override
    public void Execute(GameState gameState)
    {
        var pos = gameState.getPosition();
        pos.Y -= 1;
        gameState.setPosition(pos); // not necessary but for the sake of it
    }
}
