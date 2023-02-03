import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MyPanel extends JPanel implements KeyListener{
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Bullet> toRemove = new ArrayList<>();
    boolean left, right;
    private static Image alien;
    public static Image player;
    private Image background;
    private static final int MAX_WIDTH = 500;
    public static final int MAX_HEIGHT = 500;
    private int xAlien;
    private int xAlienVelo = 2;
    private static int xBullet;
    private static int yBullet;
    private static final int yBulletMove = 2;
    private static int xPlayer = 187;
    private static int yPlayer;
    private static int xPlayerVelo = 5;
    Timer timer;
    public MyPanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
        alien = new ImageIcon("enemy.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
        player = new ImageIcon("player.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
        yPlayer = MAX_HEIGHT - player.getHeight(null);
        background = new ImageIcon("background.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        this.setVisible(true);
        timer = new Timer(15, e -> {
            if(xAlien >= MAX_WIDTH - alien.getWidth(null) || xAlien < 0) xAlienVelo *= -1;
            xAlien += xAlienVelo;
            if(right && xPlayer < MAX_WIDTH - player.getWidth(null)) setxPlayerPlus();
            if(left && xPlayer > 0) setxPlayerMinus();
            bullets.forEach(b -> {
                if(b.yBullet > 0) {
                    b.yBullet -= yBulletMove;
                }
                else {
                    toRemove.add(b);
                }
            });
            toRemove.removeAll(toRemove);
        });
        timer.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, null);
        g2D.drawImage(alien, xAlien, 0, null);
        g2D.drawImage(player, xPlayer, MAX_HEIGHT - player.getHeight(null), null);
        for(Bullet b : bullets) g2D.drawImage(Bullet.bullet, b.xBullet, b.yBullet, null);
        repaint();
    }
    public static void setxPlayerMinus() {
        xPlayer -= xPlayerVelo;
    }
    public void drawBullet(){
        new Bullet(this, MAX_HEIGHT - player.getHeight(null), xPlayer);
    }
    public static void setxPlayerPlus() {
        xPlayer += xPlayerVelo;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
        if(e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
        if(e.getKeyCode() == KeyEvent.VK_SPACE) drawBullet();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
        if(e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
    }
}
