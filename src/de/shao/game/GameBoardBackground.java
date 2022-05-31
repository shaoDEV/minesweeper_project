package de.shao.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextHitInfo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameBoardBackground extends JPanel {

    private final int BOARD_WIDTH = 612;
    private final int BOARD_HEIGHT = 624;

    public GameBoardBackground(){
        this.setBounds(0,0, BOARD_WIDTH, BOARD_HEIGHT);
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage image;
        try {
            image = ImageIO.read(new File("resources/images/system/10x10.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2d.drawImage(image, 0,0, null);
    }
}
