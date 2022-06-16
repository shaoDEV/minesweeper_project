package de.shao.driver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SystemResources {
    private final boolean DEBUG = true;
    private static SystemResources systemResourcese = null;

    Map<String, Image> imageMap = new HashMap<>();

    public static SystemResources getInstance() {
        if (systemResourcese != null) return systemResourcese;
        return new SystemResources();
    }

    private SystemResources(){
        loadImages();
    }

    private void loadImages(){
        BufferedImage masterImage;
        try {
            if (DEBUG) {
                masterImage = ImageIO.read(new File("resources/images/system/menu/mainframe.png"));
                imageMap.put("mainframe", masterImage);
                masterImage = ImageIO.read(new File("resources/images/system/menu/gamestart.png"));
                imageMap.put("gamestart", masterImage);
                masterImage = ImageIO.read(new File("resources/images/system/menu/safebar_1.png"));
                imageMap.put("safebar_1", masterImage);
                masterImage = ImageIO.read(new File("resources/images/system/menu/safebar_2.png"));
                imageMap.put("safebar_2", masterImage);
                masterImage = ImageIO.read(new File("resources/images/system/menu/safebar_3.png"));
                imageMap.put("safebar_3", masterImage);
                masterImage = ImageIO.read(new File("resources/images/system/menu/gamestart_fade.png"));
                imageMap.put("gamestart_fade", masterImage);
                masterImage = ImageIO.read(new File("resources/images/system/menu/mainframe_profiles.png"));
                imageMap.put("mainframe_profiles", masterImage);
            } else {
                masterImage = ImageIO.read(getClass().getResource("/images/system/error.png"));
                imageMap.put("mainframe", masterImage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Image getSystemImage(String key){
        return imageMap.get(key);
    }

}
