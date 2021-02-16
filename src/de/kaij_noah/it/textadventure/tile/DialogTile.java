package de.kaij_noah.it.textadventure.tile;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.IAction;
import de.kaij_noah.it.textadventure.base.IEntity;
import de.kaij_noah.it.textadventure.base.Tile;
import de.kaij_noah.it.textadventure.entities.PlayerEntity;

import java.util.ArrayList;

public abstract class DialogTile extends Tile
{
    private static final String[] emptyTitleLines = new String[0];
    private final DialogNode entryDialogNode;
    protected boolean isFirstVisit = true;
    private DialogNode currentDialog;
    private final DialogNode rootDialog;
    private DialogNode tempEntryStore;

    public DialogTile(boolean canMoveWest, boolean canMoveEast, boolean canMoveNorth, boolean canMoveSouth)
    {
        super(canMoveWest, canMoveEast, canMoveNorth, canMoveSouth);
        var builder = new DialogBuilder();
        buildDialog(builder);
        rootDialog = builder.Build();

        var entryLines = getEntryLines();
        if (entryLines != null)
        {
            entryDialogNode = new DialogNode(rootDialog.Appearance, entryLines, new IAction[]{
                    new IAction()
                    {
                        @Override
                        public String getDescription()
                        {
                            return "Gespräch fortsetzen";
                        }

                        @Override
                        public void Execute(GameState gameState)
                        {
                            currentDialog = tempEntryStore;
                            tempEntryStore = null;
                        }
                    }
            });
        } else
        {
            entryDialogNode = null;
        }
    }

    private void setCurrentDialog(DialogNode dialogNode)
    {
        setAppearance(dialogNode.Appearance);
        currentDialog = dialogNode;
    }

    @Override
    public void initialize(GameState gameState)
    {
        super.initialize(gameState);
        setCurrentDialog(rootDialog);
    }

    @Override
    public String[] getTitleLines(GameState gameState)
    {
        return currentDialog.TitleLines;
    }

    @Override
    public IAction[] getPossibleActions()
    {
        return currentDialog.Actions;
    }

    @Override
    public void onEntityEnter(IEntity entity, GameState gameState)
    {
        super.onEntityEnter(entity, gameState);
        if (entity instanceof PlayerEntity)
        {
            if (!isFirstVisit && entryDialogNode != null)
            {
                tempEntryStore = currentDialog;
                setCurrentDialog(entryDialogNode);
            }
        }
    }

    @Override
    public void onEntityExit(IEntity entity, GameState gameState)
    {
        super.onEntityExit(entity, gameState);
        if (entity instanceof PlayerEntity)
        {
            isFirstVisit = false;
            if (tempEntryStore != null)
            {
                setCurrentDialog(currentDialog);
                tempEntryStore = null;
            }
        }
    }

    protected abstract void buildDialog(DialogBuilder dialogBuilder);

    protected String[] getEntryLines()
    {
        return null;
    }

    // poor^H^H^H^H java mans Action<GameState, DialogBuilder>
    protected interface IDialogBuilderAction
    {
        void Invoke(DialogBuilder dialogBuilder);
    }

    // same for Action<DialogBuilder>
    protected interface ITileAction
    {
        void Invoke(GameState gameState);
    }

    private final class DialogNode
    {
        public final char Appearance;
        public final String[] TitleLines;
        public final IAction[] Actions;

        public DialogNode(char appearance, String[] titleLines, IAction[] actions)
        {
            Appearance = appearance;
            TitleLines = titleLines;
            Actions = actions;
        }
    }

    protected final class DialogBuilder
    {
        private final ArrayList<DialogBuilder> children = new ArrayList<>();
        private String description = null;
        private ITileAction action;
        private char appearance = '\u0000';
        private String[] titleLines = emptyTitleLines;

        public DialogBuilder ChangeAppearance(char appearance)
        {
            this.appearance = appearance;
            return this;
        }

        public DialogBuilder ChangeTitleLines(String[] titleLines)
        {
            this.titleLines = titleLines;
            return this;
        }

        public DialogBuilder AddOption(String option, IDialogBuilderAction dialogAction, ITileAction tileAction)
        {
            var child = new DialogBuilder();
            child.description = option;
            child.action = tileAction;
            if (dialogAction != null)
            {
                dialogAction.Invoke(child);
            }

            children.add(child);

            return this;
        }


        private DialogNode Build()
        {
            var actions = new IAction[children.size()];
            for (int i = 0; i < actions.length; i++)
            {
                var childBuilder = children.get(i);
                if (childBuilder.appearance == '\u0000')
                    childBuilder.appearance = this.appearance;
                var childNode = childBuilder.Build();
                actions[i] = new IAction()
                {
                    @Override
                    public String getDescription()
                    {
                        return childBuilder.description;
                    }

                    @Override
                    public void Execute(GameState gameState)
                    {
                        setCurrentDialog(childNode);
                        if (childBuilder.action != null)
                        {
                            childBuilder.action.Invoke(gameState);
                        }
                    }
                };
            }

            return new DialogNode(appearance, titleLines, actions);
        }
    }
}
