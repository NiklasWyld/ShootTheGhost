package de.shoottheghost;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player {
    int x, y;

    int speed;
    int size;

    Shape s;
    GamePanel gp;
    KeyHandler kh;
    static BufferedImage sprite;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        x = 368;
        y = 450;
        speed = 6;
        size = 64;

        setupSprite();
    }

    private void setupSprite() {
        try {
            sprite = ImageIO.read(new FileInputStream("./img/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (kh.left_pressed) {
            if (x >= 0) {
                x -= speed;
            }
        }
        if (kh.right_pressed) {
            if (x <= Game.game_width - size * 1.3) {
                x += speed;
            }
        }
    }

    public void draw(Graphics2D g) {
        s = new Ellipse2D.Double(x, y, size, size);
        g.drawImage(sprite, x, y, size, size, null);
    }
}
