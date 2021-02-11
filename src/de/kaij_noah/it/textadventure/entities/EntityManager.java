package de.kaij_noah.it.textadventure.entities;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IEntity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public final class EntityManager
{
    private ArrayList<IEntity> entities = new ArrayList<>();

    public <T extends IEntity> Stream<T> getAll(Class<T> tClass)
    {
        return entities.stream().filter(b -> tClass.isAssignableFrom(b.getClass())).map(b -> (T)b);
    }

    public Stream<IEntity> getAll()
    {
        return entities.stream();
    }

    public <T extends IEntity> T addEntity(T entity)
    {
        entities.add(entity);
        return entity;
    }

    public void onStep(GameState gameState)
    {
        var iter = entities.iterator();
        while(iter.hasNext())
        {
            var e = iter.next();
            if (e.hasDied())
            {
                iter.remove();
                continue;
            }

            e.onStep(gameState);
        }
    }
}
