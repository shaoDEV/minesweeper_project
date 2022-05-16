package de.shao.game;

import javax.swing.*;

public class GameFrame extends JFrame {

    GameFrame(){
        this.add(new GameBoard());
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
