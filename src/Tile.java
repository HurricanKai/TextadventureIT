import java.util.ArrayList;
import java.util.List;

public abstract class Tile
{
    private final boolean canMoveWest;
    private final boolean canMoveEast;
    private final boolean canMoveNorth;
    private final boolean canMoveSouth;

    public Tile(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        this.canMoveWest = canMoveWest;
        this.canMoveEast = canMoveEast;
        this.canMoveNorth = canMoveNorth;
        this.canMoveSouth = canMoveSouth;
    }

    public boolean canMoveWest() { return canMoveWest; }

    public boolean canMoveEast()
    {
        return canMoveEast;
    }

    public boolean canMoveNorth()
    {
        return canMoveNorth;
    }

    public boolean canMoveSouth()
    {
        return canMoveSouth;
    }

    public void initialize(GameState gameState) { }

    public abstract char renderFloor(GameState gameState);
    public abstract String[] getTitleLines(GameState gameState);

    public IAction[] getPossibleActions(GameState gameState)
    {
        var list = new ArrayList<IAction>();
        addToPossibleActions(list, gameState);
        return list.toArray(IAction[]::new);
    }

    protected void addToPossibleActions(List<IAction> list, GameState gameState)
    {
    }

    public void onPlayerEnter(GameState gameState)
    {

    }

    public void onPlayerExit(GameState gameState)
    {

    }

    public void onStep(GameState gameState)
    {

    }
}

