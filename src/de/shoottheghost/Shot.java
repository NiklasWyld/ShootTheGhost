package de.shoottheghost;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Shot {
    int x, y;
    int speed;

    Shape shape;

    GamePanel gp;

    public Shot(GamePanel gp) {
        this.gp = gp;

        x = gp.player.x + gp.player.size / 2;
        y = gp.player.y;
        speed = 4;
    }

    public void update() {
        shape = new Ellipse2D.Double(x, y, 2, 4);

        for (int i = 0; i < gp.enemies.size(); i++) {
            Enemy e = gp.enemies.get(i);

            if (gp.collidesWith(e.obstacle, shape)) {
                gp.score++;
                gp.removeEnemy(e);
                gp.removeShot(this);
            }
        }

        if (y >= 0) {
            y -= speed;
        } else {
            gp.removeShot(this);
        }
    }

    public void draw(Graphics2D g) {
        shape = new Ellipse2D.Double(x, y, 2, 4);
        g.setColor(Color.white);
        g.fill(shape);
        g.draw(shape);
    }
}
