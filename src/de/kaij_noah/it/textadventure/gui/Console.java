package de.kaij_noah.it.textadventure.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public final class Console
{
    private final JTextArea textArea;
    private final Font font;
    private final JFrame frame;
    private StringBuilder backBuffer;

    public Console() throws IOException, FontFormatException
    {
        frame = new JFrame("TitleLessJFrame");
        //frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);

        textArea = new JTextArea();
        textArea.setBackground(Color.BLACK);
        frame.getContentPane().add(textArea);
        textArea.setSize(frame.getSize());
        textArea.setForeground(Color.WHITE);
        // textArea.setLineWrap(true);
        var c = textArea.getCaret();
        c.setSelectionVisible(false);
        textArea.setCaretColor(Color.WHITE);
        var tempfont = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Inconsolata/OpenType-TT/Inconsolata-Medium.ttf"));
        font = tempfont.deriveFont(15f);
        textArea.setFont(font);
        backBuffer = new StringBuilder();
    }

    public void Write(char[] chars)
    {
        backBuffer.append(String.copyValueOf(chars));
    }

    public void Write(String string)
    {
        backBuffer.append(string);
    }

    public void NewLine()
    {
        backBuffer.append("\n");
    }

    public void SwapBuffer()
    {
        textArea.setText(backBuffer.toString());
        backBuffer = new StringBuilder();
    }

    public int getStringWidth(String s)
    {
        return textArea.getGraphics().getFontMetrics(font).stringWidth(s);
    }

    public int getStringWidth(char[] c, int start, int length)
    {
        return textArea.getGraphics().getFontMetrics(font).charsWidth(c, start, length);
    }

    public int getCharWidth(char c)
    {
        return textArea.getGraphics().getFontMetrics(font).charWidth(c);
    }

    public void addKeyListener(KeyListener keyListener)
    {
        textArea.addKeyListener(keyListener);
    }
}
