import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Alien {
    public static Random rnd = new Random();
    public static Rectangle alienHitbox;
    public static Image alienImage;
    public static int x;
    public static int y;
    public static double aSpeed = 1;
    public static int life = 3;
    public Alien(){
        alienImage = new ImageIcon("enemy.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
    }
}
