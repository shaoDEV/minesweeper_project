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
    private boolean gameWon = false;

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
                getGameStatus();
                repaint();
            }
        });
    }

    private void initialize(){
        backgroundScene = new BackgroundScene(new Point(0,0), pictureController);
        gameScene = new GameScene(
                new Point(66, 72),
                new Point(pictureController.getSystemResources("background").getWidth(null), pictureController.getSystemResources("background").getHeight(null)),
                10,
                10,
                pictureController);
    }

    private void getGameStatus(){
        activeGame = gameScene.isActiveGame();
        gameWon = gameScene.isGameWon();

        if (activeGame == false){
            System.out.println("Spiel ist vorbei!");
            if (gameWon) System.out.println("Spiel gewonnen!");
            else System.out.println("Spiel verloren!");
        }

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
