package de.shao.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public enum Pictures {

    ONE("images/ONE.png"),
    TWO("images/TWO.png"),
    THREE("images/THREE.png"),
    FOUR("images/FOUR.png"),
    FIVE("images/FIVE.png"),
    SIX("images/SIX.png"),
    SEVEN("images/SEVEN.png"),
    EIGHT("images/EIGHT.png"),
    BLOCKED("images/BOX_NORMAL.png"),
    OPEN("images/FIELD_OPEN.png"),
    FLAG("images/BOX_BLOCKED.png"),
    BOMB("images/RABBIT.png"),
    CARROTCURSOR("images/CARROT_CURSOR.png");

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
