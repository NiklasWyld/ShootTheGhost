package de.shottheghost;

import javax.swing.*;

public class Game {
    private static JFrame frame;

    private static Menu menu;
    private static GamePanel panel;
    private static GameOver gameover_panel;

    public static int game_width = 800;
    public static int game_height = 600;

    public static void main(String[] args) {
        frame = new JFrame();

        menu = new Menu();
        gameover_panel = new GameOver();
        frame.add(gameover_panel);
        gameover_panel.setVisible(false);

        frame.setTitle("Shot the Ghost");
        frame.setSize(game_width, game_height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

        frame.add(menu);
        frame.setVisible(true);
    }

    public static void startGame() {
        menu.setVisible(false);

        panel = new GamePanel();
        frame.add(panel);
        panel.startThread();
    }

    public static void restartGame() {
        menu.setVisible(false);
        menu.continue_game.setEnabled(false);
        gameover_panel.setVisible(false);

        panel = new GamePanel();
        frame.add(panel);
        panel.startThread();
    }

    public static void loadMenu() {
        gameover_panel.setVisible(false);
        menu.setVisible(true);
        menu.continue_game.setEnabled(false);
    }

    public static void pauseGame() {
        panel.paused = true;
        panel.setVisible(false);
        menu.setVisible(true);
        menu.continue_game.setEnabled(true);
    }

    public static void continueGame() {
        menu.setVisible(false);
        panel.setVisible(true);
        panel.paused = false;
    }

    public static void gameOver() {
        gameover_panel.score.setText("Score: " + panel.score);

        panel.setVisible(false);
        frame.remove(panel);
        panel.paused = true;

        gameover_panel.setVisible(true);
    }
}
