import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel {
    private Image alien;
    private static Image player;
    private Image background;
    private final int MAX_WIDTH = 500;
    private final int MAX_HEIGHT = 500;
    private int xAlien;
    private int xAlienVelo = 2;
    private static int xPlayer = 187;
    private static int yPlayer;
    private static int xPlayerVelo = 15;
    Timer timer;
    public MyPanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
        alien = new ImageIcon("enemy.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
        player = new ImageIcon("player.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
        yPlayer = MAX_HEIGHT - player.getHeight(null);
        background = new ImageIcon("background.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        this.setVisible(true);
        timer = new Timer(5, null);
        timer.addActionListener(e -> {
            if(xAlien >= MAX_WIDTH - alien.getWidth(null) || xAlien < 0) xAlienVelo *= -1;
            xAlien += xAlienVelo;
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
    public static void setxPlayerMinus() {
        xPlayer -= xPlayerVelo;
    }
    public static void setxPlayerPlus() {
        xPlayer += xPlayerVelo;
    }
    public static int getxPlayer(){
        return xPlayer;
    }
    public static int getPlayerWidth(){
        return player.getWidth(null);
    }
    public static int getyPlayer(){
        return yPlayer;
    }
}
