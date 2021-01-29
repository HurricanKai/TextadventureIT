public abstract class Tile
{
    private boolean canMoveLeft;
    private boolean canMoveRight;
    private boolean canMoveUp;
    private boolean canMoveDown;

    public boolean canMoveLeft()
    {
        return canMoveLeft;
    }

    public boolean canMoveRight()
    {
        return canMoveRight;
    }

    public boolean canMoveUp()
    {
        return canMoveUp;
    }

    public boolean canMoveDown()
    {
        return canMoveDown;
    }

    public abstract char renderFloor();
}
