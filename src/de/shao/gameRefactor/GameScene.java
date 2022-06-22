package de.shao.gameRefactor;

import de.shao.driver.PictureController;
import de.shao.driver.SystemConstraints;
import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class  GameScene {

    PictureController pictureController = null;

    //Feldparamter
    private int fieldSize = 0;
    private int fieldMeasure = 0;
    private int bombCount = 0;
    private int flagsPlaced = 0;

    //Flags zur übergabe an das Gameboard
    private Boolean activeGame = null;
    private boolean gameWon = false;

    //Definiert das Feld und handled alls Felder
    private Field[][] fieldMatrix;
    private Field[] allFields;

    //Deklariert die Eckpunkte des Spieles um es korrekt zu zeichnen
    private Point sceneCorner = null;
    private Point bottomRightBackgroundCorner = null;

    private long startTime;

    private int testingGetBombsPlaced;
    private int testingGetNumbersPlaced;
    private boolean testingGetPointClicked;



    public GameScene(Point upperLeftSceneCorner, Point bottomRightBackgroundCorner, int fieldSize, int bombCount, PictureController pictureController) {
        this.fieldSize = fieldSize;
        this.bombCount = bombCount;
        this.pictureController = pictureController;
        this.bottomRightBackgroundCorner = bottomRightBackgroundCorner;
        sceneCorner = upperLeftSceneCorner;
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
        if (activeGame != null && activeGame){
            SystemResources.currentTimeInSec = (System.currentTimeMillis() - startTime)/1000;
        }
        g2d.setColor(new Color(58, 254, 245));
        g2d.setFont(new Font("Minecraft", Font.PLAIN, 35));
        if (fieldSize == 10) g2d.drawString(SystemResources.currentTimeInSec+"", SystemConstraints.TEN_TEN_TIME.point().x,SystemConstraints.TEN_TEN_TIME.point().y);
        if (fieldSize == 16) g2d.drawString(SystemResources.currentTimeInSec+"", SystemConstraints.SIXTEEN_SIXTEEN_TIME.point().x,SystemConstraints.SIXTEEN_SIXTEEN_TIME.point().y);

        for (Field field : allFields) {
            field.drawField(g2d);
        }

        //Zeichne die visuelle Darstellung der noch über bleibenden Flaggen
        g2d.setColor(Color.GREEN);
        int tileSizeEachBomb = 300/bombCount;
        int firstTileSize = tileSizeEachBomb + (300%bombCount); //Die genau Größe für das erste Teil um den evtl. entstehenden Rest abzufangen
        if (flagsPlaced == 1) g2d.fillRect(bottomRightBackgroundCorner.x - 39, bottomRightBackgroundCorner.y - 468 + firstTileSize, 18, 300 - firstTileSize);
        else g2d.fillRect(bottomRightBackgroundCorner.x - 39, bottomRightBackgroundCorner.y - 468 + (tileSizeEachBomb * flagsPlaced), 18, 300 - (tileSizeEachBomb * flagsPlaced));


    }

    /**
     * Geht alle Felder durch un platziert zufällig eine Bombe sollte an dem gewählten Feld noch keine Bombe liegen
     */
    private void setRandomBombs() {
        int bombsLeft = bombCount;
        Random random = new Random();
        int randomField;

        while (bombsLeft > 0) {
            randomField = random.nextInt(allFields.length);
            if (!(allFields[randomField].getBottomPictureIdentifier() == 'b')) {
                allFields[randomField].setBottomPictureIdentifier('b');
                bombsLeft--;
                //Jedes mal wenn eine Bombe platziert wird, wird der Zähler hochgezöhlt damit im Testing geprüft werden kann ob das platzieren richtigt durchläuft.
                testingGetBombsPlaced ++;
            }
        }
    }

    /**
     * Gehe jedes Feld durch und prüfe die umliegenden Felder nach Bomben.
     */
    private void setNumbers() {
        //Durchlaufe alle Felder der Feldmatrix
        for (int verticalPosition = 0; verticalPosition < fieldMatrix.length; verticalPosition++) {
            for (int horizontalPosition = 0; horizontalPosition < fieldMatrix[verticalPosition].length; horizontalPosition++) {

                //Wenn das aktuelle Feld keine Bombe ist, gehe alls Felder drum herum durch und zähle die Bomben
                if (fieldMatrix[verticalPosition][horizontalPosition].getBottomPictureIdentifier() != 'b') {
                    int nearBombs = 0;
                    for (int i = horizontalPosition - 1; i < horizontalPosition + 2; i++) {
                        for (int j = verticalPosition - 1; j < verticalPosition + 2; j++) {
                            if (i >= 0 && i < fieldSize && j >= 0 && j < fieldSize) {
                                Field checkField = fieldMatrix[j][i];
                                if (checkField.getBottomPictureIdentifier() == 'b') {
                                    nearBombs++;
                                }
                            }
                        }
                        if (nearBombs > 0) fieldMatrix[verticalPosition][horizontalPosition].setBottomPictureIdentifier(Character.forDigit(nearBombs, 10)); //Wandle int in Char um (radix = Zahlensystem)
                    }
                }
                testingGetNumbersPlaced += 1;
            }
        }
    }

    /**
     * Prüft alle umliegenden Felder vom übergebenen Feld und öffnet dieeses wenn es möglich ist.
     * Ruft an dem geöffneten Feld diese Methode erneut auf
     * @param fieldToCheck
     */
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

    /**
     * Hilfsmethode um die eigentliche Methode mit Punkt und Button pressed aufzurufen um diese zu testen.
     * @param mouseEvent Event übergeben vom Panel
     */
    public void callSceneInteraction(MouseEvent mouseEvent) {
        sceneInteraction(mouseEvent.getPoint(), mouseEvent.getButton());
    }

    /**
     * Methode zum handlen des MausInputs. Sie fängt den Rechtsklick/Linksclick ab und führt das Feld öffnen an der richtigen Position aus.
     * @param mousePoint Mausposition vom Event was das Panel übergibt
     * @param mouseEvent Welcher Button auf dem übergeben Maus Event gedrückt wird.
     */
    public void sceneInteraction(Point mousePoint, int mouseEvent){
        if (startTime == 0) startTime = System.currentTimeMillis();
        activeGame = true;

        //Hilfsvariablen
        int xHandledField = (mousePoint.y - sceneCorner.y) / fieldMeasure;
        int yHandledField = (mousePoint.x - sceneCorner.x) / fieldMeasure;

        //Führt Button Funktion nur aus wenn sich die Maus auch wirklich im eigentlich Feld befindet
        if (xHandledField < fieldSize && yHandledField < fieldSize){
            Field handledField = fieldMatrix[xHandledField][yHandledField];

            //Bei Rechtclick wird eine Flagge gesetzt, sollten noch Flagen übrig sein
            if (mouseEvent == 3) {

                if (flagsPlaced < bombCount && !handledField.isOpen() && !handledField.isFlagged()) {
                    handledField.setFlagged(true);
                    flagsPlaced++;
                } else if (handledField.isFlagged()) {
                    handledField.setFlagged(false);
                    flagsPlaced--;
                }
                //Öffnet das Feld und ruft das rekursive öffnen mit dem gedrückten Feld auf.
            } else if (mouseEvent == 1) {

                if (!handledField.isOpen() && !handledField.isFlagged()) {
                    if (!handledField.isFlagged()) {
                        handledField.setOpen(true);
                        searchAndOpen(handledField);
                    }
                }
            }
        }

        //Testing ob der Mausclick funktioniert hat.
        if (mousePoint.equals(new Point(50, 50))) testingGetPointClicked = true;
        checkEndOfGame();
    }

    /**
     * Ausgelagertes Verlieren.
     * Es geht alle Felder durch und öffnet alle Bombenfelder sollte eine Bombe gedrückt werden.
     */
    private void lostRound(){
        for (Field field : allFields) if (field.getBottomPictureIdentifier() == 'b') field.setOpen(true);
        activeGame = false;
        gameWon = false;
    }

    /**
     * Ausgelagerte Win Methode
     * setzt den Status des Spiels auf ende und gewonnen damit diese Werte ans GameBoard zurpcgeben werden können.
     */
    private void wonRound(){
        activeGame = false;
        gameWon = true;
    }

    /**
     * Prüft ob das Spiel vorbei ist und alle nötigen Felder geöffnet sind.
     */
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

    /**
     * @return Gibt zurück ob das aktuelle Spiel noch aktiv ist
     */
    public Boolean isActiveGame() {
        return activeGame;
    }

    /**
     * @return Gibt zurück ob das aktuelle Spiel gewonnen ist oder nicht.
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * @return Gibt für das Testing zurück wie viele Bomben platziert wurden
     */
    public int getTestingGetBombsPlaced() {
        return testingGetBombsPlaced;
    }

    /**
     * @return Gibt für das Testing zurück wie viele Nummern platziert wurden
     */
    public int getTestingGetNumbersPlaced() {
        return testingGetNumbersPlaced;
    }

    /**
     * Setzt das Spiel fürs Testing auf aktiv
     * @param activeGame status auf den das Spiel gesetzt wird
     */
    public void setActiveGame(Boolean activeGame) {
        this.activeGame = activeGame;
    }

    /**
     * Setzt das Spiel fürs Testing auf aktiv
     * @param gameWon status auf den das Spiel gesetzt wird
     */
    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    /**
     * @return Gibt zurück ob der Click durchgeführt wurde.
     */
    public boolean isTestingGetPointClicked() {
        return testingGetPointClicked;
    }
}
