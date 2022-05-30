package de.shao.menu;

import de.shao.game.Pictures;
import de.shao.menu.blueprint.MenuObject;
import de.shao.menu.items.CloseButton;
import de.shao.menu.items.GifTest;
import de.shao.menu.items.minimizeButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MenuPanel extends JPanel {

    private JFrame masterFrame;

    private final int MENU_WIDTH = 1200;
    private final int MENU_HEIGHT = 800;

    private ArrayList<MenuObject> hoveredableObjects = new ArrayList<>();
    private ArrayList<MenuObject> allObjects = new ArrayList<>();

    public MenuPanel(JFrame masterFrame){
        this.masterFrame = masterFrame;
        this.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        init();
    }

    private void init(){
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image;
        try {
                image = ImageIO.read(new File("resources/images/system/mainframe.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image,0,0,null);
    }
    //endregion
}
