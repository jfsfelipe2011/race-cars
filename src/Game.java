import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable, KeyListener {
    public static Player player;
    public static ArrayList<Block> blocks = new ArrayList<>();
    public static PointCar pointCar1;
    public static PointCar pointCar2;
    public static int count = 0;
    public static int voltas = 0;
    public static int ticks = 0;

    public Game () {
        this.setPreferredSize(new Dimension(640, 480));
        this.addKeyListener(this);
        new Sprite();
        new World("/map.png");

        player = new Player(52, 387);
        pointCar1 = new PointCar(52, 387, 40, 40);
        pointCar2 = new PointCar(533, 51, 40, 40);

        pointCar1.point = 1;
        pointCar2.point = 2;
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("Race Cars");
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run() {
        while (true) {
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void render() {
        requestFocus();
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,640,480);


        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).render(g);
        }

        Graphics2D g2 = (Graphics2D) g;
        player.render(g2);
        pointCar1.render(g);
        pointCar2.render(g);

        g.setColor(Color.white);
        g.setFont(new Font("arail", Font.BOLD, 16));
        g.drawString("Voltas: " + voltas, 10, 20);
        g.drawString("Tempo: " + count, 10, 45);

        bs.show();
    }

    public void tick() {
        ticks++;

        if (ticks >= 60) {
            ticks = 0;
            count++;
        }

        player.tick();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = false;
        }
    }
}
