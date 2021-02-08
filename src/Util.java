public class Util
{
    public static int Index2D(int x, int y, int z, int maxx, int maxy, int maxz)
    {
        return x + y * maxx + z * maxx * maxy;
    }

    public static int GetY(int i, int maxx, int maxy, int maxz)
    {
        return ( i / maxx ) % maxy;
    }

    public static int GetX(int i, int maxx, int maxy, int maxz)
    {
        return i % maxx;
    }

    public static int GetZ(int i, int maxx, int maxy, int maxz)
    {
        return i / (maxx * maxy);
    }
}
