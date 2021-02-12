package de.kaij_noah.it.textadventure.base;

import de.kaij_noah.it.textadventure.entities.EntityManager;
import de.kaij_noah.it.textadventure.entities.PlayerEntity;

import java.util.Dictionary;
import java.util.Hashtable;

public final class GameState
{
    private final PlayerEntity playerEntity;
    private final Map map;
    private final Dictionary<String, Object> states = new Hashtable<>();
    private final EntityManager entityManager;
    private int time = 0;

    public GameState(PlayerEntity playerEntity, Map map, EntityManager entityManager)
    {
        this.playerEntity = playerEntity;
        this.map = map;
        this.entityManager = entityManager;
    }

    public PlayerEntity getPlayerEntity()
    {
        return playerEntity;
    }

    public Map getMap()
    {
        return map;
    }

    public <T> T getState(String key)
    {
        return (T) states.get(key);
    }

    public <T> void setState(String key, T value)
    {
        states.put(key, value);
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public void incrementTime()
    {
        time++;
    }
    public int getTime() { return time; }

    public void onStep()
    {
        map.onStep(this);
        entityManager.onStep(this);
        System.out.printf("Step %s\n", time);
    }
}
