package de.shao.gameRefactor;

import de.shao.driver.PictureController;

import java.awt.*;

public class BackgroundScene {

    private PictureController pictureController = null;
    private Point drawPosition = null;
    private int fieldSize = 0;

    /**
     * Konstruktor zur Erstellung einer Background Scene
     * @param upperLeftBackgroundCorner Obere Ecke des Feldes.
     * @param pictureController PictureController um die Spielbilder zu erhalten und zu zeichen.
     * @param fieldSize Atkuelle Feldgröße um das richtige Bild aus dem PictureController zu erhalten.
     */
    public BackgroundScene(Point upperLeftBackgroundCorner, PictureController pictureController, int fieldSize) {
        drawPosition = upperLeftBackgroundCorner;
        this.pictureController = pictureController;
        this.fieldSize = fieldSize;
    }

    /**
     * Zeichne den Hintergrund mithilfe der vorher definierten Klassenparameter
     * @param g2d
     */
    public void drawScene(Graphics2D g2d) {
        //Lade anhand der parameter das richtige Bild und Zeichne es in die obere linke Ecke der Scene
        g2d.drawImage(pictureController.getSystemResources("background" + fieldSize), drawPosition.y, drawPosition.x, null);
    }
}
