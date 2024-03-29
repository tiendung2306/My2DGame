package MainMenu;

import main.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class AudioSetting extends JPanel implements Runnable {

    Thread gameThread;
    private BufferedImage SettingBackGround, back, exitImg, exitImg1, line, player, comment, comment1,volume,volumeX, volume0, volume1, volume2, volume3, mutecmt, mutecmt1;
    private int i;

    public AudioSetting()
    {
        getPlayerImage();
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        while (gameThread != null) {
            update();
            repaint();
        }
    }
    public String c_check;
    public void rollback()
    {
        c_check = null;
    }
    public void getPlayerImage(){
        try {
            SettingBackGround = ImageIO.read(new FileInputStream("res/MainmenuImage/settingbackground.png"));
            back = ImageIO.read(new FileInputStream("res/MainmenuImage/backicon.png"));
            exitImg1 = ImageIO.read(new FileInputStream("res/player/character_move_left (1).png"));
            line = ImageIO.read(new FileInputStream("res/MainmenuImage/straightline.png"));
            comment1 = ImageIO.read(new FileInputStream("res/MainmenuImage/comment.png"));
            player = ImageIO.read(new FileInputStream("res/player/character_move_down (4).png"));
            volume0 = ImageIO.read(new FileInputStream("res/MainmenuImage/zerovolume.png"));
            volume1 = ImageIO.read(new FileInputStream("res/MainmenuImage/lowvolume.png"));
            volume2 = ImageIO.read(new FileInputStream("res/MainmenuImage/mediumvolume.png"));
            volume3 = ImageIO.read(new FileInputStream("res/MainmenuImage/maxvolume.png"));
            volumeX = ImageIO.read(new FileInputStream("res/MainmenuImage/volumemute.png"));
            mutecmt1 = ImageIO.read(new FileInputStream("res/MainmenuImage/comment.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int volumesliderpointX = 230*Main.ex;
    public int mute_unmutestring = 163*Main.ex;
    public static boolean checkenterslider = false;
    public static int checkmute = 1;
    private String check = null, mutestring, mutestring1;
    private int numbervolume = 999*Main.ex;
    public void volumesliderpointX(int i) {
        check = "volumesliderpointclick";
        this.i = i;
    }
    public void checkcomment() {
        check = "checkcomment";
    }
    public void audiorollback() {
        check = null;
    }
    public void buttonReturnEnter() {
        check = "buttonReturnEnter";
    }
    public void muteEnter() {
        check = "muteEnter";
    }
    public void update() {
        if(check == "volumesliderpointclick")
        {
            volumesliderpointX = i;
        } else if (check == "checkcomment") {
            comment = comment1;
            numbervolume = 231*Main.ex;
        } else if (check == "buttonReturnEnter") {
            exitImg = exitImg1;
        } else if (check == "muteEnter") {
            mutecmt = mutecmt1;
            mutestring = mutestring1;
        } else {
            comment = null;
            exitImg = null;
            mutecmt = null;
            mutestring = "";
            numbervolume = 999*Main.ex;
        }
        if (checkmute > 0) {
            if ((int)((volumesliderpointX-217*Main.ex)/(3.6*Main.ex)) < 34 && (int)((volumesliderpointX-217*Main.ex)/(3.6*Main.ex)) > 0)
                volume = volume1;
            else if ((int)((volumesliderpointX-217*Main.ex)/(3.6*Main.ex)) >= 34 && (int)((volumesliderpointX-217*Main.ex)/(3.6*Main.ex)) < 67)
                volume = volume2;
            else if ((int)((volumesliderpointX-217*Main.ex)/(3.6*Main.ex)) >= 67)
                volume = volume3;
            else volume = volume0;
            mutestring1 = "Mute";
            mute_unmutestring = 166*Main.ex;
        } else {
            volume = volumeX;
            mutestring1 = "Unmute";
            mute_unmutestring = 156*Main.ex;
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(SettingBackGround, 0, 0, 768*Main.ex, 576*Main.ex, null);
        g2.drawImage(back, 10*Main.ex, 10*Main.ex, 40*Main.ex, 40*Main.ex, null);
        g2.drawImage(exitImg, 60*Main.ex, 12*Main.ex, 40*Main.ex, 40*Main.ex, null);
        g2.drawImage(line, 240*Main.ex, 250*Main.ex, 360*Main.ex,60*Main.ex, null);
        g2.drawImage(player, volumesliderpointX, 250*Main.ex, 50*Main.ex,50*Main.ex, null);
        g2.setFont(new Font("Arial", Font.BOLD, 14*Main.ex));
        g2.setColor(Color.white);
        g2.drawImage(comment, volumesliderpointX + 9*Main.ex, 215*Main.ex, 30*Main.ex,30*Main.ex, null);
        if ((int)((volumesliderpointX-217)/3.6) == 100)
            g2.drawString(""+(int)((volumesliderpointX-217*Main.ex)/(3.6*Main.ex)),volumesliderpointX + 12*Main.ex,numbervolume);
        else if ((int)((volumesliderpointX-217*Main.ex)/(3.6*Main.ex)) > 9)
            g2.drawString(""+(int)((volumesliderpointX-217*Main.ex)/(3.6*Main.ex)),volumesliderpointX + 16*Main.ex,numbervolume);
        else g2.drawString("0"+(int)((volumesliderpointX-217*Main.ex)/(3.6*Main.ex)),volumesliderpointX + 16*Main.ex,numbervolume);
        g2.drawImage(volume, 170*Main.ex, 254*Main.ex, 50*Main.ex,50*Main.ex, null);
        g2.drawImage(mutecmt, 153*Main.ex, 205*Main.ex, 60*Main.ex,50*Main.ex, null);
        g2.drawString(mutestring,mute_unmutestring,229*Main.ex);

        super.paintComponent(g2);
    }


    
}
