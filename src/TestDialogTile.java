import java.awt.*;

public final class TestDialogTile extends DialogTile
{
    @Override
    protected void buildDialog(DialogBuilder dialogBuilder)
    {
        dialogBuilder.ChangeAppearance('X');

        dialogBuilder.AddOption("1", (b1) -> b1
                        .AddOption("1-1", (b11) -> b11
                                        .AddOption("1-1-1", b -> b.ChangeTitleLines(new String[] { "Final 1-1-1"}), null)
                                        .AddOption("1-1-2", b -> b.ChangeTitleLines(new String[] { "Final 1-1-2"}), null)
                                        .AddOption("1-1-3", b -> b.ChangeTitleLines(new String[] { "Final 1-1-3"}), null)
                                , null
                        )
                        .AddOption("1-2", (b12) -> b12
                                        .AddOption("1-2-1", b -> b.ChangeTitleLines(new String[] { "Final 1-2-1"}), null)
                                        .AddOption("1-2-2", b -> b.ChangeTitleLines(new String[] { "Final 1-2-2"}), null)
                                        .AddOption("1-2-3", b -> b.ChangeTitleLines(new String[] { "Final 1-2-3"}), null)
                                , null
                        )
                        .AddOption("1-3", (b13) -> b13
                                        .AddOption("1-3-1", b -> b.ChangeTitleLines(new String[] { "Final 1-3-1"}), null)
                                        .AddOption("1-3-3", b -> b.ChangeTitleLines(new String[] { "Final 1-3-2"}), null)
                                        .AddOption("1-3-2", b -> b.ChangeTitleLines(new String[] { "Final 1-3-3"}), null)
                                , null
                        )
                , null
        );

        dialogBuilder.AddOption("2", (b1) -> b1
                        .AddOption("2-1", (b11) -> b11
                                        .AddOption("2-1-1", b -> b.ChangeTitleLines(new String[] { "Final 2-1-1"}), null)
                                        .AddOption("2-1-2", b -> b.ChangeTitleLines(new String[] { "Final 2-1-2"}), null)
                                        .AddOption("2-1-3", b -> b.ChangeTitleLines(new String[] { "Final 2-1-3"}), null)
                                , null
                        )
                        .AddOption("2-2", (b12) -> b12
                                        .AddOption("2-2-1", b -> b.ChangeTitleLines(new String[] { "Final 2-2-1"}), null)
                                        .AddOption("2-2-2", b -> b.ChangeTitleLines(new String[] { "Final 2-2-2"}), null)
                                        .AddOption("2-2-3", b -> b.ChangeTitleLines(new String[] { "Final 2-2-3"}), null)
                                , null
                        )
                        .AddOption("2-3", (b13) -> b13
                                        .AddOption("2-3-1", b -> b.ChangeTitleLines(new String[] { "Final 2-3-1"}), null)
                                        .AddOption("2-3-2", b -> b.ChangeTitleLines(new String[] { "Final 2-3-2"}), null)
                                        .AddOption("2-3-3", b -> b.ChangeTitleLines(new String[] { "Final 2-3-3"}), null)
                                , null
                        )
                , null
        );

        dialogBuilder.AddOption("3", (b1) -> b1
                        .AddOption("3-1", (b11) -> b11
                                        .AddOption("3-1-1", b -> b.ChangeTitleLines(new String[] { "Final 3-1-1"}), null)
                                        .AddOption("3-1-2", b -> b.ChangeTitleLines(new String[] { "Final 3-1-2"}), null)
                                        .AddOption("3-1-3", b -> b.ChangeTitleLines(new String[] { "Final 3-1-3"}), null)
                                , null
                        )
                        .AddOption("3-2", (b12) -> b12
                                        .AddOption("3-2-1", b -> b.ChangeTitleLines(new String[] { "Final 3-2-1"}), null)
                                        .AddOption("3-2-2", b -> b.ChangeTitleLines(new String[] { "Final 3-2-2"}), null)
                                        .AddOption("3-2-3", b -> b.ChangeTitleLines(new String[] { "Final 3-2-3"}), null)
                                , null
                        )
                        .AddOption("3-3", (b13) -> b13
                                        .AddOption("3-3-1", b -> b.ChangeTitleLines(new String[] { "Final 3-3-1"}), null)
                                        .AddOption("3-3-2", b -> b.ChangeTitleLines(new String[] { "Final 3-3-2"}), null)
                                        .AddOption("3-3-3", b -> b.ChangeTitleLines(new String[] { "Final 3-3-3"}), null)
                                , null
                        )
                , null
        );
    }

    private static final String[] entryLines = new String[]
            {
                    "Welcome back to test tile"
            };
    @Override
    protected String[] getEntryLines()
    {
        return entryLines;
    }
}
