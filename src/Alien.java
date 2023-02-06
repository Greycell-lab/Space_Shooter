import javax.swing.*;
import java.awt.*;

public class Alien {
    public Image alienImage;
    public int x;
    public int aSpeed = 2;
    public Alien(){
        alienImage = new ImageIcon("enemy.png").getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT);
    }
}
