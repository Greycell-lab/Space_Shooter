import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class Alien {
    public static ArrayList<Bullet> alienBullets = new ArrayList<>();
    public static ArrayList<Bullet> toRemove = new ArrayList<>();
    public static boolean alienShooted = false;
    public static int alienShootCounter;
    public static Image alienBulletImage = new ImageIcon(Objects.requireNonNull(Alien.class
            .getResource("/bullet1.png"))).getImage().getScaledInstance(25, 50, Image.SCALE_DEFAULT);
    public static Timer aShoot;
    public static Random rnd = new Random();
    public static Rectangle alienHitbox;
    public static Image alienImage = new ImageIcon(Objects.requireNonNull(Alien.class
            .getResource("/alien.png"))).getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
    public static int x;
    public static int y;
    public static double aSpeed = 1;
    public static int life = 3;
    public static void alienShoot(MyPanel panel){
        aShoot = new Timer(15, e ->{
            if(alienShootCounter >= 100) {
                alienShooted = false;
            }
            if(alienShooted) alienShootCounter++;
            if(!alienShooted){
                alienShootCounter = 0;
                alienBullets.add(new Bullet(panel, y + alienImage.getHeight(null), x));
                alienShooted = true;
            }
            alienBullets.forEach(b -> {
                b.bulletHitbox = new Rectangle(b.xBullet, b.yBullet, b.bulletImage.getWidth(null),
                        b.bulletImage.getHeight(null));
                if(b.yBullet < 500){
                    b.yBullet += 3;
                }
                else{
                    toRemove.add(b);
                    panel.remove(b);
                    b.bulletImage = null;
                }
                if(b.bulletHitbox.intersects(Player.playerHitboxMiddle) || b.bulletHitbox.intersects(Player.playerHitboxBottom) && !Player.shield){
                    Player.playerImage = Player.playerDestroyed;
                    MyPanel.left = false;
                    MyPanel.right = false;
                    JOptionPane.showMessageDialog(null, "You got Destroyed\nYour Score: " + MyPanel.playerScore);
                    System.exit(0);
                }
            });
            alienBullets.removeAll(toRemove);
        });
        if(!aShoot.isRunning()) aShoot.start();
    }
}
