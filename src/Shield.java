import javax.swing.*;
import java.awt.*;

public class Shield {
    public static Image shieldImage = new ImageIcon(Shield.class.getResource("/shield.png"))
            .getImage().getScaledInstance(145, 120, Image.SCALE_DEFAULT);
    public static Rectangle shieldHitbox;

}
