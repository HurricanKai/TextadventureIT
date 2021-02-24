package de.kaij_noah.it.textadventure.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

final class CustomTextComponent extends JComponent
{
    private final Queue<ArrayList<String>> drawQueue = new ArrayBlockingQueue<>(3);
    private final Queue<ArrayList<String>> writeQueue = new ArrayBlockingQueue<>(3);
    private ArrayList<String> currentDraw = new ArrayList<>();

    private final Font font;

    public CustomTextComponent(Font font)
    {
        this.font = font;
    }

    public void addLine(String line)
    {
        currentDraw.add(line);
    }

    @Override
    public void paint(Graphics g)
    {
        if (drawQueue.isEmpty())
            return;

        var lines = drawQueue.remove();
        var bounds = g.getClipBounds();
        g.setColor(this.getBackground());
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        g.setColor(this.getForeground());
        g.setFont(font);
        var fontMetrics = g.getFontMetrics(font);
        var height = fontMetrics.getHeight();
        var currentHeight = 0;

        for (var line : lines)
        {
            currentHeight += height;
            g.drawString(line, 0, currentHeight);
        }

        lines.clear();
        writeQueue.offer(lines);
    }

    @Override
    public boolean isOpaque()
    {
        return true;
    }

    public void swapBuffers()
    {
        // offering means that old frames are generally preferred, but I can't be bothered.
        if (drawQueue.offer(currentDraw))
        {
            if (writeQueue.isEmpty())
            {
                currentDraw = new ArrayList<>();
            }
            else
            {
                currentDraw = writeQueue.remove();
            }
            repaint();
        }
        else
        {
            currentDraw.clear();
        }
    }
}
