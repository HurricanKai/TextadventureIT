package de.kaij_noah.it.textadventure.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public final class Console implements de.kaij_noah.it.textadventure.base.IConsole
{
    private final CustomTextComponent textArea;
    private final Font font;
    private final JFrame frame;
    private StringBuilder lineBuffer;

    public Console() throws IOException, FontFormatException
    {
        frame = new JFrame("TitleLessJFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);

        var tempfont = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Inconsolata/OpenType-TT/Inconsolata-Medium.ttf"));
        font = tempfont.deriveFont(10f);
        textArea = new CustomTextComponent(font);
        textArea.setBackground(Color.BLACK);
        frame.getContentPane().add(textArea);
        textArea.setSize(frame.getSize());
        textArea.setPreferredSize(frame.getSize());
        textArea.setForeground(Color.WHITE);
        lineBuffer = new StringBuilder();
    }

    @Override
    public void write(char[] chars)
    {
        lineBuffer.append(String.copyValueOf(chars));
    }

    @Override
    public void write(String string)
    {
        lineBuffer.append(string);
    }

    @Override
    public void newLine()
    {
        textArea.addLine(lineBuffer.toString());
        lineBuffer.setLength(0);
    }

    @Override
    public void swapBuffer()
    {
        if (lineBuffer.length() != 0)
        {
            textArea.addLine(lineBuffer.toString());
            lineBuffer.setLength(0);
        }
        textArea.swapBuffers();
    }

    @Override
    public int getStringWidth(String s)
    {
        return textArea.getGraphics().getFontMetrics(font).stringWidth(s);
    }

    @Override
    public int getStringWidth(char[] c, int start, int length)
    {
        return textArea.getGraphics().getFontMetrics(font).charsWidth(c, start, length);
    }

    @Override
    public int getCharWidth(char c)
    {
        return textArea.getGraphics().getFontMetrics(font).charWidth(c);
    }

    @Override
    public void addKeyListener(KeyListener keyListener)
    {
        textArea.addKeyListener(keyListener);
    }
}
