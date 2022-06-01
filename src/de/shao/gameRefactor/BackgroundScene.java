package de.shao.gameRefactor;

import de.shao.driver.PictureController;

import java.awt.*;

public class BackgroundScene {

    private PictureController pictureController = null;
    private Point drawPosition = null;

    public BackgroundScene(Point upperLeftBackgroundCorner, PictureController pictureController) {
        drawPosition = upperLeftBackgroundCorner;
        this.pictureController = pictureController;
    }

    public void drawScene(Graphics2D g2d) {
        g2d.drawImage(pictureController.getSystemResources("background"), drawPosition.y, drawPosition.x, null);
    }
}
