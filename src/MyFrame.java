import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private static MyPanel panel;
    public MyFrame(){
        panel = new MyPanel();
        this.add(panel);
        this.setTitle("Space Land");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.addKeyListener(new MyPanel());
    }
    public static MyPanel getMyPanel(){
        return panel;
    }
}
