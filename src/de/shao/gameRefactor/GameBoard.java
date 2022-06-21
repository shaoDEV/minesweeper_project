package de.shao.gameRefactor;

import de.shao.driver.PictureController;
import de.shao.driver.SystemConstraints;
import de.shao.driver.SystemResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoard extends JPanel {

    private final String DB_URL = "jdbc:mysql://217.160.175.168:3306/minesweeperHS";
    private final String USER = "minesweeperMarvin";
    private final String PASS = "password";

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

        java.util.Timer menuTimer = new Timer();
        menuTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, 17);

    }

    private void getGameStatus() {
        activeGame = gameScene.isActiveGame();
        gameWon = gameScene.isGameWon();

        if (activeGame != null && !activeGame) {
            //Erstellung der Endscene mit der Berechnung wo genau sie gezeichnet werden soll.
            int widthEndSceneItem = pictureController.getSystemResources("startNewGame").getWidth(null); //Breite der MenuObjekte in der Endscene zur Berechnung des Zeichenpunktes
            int widthActiveGameArea = fieldSize * pictureController.getBomb().getWidth(null); //Breite des aktiven Spielfeldes. Wird benötigt um die Endscene in der Mitte des Spielfeldes zu zeichnen
            endScene = new EndScene(pictureController, new Point(initPoint.x + ((widthActiveGameArea / 2) - (widthEndSceneItem / 2)), 200)); //Erstellung der Endscene mit berechnetem Zeichenpunkt
            String sql = "";
            if (fieldSize == 10)
                sql = "INSERT INTO `tenFieldHighscore`(`userID`, `username`, `secToFinish`) VALUES ('" + SystemResources.actualID + "','" + SystemResources.actualUsername + "'," + SystemResources.currentTimeInSec + ")";
            if (fieldSize == 16)
                sql = "INSERT INTO `sixtenFieldHighscore`(`userID`, `username`, `secToFinish`) VALUES ('" + SystemResources.actualID + "','" + SystemResources.actualUsername + "'," + SystemResources.currentTimeInSec + ")";
            if (fieldSize == 25)
                sql = "INSERT INTO `twentyFiveFieldHighscore`(`userID`, `username`, `secToFinish`) VALUES ('" + SystemResources.actualID + "','" + SystemResources.actualUsername + "'," + SystemResources.currentTimeInSec + ")";
            if (gameWon) {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            //endScene = new EndScene(pictureController, new Point(initPoint.x + ((widthActiveGameArea / 2) - (widthEndSceneItem / 2)), 200)); //Erstellung der Endscene mit berechnetem Zeichenpunkt
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
