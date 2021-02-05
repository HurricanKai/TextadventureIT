public class DebugDisplay implements IRenderable
{
    private int frameCount = 0;

    @Override
    public void Render(Console console, GameState gameState)
    {
        console.Write(String.format("Frame: %s\n", frameCount++));
    }
}
