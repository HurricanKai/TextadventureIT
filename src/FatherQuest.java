import java.util.List;

public final class FatherQuest extends Tile
{
    private static final String[] questStartTitleLines = new String[]
            {
                    "Hallo Fremder.",
                    "Habt ihr meine Tochter gesehen? Sie ist spurlos verschwunden.",
                    "Bitte sucht sie. Sie ist doch noch ein Kind. *schluchtz* ",
            };

    private static final String[] questFinishedTitleLines = new String[]
            {
                    "Vielen Dank Fremder!",
            };

    private static final String[] daughterFoundTitleLines = new String[]
            {
                    "EMILIA! Mein Kind!",
                    "Ihr habt meine Tochter gefunden.",
                    "Ich danke euch!",
            };

    private static final String[] daughterNotFoundTitleLines = new String[]
            {
                    "Vielleicht ist ihr etwas zugestoßen.",
                    "Bitte findet sie!",
            };

    @Override
    public void initialize(GameState gameState)
    {
        super.initialize(gameState);
        gameState.putState("fatherQuestStarted", false);
        gameState.putState("fatherQuestFinished", false);
        gameState.putState("hasDaughter", false);

    }

    @Override
    public char renderFloor(GameState gameState)
    {
        if ((boolean) gameState.getState("fatherQuestFinished"))
        {
            return 'а';
        }
        else
        {
            return 'А';
        }
    }

    @Override
    public String[] getTitleLines(GameState gameState)
    {
        if ((boolean) gameState.getState("fatherQuestFinished"))
        {
            return questFinishedTitleLines;
        }
        else
        {
            if ((boolean) gameState.getState("hasDaughter"))
            {
                return daughterFoundTitleLines;
            }
            else
            {
                if ((boolean) gameState.getState("fatherQuestStarted"))
                {
                    return daughterNotFoundTitleLines;
                }
                else
                {
                    return questStartTitleLines;
                }
            }
        }
    }

    @Override
    protected void addToPossibleActions(List<IAction> list, GameState gameState)
    {
        super.addToPossibleActions(list, gameState);

        if ((boolean) gameState.getState("fatherQuestFinished"))
        {

        }
        else
        {
            if ((boolean) gameState.getState("hasDaughter"))
            {
                if ((boolean) gameState.getState("fatherQuestStarted"))
                {
                    list.add(new IAction()
                    {
                        @Override
                        public String getDescription()
                        {
                            return "Ich habe gerne geholfen. Nun lebt wohl.";
                        }

                        @Override
                        public void Execute(GameState gameState)
                        {
                            gameState.putState("fatherQuestFinished", true);
                            gameState.putState("hasDaughter", false);
                        }
                    });
                }
                else
                {
                    list.add(new IAction()
                    {
                        @Override
                        public String getDescription()
                        {
                            return "Das ist eure Tochter? Passt in Zukunft besser auf sie auf!";
                        }

                        @Override
                        public void Execute(GameState gameState)
                        {
                            gameState.putState("fatherQuestFinished", true);
                            gameState.putState("hasDaughter", false);
                        }
                    });
                }
            }
            else
            {
                if ((boolean) gameState.getState("fatherQuestStarted"))
                {

                }
                else
                {
                    list.add(new IAction()
                    {
                        @Override
                        public String getDescription()
                        {
                            return "Ich werde nach eurer Tochter Ausschau halten!";
                        }

                        @Override
                        public void Execute(GameState gameState)
                        {
                            gameState.putState("fatherQuestStarted", true);
                        }
                    });
                }
            }
        }
    }
}
