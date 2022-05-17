package de.shao.game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameBoard extends JPanel {

    public static final int FIELD_MEASURE = 48; //Groeße der Bilder
    private final int FIELD_SIZE = 10;
    private final int BOMB_COUNT = FIELD_SIZE;

    private final int BOARD_WIDTH = FIELD_MEASURE * FIELD_SIZE + 1;
    private final int BOARD_HEIGHT = BOARD_WIDTH;

    private Field[][] fieldMatrix;
    private Field[] allFields;

    GameBoard() {
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(Color.GRAY);
        fieldMatrix = new Field[FIELD_SIZE][FIELD_SIZE];
        allFields = new Field[FIELD_SIZE * FIELD_SIZE];
        init();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFields((Graphics2D) g);
    }

    private void init() {
        int fielsGenerated = 0;
        for (int verticalPosition = 0; verticalPosition < fieldMatrix.length; verticalPosition++) {
            for (int horizontalPosition = 0; horizontalPosition < fieldMatrix[verticalPosition].length; horizontalPosition++) {

                Field tempField = new Field(horizontalPosition * FIELD_MEASURE, verticalPosition * FIELD_MEASURE);

                allFields[fielsGenerated] = tempField;

                fieldMatrix[verticalPosition][horizontalPosition] = tempField;

                fielsGenerated++;
            }
        }
        setRandomBombs();
        setNumbers();
    }

    private void drawFields(Graphics2D g2d) {
        for (Field field:allFields) {
            field.drawField(g2d);
        }
    }

    private void setRandomBombs() {
        int bombsLeft = BOMB_COUNT;
        int maxiumFields = allFields.length;
        Random random = new Random();
        int randomField;

        while (bombsLeft > 0) {
            randomField = random.nextInt(maxiumFields);
            if (!(allFields[randomField].getBottomPictureIdentifier() == 'b')){
                allFields[randomField].setBottomPictureIdentifier('b');
                allFields[randomField].setOpenFlag(true);
                this.repaint();
                bombsLeft--;
            }
        }
    }

    private void setNumbers(){
        for (int verticalPosition = 0; verticalPosition < fieldMatrix.length; verticalPosition++) {
            for (int horizontalPosition = 0; horizontalPosition < fieldMatrix[verticalPosition].length; horizontalPosition++) {
                if (fieldMatrix[verticalPosition][horizontalPosition].getBottomPictureIdentifier() != 'b'){
                    int nearBombs = 0;
                    if (verticalPosition > 0 && horizontalPosition > 0){
                        if (fieldMatrix[verticalPosition-1][horizontalPosition-1].getBottomPictureIdentifier() == 'b') nearBombs++; //links Oben nach Bombe checken
                    }
                    if (verticalPosition > 0){
                        if (fieldMatrix[verticalPosition-1][horizontalPosition].getBottomPictureIdentifier() == 'b') nearBombs++; //Oben nach Bombe schauen
                    }
                    if (verticalPosition > 0 && horizontalPosition < FIELD_SIZE-1){
                        if (fieldMatrix[verticalPosition-1][horizontalPosition+1].getBottomPictureIdentifier() == 'b') nearBombs++; //Recht soben nach Bombe schauen
                    }
                    if (verticalPosition < FIELD_SIZE-1 && horizontalPosition > 0){
                        if (fieldMatrix[verticalPosition+1][horizontalPosition-1].getBottomPictureIdentifier() == 'b') nearBombs++; //unten Links nach Bombe checken
                    }
                    if (verticalPosition < FIELD_SIZE-1) {
                        if (fieldMatrix[verticalPosition + 1][horizontalPosition].getBottomPictureIdentifier() == 'b') nearBombs++;//unten nach Bombe checken
                    }
                    if (verticalPosition < FIELD_SIZE-1 && horizontalPosition < FIELD_SIZE-1){
                        if (fieldMatrix[verticalPosition+1][horizontalPosition+1].getBottomPictureIdentifier() == 'b') nearBombs++; //unten Rechts nach Bombe checken
                    }
                    if (horizontalPosition < FIELD_SIZE-1){
                        if (fieldMatrix[verticalPosition][horizontalPosition+1].getBottomPictureIdentifier() == 'b') nearBombs++; //Rechts nach Bombe checken
                    }
                    if (horizontalPosition > 0){
                        if (fieldMatrix[verticalPosition][horizontalPosition-1].getBottomPictureIdentifier() == 'b') nearBombs++; //links nach Bombe checken
                    }
                    if (nearBombs > 0){
                        fieldMatrix[verticalPosition][horizontalPosition].setBottomPictureIdentifier(Character.forDigit(nearBombs,10));
                        System.out.println(nearBombs);
                    }
                }
            }
        }
        this.repaint();
    }
}

