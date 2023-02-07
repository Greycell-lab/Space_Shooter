import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MyPanel extends JPanel implements KeyListener{
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Bullet> toRemove = new ArrayList<>();
    public static Player player = new Player();
    public static Alien alien = new Alien();
    public static boolean left, right, shootAble, enemyOnField = false;
    public static int shootCounter;
    private static Image background;
    public static final int MAX_WIDTH = 500;
    public static final int MAX_HEIGHT = 500;
    public MyPanel(){
        setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
        background = new ImageIcon("background.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        setVisible(true);
        Timer timer = new Timer(15, e -> {
            shootCounter++;
            Alien.alienHitbox = new Rectangle(Alien.x + 15, Alien.y + 15, 95, 70);
            Player.playerHitboxMiddle = new Rectangle(Player.x + 45, Player.y, 35, 100);
            Player.playerHitboxBottom = new Rectangle(Player.x, Player.y + 65, 125, 35);
            if(Alien.alienHitbox.intersects(Player.playerHitboxMiddle) || Alien.alienHitbox.intersects(Player.playerHitboxBottom)){
                player.playerImage = player.playerDestroyed;
                JOptionPane.showMessageDialog(null, "You got Destroyed");
                System.exit(0);
            }
            if(shootCounter == 30) {
                shootAble = true;
                shootCounter = 0;
            }
            if(!enemyOnField){
                Alien.x = alien.rnd.nextInt(0, MAX_WIDTH - 125);
                Alien.y = -125;
                enemyOnField = true;
            }
            Alien.y += Alien.aSpeed;
            if(Alien.y > MAX_HEIGHT + 25){
                enemyOnField = false;
                Alien.life = 3;
            }
            if(right && Player.x < MAX_WIDTH - player.playerImage.getWidth(null)) player.moveRight();
            if(left && Player.x > 0) player.moveLeft();
            bullets.forEach(b -> {
                b.bulletHitbox = new Rectangle(b.xBullet, b.yBullet, b.bulletImage.getWidth(null),
                        b.bulletImage.getHeight(null));

                if(b.yBullet > 0) {
                    b.yBullet -= b.bSpeed;
                }
                else {
                    toRemove.add(b);
                    remove(b);
                    b.bulletImage = null;
                }
                if(b.bulletHitbox.intersects(Alien.alienHitbox)){
                    Alien.life--;
                    if(Alien.life==0){
                        enemyOnField = false;
                        Alien.life = 3;
                    }
                    toRemove.add(b);
                    remove(b);
                    b.bulletImage = null;
                }
            });
            bullets.removeAll(toRemove);
        });
        timer.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, null);
        /*if(Player.playerHitboxMiddle != null) g2D.draw(Player.playerHitboxMiddle);
        if(Player.playerHitboxBottom != null) g2D.draw(Player.playerHitboxBottom);
        if(Alien.alienHitbox != null) g2D.draw(Alien.alienHitbox);*/
        g2D.drawImage(alien.alienImage, Alien.x, Alien.y, null);
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
