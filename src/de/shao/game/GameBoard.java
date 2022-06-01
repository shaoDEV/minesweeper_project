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

    public static int fieldMeasure = 0; //Groeße der Bilder
    private int fieldSize = 0;
    private int bombCount = 0;

    private int boardWidth = 0;
    private int boardHeight = 0;

    private Field[][] fieldMatrix;
    private Field[] allFields;

    private int flagsPlaced = 0;
    public static PictureController pictureController;

    private boolean gameIsOver = false;
    private boolean lostRound = false;

    private JFrame sirFrameALot = null;
    private GameBoardBackground backgroundPanel = null;

    GameBoard(JFrame frame, GameBoardBackground backgroundPanel, int fieldSize, int fieldMeasure) {
        this.fieldSize = fieldSize;
        GameBoard.fieldMeasure = fieldMeasure;
        sirFrameALot = frame;
        this.backgroundPanel = backgroundPanel;

        this.boardWidth = fieldSize * fieldMeasure;
        this.boardHeight = this.boardWidth;
        this.bombCount = this.fieldSize;

        this.setBounds(66,72, boardWidth, boardHeight);
        pictureController = PictureController.getPictureController(1, fieldMeasure);
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));
        fieldMatrix = new Field[fieldSize][fieldSize];
        allFields = new Field[fieldSize * fieldSize];
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        init();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameIsOver == false) fieldClicked(e);
            }
        });


    }

    private void fieldClicked(MouseEvent e) {
        Field handledField = fieldMatrix[(e.getY())/ fieldMeasure][(e.getX())/ fieldMeasure];
        if (e.getButton() == 3){
            if (flagsPlaced < bombCount && !handledField.isOpen() && !handledField.isFlagged()){
                handledField.setFlagged(true);
                flagsPlaced++;
                backgroundPanel.setFlagsLeft(bombCount - flagsPlaced);
                redrawOuterObjects();
            }
            else if (handledField.isFlagged()){
                handledField.setFlagged(false);
                flagsPlaced--;
                backgroundPanel.setFlagsLeft(bombCount - flagsPlaced);
                redrawOuterObjects();
            }
        } else if (e.getButton() == 1){
            if (!handledField.isOpen() && !handledField.isFlagged()){
                if (!handledField.isFlagged()){
                    handledField.setOpen(true);
                    checkEndOfGame();
                    searchAndOpen(handledField);
                    redrawOuterObjects();
                }
            }
        }
        redrawOuterObjects();
        repaint();
    }



    private void searchAndOpen(Field fieldToCheck) {
        int fieldToCheckY = fieldToCheck.getY() / fieldMeasure;
        int fieldToCheckX = fieldToCheck.getX() / fieldMeasure;

        //Mit freundlicher Unterstzung von Borgi
        for (int i = fieldToCheckX - 1; i < fieldToCheckX + 2; i++) {
            for (int j = fieldToCheckY - 1; j < fieldToCheckY + 2; j++) {
                if (i >= 0 && i < fieldSize && j >= 0 && j < fieldSize && fieldToCheck.getBottomPictureIdentifier() == ' ') {
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
        if (gameIsOver) drawEndMenu(g2d);
    }

    private void drawEndMenu(Graphics2D g2d){
        if (lostRound) {
            System.out.println("Verloren!");
            System.out.println(pictureController.getSystemResources("startNewGame"));
            g2d.drawImage(pictureController.getSystemResources("startNewGame"), (boardWidth/2)-150,(boardHeight/2)-50, null);
            g2d.drawImage(pictureController.getSystemResources("backToMenu"), (boardWidth/2)-150,(boardHeight/2)+50, null);
        } else {
            System.out.println("Gewonnen!");
        }
    }

    private void lostRound(){
        for (Field field : allFields) if (field.getBottomPictureIdentifier() == 'b') field.setOpen(true);
        gameIsOver = true;
        lostRound = true;
        redrawOuterObjects();
        repaint();
    }

    private void wonRound(){
        gameIsOver = true;
        redrawOuterObjects();
        repaint();
    }

    private void checkEndOfGame(){
        int fieldsOpened = 0;
        for (Field field : allFields) {
            if (field.isOpen() && field.getBottomPictureIdentifier() == 'b'){
                lostRound();
            } else if (field.isOpen()){
                fieldsOpened++;
            }
        }
        if (fieldsOpened >= allFields.length - bombCount){
            wonRound();
        }
    }

    private void redrawOuterObjects(){
        backgroundPanel.repaint();
        sirFrameALot.repaint();
    }

    private void init() {
        int fielsGenerated = 0;
        for (int verticalPosition = 0; verticalPosition < fieldMatrix.length; verticalPosition++) {
            for (int horizontalPosition = 0; horizontalPosition < fieldMatrix[verticalPosition].length; horizontalPosition++) {

                Field tempField = new Field((horizontalPosition * fieldMeasure), (verticalPosition * fieldMeasure));

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
        int bombsLeft = bombCount;
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
                int fieldToCheckY = fieldToCheck.getY() / fieldMeasure;
                int fieldToCheckX = fieldToCheck.getX() / fieldMeasure;
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

