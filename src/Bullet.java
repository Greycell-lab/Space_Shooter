import javax.swing.*;
import java.awt.*;

public class Bullet extends Component{
    public static Image bullet;
    public int yBullet;
    public int xBullet;
    public Bullet(MyPanel panel, int yBullet, int xPlayer){
        this.yBullet = yBullet;
        this.xBullet = xPlayer + 50;
        bullet = new ImageIcon("bullet.png").getImage().getScaledInstance(25, 50, Image.SCALE_DEFAULT);
        MyPanel.bullets.add(this);
        panel.add(this);
    }
}