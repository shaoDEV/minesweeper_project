package de.shao.menu;

import de.shao.menu.blueprint.MenuObject;
import de.shao.menu.items.CloseButton;
import de.shao.menu.items.minimizeButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
        checkHoveredHoverableObject();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                buttonIsPressed(e);
            }
        });
    }

    private void init(){
        addObject(new CloseButton(1106, 206, 32, 32, "close", "close_pressed"), true);
        addObject(new minimizeButton(1071, 206, 32,32,"minimize", "minimize_pressed", masterFrame), true);
        repaint();
    }


    //region PanelFunktionen
    private void buttonIsPressed(MouseEvent event){
        for (MenuObject objects : allObjects){
            if (objects.isInItem(event.getPoint())) objects.useAction();
        }
    }

    private void addObject(MenuObject object, boolean isHoverable){
        allObjects.add(object);
        if (isHoverable == true) hoveredableObjects.add(object);
    }

    public void checkHoveredHoverableObject(){
        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    for (MenuObject objects : hoveredableObjects) {
                        if (getMousePosition() != null && objects.isInItem(new Point(getMousePosition().x, getMousePosition().y))){
                            objects.setHovered(true);
                        } else objects.setHovered(false);
                        repaint();
                    }
            }
        }).start();
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

        for (MenuObject objects : allObjects) {
            objects.drawMenuObject(g2d);
        }
    }
    //endregion
}
