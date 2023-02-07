import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MyPanel extends JPanel implements KeyListener{
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Bullet> toRemove = new ArrayList<>();
    public static Player player = new Player();
    public static Alien alien = new Alien();
    public static boolean left, right, shootAble, enemyOnField = false, upgrade = false;
    public static int shootCounter;
    private static Image background;
    public static final int MAX_WIDTH = 500;
    public static final int MAX_HEIGHT = 500;
    public static int playerScore;
    public MyPanel(){
        setLayout(null);
        //TextField score = new TextField();
        JLabel score = new JLabel();
        score.setFocusable(false);
        score.setBounds(325, 0, 175, 35);
        score.setFont(new Font("Curier New", 0, 25));
        score.setBackground(Color.BLACK);
        score.setForeground(Color.WHITE);
        add(score);
        setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
        background = new ImageIcon("background.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        setVisible(true);

        //Game-Engine every 15ms timer
        Timer timer = new Timer(15, e -> {
            if(playerScore % 500 == 0 && playerScore >= 500 && !upgrade) {
                Alien.aSpeed += 0.5;
                upgrade = true;
            }
            System.out.println(Alien.aSpeed);
            score.setText("Score: " + playerScore);
            if(!shootAble) shootCounter++;
            Alien.alienHitbox = new Rectangle(Alien.x + 15, Alien.y + 15, 95, 70);
            Player.playerHitboxMiddle = new Rectangle(Player.x + 45, Player.y, 35, 100);
            Player.playerHitboxBottom = new Rectangle(Player.x, Player.y + 65, 125, 35);
            if(Alien.alienHitbox.intersects(Player.playerHitboxMiddle) || Alien.alienHitbox.intersects(Player.playerHitboxBottom)){
                Player.playerImage = Player.playerDestroyed;
                JOptionPane.showMessageDialog(null, "You got Destroyed\nYour Score: " + playerScore);
                System.exit(0);
            }

            //Prevent multiple firing every 15 ms
            if(shootCounter == 15) {
                shootAble = true;
                shootCounter = 0;
            }

            //If there is no enemy on field, set the Enemy to position above 0
            if(!enemyOnField){
                    Alien.x = Alien.rnd.nextInt(0, MAX_WIDTH - 125);
                    Alien.y = -125;

                //Randomizer for alienship Image
                switch(Alien.rnd.nextInt(0,6)){
                    case 0 -> Alien.alienImage = new ImageIcon(AlienShips.SHIP1.fileName).getImage()
                            .getScaledInstance(125, 100, Image.SCALE_DEFAULT);
                    case 1 -> Alien.alienImage = new ImageIcon(AlienShips.SHIP2.fileName).getImage()
                            .getScaledInstance(125, 100, Image.SCALE_DEFAULT);
                    case 2 -> Alien.alienImage = new ImageIcon(AlienShips.SHIP3.fileName).getImage()
                            .getScaledInstance(125, 100, Image.SCALE_DEFAULT);
                    case 3 -> Alien.alienImage = new ImageIcon(AlienShips.SHIP4.fileName).getImage()
                            .getScaledInstance(125, 100, Image.SCALE_DEFAULT);
                    case 4 -> Alien.alienImage = new ImageIcon(AlienShips.SHIP5.fileName).getImage()
                            .getScaledInstance(125, 100, Image.SCALE_DEFAULT);
                    case 5 -> Alien.alienImage = new ImageIcon(AlienShips.SHIP6.fileName).getImage()
                            .getScaledInstance(125, 100, Image.SCALE_DEFAULT);
                }
                enemyOnField = true;
            }

            //Alien y movement
            Alien.y += Alien.aSpeed;
            if(Alien.y > MAX_HEIGHT + 25){
                enemyOnField = false;
                Alien.life = 3;
            }

            //Player x movement
            if(right && Player.x < MAX_WIDTH - Player.playerImage.getWidth(null)) player.moveRight();
            if(left && Player.x > 0) player.moveLeft();

            //Bullet flights and Hitbox-Check for intersection with alienship
            bullets.forEach(b -> {
                b.bulletHitbox = new Rectangle(b.xBullet, b.yBullet, b.bulletImage.getWidth(null),
                        b.bulletImage.getHeight(null));

                if(b.yBullet > 0) {
                    b.yBullet -= b.bSpeed;
                }
                else {
                    toRemove.add(b);
                    remove(b);
                    b.bulletImage = null;
                }
                if(b.bulletHitbox.intersects(Alien.alienHitbox)){
                    Alien.life--;
                    if(Alien.life==0){
                        playerScore += 100;
                        enemyOnField = false;
                        upgrade = false;
                        Alien.life = 3;
                    }
                    toRemove.add(b);
                    remove(b);
                    b.bulletImage = null;
                }
            });
            bullets.removeAll(toRemove);
        });
        //Starts the timer
        timer.start();
    }
    //Paint everything on the Screen
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, null);

        //Hitbox visibility
        /*if(Player.playerHitboxMiddle != null) g2D.draw(Player.playerHitboxMiddle);
        if(Player.playerHitboxBottom != null) g2D.draw(Player.playerHitboxBottom);
        if(Alien.alienHitbox != null) g2D.draw(Alien.alienHitbox);*/

        g2D.drawImage(alien.alienImage, Alien.x, Alien.y, null);
        g2D.drawImage(player.playerImage, Player.x, MAX_HEIGHT - player.playerImage.getHeight(null), null);
        for(Bullet b : bullets) {
            g2D.drawImage(b.bulletImage, b.xBullet, b.yBullet, null);
        }
        repaint();
    }

    //KeyEvent Handler
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
        if(e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
        if(e.getKeyCode() == KeyEvent.VK_SPACE && shootAble) {
            new Bullet(this, MAX_HEIGHT - player.playerImage.getHeight(null), Player.x);
            shootAble = false;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
        if(e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
    }
}
