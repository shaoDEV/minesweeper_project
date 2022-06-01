package de.shao.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameMenuPanel extends JPanel {

    private int boardWidth = 0;
    private int boardHeight = 0;

    private JFrame frame = null;
    private GameBoardBackground gameBoardBackground = null;
    private GameBoard gameBoard = null;

    public static GameMenuPanel gameMenuPanel = null;

    GameMenuPanel(int boardHeight, int boardWidth, JFrame frame, GameBoardBackground backgroundPanel, GameBoard gamePanel){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.frame = frame;
        this.gameBoardBackground = backgroundPanel;
        this.gameBoard = gamePanel;

        this.setBounds(0,0,this.boardWidth,this.boardHeight);
        this.setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        GameMenuPanel.gameMenuPanel = this;
        repaint();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage image;
        try {
            image = ImageIO.read(new File("resources/images/system/backToMenu.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2d.drawImage(image, 0,0, null);
    }

    public static void activateMenu(){
        gameMenuPanel.repaint();
    }
}
