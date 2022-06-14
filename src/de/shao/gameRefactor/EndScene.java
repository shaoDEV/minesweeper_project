package de.shao.gameRefactor;

import de.shao.driver.PictureController;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EndScene {

    PictureController pictureController = null;
    Point drawPoint = null;

    Rectangle backToMenuArea = null;
    Rectangle startNewGameArea = null;

    Image backToMenu = null;
    Image startNewGame = null;

    boolean goBackToMenu = false;
    boolean startANewGame = false;


    EndScene(PictureController pictureController, Point drawPoint){
        this.pictureController = pictureController;
        this.drawPoint = drawPoint;

        backToMenu = pictureController.getSystemResources("backToMenu");
        startNewGame = pictureController.getSystemResources("startNewGame");

        backToMenuArea = new Rectangle(drawPoint.x, drawPoint.y, backToMenu.getWidth(null), backToMenu.getHeight(null));
        startNewGameArea = new Rectangle(drawPoint.x, drawPoint.y + 10 + startNewGame.getHeight(null), startNewGame.getWidth(null), startNewGame.getHeight(null));
    }

    public void drawScene(Graphics2D g2d){
        g2d.drawImage(backToMenu, drawPoint.x, drawPoint.y, null);
        g2d.drawImage(startNewGame, drawPoint.x, drawPoint.y + 10 + startNewGame.getHeight(null), null);
    }

    public void sceneInteraction(MouseEvent mouseEvent){
        if (backToMenuArea.contains(mouseEvent.getPoint())){
            goBackToMenu = true;
        }
        if (startNewGameArea.contains(mouseEvent.getPoint())){
            startANewGame = true;
        }
    }

    public boolean isGoBackToMenu() {
        return goBackToMenu;
    }

    public boolean isStartANewGame() {
        return startANewGame;
    }
}
