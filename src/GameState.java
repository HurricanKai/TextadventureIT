import java.util.Dictionary;
import java.util.Hashtable;

public class GameState
{
    private Vector3I position;
    private final Map map;
    private int timeStep;
    private Dictionary<String, Object> customState = new Hashtable<>();

    public GameState(Vector3I position, Map map)
    {
        this.position = position;
        this.map = map;
        putState("health", 1.0f);
    }

    public Tile getTile()
    {
        return map.get_tile(position.X, position.Y, position.Z);
    }

    public Map getMap()
    {
        return map;
    }

    public Vector3I getPosition()
    {
        // for safety reasons, we return a copy
        return position.copy();
    }

    public int getTimeStep() { return timeStep; }

    public void incrementTimeStep() { timeStep++; }

    public void setPosition(Vector3I position)
    {
        getTile().onPlayerExit(this);
        this.position = position;
        getTile().onPlayerEnter(this);
    }

    public void onStep()
    {
        var tiles = map.get_tiles();
        for (Tile[][] v : tiles)
        {
            for (Tile[] v2 : v)
                for (Tile tile : v2)
                    tile.onStep(this);
        }
    }

    public <T> void putState(String key, T value)
    {
        customState.put(key, value);
    }

    public <T> T getState(String key)
    {
        return (T) customState.get(key);
    }
}
