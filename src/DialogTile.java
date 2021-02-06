import java.util.ArrayList;

public abstract class DialogTile extends Tile
{
    private static final String[] emptyTitleLines = new String[0];

    private DialogNode currentDialog;
    private DialogNode rootDialog;
    private DialogNode tempEntryStore;
    private final DialogNode entryDialogNode;

    public DialogTile()
    {
        var builder = new DialogBuilder();
        buildDialog(builder);
        rootDialog = builder.Build();
        currentDialog = rootDialog;

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
                        }
                    }
            });
        }
        else
        {
            entryDialogNode = null;
        }
    }

    @Override
    public char renderFloor(GameState gameState)
    {
        return currentDialog.Appearance;
    }

    @Override
    public String[] getTitleLines(GameState gameState)
    {
        return currentDialog.TitleLines;
    }

    @Override
    public IAction[] getPossibleActions(GameState gameState)
    {
        return currentDialog.Actions;
    }

    @Override
    public void onPlayerEnter(GameState gameState)
    {
        super.onPlayerEnter(gameState);
        if (entryDialogNode != null)
        {
            tempEntryStore = currentDialog;
            currentDialog = entryDialogNode;
        }
    }

    protected abstract void buildDialog(DialogBuilder dialogBuilder);
    protected String[] getEntryLines() { return null; }

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

    protected final class DialogBuilder
    {
        private final ArrayList<DialogBuilder> children = new ArrayList<>();
        private String description = null;
        private ITileAction action;
        private char appearance = ' ';
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
                        currentDialog = childNode;
                        childBuilder.action.Invoke(gameState);
                    }
                };
            }

            return new DialogNode(appearance, titleLines, actions);
        }
    }
}
