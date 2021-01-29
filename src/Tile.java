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

    public void setCanMoveLeft(boolean canMoveLeft)
    {
        this.canMoveLeft = canMoveLeft;
    }

    public void setCanMoveRight(boolean canMoveRight)
    {
        this.canMoveRight = canMoveRight;
    }

    public void setCanMoveUp(boolean canMoveUp)
    {
        this.canMoveUp = canMoveUp;
    }

    public void setCanMoveDown(boolean canMoveDown)
    {
        this.canMoveDown = canMoveDown;
    }

    public abstract char renderFloor();
}
