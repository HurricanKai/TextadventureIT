import java.util.List;

public final class Teleporter extends Tile
{
    private Vector2I target;
    private String directionString;

    public Teleporter(Vector2I target, String directionString)
    {
        this.target = target;
        this.directionString = directionString;
    }

    @Override
    public char renderFloor(GameState gameState)
    {
        return 'Т';
    }

    private static final String[] titleLines = new String[]
    {
        "Ein uralter Teleporter. Du könntest ihn aktivieren, aber es ist ungewiss wohin er dich bringt.",
        "Nur die richtung kannst du abschätzen."
    };
    @Override
    public String[] getTitleLines(GameState gameState) { return titleLines; }

    @Override
    protected void addToPossibleActions(List<IAction> list, GameState gameState)
    {
        super.addToPossibleActions(list, gameState);
        list.add(new IAction()
        {
            @Override
            public String getDescription()
            {
                return "Teleport " + directionString;
            }

            @Override
            public void Execute(GameState gameState)
            {
                gameState.setPosition(target);
            }
        });
    }
}
