import javax.swing.*;
import java.awt.*;

public class Player {
    public Image playerImage;
    public int x;
    public int y;
    public int pSpeed = 5;
    public Player() {
        playerImage = new ImageIcon("player.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
        y = MyPanel.MAX_HEIGHT - playerImage.getHeight(null);
    }
}
