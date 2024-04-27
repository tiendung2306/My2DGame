package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font arial_40;
    public String currentDialog = "";
    public String text = "";
    public int i;
    BufferedImage nextIcon, skipIcon, skipWord;
    public boolean isFinishDialogue;
    int iconX = (int) (207 * GamePanel.scale), iconY = (int) (170 * GamePanel.scale);
    int skipX = (int) (8 * GamePanel.scale), skipY = (int) (10 * GamePanel.scale);
    int step = 0;
    boolean reverse;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        getImage();
    }

    void getImage() {
        try {
            nextIcon = ImageIO.read(new FileInputStream("res/Dialog/nextIcon.png"));
            skipIcon = ImageIO.read(new FileInputStream("res/Dialog/skipIcon.png"));
            skipWord = ImageIO.read(new FileInputStream("res/Dialog/skipWord.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void screenResize(){
        iconX = (int) (207 * GamePanel.scale);
        iconY = (int) (170 * GamePanel.scale);
        skipX = (int) (8 * GamePanel.scale);
        skipY = (int) (10 * GamePanel.scale);
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        if (Main.topGameState().equals("GamePause")) {
            drawPauseScreen();
        }
        if (Main.topGameState().equals("Dialog")) {
            drawDialogScreen();
        }
        if (Main.topGameState().equals("Dialogue")) {
            drawDialogueScreen();
        }
        if (Main.topGameState().equals("GamePlay")) {
            if (gamePanel.player.ButtonInteract && !gamePanel.phone.isDrawPhone) {
                drawInteractButton();
            }
        }
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = (int) (GamePanel.screenWidth / 2 - length / 2);
        int y = (int) (GamePanel.screenHeight / 2);
        g2.drawString(text, x, y);
    }

    public void drawDialogScreen() {
        int x = 32 * (int) GamePanel.scale;
        int width = (int) (GamePanel.screenWidth - 64 * GamePanel.scale);
        int height = (int) (64 * GamePanel.scale);
        int y = (int) (GamePanel.screenHeight - height - 8 * GamePanel.scale);
        int FontSize = (int)(GamePanel.scale * 9);
        int FontPixel = (int)(GamePanel.scale * 4);
        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, FontSize));
        x += (int) (16 * GamePanel.scale);
        y += (int) (16 * GamePanel.scale);
        String str = "";
        int strSize = 0;
        for (int i = 0; i < currentDialog.length(); ++i) {
            strSize += FontPixel;
            if (currentDialog.charAt(i) == ' ') {
                for (int j = i + 1; j <= currentDialog.length(); ++j)
                    if (j == currentDialog.length() || currentDialog.charAt(j) == ' ') {
                        if (strSize + (j - i - 1) * FontPixel >= width - 36 * GamePanel.scale) {
                            g2.drawString(str, x, y);
                            str = "";
                            y += 40;
                            strSize = 0;
                        }
                        break;
                    }
                if (strSize > 0)
                    str += ' ';
            } else
                str += currentDialog.charAt(i);
        }
        if (strSize > 0)
            g2.drawString(str, x, y);
    }
    public Timer timer = new Timer(60, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            text += currentDialog.charAt(i);
            i++;
            if (i == currentDialog.length()){
                timer.stop();
                isFinishDialogue = true;
                i = 0;
            }
        }
    });
    public void drawDialogueScreen(){
        int x = 32 * (int)GamePanel.scale;
        int width = (int) (GamePanel.screenWidth - 64 * GamePanel.scale);
        int height = (int) (64 * GamePanel.scale);
        int y = (int) (GamePanel.screenHeight - height - 8 * GamePanel.scale);
        int FontSize = (int)(GamePanel.scale * 9);
        int FontPixel = (int)(GamePanel.scale * 4);
        drawSubWindow(x,y,width,height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, FontSize));
        x += (int) (16 * GamePanel.scale);
        y += (int) (16 * GamePanel.scale);
        String str = "";
        int strSize = 0;
        for (int i = 0; i < text.length(); ++i){
            strSize += FontPixel;
            if (text.charAt(i) == ' '){
                for (int j = i + 1; j <= text.length(); ++j)
                    if (j == text.length() || text.charAt(j) == ' '){
                        if (strSize + (j - i - 1) * FontPixel >= width - 36 * GamePanel.scale){
                            g2.drawString(str, x, y);
                            str = "";
                            y += 40;
                            strSize = 0;
                        }
                        break;
                    }
                if (strSize > 0)
                    str += ' ';
            } else str += text.charAt(i);
        }
        if (strSize > 0)
            g2.drawString(str, x, y);
        ++step;
        if (step == 8) {
            if (!reverse) {
                iconX += (int) GamePanel.scale;
                //skipX += (int) GamePanel.scale;
                if (iconX >= 209 * GamePanel.scale) {
                    reverse = true;
                }
            } else {
                iconX -= (int) GamePanel.scale;
                //skipX -= (int) GamePanel.scale;
                if (iconX <= 207 * GamePanel.scale) {
                    reverse = false;
                }
            }
            step = 0;
        }
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, (int) (GamePanel.scale * 4)));
        g2.drawString("Press Space", (int) (182 * GamePanel.scale), (int) (175 * GamePanel.scale));
        g2.drawImage(nextIcon, iconX, iconY, (int)(10 * GamePanel.scale), (int)(7 * GamePanel.scale), null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, (int) (GamePanel.scale * 7)));
        g2.drawImage(skipWord, (int) (27 * GamePanel.scale), (int) (11 * GamePanel.scale), (int) (15 * GamePanel.scale),(int) (5 * GamePanel.scale),  null);
        g2.drawImage(skipIcon, skipX, skipY, (int)(17 * GamePanel.scale), (int)(7 * GamePanel.scale), null);
    }
    public void drawInteractButton(){
        String text =  "F";
        g2.setColor(Color.BLACK);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, (int)(GamePanel.scale * 4)));
        int x = gamePanel.player.getBoundingBoxX() + gamePanel.player.getBoundingBoxWidth();
        int y = gamePanel.player.getBoundingBoxY();
        int width = (int) ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() + 8 * GamePanel.scale);
        int height = (int) (8 * GamePanel.scale);
        drawSubInteractButton((int) (x - 4 * GamePanel.scale), (int) (y - height / 2 - GamePanel.scale), width, height);
        g2.drawString(text, x, y);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 100);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawSubInteractButton(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 100);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 25, 25);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x + 2, y + 2, width - 4, height - 4, 25, 25);
    }

    public void drawBackground(BufferedImage image) {
        g2.drawImage(image, 0, 0, (int) GamePanel.screenWidth, (int) GamePanel.screenHeight, null);
    }
}
