package de.kaij_noah.it.textadventure.tile.special;

import de.kaij_noah.it.textadventure.base.*;
import de.kaij_noah.it.textadventure.math.Vector3I;
import de.kaij_noah.it.textadventure.math.Weighted;

import java.util.List;

public final class StairUpTile extends Tile
{
    private static final String[] titleLines = new String[0];

    public StairUpTile(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
    }

    @Override
    public void initialize(GameState gameState)
    {
        super.initialize(gameState);
        setAppearance(IconCache.Instance.getIcon("StairUp"));
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
                return "Geh die treppe hinauf";
            }

            @Override
            public void Execute(GameState gameState)
            {
                var pos = gameState.getPlayerEntity().getPosition();
                pos.Z++;
                gameState.getPlayerEntity().setPosition(pos);
            }
        });
    }

    @Override
    public void addToReferences(List<Weighted<Vector3I>> references, Vector3I ownPosition)
    {
        super.addToReferences(references, ownPosition);
        var p = ownPosition.copy();
        p.Z++;
        references.add(new Weighted<>(10, p));
    }
}
