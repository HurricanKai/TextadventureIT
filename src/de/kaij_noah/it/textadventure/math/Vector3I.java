package de.kaij_noah.it.textadventure.math;

import java.util.Objects;

public final class Vector3I
{
    public int X;
    public int Y;
    public int Z;

    public Vector3I(int x, int y, int z)
    {
        X = x;
        Y = y;
        Z = z;
    }

    public Vector3I copy()
    {
        return new Vector3I(X, Y, Z);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3I vector3I = (Vector3I) o;
        return X == vector3I.X && Y == vector3I.Y && Z == vector3I.Z;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(X, Y, Z);
    }
}
