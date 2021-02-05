public class GameState
{
    private Vector2I position;
    private final Map map;
    private int timeStep;
    private float health;

    public GameState(Vector2I position, Map map)
    {
        this.position = position;
        this.map = map;
        this.health = 1.0f;
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
        // for safety reasons, we return a copy
        return position.copy();
    }

    public int getTimeStep() { return timeStep; }

    public void incrementTimeStep() { timeStep++; }

    public void setPosition(Vector2I position)
    {
        getTile().onPlayerExit(this);
        this.position = position;
        getTile().onPlayerEnter(this);
    }

    public float getHealth() { return health; }

    public void setHealth(float health)
    {
        this.health = health;
    }

    public void onStep()
    {
        var tiles = map.get_tiles();
        for (Tile[] v : tiles)
        {
            for (Tile tile : v)
                tile.onStep(this);
        }
    }
}
