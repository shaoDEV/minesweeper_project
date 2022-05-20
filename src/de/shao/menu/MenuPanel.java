package de.shao.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private final int MENU_WIDTH = 1200;
    private final int MENU_HEIGHT = 800;

    public MenuPanel(){
        this.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image;
        try {
                image = ImageIO.read(new File("resources/images/system/menu_background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image,0,0,null);
    }
}
