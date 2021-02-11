package de.kaij_noah.it.textadventure.tile.special;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IAction;
import de.kaij_noah.it.textadventure.base.Tile;

import java.util.List;

public final class StairDownTile extends Tile
{
    private static final String[] titleLines = new String[0];

    public StairDownTile(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
        setAppearance('d');
    }

    @Override
    public String[] getTitleLines(GameState gameState)
    {
        return titleLines;
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
                return "Geh die treppe hinunter";
            }

            @Override
            public void Execute(GameState gameState)
            {
                var pos = gameState.getPlayerEntity().getPosition();
                pos.Z--;
                gameState.getPlayerEntity().setPosition(pos);
            }
        });
    }
}
