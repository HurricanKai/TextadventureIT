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
        return position;
    }

    public int getTimeStep() { return timeStep; }

    public void incrementTimeStep() { timeStep++; }

    public void setPosition(Vector2I position)
    {
        this.position = position;
    }

    public float getHealth() { return health; }

    public void setHealth(float health)
    {
        this.health = health;
    }
}
