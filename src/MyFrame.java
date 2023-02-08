import javax.swing.*;

public class MyFrame extends JFrame {
    private final static MyPanel panel = new MyPanel();
    public MyFrame(){
        init();
    }
    public void init(){
        add(panel);
        setTitle("Space Land");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        addKeyListener(new MyPanel());
    }
}
