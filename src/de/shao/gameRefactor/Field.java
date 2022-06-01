package de.shao.gameRefactor;

import de.shao.driver.PictureController;

import java.awt.*;

public class Field {
    private int x;
    private int y;

    private char bottomPictureIdentifier;
    private boolean flagged;
    private boolean open;

    private PictureController pictureController = null;

    private Field() {
        this.x = 0;
        this.y = 0;
    }

    public char getBottomPictureIdentifier() {
        return bottomPictureIdentifier;
    }

    public Field(int x, int y, PictureController pictureController) {
        this();
        this.x = x;
        this.y = y;
        bottomPictureIdentifier = ' ';
        flagged = false;
        open = false;
        this.pictureController = pictureController;
    }

    public void drawField(Graphics2D g2d) {
        Image image;
        if (open) {
            if (bottomPictureIdentifier == 'b') image = pictureController.getBomb();
            else if (bottomPictureIdentifier == ' ') image = pictureController.getBlock(true);
            else image = pictureController.getNumber(bottomPictureIdentifier);
        } else if (flagged) image = pictureController.getFlag();
        else image = pictureController.getBlock(false);
        g2d.drawImage(image, x, y, null);
    }

    public void setBottomPictureIdentifier(char bottomPicture) {
        this.bottomPictureIdentifier = bottomPicture;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isFlagged() {
        return flagged;
    }
}
