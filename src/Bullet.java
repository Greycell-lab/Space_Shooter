import javax.swing.*;
import java.awt.*;

public class Bullet extends Component{
    public static Image bullet;
    public final int yBullet = MyPanel.MAX_HEIGHT-MyPanel.player.getHeight(null)-30;
    public Bullet(MyPanel panel){
        bullet = new ImageIcon("bullet.png").getImage().getScaledInstance(25, 50, Image.SCALE_DEFAULT);
        MyPanel.bullets.add(this);
        panel.add(this);
    }
}