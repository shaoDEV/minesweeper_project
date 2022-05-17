package de.shao.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public enum Pictures{

    ONE("src/resources/ONE.png"),
    TWO("src/resources/TWO.png"),
    THREE("src/resources/THREE.png"),
    FOUR("src/resources/FOUR.png"),
    FIVE("src/resources/FIVE.png"),
    SIX("src/resources/SIX.png"),
    SEVEN("src/resources/SEVEN.png"),
    EIGHT("src/resources/EIGHT.png"),
    BLOCKED("src/resources/CLOSED_FIELD.png"),
    OPEN("src/resources/OPEN_FIELD.png"),
    FLAG("src/resources/FLAG.png"),
    BOMB("src/resources/BOMB.png");

    private String path;
    Map<String, BufferedImage> imageMap = new HashMap<String, BufferedImage>();


    Pictures(String path){
        this.path = path;
    }

    public BufferedImage image(){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
}
