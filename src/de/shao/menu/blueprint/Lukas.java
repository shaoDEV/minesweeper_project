package de.shao.menu.blueprint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Lukas {

    ArrayList<Image> images = new ArrayList<Image>();
    Lukas(){
        init();
    }

    void init(){
        Image image;
        try {
            image = ImageIO.read(new File("resources/images/1/1.png"));
            images.add(image);
            image = ImageIO.read(new File("resources/images/1/2.png"));
            images.add(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Lukas lukas = new Lukas();
        for (Image image: lukas.images) {
            System.out.println(image);
        }
    }
}
