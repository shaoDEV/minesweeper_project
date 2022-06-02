package de.shao.gameRefactor;

import de.shao.driver.PictureController;

import java.awt.*;

public class BackgroundScene {

    private PictureController pictureController = null;
    private Point drawPosition = null;
    private int fieldSize = 0;

    public BackgroundScene(Point upperLeftBackgroundCorner, PictureController pictureController, int fieldSize) {
        drawPosition = upperLeftBackgroundCorner;
        this.pictureController = pictureController;
        this.fieldSize = fieldSize;
    }

    public void drawScene(Graphics2D g2d) {
        g2d.drawImage(pictureController.getSystemResources("background" + fieldSize), drawPosition.y, drawPosition.x, null);
    }
}
