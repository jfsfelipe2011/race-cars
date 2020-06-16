import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {
    public int width = 88;
    public int height = 40;

    public double x, y;
    public double dx, dy;
    public double speed = 0;
    public double aceleration = 0.2;
    public double rotation = 0;

    public boolean right = false;
    public boolean left = false;
    public boolean up = false;

    public int lastPoint = 0;

    public BufferedImage sprite;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;

        try {
            sprite = ImageIO.read(getClass().getResource("/car_player.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void tick() {
        Rectangle boxCar = new Rectangle((int) x, (int) y, 88/2, 40/2);

        if (right) {
            rotation+=3;
        } else if (left) {
            rotation-=3;
        }

        for (int i = 0; i < Game.blocks.size(); i++) {
            Block block = Game.blocks.get(i);

            Rectangle boxBlock = new Rectangle(block.x, block.y, 48, 48);

            if (boxCar.intersects(boxBlock)) {
                if (block.type > 1) {
                    if (speed >= 2) {
                        speed = 2;
                    }
                }
            }
        }

        if (up) {
            speed += aceleration;

            if (speed >= 6) {
                speed = 6;
            }
        } else {
            speed -= aceleration;

            if (speed <= 0) {
                speed = 0;
            }
        }

        dx = Math.cos(Math.toRadians(rotation));
        dy = Math.sin(Math.toRadians(rotation));

        x+= dx * speed;
        y+= dy * speed;

        if (boxCar.intersects(Game.pointCar1)) {
            lastPoint = 1;
        } else if (boxCar.intersects(Game.pointCar2)) {
            if (lastPoint == 1) {
                Game.voltas++;
            }

            lastPoint = 2;
        }
    }

    public void render(Graphics2D g2) {
        g2.rotate(Math.toRadians(rotation), x + width / 2 / 2,y + height / 2 / 2);
        g2.drawImage(sprite, (int) x, (int) y, width/ 2, height / 2, null);
        g2.rotate(Math.toRadians(-rotation), x + width / 2 / 2,y + height / 2 / 2);
    }
}
