package de.shao.driver;

import resources.ResHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SystemResources {
    private static SystemResources systemResourcese = null;

    //Pasta Code
    public static Point FramePoint = null;
    public static Point GameFramePoint = null;
    public static boolean isGameActive = false;
    public static int actualID = 0;
    public static String actualUsername = "";
    public static long currentTimeInSec = 0;
    //Pasta Code Ende

    //Deklaration der Hash Map für alle System Bilder die vom Menu gebnutzt werden.
    Map<String, Image> imageMap = new HashMap<>();

    /**
     * @return Gibt die aktive Instanz von SystemResources zurück
     */
    public static SystemResources getInstance() {
        if (systemResourcese != null) return systemResourcese;
        return new SystemResources();
    }

    /**
     * Standartkonstruktor der die Methode zum laden der Bilder aufruft.
     */
    private SystemResources(){
        loadImages();
    }

    /**
     * Lade Methode der alle für das Menu benötigen Bilder vorlädt und in einer Hash Map speichert um diese schnell aufzurufen.
     */
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
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/customButtonDisable.png"));
            imageMap.put("customButtonDisable", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/buttonDisable.png"));
            imageMap.put("buttonDisable", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/icon.png"));
            imageMap.put("icon", masterImage);
            masterImage = ImageIO.read(ResHelper.getResourcenStream("images/system/menu/profilIcon_5.png"));
            imageMap.put("profilIcon_5", masterImage);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param fieldSize aktuelle Feldgröße
     * @return Gibt die Weite des Feldes anhand des Übergeben Parameters zurück
     */
    public int getFieldWidthBySize(int fieldSize){
        switch (fieldSize){
            case 10 -> {return 666;}
            case 16 -> {return 954;}
            case 25 -> {return 666;}
        }
        return 666;
    }
    /**
     * @param fieldSize aktuelle Feldgröße
     * @return Gibt die Höhe des Feldes anhand des Übergeben Parameters zurück
     */
    public int getFieldHeightBySize(int fieldSize){
        switch (fieldSize){
            case 10 -> {return 630;}
            case 16 -> {return 917;}
            case 25 -> {return 666;}
        }
        return 630;
    }

    /**
     * Sucht mithilfe des Keys das gescuhte Bild aus der Hashmap und liefert dieses zurück
     * @param key Bilder Key zur Suche in der HashMap
     * @return Gibt gesuchtes BIld zurück
     */
    public Image getSystemImage(String key){
        return imageMap.get(key);
    }

}
