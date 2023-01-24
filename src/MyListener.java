import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) MyPanel.setxPlayerPlus();
        if(e.getKeyCode() == KeyEvent.VK_LEFT) MyPanel.setxPlayerMinus();
        if(e.getKeyCode() == KeyEvent.VK_SPACE) new Bullet();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
