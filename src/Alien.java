import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Alien {
    public static Random rnd = new Random();
    public static Rectangle alienHitbox;
    public static Image alienImage = new ImageIcon(Objects.requireNonNull(Alien.class.getResource("/alien.png"))).getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
    public static int x;
    public static int y;
    public static double aSpeed = 1;
    public static int life = 3;
}
