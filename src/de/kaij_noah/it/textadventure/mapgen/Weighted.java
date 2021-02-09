package de.kaij_noah.it.textadventure.mapgen;

public final class Weighted<T>
{
    private final double weight;
    private final T value;

    public Weighted(double weight, T value)
    {
        this.weight = weight;
        this.value = value;
    }

    public double getWeight()
    {
        return weight;
    }

    public T getValue()
    {
        return value;
    }
}
