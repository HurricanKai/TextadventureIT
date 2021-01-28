import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Console
{
    private final JTextArea textArea;
    private StringBuilder backBuffer;

    public Console() throws IOException, FontFormatException
    {
        JFrame frame = new JFrame("TitleLessJFrame");
        //frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setVisible(true);

        textArea = new JTextArea();
        textArea.setBackground(Color.BLACK);
        frame.getContentPane().add(textArea);
        textArea.setSize(frame.getSize());
        textArea.setForeground(Color.WHITE);
        textArea.setLineWrap(true);
        var c = textArea.getCaret();
        c.setSelectionVisible(false);
        textArea.setCaretColor(Color.WHITE);
        var font = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/NotoSansJP-Bold.otf"));
        textArea.setFont(font.deriveFont(10f));
        backBuffer = new StringBuilder();
    }

    public void Write(char[] chars)
    {
        backBuffer.append(String.copyValueOf(chars));
    }
    public void Write(String string) { backBuffer.append(string); }

    public void NewLine()
    {
        backBuffer.append("\n");
    }

    public void SwapBuffer()
    {
        textArea.setText(backBuffer.toString());
        backBuffer = new StringBuilder();

    }

    public String ReadLine()
    {
        return "";
    }
}
