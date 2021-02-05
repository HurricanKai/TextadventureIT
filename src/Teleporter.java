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
        return 'Р';
    }

    @Override
    protected void addToPossibleActions(List<IAction> list)
    {
        super.addToPossibleActions(list);
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
