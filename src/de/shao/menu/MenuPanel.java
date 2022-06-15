package de.shao.menu;

import de.shao.driver.SystemResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class MenuPanel extends JPanel {

    private JFrame masterFrame;

    private Timer menuTimer;

    GameStartScene gameStartScene;

    int i = 0;

    private final int MENU_WIDTH = 1275;
    private final int MENU_HEIGHT = 900;

    SystemResources systemResources = null;

    public MenuPanel(JFrame masterFrame){
        this.masterFrame = masterFrame;
        this.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mouseInteraction(e);
            }
        });

        init();
    }

    private void init(){
        systemResources = SystemResources.getInstance();
        gameStartScene = new GameStartScene(systemResources);
        cycleStart();
    }

    private void cycleStart(){
        menuTimer = new Timer();
        menuTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, 17);

    }

    private void mouseInteraction(MouseEvent mouseEvent){
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(systemResources.getSystemImage("mainframe"),0,i,null);
        gameStartScene.drawScene(g2d);
    }

}
