package Intentory;

import main.GamePanel;
import tile.Tile;

public class Inventory {
    InventoryPage[] pages;
    GamePanel gamePanel;
    public Inventory(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        pages = new InventoryPage[10];
        for (int i = 0; i < 10; ++i) {
            pages[i] = new InventoryPage();
        }
    }
    public void pushToInventory(Tile tile){
        for (int pageIndex = 0; pageIndex < 10; ++pageIndex)
            for (int x = 0; x < 5; ++x)
                for (int y = 0; y < 5; ++y){
                    if (pages[pageIndex].slot[x][y].Name.equals(tile.Name)){
                        ++pages[pageIndex].slot[x][y].numOwn;
                        return;
                    }
                }
        for (int pageIndex = 0; pageIndex < 10; ++pageIndex)
            for (int x = 0; x < 5; ++x)
                for (int y = 0; y < 5; ++y){
                    if (pages[pageIndex].slot[x][y].Name.equals("Empty")){
                        pages[pageIndex].slot[x][y] = tile;
                        pages[pageIndex].slot[x][y].numOwn = 1;
                        return;
                    }
                }
    }
    public void popFromInventory(int pageIndex, int x, int y){
        if (!pages[pageIndex].slot[x][y].Name.equals("Empty")){
            --pages[pageIndex].slot[x][y].numOwn;
            Tile tile = new Tile(gamePanel.player.getMapX(), gamePanel.player.getMapX() + pages[pageIndex].slot[x][y].getWidth(), gamePanel.player.getMapY() + gamePanel.tileSize, gamePanel.player.getMapY() + gamePanel.tileSize + pages[pageIndex].slot[x][y].getHeight(), pages[pageIndex].slot[x][y].Name, pages[pageIndex].slot[x][y].Type, pages[pageIndex].slot[x][y].image);
            gamePanel.currentMap.addTile(tile);
            if (pages[pageIndex].slot[x][y].numOwn == 0) {
                pages[pageIndex].slot[x][y].Name = "Empty";
            }
        }
    }
}
