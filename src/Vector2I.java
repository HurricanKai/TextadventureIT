public class Vector2I
{
    public int X;
    public int Y;

    public Vector2I(int x, int y)
    {
        X = x;
        Y = y;
    }

    public Vector2I copy()
    {
        return new Vector2I(X, Y);
    }
}
