package de.shao.game;

import java.awt.*;

public class Field {
    private int x;
    private int y;

    private char bottomPictureIdentifier;
    private boolean flagged;
    private boolean open;

    private Field() {
        this.x = 0;
        this.y = 0;
    }

    public char getBottomPictureIdentifier() {
        return bottomPictureIdentifier;
    }

    public Field(int x, int y) {
        this();
        this.x = x;
        this.y = y;
        bottomPictureIdentifier = ' ';
        flagged = false;
        open = false;
    }

    public void drawField(Graphics2D g2d) {
        Image image;
        if (open) {
            if (bottomPictureIdentifier == 'b') image = GameBoard.pictureController.getBomb();
            else if (bottomPictureIdentifier == ' ') image = GameBoard.pictureController.getBlock(true);
            else image = GameBoard.pictureController.getNumber(bottomPictureIdentifier);
        } else if (flagged) image = GameBoard.pictureController.getFlag();
        else image = GameBoard.pictureController.getBlock(false);
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
