import java.util.ArrayList;
import java.util.List;

public abstract class Tile
{
    private boolean canMoveWest;
    private boolean canMoveEast;
    private boolean canMoveNorth;
    private boolean canMoveSouth;

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

    public void setCanMoveWest(boolean canMoveWest)
    {
        this.canMoveWest = canMoveWest;
    }

    public void setCanMoveEast(boolean canMoveEast)
    {
        this.canMoveEast = canMoveEast;
    }

    public void setCanMoveNorth(boolean canMoveNorth)
    {
        this.canMoveNorth = canMoveNorth;
    }

    public void setCanMoveSouth(boolean canMoveSouth)
    {
        this.canMoveSouth = canMoveSouth;
    }

    public abstract char renderFloor(GameState gameState);

    public IAction[] getPossibleActions()
    {
        var list = new ArrayList<IAction>();
        addToPossibleActions(list);
        return list.toArray(IAction[]::new);
    }

    protected void addToPossibleActions(List<IAction> list)
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

