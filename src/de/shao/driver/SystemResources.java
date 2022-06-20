package de.shao.driver;

import resources.ResHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SystemResources {
    private final boolean DEBUG = true;
    private static SystemResources systemResourcese = null;

    //Pasta Code
    public static Point FramePoint = null;
    public static Point GameFramePoint = null;
    public static boolean isGameActive = false;
    public static int actualID = 0;
    public static String actualUsername = "";
    //Pasta Code Ende

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
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/mainframe.png"));
            imageMap.put("mainframe", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/gamestart.png"));
            imageMap.put("gamestart", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/safebar_1.png"));
            imageMap.put("safebar_1", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/safebar_2.png"));
            imageMap.put("safebar_2", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/safebar_3.png"));
            imageMap.put("safebar_3", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/gamestart_fade.png"));
            imageMap.put("gamestart_fade", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/profilIcon_1.png"));
            imageMap.put("profilIcon_1", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/profilIcon_2.png"));
            imageMap.put("profilIcon_2", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/profilIcon_3.png"));
            imageMap.put("profilIcon_3", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/neuesProfil.png"));
            imageMap.put("neuesProfil", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/deleteProfil.png"));
            imageMap.put("deleteProfil", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/close.png"));
            imageMap.put("close", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/profilIcon_4.png"));
            imageMap.put("profilIcon_4", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/arrowLeft.png"));
            imageMap.put("arrowLeft", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/arrowRight.png"));
            imageMap.put("arrowRight", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/button.png"));
            imageMap.put("button", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/customButton.png"));
            imageMap.put("customButton", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/customButtonHover.png"));
            imageMap.put("customButtonHover", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/buttonHover.png"));
            imageMap.put("buttonHover", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/arrowLeftHover.png"));
            imageMap.put("arrowLeftHover", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/arrowRightHover.png"));
            imageMap.put("arrowRightHover", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/backToProfile.png"));
            imageMap.put("backToProfile", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/backToProfileHover.png"));
            imageMap.put("backToProfileHover", masterImage);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getFieldWidthBySize(int fieldSize){
        switch (fieldSize){
            case 10 -> {return 666;}
            case 16 -> {return 954;}
            case 25 -> {return 666;}
        }
        return 666;
    }
    public int getFieldHeightBySize(int fieldSize){
        switch (fieldSize){
            case 10 -> {return 630;}
            case 16 -> {return 917;}
            case 25 -> {return 666;}
        }
        return 630;
    }

    public Image getSystemImage(String key){
        return imageMap.get(key);
    }

}
