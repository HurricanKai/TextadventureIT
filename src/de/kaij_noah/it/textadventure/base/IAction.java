package de.kaij_noah.it.textadventure.base;

public interface IAction
{
    String getDescription();

    void Execute(GameState gameState);      //todo name conventions kontrollieren
}
