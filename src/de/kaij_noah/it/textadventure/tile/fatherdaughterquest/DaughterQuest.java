package de.kaij_noah.it.textadventure.tile.fatherdaughterquest;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IAction;
import de.kaij_noah.it.textadventure.base.Tile;

import java.util.List;

public final class DaughterQuest extends Tile
{
    private final static String[] titleLine = new String[]
            {
                    "Entschuldigt, könnt ihr mir helfen? Ich habe mich verirrt! *schluchz*",
            };
    private final static String[] daughterAwayTitleLineFatherQuest = new String[]
            {
                    "Hier habe ich die Tochter eines Mannes gefunden."
            };
    private final static String[] daughterAwayTitleLineNoFatherQuest = new String[]
            {
                    "Hier habe ich ein Mädchen gefunden."
            };
    private boolean daughterAway = false;
    private boolean fatherQuestStarted = false;

    public DaughterQuest(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
    }

    @Override
    public void onStep(GameState gameState)
    {
        super.onStep(gameState);
        fatherQuestStarted = gameState.getState("fatherQuestStarted");
        if (daughterAway)
        {
            setAppearance('р');
        } else
        {
            setAppearance('Р');
        }
    }

    @Override
    public String[] getTitleLines(GameState gameState)
    {
        if (daughterAway)
        {
            if ((boolean) gameState.getState("fatherQuestStarted") ||
                    (boolean) gameState.getState("fatherQuestFinished"))
            {
                return daughterAwayTitleLineFatherQuest;
            } else
            {
                return daughterAwayTitleLineNoFatherQuest;
            }
        } else
        {
            return titleLine;
        }
    }

    @Override
    protected void addToPossibleActions(List<IAction> list)
    {
        super.addToPossibleActions(list);

        if (daughterAway)
            return;
        else
        {
            if (fatherQuestStarted)
            {
                list.add(new IAction()
                {
                    @Override
                    public String getDescription()
                    {
                        return "Hallo. Ich habe euren Vater getroffen. Er ist sehr besorgt um euch. Begleitet mich!";       //todo Antwort
                    }

                    @Override
                    public void Execute(GameState gameState)
                    {
                        gameState.setState("hasDaughter", true);
                        daughterAway = true;
                    }
                });
            } else
            {
                list.add(new IAction()
                {
                    @Override
                    public String getDescription()
                    {
                        return "Ja, kommt mit mir. Ich werde auf euch aufpassen.";       //todo Antwort
                    }

                    @Override
                    public void Execute(GameState gameState)
                    {
                        gameState.setState("hasDaughter", true);
                        daughterAway = true;
                    }
                });
            }
        }
    }
}
