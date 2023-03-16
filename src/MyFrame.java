import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MyFrame extends JFrame {
    public static MyPanel panel = new MyPanel();
    public static Image icon = new ImageIcon(Objects.requireNonNull(MyFrame.class.getResource("/player.png"))).getImage();
    public MyFrame(StartMenu startframe){
        init(startframe);
    }
    public void init(StartMenu startframe){
        add(panel);
        setIconImage(icon);
        setTitle("Space Land");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(startframe);
        startframe.dispose();
        addKeyListener(new MyPanel());
    }
}
