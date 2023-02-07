import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private static MyPanel panel;
    public MyFrame(){
        panel = new MyPanel();
        add(panel);
        setTitle("Space Land");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        addKeyListener(new MyPanel());
    }
}
