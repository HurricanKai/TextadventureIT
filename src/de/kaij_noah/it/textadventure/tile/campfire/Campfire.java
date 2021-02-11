package de.kaij_noah.it.textadventure.tile.campfire;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IAction;
import de.kaij_noah.it.textadventure.tile.AnimatedTile;

import java.util.List;

public final class Campfire extends AnimatedTile
{
    private final char[] possibleTiles = new char[]
            {
                    'a',
                    'b'
            };
    private final String[] titleLine = new String[]
            {
                    "Ein Lagerfeuer, es ist schön hier zu sitzen."
            };

    public Campfire(int startIndex, boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
        setStartIndex(startIndex);
    }

    @Override
    protected char[] getPossibleTiles()
    {
        return possibleTiles;
    }

    @Override
    public String[] getTitleLines(GameState gameState)
    {
        return titleLine;
    }

    @Override
    protected void addToPossibleActions(List<IAction> list)
    {
        super.addToPossibleActions(list);
        list.add(new IAction()
        {
            @Override
            public String getDescription()
            {
                return "Ausruhen";
            }

            @Override
            public void Execute(GameState gameState)
            {
                gameState.getPlayerEntity().setHealth(1f);
            }
        });
    }
}
