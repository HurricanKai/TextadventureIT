public class TestComponent implements IComponent
{

    @Override
    public String[] getLines(GameState gameState)
    {
        return new String[]
                {
                    "test1", "Ein ganz langer test text"
                };
    }
}
