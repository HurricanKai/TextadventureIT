public final class TileTemplate
{
    private boolean canMoveWest;
    private boolean canMoveEast;
    private boolean canMoveNorth;
    private boolean canMoveSouth;
    private boolean canMoveUp;
    private boolean canMoveDown;

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
    public boolean canMoveUp()
    {
        return canMoveUp;
    }
    public boolean canMoveDown()
    {
        return canMoveDown;
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

    public void setCanMoveUp(boolean canMoveUp)
    {
        this.canMoveUp = canMoveUp;
    }

    public void setCanMoveDown(boolean canMoveDown)
    {
        this.canMoveDown = canMoveDown;
    }
}
