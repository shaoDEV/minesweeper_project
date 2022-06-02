package de.shao.gameRefactor;

import de.shao.driver.PictureController;
import de.shao.driver.SystemConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JPanel {

    private int panelWidth = 0;
    private int panelHeight = 0;
    private int fieldSize = 0;
    private int bombCount = 0;
    private PictureController pictureController = null;
    private Boolean activeGame = null;
    private boolean gameWon = false;

    private JFrame sirFrameALot = null;

    Point initPoint = null;

    BackgroundScene backgroundScene = null;
    GameScene gameScene = null;
    EndScene endScene = null;

    public GameBoard(JFrame frame, int width, int height, PictureController pictureController, int bombCount, int fieldSize) {
        sirFrameALot = frame;
        panelWidth = width;
        panelHeight = height;
        this.bombCount = bombCount;
        this.fieldSize = fieldSize;

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

    private void initialize() {
        backgroundScene = new BackgroundScene(new Point(0, 0), pictureController, fieldSize);

        switch (fieldSize) {
            case 10 -> initPoint = SystemConstraints.TEN_TEN.point();
            case 16 -> initPoint = SystemConstraints.SIXTEEN_SIXTEEN.point();
            case 20 -> initPoint = SystemConstraints.TWENTY_TWENTY.point();
        }
        gameScene = new GameScene(
                initPoint,
                new Point(pictureController.getSystemResources("background" + fieldSize).getWidth(null), pictureController.getSystemResources("background" + fieldSize).getHeight(null)),
                fieldSize,
                bombCount,
                pictureController);

    }

    private void getGameStatus() {
        activeGame = gameScene.isActiveGame();
        gameWon = gameScene.isGameWon();

        if (activeGame != null && !activeGame) {
            //Erstellung der Endscene mit der Berechnung wo genau sie gezeichnet werden soll.
            int widthEndSceneItem = pictureController.getSystemResources("startNewGame").getWidth(null); //Breite der MenuObjekte in der Endscene zur Berechnung des Zeichenpunktes
            int widthActiveGameArea = fieldSize * pictureController.getBomb().getWidth(null); //Breite des aktiven Spielfeldes. Wird benötigt um die Endscene in der Mitte des Spielfeldes zu zeichnen

            if (gameWon) {

            } else {
                endScene = new EndScene(pictureController, new Point(initPoint.x + ((widthActiveGameArea / 2) - (widthEndSceneItem / 2)), 200)); //Erstellung der Endscene mit berechnetem Zeichenpunkt
            }
        }

    }

    private void boardInteraction(MouseEvent e) {
        if (activeGame == null || activeGame) gameScene.sceneInteraction(e);
        else {
            endScene.sceneInteraction(e);
            if (endScene.isGoBackToMenu()) sirFrameALot.dispose();
            if (endScene.isStartANewGame()) {
                initialize();
            }
        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        backgroundScene.drawScene(g2d);
        gameScene.drawScene(g2d);
        if (activeGame != null && !activeGame) {
            endScene.drawScene(g2d);
        }
    }
}
