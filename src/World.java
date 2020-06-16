import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World {
    public World(String path) {
        try {
            BufferedImage bitmap = ImageIO.read(getClass().getResource(path));
            int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
            bitmap.getRGB(0, 0, bitmap.getWidth(), bitmap.getHeight(), pixels, 0, bitmap.getWidth());

            for (int xx = 0; xx < 14; xx++) {
                for (int yy = 0; yy < 10; yy++) {
                    if (pixels[xx + yy * bitmap.getWidth()] == 0xFF000000) {
                        Game.blocks.add(new Block(xx * 48, yy * 48, 2));
                    } else if (pixels[xx + yy * bitmap.getWidth()] == 0xFFFFFFFF) {
                        if (pixels[xx + ((yy + 1) * bitmap.getWidth())] == 0xFF000000 ||
                                pixels[xx + ((yy - 1) * bitmap.getWidth())] == 0xFF000000) {
                            Game.blocks.add(new Block(xx * 48, yy * 48, 1));
                        } else {
                            Game.blocks.add(new Block(xx * 48, yy * 48, 0));
                        }

                    }
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
