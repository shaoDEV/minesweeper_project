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
            switch (bottomPictureIdentifier) {
                case '1' -> image = Pictures.ONE.image(false);
                case '2' -> image = Pictures.TWO.image(false);
                case '3' -> image = Pictures.THREE.image(false);
                case '4' -> image = Pictures.FOUR.image(false);
                case '5' -> image = Pictures.FIVE.image(false);
                case '6' -> image = Pictures.SIX.image(false);
                case '7' -> image = Pictures.SEVEN.image(false);
                case '8' -> image = Pictures.EIGHT.image(false);
                case 'b' -> image = Pictures.BOMB.image(false);
                default -> image = Pictures.OPEN.image(false);
            }
        } else if (blockedFlag) {
            image = Pictures.FLAG.image(false);
        } else {
            image = Pictures.BLOCKED.image(false);
        }
        g2d.drawImage(image, x, y, null);
    }

    public void pressed(int mouseButton){
        //Linke Maustaste
        if (mouseButton == 1){
            if (!openFlag && !blockedFlag) openFlag = true;
        }
        //Rechte Maustaste
        if (mouseButton == 3){
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
}
