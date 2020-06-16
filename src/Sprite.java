import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {
    public static BufferedImage road_left;
    public static BufferedImage road_top;
    public static BufferedImage grass;

    public Sprite() {
        try {
            road_left = ImageIO.read(getClass().getResource("/road_left.png"));
            road_top = ImageIO.read(getClass().getResource("/road_top.png"));
            grass = ImageIO.read(getClass().getResource("/grass.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
