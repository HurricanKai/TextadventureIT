public final class TitleLineRenderer implements IRenderable
{
    @Override
    public void Render(Console console, GameState gameState)
    {
        var titleLines = gameState.getTile().getTitleLines(gameState);

        for (var line : titleLines)
        {
            console.Write(line);
            console.NewLine();
        }
    }
}
