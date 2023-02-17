package de.shoottheghost;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy {
    int x, y;

    int speed;
    int size;

    GamePanel gp;

    Shape obstacle;

    BufferedImage sprite;

    public Enemy(GamePanel gp) {
        this.gp = gp;

        x = ThreadLocalRandom.current().nextInt(1, Game.game_width + 1);
        y = 1;
        size = 64;
        speed = 3;

        setupSprite();
    }

    private void setupSprite() {
        try {
            sprite = ImageIO.read(new FileInputStream("./img/enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkForCollision() {
        if (gp.collidesWith(obstacle, gp.player.s)) {
            Game.gameOver();
        }
    }

    public void update() {
        obstacle = new Ellipse2D.Double(x, y, size, size);
        if (!gp.paused) {
            checkForCollision();
            if (y <= Game.game_width) {
                y += speed;
            } else {
                gp.removeEnemy(this);
            }
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(sprite, x, y, size, size, null);
    }
}