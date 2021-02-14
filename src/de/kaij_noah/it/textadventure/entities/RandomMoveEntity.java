package de.kaij_noah.it.textadventure.entities;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.math.Vector3I;
import de.kaij_noah.it.textadventure.tile.special.StairDownTile;
import de.kaij_noah.it.textadventure.tile.special.StairUpTile;

import java.util.ArrayList;

public abstract class RandomMoveEntity extends BaseEntity
{
    private ArrayList<Vector3I> dirs = new ArrayList<>();

    public RandomMoveEntity(Vector3I position)
    {
        super(position);
    }

    @Override
    public void onStep(GameState gameState)
    {
        super.onStep(gameState);
        var pos = this.getPosition();

        {
            var p = pos.copy();
            p.X--;
            if (p.X >= 0)
                dirs.add(p);
        }

        {
            var p = pos.copy();
            p.Y--;
            if (p.Y >= 0)
                dirs.add(p);

        }

        {
            var p = pos.copy();
            p.X++;
            if (p.X < gameState.getMap().getSizeX())
                dirs.add(p);
        }

        {
            var p = pos.copy();
            p.Y++;
            if (p.Y < gameState.getMap().getSizeY())
                dirs.add(p);
        }

        var t = gameState.getMap().getTile(pos);
        if (t instanceof StairDownTile)
        {
            var p = pos.copy();
            p.Z--;
            dirs.add(p);
        }

        if (t instanceof StairUpTile)
        {
            var p = pos.copy();
            p.Z++;
            dirs.add(p);
        }

        this.setPosition(dirs.get(gameState.getRandom().nextInt(dirs.size())));
    }
}
