public class EmptyTileGenerator implements ITileGenerator
{
    @Override
    public Tile Generate(int x, int y)
    {
        return new Tile()
        {
            @Override
            public char renderFloor()
            {
                return '░';
            }
        };
    }
}
