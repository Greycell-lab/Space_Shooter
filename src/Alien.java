import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;

public class Alien {
    public static LinkedList<Bullet> alienBullets = new LinkedList<>();
    public static LinkedList<Bullet> toRemove = new LinkedList<>();
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
        aShoot = new Timer(10, e ->{
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
                }
                if((b.bulletHitbox.intersects(Player.playerHitboxMiddle) || b.bulletHitbox.intersects(Player.playerHitboxBottom))){
                    if(!Player.shield) {
                        Player.playerImage = Player.playerDestroyed;
                        toRemove.add(b);
                        MyPanel.left = false;
                        MyPanel.right = false;
                        JOptionPane.showMessageDialog(null, "You got Destroyed\nYour Score: " + MyPanel.playerScore);
                        System.exit(0);
                    }
                    else{
                        Player.shield = false;
                        toRemove.add(b);
                    }
                }
            });
            alienBullets.removeAll(toRemove);
        });
        if(!aShoot.isRunning()) aShoot.start();
    }
}
