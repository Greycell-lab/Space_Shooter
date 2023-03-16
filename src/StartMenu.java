import javax.swing.*;
import java.awt.*;

public class StartMenu extends JFrame {
    private final static JButton start = new JButton("Start");
    private final static JButton exit = new JButton("Exit");
    private final static JLabel title = new JLabel("Space Land");
    public StartMenu(){
        setSize(450, 500);
        start.addActionListener(e -> {
            new MyFrame(this);
        });
        exit.addActionListener(e -> {
            System.exit(0);
        });
        setLayout(null);
        title.setFont(new Font("Curier New", Font.BOLD, 45));
        title.setBounds(10, 10, 400, 125);
        add(title);
        start.setBackground(Color.BLACK);
        start.setForeground(Color.WHITE);
        exit.setBackground(Color.WHITE);
        exit.setForeground(Color.BLACK);
        start.setBounds(10, 130, 400, 125);
        exit.setBounds(10, 270, 400, 125);
        add(start);
        add(exit);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
