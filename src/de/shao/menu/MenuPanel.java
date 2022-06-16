package de.shao.menu;

import de.shao.driver.SystemResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class MenuPanel extends JPanel {

    private JFrame masterFrame;

    private Timer menuTimer;

    int i = 0;

    private final int MENU_WIDTH = 1275;
    private final int MENU_HEIGHT = 900;

    SystemResources systemResources = null;

    Queue<MenuScenes> sceneStack = null;

    public MenuPanel(JFrame masterFrame){
        this.masterFrame = masterFrame;
        this.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        sceneStack = new LinkedList<MenuScenes>();

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
        sceneStack.add(new StartScene(systemResources));
        sceneStack.add(new StartFadeOutScene(systemResources));
        sceneStack.add(new ProfilFadeScene(true, systemResources));
        sceneStack.add(new ProfilScene(systemResources));

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
        if (!sceneStack.isEmpty()) sceneStack.peek().mouseInteraction(mouseEvent);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground((new Color(1.0f, 1.0f, 1.0f, 0.0f)));
        g2d.clearRect(0,0,MENU_WIDTH, MENU_HEIGHT);
        g2d.drawImage(systemResources.getSystemImage("mainframe"),0,i,null);
        if (!sceneStack.isEmpty()){
            if (!sceneStack.peek().drawScene(g2d)) sceneStack.remove();
        }
    }

}
