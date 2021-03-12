package de.kaij_noah.it.textadventure.entities.fatherdaughterquest;


import de.kaij_noah.it.textadventure.base.Icon;
import de.kaij_noah.it.textadventure.base.IconCache;
import de.kaij_noah.it.textadventure.entities.BaseEntity;
import de.kaij_noah.it.textadventure.math.Vector3I;

public final class DaughterQuest extends BaseEntity
{
   private FatherQuest father;
   private boolean waiting = true;

    public DaughterQuest(Vector3I position, FatherQuest father)
    {
        super(position);
        this.father = father;
    }

    @Override
    public boolean hasDied()
    {
        return false;
    }

    @Override
    public Icon render()
    {
        if (waiting)
        {
            return IconCache.Instance.getIcon("Daughter");
        }
        else
        {
            return IconCache.Instance.getIcon("Chair");
        }
    }
}
