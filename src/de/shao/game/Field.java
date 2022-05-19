package de.shao.game;

import java.awt.*;

public class Field {
    private int x;
    private int y;

    private char bottomPictureIdentifier;
    private boolean blockedFlag;
    private boolean openFlag;

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
        blockedFlag = false;
        openFlag = false;
    }

    public void drawField(Graphics2D g2d) {
        Image image;
        if (openFlag) {
            if (bottomPictureIdentifier == 'b') image = GameBoard.pictureController.getBomb();
            else if (bottomPictureIdentifier == ' ') image = GameBoard.pictureController.getBlock(true);
            else image = GameBoard.pictureController.getNumber(bottomPictureIdentifier);
        } else if (blockedFlag) image = GameBoard.pictureController.getFlag();
        else image = GameBoard.pictureController.getBlock(false);
        g2d.drawImage(image, x, y, null);
    }

    public void pressed(int mouseButton) {
        //Linke Maustaste
        if (mouseButton == 1) {
            if (!openFlag && !blockedFlag) openFlag = true;
        }
        //Rechte Maustaste
        if (mouseButton == 3) {
            if (!openFlag) blockedFlag = !blockedFlag;
        }
    }

    public void setBottomPictureIdentifier(char bottomPicture) {
        this.bottomPictureIdentifier = bottomPicture;
    }

    public void setBlockedFlag(boolean blockedFlag) {
        this.blockedFlag = blockedFlag;
    }

    public void setOpenFlag(boolean openFlag) {
        this.openFlag = openFlag;
    }

    public boolean isOpenFlag() {
        return openFlag;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
