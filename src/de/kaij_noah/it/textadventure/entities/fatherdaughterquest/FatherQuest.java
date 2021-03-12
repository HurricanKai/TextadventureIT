package de.kaij_noah.it.textadventure.entities.fatherdaughterquest;

import de.kaij_noah.it.textadventure.base.Icon;
import de.kaij_noah.it.textadventure.base.IconCache;
import de.kaij_noah.it.textadventure.entities.BaseEntity;
import de.kaij_noah.it.textadventure.math.Vector3I;

public final class FatherQuest extends BaseEntity
{
    private DaughterQuest daughter;
    private boolean questFinished;

    public FatherQuest(Vector3I position)
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
        if(questFinished)
        {
            return IconCache.Instance.getIcon("FatherDaughter");
        }
        else
        {
            return IconCache.Instance.getIcon("Father");
        }
    }

    public void setDaughter(DaughterQuest daughter)
    {
        this.daughter = daughter;
    }
}
