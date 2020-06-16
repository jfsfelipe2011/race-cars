import java.awt.*;

public class Block {
    public int type = 0;
    public int x, y;

    public Block(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void render(Graphics g) {
        if (type == 0) {
            g.drawImage(Sprite.road_top, x, y, null);
        } else if (type == 1){
            g.drawImage(Sprite.road_left, x, y, null);
        } else {
            g.drawImage(Sprite.grass, x, y, null);
        }
    }
}
