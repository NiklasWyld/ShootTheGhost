package de.shottheghost;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JPanel {
    JLabel score;

    public GameOver() {
        setBackground(Color.white);
        setSize(Game.game_width, Game.game_height);
        setLayout(null);

        setupUI();
    }

    private void setupUI() {
        JLabel title = new JLabel("GAME OVER", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.red);
        title.setBounds(275, 50, 250, 60);

        score = new JLabel("Score: 0", SwingConstants.CENTER);
        score.setBounds(300, 140, 200, 30);

        JButton restart = new JButton("Restart");
        restart.setBackground(Color.white);
        restart.setBounds(325, 300, 150, 40);

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.restartGame();
            }
        });

        JButton load_menu = new JButton("Return to menu");
        load_menu.setBackground(Color.white);
        load_menu.setBounds(325, 350, 150, 40);

        load_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.loadMenu();
            }
        });

        this.add(title);
        this.add(score);
        this.add(restart);
        this.add(load_menu);
    }
}
