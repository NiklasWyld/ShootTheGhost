package de.shoottheghost;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    // 68 -> Dev Key (D)

    boolean left_pressed, right_pressed;
    boolean shooting = false;
    GamePanel gp;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 68) {

        }
        if (e.getKeyCode() == 38) {
            if(shooting) {
                return;
            } else {
                shooting = true;
                gp.shot();
            }
        }
        if (e.getKeyCode() == 37) {
            left_pressed = true;
        }
        if (e.getKeyCode() == 39) {
            right_pressed = true;
        }
        if (e.getKeyCode() == 27) {
            Game.pauseGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 37) {
            left_pressed = false;
        }
        if (e.getKeyCode() == 39) {
            right_pressed = false;
        }
        if (e.getKeyCode() == 38) {
            shooting = false;
        }
    }
}
