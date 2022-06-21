package de.shao.gameRefactor;

import de.shao.driver.PictureController;
import de.shao.driver.SystemResources;

import java.awt.*;

public class Field {
    //Koordinaten des Feldes
    private int x;
    private int y;

    //Identifier um was für eine Art Feld es sich handelt und wie es gezeichnet werden muss
    private char bottomPictureIdentifier; //Gibt an was gezeihnet wird wenn das Feld aufgedeckt wird.
    private boolean flagged; //Gibt an ob das Feld mit einer Flagge gezeichnet werden muss oder nicht.
    private boolean open; //Gibt an ob das Feld offen ist.

    //Aktuelle Area des Feldes für spätere Interaktion mit dem aktuellen Feld
    private Rectangle fieldArea;

    private PictureController pictureController = null;

    /**
     * Standardkonstruktor
     */
    private Field() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * BottomPictureIdentifiert ist der Identifier um welches Feld es sich im aufgedeckten Zustand handelt.
     * @return Liefert den Identifier des aktuellen Feldes zurück
     */
    public char getBottomPictureIdentifier() {
        return bottomPictureIdentifier;
    }

    /**
     * Custom Konstruktor um die Standartwerde des Feldes zu setzen bevor es von der GameScene richitg deklariert wird
     * @param x Position des zu erzeugenden Feldes.
     * @param y Position des zu erzeugenden Feldes.
     * @param pictureController vom Menu definierter PictureController
     */
    public Field(int x, int y, PictureController pictureController) {
        this();
        this.x = x;
        this.y = y;
        bottomPictureIdentifier = ' '; //Definiert das Feld erstamllig ohne Identifiert damit dieser im nachhinein deklariert werden kann.
        flagged = false;
        open = false;
        this.pictureController = pictureController;
        fieldArea = new Rectangle(x,y,48,48);
    }

    /**
     * Zeichne das aktuelle Feld
     * @param g2d Graphics Object vom aufgerufenen Panel
     */
    public void drawField(Graphics2D g2d) {
        //Greift die aktuelle Mausposition ab und rechnet die Feldbreite ab um die Position innerhalb des Frames zu bekommen.
        int mouseX = MouseInfo.getPointerInfo().getLocation().x - SystemResources.GameFramePoint.x;
        int mouseY = MouseInfo.getPointerInfo().getLocation().y - SystemResources.GameFramePoint.y;
        Point mouseEvent = new Point(mouseX, mouseY);

        //Zeichnet anhand des Identifiers des Feldes das passende Bild welches er aus dem Picturcontroller bekommt
        Image image;
        if (open) {
            if (bottomPictureIdentifier == 'b') image = pictureController.getBomb();
            else if (bottomPictureIdentifier == ' ') image = pictureController.getBlock(true);
            else image = pictureController.getNumber(bottomPictureIdentifier);
        } else if (flagged) image = pictureController.getFlag();
        else image = pictureController.getBlock(false);
        g2d.drawImage(image, x, y, null);

        //Zeichnet falls die Maus das aktuelle Feld kreuzt einen halbtransparenten Rahmen über das Feld um ein besseres User Feedback zu geben.
        if (fieldArea.contains(mouseEvent)) g2d.drawImage(pictureController.getSystemResources("fieldHovering"), x, y, null);
    }

    /**
     * @param bottomPicture Definiert den Inhalt des aktuellen Feldes Neu
     */
    public void setBottomPictureIdentifier(char bottomPicture) {
        this.bottomPictureIdentifier = bottomPicture;
    }

    /**
     * Definiert ob das zugedeckte Feld als Flagge dargestellt werden soll.
     * @param flagged Übergibt den Flaggenstatus
     */
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    /**
     * Ändert die Eigenschaft ob das aktuelle Feld offen oder geschlossen ist und dargestellt wird
     * @param open Übergibt den Öffnnungsstauts des Feldes
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * @return Gibt zurück ob das Feld geöffnet ist.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * @return Gibt die X Position des Feldes zurück
     */
    public int getX() {
        return x;
    }

    /**
     * @return Gibt die Y Position des Feldes zurück.
     */
    public int getY() {
        return y;
    }

    /**
     * @return GIbt den Flaggenstatus des Feldes zurück.
     */
    public boolean isFlagged() {
        return flagged;
    }
}
