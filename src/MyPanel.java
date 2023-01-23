import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MyPanel extends JPanel {
    private Image alien;
    private Image player;
    private Image background;
    private final int MAX_WIDTH = 500;
    private final int MAX_HEIGHT = 500;
    private int xAlien;
    private int xAlienVelo = 2;
    private int xPlayer;
    private int xPlayerVelo = 2;
    Timer timer;
    public MyPanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
        alien = new ImageIcon("enemy.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
        player = new ImageIcon("player.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
        background = new ImageIcon("background.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        this.setVisible(true);
        timer = new Timer(5, null);
        timer.addActionListener(e -> {
            if(xAlien >= MAX_WIDTH - alien.getWidth(null) || xAlien < 0) xAlienVelo *= -1;
            if(xPlayer >= MAX_WIDTH - player.getWidth(null) || xPlayer <0) xPlayerVelo *= -1;
            xAlien += xAlienVelo;
            xPlayer += xPlayerVelo;
            repaint();
        });
        timer.start();

    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, null);
        g2D.drawImage(alien, xAlien, 0, null);
        g2D.drawImage(player, xPlayer, MAX_HEIGHT - player.getHeight(null), null);
    }
}
