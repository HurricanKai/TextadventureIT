package de.kaij_noah.it.textadventure.entities;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.Icon;
import de.kaij_noah.it.textadventure.base.Map;
import de.kaij_noah.it.textadventure.entities.base.IMapInitializable;
import de.kaij_noah.it.textadventure.entities.base.IOptionsInitializable;
import de.kaij_noah.it.textadventure.math.Vector3I;
import de.kaij_noah.it.textadventure.options.GameOptions;
import de.kaij_noah.it.textadventure.pathfinding.lpastar.LPAStarPathFinder;

public final class SimpleHomingEntity extends BaseEntity implements IMapInitializable, IOptionsInitializable
{
    private LPAStarPathFinder pathFinder;
    private Icon icon;

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
    public Icon render()
    {
        return icon;
    }

    @Override
    public void onStep(GameState gameState)
    {
        super.onStep(gameState);
        if (gameState.getTime() % 10 == 0 && pathFinder.hasNext())
        {
            var p = pathFinder.next();
            this.setPosition(p);
        }
    }

    @Override
    public void mapInitialize(Map map)
    {
        pathFinder = new LPAStarPathFinder(map);
        pathFinder.beginCalculation(this.getPosition(), new Vector3I(0, 0, 0));
    }

    @Override
    public void optionsInitialize(GameOptions options)
    {
        icon = Icon.DebugIcon;
    }
}
