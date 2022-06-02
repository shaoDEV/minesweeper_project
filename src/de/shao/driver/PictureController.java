package de.shao.driver;

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
            if (DEBUG) {
                image = ImageIO.read(new File("resources/images/system/error.png"));

            } else {
                image = ImageIO.read(getClass().getResource("/images/system/error.png"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        missingImage = scaleImage(image);
    }

    private void loadFlag() {
        BufferedImage image;
        try {
            if (DEBUG) {
                image = ImageIO.read(new File("resources/images/" + flag + "/flag.png"));
            } else {
                image = ImageIO.read(getClass().getResource("/images/" + flag + "/flag.png"));
            }
        } catch (IOException e) {
            scaledFlagImage = missingImage;
            throw new RuntimeException(e);
        }

        scaledFlagImage = scaleImage(image);
    }

    private void loadCursor() {
        BufferedImage image;
        try {
            if (DEBUG) {
                image = ImageIO.read(new File("resources/images/" + cursor + "/cursor.png"));

            } else {
                image = ImageIO.read(getClass().getResource("/images/" + cursor + "/cursor.png"));
            }
        } catch (IOException e) {
            cursorImage = missingImage;
            throw new RuntimeException(e);
        }

        cursorImage = image;
    }

    private void loadBlocks() {
        BufferedImage image;
        try {
            if (DEBUG) {
                image = ImageIO.read(new File("resources/images/" + blocks + "/block_closed.png"));
                scaledBlockImages.add(scaleImage(image));
                image = ImageIO.read(new File("resources/images/" + blocks + "/block_open.png"));
                scaledBlockImages.add(scaleImage(image));

            } else {
                image = ImageIO.read(getClass().getResource("/images/" + blocks + "/block_closed.png"));
                scaledBlockImages.add(scaleImage(image));
                image = ImageIO.read(getClass().getResource("/images/" + blocks + "/block_open.png"));
                scaledBlockImages.add(scaleImage(image));
            }
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
                if (DEBUG) {
                    image = ImageIO.read(new File("resources/images/" + numbers + "/" + identifier + ".png"));
                    scaledNumberImages.add(scaleImage(image));
                }else {
                    image = ImageIO.read(getClass().getResource("/images/" + numbers + "/" + identifier + ".png"));
                    scaledBlockImages.add(scaleImage(image));
                }
            } catch (IOException e) {
                scaledBlockImages.add(missingImage);
                e.printStackTrace();
            }
        }
    }

    private void loadBomb(){
        BufferedImage image;
        try {
            if (DEBUG) {
                image = ImageIO.read(new File("resources/images/" + bomb + "/bomb.png"));

            } else {
                image = ImageIO.read(getClass().getResource("/images/" + bomb + "/bomb.png"));
            }
        } catch (IOException e) {
            scaledBombImage = missingImage;
            throw new RuntimeException(e);
        }
        scaledBombImage = scaleImage(image);
    }

    private void loadSystemResources(){
        BufferedImage image;
        try {
            if (DEBUG) {
                image = ImageIO.read(new File("resources/images/system/backToMenu.png"));
                systemResources.put("backToMenu", image);
                image = ImageIO.read(new File("resources/images/system/startNewGame.png"));
                systemResources.put("startNewGame", image);
                image = ImageIO.read(new File("resources/images/system/10x10.png"));
                systemResources.put("background", image);
            }else{
                image = ImageIO.read(getClass().getResource("resources/images/system/backToMenu.png"));
                systemResources.put("backToMenu", image);
                image = ImageIO.read(getClass().getResource("resources/images/system/startNewGame.png"));
                systemResources.put("startNewGame", image);
                image = ImageIO.read(getClass().getResource("resources/images/system/10x10.png"));
                systemResources.put("background", image);
            }
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





















