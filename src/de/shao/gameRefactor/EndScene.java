package de.shao.gameRefactor;

import de.shao.driver.PictureController;
import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EndScene {

    PictureController pictureController = null;
    Point drawPoint = null;

    Rectangle backToMenuArea = null;
    Rectangle startNewGameArea = null;

    Image backToMenu = null;
    Image startNewGame = null;

    boolean goBackToMenu = false;
    boolean startANewGame = false;


    /**
     * Konstruktor
     * @param pictureController Picturcontroller der alle benötigen Bilder enthält.
     * @param drawPoint gibt an, an welcher Stelle die Endgame Buttons gezeichnet werden sollen
     */
    EndScene(PictureController pictureController, Point drawPoint){
        this.pictureController = pictureController;
        this.drawPoint = drawPoint;

        //Lädt die zwei benötigten Button Images
        backToMenu = pictureController.getSystemResources("backToMenu");
        startNewGame = pictureController.getSystemResources("startNewGame");

        //Erstelle Rectangles um die Area der beiden Knöpfe zu speichern, um später einer Kollision mit der Maus abzufangen
        backToMenuArea = new Rectangle(drawPoint.x, drawPoint.y, backToMenu.getWidth(null), backToMenu.getHeight(null));
            //Beide Rectangles starten vom gleichen Punkt. Es wird zum zweiten nur die Höhe der Bilder dazuaddiert
        startNewGameArea = new Rectangle(drawPoint.x, drawPoint.y + 10 + startNewGame.getHeight(null), startNewGame.getWidth(null), startNewGame.getHeight(null));
    }

    /**
     * Zeichne alle Komponenten der Scene
     * @param g2d Graphics Object, übergeben vom aufrufenden Panel
     */
    public void drawScene(Graphics2D g2d){
        //Zeichne beide Buttons vom Draw Point aus. Zweiter Button nutzt seine Bildergröße um die genaue Position zu bestimmen
        g2d.drawImage(backToMenu, drawPoint.x, drawPoint.y, null);
        g2d.drawImage(startNewGame, drawPoint.x, drawPoint.y + 10 + startNewGame.getHeight(null), null);
    }

    /**
     * Weitergabe des MouseEvent aus dem aufrufenden Panel
     * Fängt alle Überschneidungen der Knöpfe mit der Mauspositionen während eines Clicks ab
     * @param mouseEvent
     */
    public void sceneInteraction(MouseEvent mouseEvent){
        //Sobald der BackToMenu Button gedrückt wird, wird allen anderen Klassen über die SystemResourcen mitgetilt das kein aktives Spiel läuft
        if (backToMenuArea.contains(mouseEvent.getPoint())){
            SystemResources.isGameActive = false;
            goBackToMenu = true;
        }

        if (startNewGameArea.contains(mouseEvent.getPoint())){
            startANewGame = true;
        }
    }

    /**
     *
     * @return Liefer den Status zurück ob ein neues Spiel gestartet wird oder ob zum Menu zurückgekehrt werden soll und das Fenster geschlossen wird.
     */
    public boolean isGoBackToMenu() {
        return goBackToMenu;
    }

    /**
     *
     * @return Liefert zurück ob die aufruden Klase eine neues SPiel erzeugen soll.
     */
    public boolean isStartANewGame() {
        return startANewGame;
    }
}
