package main;

import Keyboard.KeyboardManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    KeyboardManager keyboardManager = new KeyboardManager();

    public void keyTyped(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){

        int code = e.getKeyCode();

        if(code == keyboardManager.getKey("UP")){
            upPressed = true;
        }
        if(code == keyboardManager.getKey("DOWN")){
            downPressed = true;
        }
        if(code == keyboardManager.getKey("LEFT")){
            leftPressed = true;
        }
        if(code == keyboardManager.getKey("RIGHT")){
            rightPressed = true;
        }
    }

    public void keyReleased(KeyEvent e){

        int code = e.getKeyCode();

        if(code == keyboardManager.getKey("UP")){
            upPressed = false;
        }
        if(code == keyboardManager.getKey("DOWN")){
            downPressed = false;
        }
        if(code == keyboardManager.getKey("LEFT")){
            leftPressed = false;
        }
        if(code == keyboardManager.getKey("RIGHT")){
            rightPressed = false;
        }
    }

}
