package de.kaij_noah.it.textadventure.base;

import de.kaij_noah.it.textadventure.math.Vector3I;

public interface IEntity
{
    Vector3I getPosition();
    void setPosition(Vector3I position);
    boolean hasDied();
    void onStep(GameState gameState);
    Icon render();
}
