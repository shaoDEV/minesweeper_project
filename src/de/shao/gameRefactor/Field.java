package de.shao.gameRefactor;

import de.shao.driver.PictureController;
import de.shao.driver.SystemResources;

import java.awt.*;

public class Field {
    private int x;
    private int y;

    private char bottomPictureIdentifier;
    private boolean flagged;
    private boolean open;

    private Rectangle fieldArea;

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
        fieldArea = new Rectangle(x,y,48,48);
    }

    public void drawField(Graphics2D g2d) {
        int mouseX = MouseInfo.getPointerInfo().getLocation().x - SystemResources.GameFramePoint.x;
        int mouseY = MouseInfo.getPointerInfo().getLocation().y - SystemResources.GameFramePoint.y;
        Point mouseEvent = new Point(mouseX, mouseY);

        Image image;
        if (open) {
            if (bottomPictureIdentifier == 'b') image = pictureController.getBomb();
            else if (bottomPictureIdentifier == ' ') image = pictureController.getBlock(true);
            else image = pictureController.getNumber(bottomPictureIdentifier);
        } else if (flagged) image = pictureController.getFlag();
        else image = pictureController.getBlock(false);
        g2d.drawImage(image, x, y, null);

        if (fieldArea.contains(mouseEvent)) g2d.drawImage(pictureController.getSystemResources("fieldHovering"), x, y, null);
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
