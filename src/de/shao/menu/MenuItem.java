package de.shao.menu;

import javax.imageio.ImageIO;
import java.awt.*;

public class MenuItem {
    private int x;
    private int y;

    private int width;
    private int height;

    private Image menuItemImage;

    public boolean isInItem(Point point){
        return (point.getX() >= x && point.getX() <= x+width && point.getY() >= y && point.getY() <= y+height);
    }

}
