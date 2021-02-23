package de.kaij_noah.it.textadventure.tile.teleporter;

import de.kaij_noah.it.textadventure.base.*;
import de.kaij_noah.it.textadventure.math.Vector3I;
import de.kaij_noah.it.textadventure.math.Weighted;

import java.util.List;

public final class Teleporter extends Tile
{
    private static final String[] titleLines = new String[]
            {
                    "Ein uralter Teleporter. Du könntest ihn aktivieren, aber es ist ungewiss wohin er dich bringt.",
                    "Nur die richtung kannst du abschätzen."
            };
    private final Vector3I target;
    private final String directionString;

    public Teleporter(Vector3I target, String directionString, boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
        this.target = target;
        this.directionString = directionString;
    }

    @Override
    public void initialize(GameState gameState)
    {
        super.initialize(gameState);
        setAppearance(IconCache.Instance.getIcon("Teleporter"));
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
                return "Teleport " + directionString;
            }

            @Override
            public void Execute(GameState gameState)
            {
                gameState.getPlayerEntity().setPosition(target);
            }
        });
    }

    @Override
    public void addToReferences(List<Weighted<Vector3I>> references, Vector3I ownPosition)
    {
        super.addToReferences(references, ownPosition);
        references.add(new Weighted<>(2, target));
    }
}
