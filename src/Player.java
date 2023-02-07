import javax.swing.*;
import java.awt.*;

public class Player {
    public static Image playerImage = new ImageIcon("player.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
    public static Image playerDestroyed = new ImageIcon("explo.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
    public static Rectangle playerHitboxMiddle;
    public static Rectangle playerHitboxBottom;
    public static int x = 187;
    public static int y = 400;
    public static int pSpeed = 5;
    public static void moveLeft() {
        x -= pSpeed;
    }
    public static void moveRight() {
        x += pSpeed;
    }
}
