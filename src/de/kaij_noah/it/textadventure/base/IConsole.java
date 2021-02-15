package de.kaij_noah.it.textadventure.base;

import java.awt.event.KeyListener;

public interface IConsole
{
    void write(char[] chars);

    void write(String string);

    void newLine();

    void swapBuffer();

    int getStringWidth(String s);

    int getStringWidth(char[] c, int start, int length);

    int getCharWidth(char c);

    void addKeyListener(KeyListener keyListener);
}
