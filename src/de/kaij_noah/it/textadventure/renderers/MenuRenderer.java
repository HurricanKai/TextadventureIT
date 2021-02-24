package de.kaij_noah.it.textadventure.renderers;

import de.kaij_noah.it.textadventure.base.*;
import de.kaij_noah.it.textadventure.base.Map;
import de.kaij_noah.it.textadventure.entities.PlayerEntity;

import java.util.*;

public final class MenuRenderer implements IRenderer, IMenuManager
{
    private final ArrayList<IMenuDisplay> displays;
    private final Map map;
    private final PlayerEntity playerEntity;

    public MenuRenderer(Map map, PlayerEntity playerEntity)
    {
        this.map = map;
        this.playerEntity = playerEntity;
        displays = new ArrayList<>();
    }

    @Override
    public void render(IConsole console, GameState gameState)
    {
        int maxLineLength = 0;
        int spaceWidth = console.getCharWidth(' ');
        var actions = map.getTile(playerEntity.getPosition()).getPossibleActions();

        for (IMenuDisplay display : displays)
        {
            String[] lines = display.getLines();
            for (int i = 0; i < lines.length; i++)
            {
                maxLineLength = Math.max(console.getStringWidth(lines[i]), maxLineLength);

            }
        }

        int actionIndex = 0;
        int displayIndex = 0;
        int lineIndex = 0;

        do
        {
            int linesLength = 0;
            int lineWidth = 0;
            if (displayIndex < displays.size())
            {
                String[] lines = displays.get(displayIndex).getLines();
                linesLength = lines.length;

                if (lines.length > 0)
                {
                    lineWidth = console.getStringWidth(lines[lineIndex]);
                    console.write(lines[lineIndex]);
                }
            }
            int amountSpaces = (maxLineLength - lineWidth) / spaceWidth;

            for (int j = 0; j < amountSpaces; j++)
            {
                console.write(" ");
            }

            if (lineWidth % 2 == 0)
            {
                console.write(" ");
            }

            console.write(" | ");


            if (actionIndex < actions.length)
            {
                console.write(+(actionIndex + 1) + ": ");
                console.write(actions[actionIndex].getDescription());
                actionIndex++;
            }

            console.newLine();

            lineIndex++;
            if (lineIndex >= linesLength)
            {
                displayIndex++;
                lineIndex = 0;
            }

        } while (actionIndex < actions.length || displayIndex < displays.size());
    }

    public void keyTyped(char c, GameState gameState)
    {
        var possibleActions = map.getTile(playerEntity.getPosition()).getPossibleActions();
        int index = -1;
        switch (c)
        {
            case '1':
                index = 0;
                break;
            case '2':
                index = 1;
                break;
            case '3':
                index = 2;
                break;
            case '4':
                index = 3;
                break;
            case '5':
                index = 4;
                break;
            case '6':
                index = 5;
                break;
            case '7':
                index = 6;
                break;
            case '8':
                index = 7;
                break;
            case '9':
                index = 8;
                break;
        }
        if (index < 0 || index >= possibleActions.length)
            return;
        possibleActions[index].Execute(gameState);
    }

    @Override
    public int size()
    {
        return displays.size();
    }

    @Override
    public boolean isEmpty()
    {
        return displays.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return displays.contains(o);
    }

    @Override
    public Iterator<IMenuDisplay> iterator()
    {
        return displays.iterator();
    }

    @Override
    public Object[] toArray()
    {
        return displays.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return displays.toArray(a);
    }

    @Override
    public boolean add(IMenuDisplay iMenuDisplay)
    {
        return displays.add(iMenuDisplay);
    }

    @Override
    public boolean remove(Object o)
    {
        return displays.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return displays.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends IMenuDisplay> c)
    {
        return displays.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends IMenuDisplay> c)
    {
        return displays.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return displays.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return displays.retainAll(c);
    }

    @Override
    public void clear()
    {
        displays.clear();
    }

    @Override
    public IMenuDisplay get(int index)
    {
        return displays.get(index);
    }

    @Override
    public IMenuDisplay set(int index, IMenuDisplay element)
    {
        return displays.set(index, element);
    }

    @Override
    public void add(int index, IMenuDisplay element)
    {
        displays.add(index, element);
    }

    @Override
    public IMenuDisplay remove(int index)
    {
        return displays.remove(index);
    }

    @Override
    public int indexOf(Object o)
    {
        return displays.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return displays.lastIndexOf(o);
    }

    @Override
    public ListIterator<IMenuDisplay> listIterator()
    {
        return displays.listIterator();
    }

    @Override
    public ListIterator<IMenuDisplay> listIterator(int index)
    {
        return displays.listIterator(index);
    }

    @Override
    public List<IMenuDisplay> subList(int fromIndex, int toIndex)
    {
        return displays.subList(fromIndex, toIndex);
    }
}