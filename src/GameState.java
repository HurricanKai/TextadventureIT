public class GameState
{
    private Vector2I position;
    private final Map map;

    public GameState(Vector2I position, Map map)
    {
        this.position = position;
        this.map = map;
    }

    public Tile getTile()
    {
        return map.get_tile(position.X, position.Y);
    }

    public Map getMap()
    {
        return map;
    }

    public Vector2I getPosition()
    {
        return position;
    }

    public void setPosition(Vector2I position)
    {
        this.position = position;
    }
}
