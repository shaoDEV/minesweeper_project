package de.shao.gameRefactor;

import de.shao.driver.PictureController;
import de.shao.gameRefactor.Field;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameScene {

    PictureController pictureController = null;

    private int fieldSize = 0;
    private int fieldMeasure = 0;
    private int bombCount = 0;
    private int flagsPlaced = 0;

    private Field[][] fieldMatrix;
    private Field[] allFields;

    private Point sceneCorner = null;

    public GameScene(Point upperRightSceneCorner, int fieldSize, int bombCount, PictureController pictureController) {
        this.fieldSize = fieldSize;
        this.bombCount = bombCount;
        this.pictureController = pictureController;
        sceneCorner = upperRightSceneCorner;
        fieldMeasure = pictureController.getBomb().getHeight(null);

        fieldMatrix = new Field[fieldSize][fieldSize];
        allFields = new Field[fieldSize * fieldSize];

        initialize();
    }

    private void initialize() {
        int fieldsGenerated = 0;
        for (int verticalPosition = 0; verticalPosition < fieldMatrix.length; verticalPosition++) {
            for (int horizontalPosition = 0; horizontalPosition < fieldMatrix[verticalPosition].length; horizontalPosition++) {

                Field tempField = new Field((horizontalPosition * fieldMeasure) + sceneCorner.x, (verticalPosition * fieldMeasure) + sceneCorner.y, pictureController);

                allFields[fieldsGenerated] = tempField;

                fieldMatrix[verticalPosition][horizontalPosition] = tempField;

                fieldsGenerated++;
            }
        }
        setRandomBombs();
        setNumbers();
    }

    public void drawScene(Graphics2D g2d) {
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
    }

    private void setNumbers() {
        //Durchlaufe alle Felder der Feldmatrix
        for (int verticalPosition = 0; verticalPosition < fieldMatrix.length; verticalPosition++) {
            for (int horizontalPosition = 0; horizontalPosition < fieldMatrix[verticalPosition].length; horizontalPosition++) {

                //Wenn das aktuelle Feld keine Bombe ist, gehe alls Felder drum herum durch und zähle die Bomben
                if (fieldMatrix[verticalPosition][horizontalPosition].getBottomPictureIdentifier() != 'b') {
                    int nearBombs = 0;
                    for (int i = horizontalPosition - 1; i < horizontalPosition + 2; i++) {
                        for (int j = verticalPosition - 1; j < verticalPosition + 2; j++) {
                            if (i >= 0 && i < 10 && j >= 0 && j < 10) {
                                Field checkField = fieldMatrix[j][i];
                                if (checkField.getBottomPictureIdentifier() == 'b') {
                                    nearBombs++;
                                }
                            }
                        }
                        if (nearBombs > 0)
                            fieldMatrix[verticalPosition][horizontalPosition].setBottomPictureIdentifier(Character.forDigit(nearBombs, 10)); //Wandle int in Char um (radix = Zahlensystem)
                    }
                }
            }
        }
    }

    private void searchAndOpen(Field fieldToCheck) {
        int fieldToCheckY = (fieldToCheck.getY() - sceneCorner.y) / fieldMeasure;
        int fieldToCheckX = (fieldToCheck.getX() - sceneCorner.x) / fieldMeasure;

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

    public void sceneInteraction(MouseEvent mouseEvent) {
        Field handledField = fieldMatrix[(mouseEvent.getY() - sceneCorner.y) / fieldMeasure][(mouseEvent.getX() - sceneCorner.x) / fieldMeasure];
        if (mouseEvent.getButton() == 3) {
            if (flagsPlaced < bombCount && !handledField.isOpen() && !handledField.isFlagged()) {
                handledField.setFlagged(true);
                flagsPlaced++;
            } else if (handledField.isFlagged()) {
                handledField.setFlagged(false);
                flagsPlaced--;
            }
        } else if (mouseEvent.getButton() == 1) {
            if (!handledField.isOpen() && !handledField.isFlagged()) {
                if (!handledField.isFlagged()) {
                    handledField.setOpen(true);
                    searchAndOpen(handledField);
                }
            }
        }
    }
}
