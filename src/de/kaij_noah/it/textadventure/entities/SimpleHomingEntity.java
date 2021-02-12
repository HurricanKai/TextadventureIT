package de.kaij_noah.it.textadventure.entities;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.Map;
import de.kaij_noah.it.textadventure.entities.base.IMapInitializable;
import de.kaij_noah.it.textadventure.math.Vector3I;
import de.kaij_noah.it.textadventure.pathfinding.lpastar.LPAStarPathFinder;

public final class SimpleHomingEntity extends BaseEntity implements IMapInitializable
{
    private LPAStarPathFinder pathFinder;
    private boolean hasBegun = false;

    public SimpleHomingEntity(Vector3I position)
    {
        super(position);
    }

    @Override
    public boolean hasDied()
    {
        return false;
    }

    @Override
    public char render()
    {
        return 'H';
    }

    @Override
    public void onStep(GameState gameState)
    {
        super.onStep(gameState);
        if (gameState.getTime() % 2 == 0 && pathFinder.hasNext())
        {
            var p = pathFinder.next();
            this.setPosition(p);
            gameState.getMap().getTile(p).setAppearance('E');
            System.out.printf("Moving to %s %s %s\n", p.X, p.Y, p.Z);
        }
    }

    @Override
    public void mapInitialize(Map map)
    {
        pathFinder = new LPAStarPathFinder(map);
        pathFinder.beginCalculation(this.getPosition(), new Vector3I(0, 0, 0));
    }
}
