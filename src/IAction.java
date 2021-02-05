public interface IAction
{
    String getDescription();
    void Execute(GameState gameState);
}
