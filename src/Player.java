import javax.swing.*;
import java.awt.*;

public class Player {
    public Image playerImage;
    public Image playerDestroyed;
    public static Rectangle playerHitboxMiddle;
    public static Rectangle playerHitboxBottom;
    public static int x = 187;
    public static int y;
    public int pSpeed = 5;
    public Player() {
        playerImage = new ImageIcon("player.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
        playerDestroyed = new ImageIcon("explo.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
        y = 400;
    }
    public void moveLeft() {
        x -= pSpeed;
    }
    public void moveRight() {
        x += pSpeed;
    }
}
