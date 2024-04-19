package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class TileSection extends Tile{
    public int x,y,width_tile,height_tile;
    public String name,type,name_path;
    GamePanel gamePanel;


    public TileSection(GamePanel gamePanel, int x, int y, int width_tile, int height_tile, String name, String type, String name_path) {
        this.x = x;
        this.y = y;
        this.width_tile = width_tile;
        this.height_tile = height_tile;
        this.gamePanel=gamePanel;
        this.name = name;
        this.type = type;
        this.name_path = name_path;
        Name = name;
        Type = type;
        try {
            image = ImageIO.read(new FileInputStream(name_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BoundingBox();
    }
    public void BoundingBox() {
        setLeftX(x * gamePanel.scale);
        setTopY(y * gamePanel.scale);
        setRightX((x + width_tile) * gamePanel.scale);
        setBottomY((y + height_tile) * gamePanel.scale);
        setWidth(width_tile * gamePanel.scale);
        setHeight(height_tile * gamePanel.scale);
    }

}