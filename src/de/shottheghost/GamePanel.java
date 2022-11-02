package de.shottheghost;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    Thread thread;
    Thread enemy_handle_thread;

    boolean paused = false;

    KeyHandler kh;

    public Player player;
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Shot> shots = new ArrayList<>();

    int fps = 60;

    int score = 0;
    JLabel score_display;

    public GamePanel() {
        setBackground(Color.black);
        setSize(Game.game_width, Game.game_height);
        setLayout(null);

        kh = new KeyHandler();
        kh.gp = this;

        player = new Player(this, kh);

        score_display = new JLabel("Score: " + score);
        score_display.setForeground(Color.white);
        score_display.setFont(new Font("Arial", Font.BOLD, 16));
        score_display.setBounds(10, 10, 150, 25);
        add(score_display);

        setupEnemyThread();

        addKeyListener(kh);
        setFocusable(true);
    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    public boolean collidesWith(Shape a, Shape b) {
        Area areaA = new Area(a);
        areaA.intersect(new Area(b));
        return !areaA.isEmpty();
    }

    private void setupEnemyThread() {
        enemy_handle_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    updateEnemy();
                }
            }
        });
        enemy_handle_thread.start();
    }

    public void removeEnemy(Enemy e) {
        enemies.remove(e);
    }

    public void removeShot(Shot e) {
        shots.remove(e);
    }

    public void shot() {
        shots.add(new Shot(this));
    }

    @Override
    public void run() {
        double draw_interval = 1000000000 / fps;
        double next_draw_update = System.nanoTime() + draw_interval;

        while (thread != null) {
            if (!paused) {
                update();
                repaint();
            }
            if (paused) {
                kh.left_pressed = false;
                kh.right_pressed = false;
            }

            try {
                double remaining_time = next_draw_update - System.nanoTime();
                remaining_time = remaining_time / 1000000;

                if (remaining_time < 0) {
                    remaining_time = 0;
                }

                Thread.sleep((long) remaining_time);

                next_draw_update += draw_interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        grabFocus();

        score_display.setText("Score: " + score);

        player.update();

        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
        }

        for (int i = 0; i < shots.size(); i++) {
            Shot e = shots.get(i);
            e.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < shots.size(); i++) {
            Shot e = shots.get(i);
            e.draw((Graphics2D) g);
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.draw((Graphics2D) g);
        }

        player.draw((Graphics2D) g);
    }

    public void updateEnemy() {
        // Error
        if(!paused) {
            try {
                Thread.sleep(1400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enemies.add(new Enemy(this));
        }
    }
}
