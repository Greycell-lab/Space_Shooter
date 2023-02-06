import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MyPanel extends JPanel implements KeyListener{
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Bullet> toRemove = new ArrayList<>();
    Player player = new Player();
    Alien alien = new Alien();
    public static boolean left, right, shootAble;
    public static int shootCounter;
    private static Image background;
    public static final int MAX_WIDTH = 500;
    public static final int MAX_HEIGHT = 500;
    public MyPanel(){
        this.setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
        background = new ImageIcon("background.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        this.setVisible(true);
        Timer timer = new Timer(15, e -> {
            shootCounter++;
            if(shootCounter == 40) {
                shootAble = true;
                shootCounter = 0;
            }
            if(alien.x >= MAX_WIDTH - alien.alienImage.getWidth(null) || alien.x < 0) alien.aSpeed *= -1;
            alien.x += alien.aSpeed;
            if(right && Player.x < MAX_WIDTH - player.playerImage.getWidth(null)) player.moveRight();
            if(left && Player.x > 0) player.moveLeft();
            bullets.forEach(b -> {
                if(b.yBullet > 0) {
                    b.yBullet -= b.bSpeed;
                }
                else {
                    toRemove.add(b);
                    remove(b);
                    b.bulletImage = null;
                }
            });
        });
        timer.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, null);

        g2D.drawImage(alien.alienImage, alien.x, 0, null);
        g2D.drawImage(player.playerImage, Player.x, MAX_HEIGHT - player.playerImage.getHeight(null), null);
        for(Bullet b : bullets) {
            g2D.drawImage(b.bulletImage, b.xBullet, b.yBullet, null);
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
        if(e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
        if(e.getKeyCode() == KeyEvent.VK_SPACE && shootAble) {
            new Bullet(this, MAX_HEIGHT - player.playerImage.getHeight(null), Player.x);
            shootAble = false;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
        if(e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
    }
}
