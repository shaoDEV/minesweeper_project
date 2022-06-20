package de.shao.driver;

import resources.ResHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PictureController {

    private final boolean DEBUG = true;

    private static PictureController thisController = null;

    private int cursor;
    private int blocks;
    private int numbers;
    private int bomb;
    private int flag;
    private int scaleToSize;

    private ArrayList<Image> scaledBlockImages = new ArrayList<>();
    private ArrayList<Image> scaledNumberImages = new ArrayList<>();
    private HashMap<String, Image> systemResources = new HashMap<String, Image>();
    private Image cursorImage;
    private Image scaledBombImage;
    private Image scaledFlagImage;

    private Image missingImage;

    private PictureController() {
        cursor = 0;
        blocks = 0;
        numbers = 0;
        bomb = 0;
        flag = 0;
        scaleToSize = 64;
        imageLoader();
    }

    private PictureController(int scaleToSize) {
        cursor = 0;
        blocks = 0;
        numbers = 0;
        bomb = 0;
        flag = 0;
        this.scaleToSize = scaleToSize;
        imageLoader();
    }

    private PictureController(int skinSet, int scaleToSize) {
        cursor = skinSet;
        blocks = skinSet;
        numbers = skinSet;
        bomb = skinSet;
        flag = skinSet;
        this.scaleToSize = scaleToSize;
        imageLoader();
    }

    private PictureController(int cursor, int blocks, int numbers, int bomb, int flag, int scaleToSize) {
        this.cursor = cursor;
        this.blocks = blocks;
        this.numbers = numbers;
        this.bomb = bomb;
        this.flag = flag;
        this.scaleToSize = scaleToSize;
        imageLoader();
    }

    public static PictureController getPictureController(int skinSet, int scaleToSize) {
        thisController = new PictureController(skinSet, scaleToSize);
        return thisController;
    }

    public static PictureController getPictureController(int cursor, int blocks, int numbers, int bomb, int flag, int scaleToSize) {
        thisController = new PictureController(cursor, blocks, numbers, bomb, flag, scaleToSize);
        return thisController;
    }

    public static PictureController getPictureController() {
        thisController = new PictureController();
        return thisController;
    }

    public static PictureController getPictureController(int scaleToSize){
        thisController = new PictureController(scaleToSize);
        return thisController;
    }

    private void imageLoader() {
        loadMissingImage();
        loadCursor();
        loadBlocks();
        loadNumbers();
        loadBomb();
        loadFlag();
        loadSystemResources();
    }

    private Image scaleImage(BufferedImage imageToScale) {
        return imageToScale.getScaledInstance(scaleToSize, scaleToSize, Image.SCALE_SMOOTH);
    }

    private void loadMissingImage(){
        BufferedImage image;
        try {
            image = ImageIO.read(ResHelper.getResourcenStream("images/system/error.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        missingImage = scaleImage(image);
    }

    private void loadFlag() {
        BufferedImage image;
        try {
            image = ImageIO.read(ResHelper.getResourcenStream("images/" + flag + "/flag.png"));
        } catch (IOException e) {
            scaledFlagImage = missingImage;
            throw new RuntimeException(e);
        }

        scaledFlagImage = scaleImage(image);
    }

    private void loadCursor() {
        BufferedImage image;
        try {
            image = ImageIO.read(ResHelper.getResourcenStream("images/" + cursor + "/cursor.png"));
        } catch (IOException e) {
            cursorImage = missingImage;
            throw new RuntimeException(e);
        }

        cursorImage = image;
    }

    private void loadBlocks() {
        BufferedImage image;
        try {
            image = ImageIO.read(ResHelper.getResourcenStream("images/" + flag + "/block_closed.png"));
            scaledBlockImages.add(scaleImage(image));
            image = ImageIO.read(ResHelper.getResourcenStream("images/" + flag + "/block_open.png"));
            scaledBlockImages.add(scaleImage(image));
        } catch (IOException e) {
            scaledBlockImages.add(missingImage);
            scaledBlockImages.add(missingImage);
            throw new RuntimeException(e);
        }
    }

    private void loadNumbers() {
        BufferedImage image;
        for (int identifier = 1; identifier < 8; identifier++){
            try {
                image = ImageIO.read(ResHelper.getResourcenStream("images/" + numbers + "/" + identifier + ".png"));
                scaledNumberImages.add(scaleImage(image));
            } catch (IOException e) {
                scaledBlockImages.add(missingImage);
                e.printStackTrace();
            }
        }
    }

    private void loadBomb(){
        BufferedImage image;
        try {
            image = ImageIO.read(ResHelper.getResourcenStream("images/" + bomb + "/bomb.png"));
        } catch (IOException e) {
            scaledBombImage = missingImage;
            throw new RuntimeException(e);
        }
        scaledBombImage = scaleImage(image);
    }

    private void loadSystemResources(){
        BufferedImage image;
        try {
            image = ImageIO.read(ResHelper.getResourcenStream("images/system/ingame/backToMenu.png"));
            systemResources.put("backToMenu", image);
            image = ImageIO.read(ResHelper.getResourcenStream("images/system/ingame/startNewGame.png"));
            systemResources.put("startNewGame", image);
            image = ImageIO.read(ResHelper.getResourcenStream("images/system/ingame/10x10.png"));
            systemResources.put("background10", image);
            image = ImageIO.read(ResHelper.getResourcenStream("images/system/ingame/16x16.png"));
            systemResources.put("background16", image);
            image = ImageIO.read(ResHelper.getResourcenStream("images/system/ingame/fieldHovering.png"));
            systemResources.put("fieldHovering", scaleImage(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getSystemResources(String resourceName){
        return systemResources.get(resourceName);
    }

    public Image getBomb(){
        return scaledBombImage;
    }

    public Image getNumber(char numberIdentifier) {
        int numberIdentifierAsInt = Character.getNumericValue(numberIdentifier);
        if (numberIdentifierAsInt < 9 && numberIdentifierAsInt > 0) return scaledNumberImages.get(numberIdentifierAsInt-1);
        return missingImage;
    }

    public Image getBlock(boolean openBlock){
        if (openBlock) return scaledBlockImages.get(1);
        else return scaledBlockImages.get(0);
    }

    public Image getCursor(){
        return cursorImage;
    }

    public Image getFlag(){
        return scaledFlagImage;
    }
}





















