import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Alien {
    public Random rnd = new Random();
    public static Rectangle alienHitbox;
    public Image alienImage;
    public static int x;
    public static int y;
    public static int aSpeed = 2;
    public static int life = 3;
    public Alien(){
        alienImage = new ImageIcon("enemy.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
    }
}
