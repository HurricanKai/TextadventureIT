public class Vector3I
{
    public int X;
    public int Y;
    public int Z;

    public Vector3I(int x, int y, int z)
    {
        X = x;
        Y = y;
        Z = z;
    }

    public Vector3I copy()
    {
        return new Vector3I(X, Y, Z);
    }
}
