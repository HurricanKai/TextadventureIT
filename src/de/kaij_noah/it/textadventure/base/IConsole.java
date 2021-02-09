package de.kaij_noah.it.textadventure.base;

import java.awt.event.KeyListener;

public interface IConsole
{
    void Write(char[] chars);

    void Write(String string);

    void NewLine();

    void SwapBuffer();

    int getStringWidth(String s);

    int getStringWidth(char[] c, int start, int length);

    int getCharWidth(char c);

    void addKeyListener(KeyListener keyListener);
}
