import java.util.List;

public class Campfire extends AnimatedTile
{
    public Campfire(int startIndex, boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
        setStartIndex(startIndex);
    }

    private char[] possibleTiles = new char[]
    {
            'a',
            'b'
    };

    private String[] titleLine = new String[]
    {
            "Ein Lagerfeuer, es ist schön hier zu sitzen."
    };


    @Override
    protected char[] getPossibleTiles()
    {
        return possibleTiles;
    }

    @Override
    public String[] getTitleLines(GameState gameState)
    {
        return titleLine;
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
                return "Ausruhen";
            }

            @Override
            public void Execute(GameState gameState)
            {
                gameState.putState("health", 1f);
            }
        });
    }
}
