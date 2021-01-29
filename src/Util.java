public class Util
{
    public static int Index2D(int x, int y, int maxx, int maxy)
    {
        return (maxx * x) + y;
    }

    public static int GetY(int i, int maxx, int maxy)
    {
        return i % maxx;
    }

    public static int GetX(int i, int maxx, int maxy)
    {
        return i / maxx;
    }
}
