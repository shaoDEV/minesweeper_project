package de.shao.menu;

import de.shao.driver.SystemResources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private JFrame masterFrame;

    private final int MENU_WIDTH = 1275;
    private final int MENU_HEIGHT = 900;

    SystemResources systemResources = null;

    public MenuPanel(JFrame masterFrame){
        this.masterFrame = masterFrame;
        this.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        init();
    }

    private void init(){
        systemResources = SystemResources.getInstance();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(systemResources.getSystemImage("mainframe"),0,0,null);
    }

}
