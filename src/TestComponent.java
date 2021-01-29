public class TestComponent implements IComponent
{

    @Override
    public String[] getLines()
    {
        return new String[]
                {
                    "test1", "Ein ganz langer test text"
                };
    }
}
