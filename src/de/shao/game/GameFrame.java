package de.shao.game;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class GameFrame extends JFrame {

    private int boardWidth = 0;
    private int boardHeight = 0;

    private JLayeredPane lPane = new JLayeredPane();
    private GameBoardBackground backgroundLayer = null;
    private GameMenuPanel gameMenuPanel = null;
    private GameBoard gameLayer = null;

    GameFrame(int boardWidth, int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.setSize(new Dimension(this.boardWidth,this.boardHeight));
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        backgroundLayer = new GameBoardBackground(this.boardHeight, this.boardWidth);
        gameLayer = new GameBoard(this, backgroundLayer, 10, 48);

        lPane.setBounds(0,0,this.boardWidth,this.boardHeight);
        this.add(lPane, BorderLayout.CENTER);
        lPane.add(backgroundLayer, JLayeredPane.DEFAULT_LAYER, 0);
        lPane.add(gameLayer, JLayeredPane.PALETTE_LAYER, 0);
        this.setVisible(true);
    }

}
