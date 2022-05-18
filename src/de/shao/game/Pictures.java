package de.shao.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public enum Pictures {

    ONE("resources/ONE.png"),
    TWO("resources/TWO.png"),
    THREE("resources/THREE.png"),
    FOUR("resources/FOUR.png"),
    FIVE("resources/FIVE.png"),
    SIX("resources/SIX.png"),
    SEVEN("resources/SEVEN.png"),
    EIGHT("resources/EIGHT.png"),
    BLOCKED("resources/BOX_NORMAL.png"),
    OPEN("resources/FIELD_OPEN.png"),
    FLAG("resources/BOX_BLOCKED.png"),
    BOMB("resources/RABBIT.png");

    private String path;
    Map<String, Image> imageMap = new HashMap<>();

    Pictures(String path) {
        this.path = path;
    }

    public Image image() {
        if (imageMap.containsKey(path)) {
            return imageMap.get(path);
        } else {
            BufferedImage image;
            try {
                image = ImageIO.read(new File(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Image scaledImage = image.getScaledInstance(GameBoard.FIELD_MEASURE, GameBoard.FIELD_MEASURE, Image.SCALE_SMOOTH);
            imageMap.put(path, scaledImage);
            return scaledImage;
        }
    }
}
