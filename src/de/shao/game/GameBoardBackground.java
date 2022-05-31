package de.shao.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameBoardBackground extends JPanel {

    private final int BOARD_WIDTH = 663;
    private final int BOARD_HEIGHT = 630;

    private Font font = null;
    private boolean timerShouldRun = false;
    private int secondsSinceStart = 0;

    public void setTimerShouldRun(boolean timerShouldRun) {
        this.timerShouldRun = timerShouldRun;
    }
    public void setFlagsLeft(int flagsLeft) {
        this.flagsLeft = flagsLeft;
    }

    private int flagsLeft = 10;

    public GameBoardBackground(){
//        try {
//            Font customFont = Font.createFont(0, new File("resources/ttf/Minecraft.ttf"));
//            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//            ge.registerFont(customFont);
//        } catch (FontFormatException | IOException e) {
//            e.printStackTrace();
//        }
//        font = new Font("Minecraft", Font.PLAIN, 40);

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
            image = ImageIO.read(new File("resources/images/system/10x10_" + flagsLeft +".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2d.drawImage(image, 0,0, null);
    }

}
