package de.shoottheghost;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    JButton continue_game;

    public Menu() {
        setBackground(Color.white);
        setSize(Game.game_width, Game.game_height);
        setLayout(null);

        setupUI();
    }

    private void setupUI() {
        JLabel title = new JLabel("Shot the Ghost");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(300, 100, 300, 50);

        JButton start_game = new JButton("Start Game");
        start_game.setBackground(Color.white);
        start_game.setBounds(350, 300, 100, 40);

        start_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.startGame();
            }
        });

        continue_game = new JButton("Continue");
        continue_game.setBackground(Color.white);
        continue_game.setBounds(350, 350, 100, 40);
        continue_game.setEnabled(false);

        continue_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.continueGame();
            }
        });

        JButton exit_game = new JButton("Exit");
        exit_game.setBackground(Color.white);
        exit_game.setBounds(350, 400, 100, 40);

        exit_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(title);
        this.add(start_game);
        this.add(continue_game);
        this.add(exit_game);
    }
}
