package de.shao.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public enum Pictures {

    ONE("images/0/1.png"),
    TWO("images/0/2.png"),
    THREE("images/0/3.png"),
    FOUR("images/0/4.png"),
    FIVE("images/0/5.png"),
    SIX("images/0/6.png"),
    SEVEN("images/0/7.png"),
    EIGHT("images/0/8.png"),
    BLOCKED("images/BOX_NORMAL.png"),
    OPEN("images/0/block_open.png"),
    FLAG("images/BOX_BLOCKED.png"),
    BOMB("images/RABBIT.png"),
    CARROTCURSOR("images/cursor.png");

    private final boolean DEBUG = true;

    private String path;
    Map<String, Image> imageMap = new HashMap<>();

    Pictures(String path) {
        this.path = path;
    }

    public Image image(boolean isCursor) {
        if (imageMap.containsKey(path)) {
            return imageMap.get(path);
        } else {
            BufferedImage image;
            try {
                if (DEBUG){
                    image = ImageIO.read(new File("resources/" + path));
                }else{
                    image = ImageIO.read(getClass().getResource("/"+path));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (!isCursor){
                Image scaledImage = image.getScaledInstance(GameBoard.FIELD_MEASURE, GameBoard.FIELD_MEASURE, Image.SCALE_SMOOTH);
                imageMap.put(path, scaledImage);
                return scaledImage;
            }
            return image;
        }
    }
}
