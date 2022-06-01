package de.shao.gameRefactor;

import de.shao.driver.PictureController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JPanel {

    private int panelWidth = 0;
    private int panelHeight = 0;
    private PictureController pictureController = null;
    private boolean activeGame = true;

    BackgroundScene backgroundScene = null;
    GameScene gameScene = null;

    public GameBoard(int width, int height, PictureController pictureController) {
        panelWidth = width;
        panelHeight = height;

        this.pictureController = pictureController;

        setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        initialize();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                boardInteraction(e);
                repaint();
            }
        });
    }

    private void initialize(){
        backgroundScene = new BackgroundScene(new Point(0,0), pictureController);
        gameScene = new GameScene(new Point(66, 72), 10, 10, pictureController);
    }

    public void setActiveGame(boolean activeGame) {
        this.activeGame = activeGame;
    }

    private void boardInteraction(MouseEvent e){
        if (activeGame) gameScene.sceneInteraction(e);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        backgroundScene.drawScene(g2d);
        gameScene.drawScene(g2d);
    }
}
