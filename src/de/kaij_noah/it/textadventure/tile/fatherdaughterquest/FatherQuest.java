package de.kaij_noah.it.textadventure.tile.fatherdaughterquest;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IAction;
import de.kaij_noah.it.textadventure.base.IconCache;
import de.kaij_noah.it.textadventure.base.Tile;

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
    private boolean fatherQuestFinished = false;
    private boolean hasDaughter = false;
    private boolean fatherQuestStarted = false;

    public FatherQuest(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
    }

    @Override
    public void initialize(GameState gameState)
    {
        super.initialize(gameState);
        gameState.setState("fatherQuestStarted", false);
        gameState.setState("fatherQuestFinished", false);
        gameState.setState("hasDaughter", false);
    }

    @Override
    public void onStep(GameState gameState)
    {
        super.onStep(gameState);
        fatherQuestFinished = gameState.getState("fatherQuestFinished");
        hasDaughter = gameState.getState("hasDaughter");
        fatherQuestStarted = gameState.getState("fatherQuestStarted");
        if (fatherQuestFinished)
        {
            setAppearance(IconCache.Instance.getIcon("FatherDaughter"));
        }
        else
        {
            setAppearance(IconCache.Instance.getIcon("Father"));
        }
    }

    @Override
    public String[] getTitleLines(GameState gameState)
    {
        if ((boolean) gameState.getState("fatherQuestFinished"))
        {
            return questFinishedTitleLines;
        } else
        {
            if ((boolean) gameState.getState("hasDaughter"))
            {
                return daughterFoundTitleLines;
            } else
            {
                if ((boolean) gameState.getState("fatherQuestStarted"))
                {
                    return daughterNotFoundTitleLines;
                } else
                {
                    return questStartTitleLines;
                }
            }
        }
    }

    @Override
    protected void addToPossibleActions(List<IAction> list)
    {
        super.addToPossibleActions(list);

        if (!fatherQuestFinished)
        {
            if (hasDaughter)
            {
                if (fatherQuestStarted)
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
                            gameState.setState("fatherQuestFinished", true);
                            gameState.setState("hasDaughter", false);
                        }
                    });
                } else
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
                            gameState.setState("fatherQuestFinished", true);
                            gameState.setState("hasDaughter", false);
                        }
                    });
                }
            } else
            {
                if (!fatherQuestStarted)
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
                            gameState.setState("fatherQuestStarted", true);
                        }
                    });
                }
            }
        }
    }
}
