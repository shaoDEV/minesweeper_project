package de.shao.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextHitInfo;
import java.util.Random;

public class GameBoard extends JPanel{

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
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fieldClicked(e);
            }
        });
    }

    private void fieldClicked(MouseEvent e){
        int xCordinateClicked = e.getX()/FIELD_MEASURE;
        int yCordinateClicked = e.getY()/FIELD_MEASURE;
        if (!(fieldMatrix[yCordinateClicked][xCordinateClicked].getBottomPictureIdentifier() == 'b')){
            fieldMatrix[yCordinateClicked][xCordinateClicked].pressed(e.getButton());
            repaint();
            //searchAndOpen(fieldMatrix[yCordinateClicked][xCordinateClicked]);
        }else{
            int input = JOptionPane.showConfirmDialog(null, "Willst du erneut spielen ?", "Erneut spielen ?",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            switch (input){
                case 0 -> System.out.println("Geht nicht!");
                case 1 -> System.out.println("Pech!");
            }
        }
    }

//    private void searchAndOpen(Field fieldToCheck){
//        if (fieldToCheck.getY()/FIELD_MEASURE > 0 && fieldToCheck.getX()/FIELD_MEASURE > 0){
//            if (!(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE-1][fieldToCheck.getX()/FIELD_MEASURE-1].getBottomPictureIdentifier() == 'b')){
//                fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE-1][fieldToCheck.getX()/FIELD_MEASURE-1].setOpenFlag(true);
//                searchAndOpen(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE-1][fieldToCheck.getX()/FIELD_MEASURE-1]);
//            }
//        }
//        if (fieldToCheck.getY()/FIELD_MEASURE > 0){
//            if (!(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE-1][fieldToCheck.getX()/FIELD_MEASURE].getBottomPictureIdentifier() == 'b')){
//                fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE-1][fieldToCheck.getX()/FIELD_MEASURE].setOpenFlag(true);
//                searchAndOpen(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE-1][fieldToCheck.getX()/FIELD_MEASURE]);
//            }
//        }
//        if (fieldToCheck.getY()/FIELD_MEASURE > 0 && fieldToCheck.getX()/FIELD_MEASURE < FIELD_SIZE-1){
//            if (!(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE-1][fieldToCheck.getX()/FIELD_MEASURE+1].getBottomPictureIdentifier() == 'b')){
//                fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE-1][fieldToCheck.getX()/FIELD_MEASURE+1].setOpenFlag(true);
//                searchAndOpen(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE-1][fieldToCheck.getX()/FIELD_MEASURE+1]);
//            }
//        }
//        if (fieldToCheck.getY()/FIELD_MEASURE < FIELD_SIZE-1 && fieldToCheck.getX()/FIELD_MEASURE > 0){
//            if (!(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE+1][fieldToCheck.getX()/FIELD_MEASURE-1].getBottomPictureIdentifier() == 'b')){
//                fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE+1][fieldToCheck.getX()/FIELD_MEASURE-1].setOpenFlag(true);
//                searchAndOpen(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE+1][fieldToCheck.getX()/FIELD_MEASURE-1]);
//            }
//        }
//        if (fieldToCheck.getY()/FIELD_MEASURE < FIELD_SIZE-1) {
//            if (!(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE + 1][fieldToCheck.getX()/FIELD_MEASURE].getBottomPictureIdentifier() == 'b')){
//                fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE + 1][fieldToCheck.getX()/FIELD_MEASURE].setOpenFlag(true);
//                searchAndOpen(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE + 1][fieldToCheck.getX()/FIELD_MEASURE]);
//            }
//        }
//        if (fieldToCheck.getY()/FIELD_MEASURE < FIELD_SIZE-1 && fieldToCheck.getX()/FIELD_MEASURE < FIELD_SIZE-1){
//            if (!(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE+1][fieldToCheck.getX()/FIELD_MEASURE+1].getBottomPictureIdentifier() == 'b')){
//                fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE+1][fieldToCheck.getX()/FIELD_MEASURE+1].setOpenFlag(true);
//                searchAndOpen(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE+1][fieldToCheck.getX()/FIELD_MEASURE+1]);
//            }
//        }
//        if (fieldToCheck.getX()/FIELD_MEASURE < FIELD_SIZE-1){
//            if (!(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE][fieldToCheck.getX()/FIELD_MEASURE+1].getBottomPictureIdentifier() == 'b')){
//                fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE][fieldToCheck.getX()/FIELD_MEASURE+1].setOpenFlag(true);
//                searchAndOpen(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE][fieldToCheck.getX()/FIELD_MEASURE+1]);
//            }
//        }
//        if (fieldToCheck.getX()/FIELD_MEASURE > 0){
//            if (!(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE][fieldToCheck.getX()/FIELD_MEASURE-1].getBottomPictureIdentifier() == 'b')){
//                fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE][fieldToCheck.getX()/FIELD_MEASURE-1].setOpenFlag(true);
//                searchAndOpen(fieldMatrix[fieldToCheck.getY()/FIELD_MEASURE][fieldToCheck.getX()/FIELD_MEASURE-1]);
//            }
//        }
//        repaint();
//    }

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
                    }
                }
            }
        }
        this.repaint();
    }
}

