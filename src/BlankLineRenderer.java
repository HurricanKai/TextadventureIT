public final class BlankLineRenderer implements IRenderable
{
    @Override
    public void Render(Console console, GameState gameState)
    {
        console.NewLine();
    }
}
