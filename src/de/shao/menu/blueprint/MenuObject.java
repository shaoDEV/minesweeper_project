package de.shao.menu.blueprint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuObject {
    protected int x;
    protected int y;

    protected int width;
    protected int height;

    private Image menuObjectImage;
    private Image menuObjectImageWhileHovered;

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    private boolean hovered;

    protected MenuObject(int x, int y, int width, int height, String image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imageLoader(image);
    }

    protected MenuObject(int x, int y, int width, int height, String image, String imageHovered) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imageLoader(image, imageHovered);
    }

    public boolean isInItem(Point point){
        return (point.getX() >= x && point.getX() <= x+width && point.getY() >= y && point.getY() <= y+height);
    }

    public void imageLoader(String pictureName){
        try {
            menuObjectImage = ImageIO.read(new File("resources/images/system/" + pictureName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void imageLoader(String pictureName, String pictureNameHovered){
        try {
            menuObjectImage = ImageIO.read(new File("resources/images/system/" + pictureName + ".png"));
            menuObjectImageWhileHovered = ImageIO.read(new File("resources/images/system/" + pictureNameHovered + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void drawMenuObject(Graphics2D g2d) {
        if (hovered == false) g2d.drawImage(menuObjectImage, x, y, null);
        else g2d.drawImage(menuObjectImageWhileHovered, x, y, null);
    }

    public void useAction(){
        objectAction();
    }

    protected void objectAction(){};
}
