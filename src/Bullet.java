import javax.swing.*;
import java.awt.*;

public class Bullet extends Component{
    public Image bulletImage;
    public int yBullet;
    public int xBullet;
    public int bSpeed = 2;
    public Bullet(MyPanel panel, int yBullet, int xPlayer){
        this.yBullet = yBullet;
        this.xBullet = xPlayer + 50;
        bulletImage = new ImageIcon("bullet.png").getImage().getScaledInstance(25, 50, Image.SCALE_DEFAULT);
        MyPanel.bullets.add(this);
        panel.add(this);
    }
}