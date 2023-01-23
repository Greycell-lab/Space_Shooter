import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private MyPanel panel;
    public MyFrame(){
        panel = new MyPanel();
        this.add(panel);
        this.setTitle("Space Land");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }
}
