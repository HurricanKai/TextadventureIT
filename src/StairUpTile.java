import java.util.List;

public final class StairUpTile extends Tile
{
    public StairUpTile(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
    }

    @Override
    public char renderFloor(GameState gameState)
    {
        return 'u';
    }

    private static final String[] titleLines = new String[0];
    @Override
    public String[] getTitleLines(GameState gameState)
    {
        return titleLines;
    }

    @Override
    protected void addToPossibleActions(List<IAction> list, GameState gameState)
    {
        super.addToPossibleActions(list, gameState);
        list.add(new IAction()
        {
            @Override
            public String getDescription()
            {
                return "Geh die treppe hinauf";
            }

            @Override
            public void Execute(GameState gameState)
            {
                var pos = gameState.getPosition();
                pos.Z++;
                gameState.setPosition(pos);
            }
        });
    }
}
