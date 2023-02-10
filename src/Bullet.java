import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Bullet extends Component{
    public Image bulletImage;
    public int yBullet;
    public int xBullet;
    public int bSpeed = 4;
    public Rectangle bulletHitbox;
    public Bullet(MyPanel panel, int yBullet, int xBullet){
        this.yBullet = yBullet;
        this.xBullet = xBullet + 50;
        bulletImage = new ImageIcon(Objects.requireNonNull(Bullet.class.getResource("/bullet.png"))).getImage().getScaledInstance(25, 50, Image.SCALE_DEFAULT);
        panel.add(this);
    }
}