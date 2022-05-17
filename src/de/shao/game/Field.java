package de.shao.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Field {
    private int x;
    private int y;
    private int field_measure;

    private boolean bunnyFlag;
    private boolean blockedFlag;

    private Field() {
        this.x = 0;
        this.y = 0;
        this.field_measure = 1;
    }

    public Field(int x, int y, int field_measure) {
        this();
        this.x = x;
        this.y = y;
        this.field_measure = field_measure;
    }

    public void drawField(Graphics2D g2d) {
        BufferedImage image = null;
        image = Pictures.BLOCKED.image();
        Image scaledImage = image.getScaledInstance(field_measure, field_measure, Image.SCALE_SMOOTH);
        g2d.drawImage(scaledImage, x, y, null);
    }


}
