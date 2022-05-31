package de.shao.game;

import de.shao.driver.PictureController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameBoard extends JPanel {

    public static final int FIELD_MEASURE = 48; //Groeße der Bilder
    private final int FIELD_SIZE = 10;
    private final int BOMB_COUNT = FIELD_SIZE;

    private final int BOARD_WIDTH = FIELD_SIZE * FIELD_MEASURE;
    private final int BOARD_HEIGHT = BOARD_WIDTH;

    private Field[][] fieldMatrix;
    private Field[] allFields;

    private int flagsPlaced = 0;
    public static PictureController pictureController;

    private boolean firstClick = false;

    private JFrame sirFrameALot = null;
    private GameBoardBackground backgroundPanel = null;

    GameBoard(JFrame frame, GameBoardBackground backgroundPanel) {
        sirFrameALot = frame;
        this.backgroundPanel = backgroundPanel;
        this.setBounds(66,72,BOARD_WIDTH,BOARD_HEIGHT);
        pictureController = PictureController.getPictureController(1, FIELD_MEASURE);
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        fieldMatrix = new Field[FIELD_SIZE][FIELD_SIZE];
        allFields = new Field[FIELD_SIZE * FIELD_SIZE];
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        init();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fieldClicked(e);
            }
        });

    }

    private void fieldClicked(MouseEvent e) {
        Field handledField = fieldMatrix[(e.getY())/FIELD_MEASURE][(e.getX())/FIELD_MEASURE];
        if (e.getButton() == 3){
            if (flagsPlaced < BOMB_COUNT && !handledField.isOpen() && !handledField.isFlagged()){
                handledField.setFlagged(true);
                flagsPlaced++;
                backgroundPanel.setFlagsLeft(BOMB_COUNT - flagsPlaced);
                redrawOuterObjects();
            }
            else if (handledField.isFlagged()){
                handledField.setFlagged(false);
                flagsPlaced--;
                backgroundPanel.setFlagsLeft(BOMB_COUNT - flagsPlaced);
                redrawOuterObjects();
            }
        } else if (e.getButton() == 1){
            if (!handledField.isOpen() && !handledField.isFlagged()){
                if (handledField.getBottomPictureIdentifier() == 'b') lostRound();
                else if (!handledField.isFlagged()){
                    handledField.setOpen(true);
                    searchAndOpen(handledField);
                    redrawOuterObjects();
                }
            }
        }
        redrawOuterObjects();
        repaint();
    }

    private void lostRound(){
        for (Field field : allFields) if (field.getBottomPictureIdentifier() == 'b') field.setOpen(true);
        redrawOuterObjects();
        repaint();
        System.out.println("verloren");
    }

    private void searchAndOpen(Field fieldToCheck) {
        int fieldToCheckY = fieldToCheck.getY() / FIELD_MEASURE;
        int fieldToCheckX = fieldToCheck.getX() / FIELD_MEASURE;

        //Mit freundlicher Unterstzung von Borgi
        for (int i = fieldToCheckX - 1; i < fieldToCheckX + 2; i++) {
            for (int j = fieldToCheckY - 1; j < fieldToCheckY + 2; j++) {
                if (i >= 0 && i < FIELD_SIZE && j >= 0 && j < FIELD_SIZE && fieldToCheck.getBottomPictureIdentifier() == ' ') {
                    Field nextField = fieldMatrix[j][i];
                    if (nextField.getBottomPictureIdentifier() != 'b' && !nextField.isOpen() && !nextField.isFlagged()) {
                        nextField.setOpen(true);
                        searchAndOpen(nextField);
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawFields(g2d);
    }

    private void redrawOuterObjects(){
        backgroundPanel.repaint();
        sirFrameALot.repaint();
    }

    private void init() {
        int fielsGenerated = 0;
        for (int verticalPosition = 0; verticalPosition < fieldMatrix.length; verticalPosition++) {
            for (int horizontalPosition = 0; horizontalPosition < fieldMatrix[verticalPosition].length; horizontalPosition++) {

                Field tempField = new Field((horizontalPosition * FIELD_MEASURE), (verticalPosition * FIELD_MEASURE));

                allFields[fielsGenerated] = tempField;

                fieldMatrix[verticalPosition][horizontalPosition] = tempField;

                fielsGenerated++;
            }
        }
        setRandomBombs();
        setNumbers();
    }

    private void drawFields(Graphics2D g2d) {
        for (Field field : allFields) {
            field.drawField(g2d);
        }
    }

    private void setRandomBombs() {
        int bombsLeft = BOMB_COUNT;
        Random random = new Random();
        int randomField;

        while (bombsLeft > 0) {
            randomField = random.nextInt(allFields.length);
            if (!(allFields[randomField].getBottomPictureIdentifier() == 'b')) {
                allFields[randomField].setBottomPictureIdentifier('b');
                bombsLeft--;
            }
        }
        this.repaint();
    }

    private void setNumbers() {
        for (int verticalPosition = 0; verticalPosition < fieldMatrix.length; verticalPosition++) {
            for (int horizontalPosition = 0; horizontalPosition < fieldMatrix[verticalPosition].length; horizontalPosition++) {

                Field fieldToCheck = fieldMatrix[verticalPosition][horizontalPosition];
                int fieldToCheckY = fieldToCheck.getY() / FIELD_MEASURE;
                int fieldToCheckX = fieldToCheck.getX() / FIELD_MEASURE;
                if (fieldToCheck.getBottomPictureIdentifier() != 'b') {
                    int nearBombs = 0;
                    for (int i = fieldToCheckX - 1; i < fieldToCheckX + 2; i++) {
                        for (int j = fieldToCheckY - 1; j < fieldToCheckY + 2; j++) {
                            if (i >= 0 && i < 10 && j >= 0 && j < 10) {
                                Field checkField = fieldMatrix[j][i];
                                if (checkField.getBottomPictureIdentifier() == 'b') {
                                    nearBombs++;
                                }
                            }
                        }
                        if (nearBombs > 0) fieldToCheck.setBottomPictureIdentifier(Character.forDigit(nearBombs, 10));
                    }
                }
            }
            this.repaint();
        }
    }
}

