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
    /**
     * Klasse zum Handeln der Bilder im Spiel.
     */


    private static PictureController thisController = null; //Für die Singelton Abwandlung

    //Definition der Werte welcher Skin gewählt ist anhand der die Bilder geladen werden.
    private int cursor;
    private int blocks;
    private int numbers;
    private int bomb;
    private int flag;
    private int scaleToSize;

    //Sammlung der geladenen Bilder
        //Bilder in ArrayList um sie anhand nummerischer Werte durch die Liste zu gehen
    private ArrayList<Image> scaledBlockImages = new ArrayList<>();
    private ArrayList<Image> scaledNumberImages = new ArrayList<>();
        //System Bilder als HashMap zum vereinfachten Zugriff über Worte anstatt zahlen
    private HashMap<String, Image> systemResources = new HashMap<String, Image>();

    private Image cursorImage;
    private Image scaledBombImage;
    private Image scaledFlagImage;

    //dedizirtes Bild was ein Bild nicht geladen werden kann.
    private Image missingImage;

    /**
     * StandartKonstruktor mit dem die Bilder in Standdartwerten geladen werden
     */
    private PictureController() {
        cursor = 1;
        blocks = 1;
        numbers = 1;
        bomb = 1;
        flag = 1;
        scaleToSize = 64;
        imageLoader();
    }

    /**
     * Standartkonstruktor mit Definition der Größe auf was die Bilder skaliert werden.
     * @param scaleToSize Wert auf den skaliert wird
     */
    private PictureController(int scaleToSize) {
        cursor = 0;
        blocks = 0;
        numbers = 0;
        bomb = 0;
        flag = 0;
        this.scaleToSize = scaleToSize;
        imageLoader();
    }

    /**
     * Konstruktor mit Auswahl des skalierung und anderem Skinset
     * @param skinSet Skinsetdefinition (siehe resourcen (0,1,2)
     * @param scaleToSize Wert auf den skaliert wird
     */
    private PictureController(int skinSet, int scaleToSize) {
        cursor = skinSet;
        blocks = skinSet;
        numbers = skinSet;
        bomb = skinSet;
        flag = skinSet;
        this.scaleToSize = scaleToSize;
        imageLoader();
    }

    /**
     * Konstruktor zum kompleten freien konfigugieren des Skins
     * @param cursor aus welchen Skinordner der Cursor geladen wird
     * @param blocks aus welchen Skinordner die Bloecke geladen geladen wird
     * @param numbers aus welchen Skinordner die Nummern geladen wird
     * @param bomb aus welchen Skinordner die Bombe geladen wird
     * @param flag aus welchen Skinordner die Flage geladen wird
     * @param scaleToSize Wert auf den skaliert wird
     */
    private PictureController(int cursor, int blocks, int numbers, int bomb, int flag, int scaleToSize) {
        this.cursor = cursor;
        this.blocks = blocks;
        this.numbers = numbers;
        this.bomb = bomb;
        this.flag = flag;
        this.scaleToSize = scaleToSize;
        imageLoader();
    }

    /**
     * Erzeugt einen neuen PictureController bzw überschreibt den alten mit den übergebenden Werten.
     * @param skinSet Skinsetdefinition (siehe resourcen (0,1,2)
     * @param scaleToSize Wert auf den skaliert wird
     * @return gibt den neu erzeugten Controller zurück an den Aufrufer
     */
    public static PictureController getPictureController(int skinSet, int scaleToSize) {
        thisController = new PictureController(skinSet, scaleToSize);
        return thisController;
    }

    /**
     * Erzeugt einen neuen PictureController bzw überschreibt den alten mit den übergebenden Werten.
     * @param cursor aus welchen Skinordner der Cursor geladen wird
     * @param blocks aus welchen Skinordner die Bloecke geladen geladen wird
     * @param numbers aus welchen Skinordner die Nummern geladen wird
     * @param bomb aus welchen Skinordner die Bombe geladen wird
     * @param flag aus welchen Skinordner die Flage geladen wird
     * @param scaleToSize Wert auf den skaliert wird
     * @return @return gibt den neu erzeugten Controller zurück an den Aufrufer
     */
    public static PictureController getPictureController(int cursor, int blocks, int numbers, int bomb, int flag, int scaleToSize) {
        thisController = new PictureController(cursor, blocks, numbers, bomb, flag, scaleToSize);
        return thisController;
    }

    /**
     * Erzeugt einen neuen PictureController mithilfe des Standartkonstruktors ohne dedizierte Wertedefinition
     * @return @return gibt den neu erzeugten Controller zurück an den Aufrufer
     */
    public static PictureController getPictureController() {
        thisController = new PictureController();
        return thisController;
    }

    /**
     * * Erzeugt einen neuen PictureController mithilfe des Standartkonstruktors ausschlißelich mit Skalierungswert
     * @param scaleToSize Wert auf den skaliert wird
     * @return @return gibt den neu erzeugten Controller zurück an den Aufrufer
     */
    public static PictureController getPictureController(int scaleToSize){
        thisController = new PictureController(scaleToSize);
        return thisController;
    }

    /**
     * Hilfsmethode um alle Bilder zu laden
     */
    private void imageLoader() {
        loadMissingImage();
        loadCursor();
        loadBlocks();
        loadNumbers();
        loadBomb();
        loadFlag();
        loadSystemResources();
    }

    /**
     * Wird genutzt um geladene Bilder vor dem Speicher auf die gewünschte Größe zu skalieren
     * @param imageToScale Gibt das Bild an welches mit den in der Klasse hinterlegen Werten skaliert werden soll.
     * @return Gibt das skalierte Bild zurück
     */
    private Image scaleImage(BufferedImage imageToScale) {
        //Skaliert das übergeben Bild auf die übergeben Größe mithilfe eines Skalierungsverfahrens(SMOOTH SCALE)
        return imageToScale.getScaledInstance(scaleToSize, scaleToSize, Image.SCALE_SMOOTH);
    }

    /**
     * Lädt zu Beginn das MissingImage für den Fall das ein einzelnes Bild nicht richtig geladne werden konnte.
     * Es ist sicher zu stellen das ein "error.png" im System Pfad immer vorhanden ist.
     */
    private void loadMissingImage(){
        //Definition eines Zwischenspeichers für das zu ladene Bild.
        BufferedImage image;
        try {
            //Liest das BIld mithilfe von ImageIO und der Nutzung des ResHelpers (siehe ResHelper) ein.
            image = ImageIO.read(ResHelper.getResourcenStream("images/system/error.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Bevor das Bild in der Klasse final gespeichert wird, wird es auf die richtige Größe skaliert.
        missingImage = scaleImage(image);
    }

    /**
     * Lädt die Flage anhand der im Picture Controller hinterlegten Werte
     */
    private void loadFlag() {
        //Definition eines Zwischenspeichers für das zu ladene Bild.
        BufferedImage image;
        try {
            //Liest das BIld mithilfe von ImageIO und der Nutzung des ResHelpers (siehe ResHelper) ein.
            //Ruft das Bild aus dem definierte Skinset auf.
            image = ImageIO.read(ResHelper.getResourcenStream("images/" + flag + "/flag.png"));
        } catch (IOException e) {
            //Sollte es zu einem Fehler kommen wird das Missing Image in die Flage geladen um das Programm vor einem Fehler zu schützen.
            scaledFlagImage = missingImage;
            throw new RuntimeException(e);
        }
        //Bevor das Bild in der Klasse final gespeichert wird, wird es auf die richtige Größe skaliert.
        scaledFlagImage = scaleImage(image);
    }

    /**
     * Lädt den Custom Cursor anhand der definieten Werte im Picture Cntroller
     */
    private void loadCursor() {
        //Definition eines Zwischenspeichers für das zu ladene Bild.
        BufferedImage image;
        try {
            //Liest das BIld mithilfe von ImageIO und der Nutzung des ResHelpers (siehe ResHelper) ein.
            //Ruft das Bild aus dem definierte Skinset auf.
            image = ImageIO.read(ResHelper.getResourcenStream("images/" + cursor + "/cursor.png"));
        } catch (IOException e) {
            //Sollte es zu einem Fehler kommen wird das Missing Image in die Flage geladen um das Programm vor einem Fehler zu schützen.
            cursorImage = missingImage;
            throw new RuntimeException(e);
        }
        //Bevor das Bild in der Klasse final gespeichert wird, wird es auf die richtige Größe skaliert.
        cursorImage = image;
    }

    /**
     * Lädt beide Blöcke und speichert
     */
    private void loadBlocks() {
        //Definition eines Zwischenspeichers für das zu ladene Bild.
        BufferedImage image;
        try {
            //Lädt das beide blocktypen nach einander.
            //Ruft das Bild aus dem definierten Skinset auf.
            image = ImageIO.read(ResHelper.getResourcenStream("images/" + flag + "/block_closed.png"));
            //Das geladene Bild wird skaliert und in die ArrayListe für die Bloecke gespeichert.
            scaledBlockImages.add(scaleImage(image));
            image = ImageIO.read(ResHelper.getResourcenStream("images/" + flag + "/block_open.png"));
            scaledBlockImages.add(scaleImage(image));
        } catch (IOException e) {
            //Beim Fehler des laden wird das Missing Image geladen.
            scaledBlockImages.add(missingImage);
            scaledBlockImages.add(missingImage);
            throw new RuntimeException(e);
        }
    }

    /**
     * Lädt alle Nummern nacheinander ein, skaliert Sie und speichert Sie in der Im Konstruktor definierten ArrayList
     */
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

    /**
     * Lädt das Bombenbild anhand des ausgewählten Skins und speichert es skaliert ab.
     */
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

    /**
     * Lädt alles für das Spiel wichtigen System Resourceen ein, skaliert Sie und speichert Sie in der im Klassenkonstruktor deifnierten HashMap.
     */
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

    /**
     * @param resourceName Schlüsselname der gewünschten SystemResource
     * @return Gibt die System Rescouce anhand des übergebenen SChlüssels zurück
     */
    public Image getSystemResources(String resourceName){
        return systemResources.get(resourceName);
    }

    /**
     * @return Gibt Das Bild der Bombe zurück
     */
    public Image getBomb(){
        return scaledBombImage;
    }

    /**
     * Überprüft ob Die übergebene Zahl im Kontext valide ist und gibt das passende Bild an den Aufrufer zurück
     * @param numberIdentifier Übergabe der Zahl des gewünschten Bildes als Char
     * @return Gibt Bild an den Aufrufer zurück
     */
    public Image getNumber(char numberIdentifier) {
        int numberIdentifierAsInt = Character.getNumericValue(numberIdentifier);
        //Gibt das gewünschte Bild zurück
        if (numberIdentifierAsInt < 9 && numberIdentifierAsInt > 0) return scaledNumberImages.get(numberIdentifierAsInt-1);
        //Ist die übergeben Zahl nicht valide, wird das Missing Image zurück gegeben werden
        return missingImage;
    }

    /**#
     * Gibt anhand des Übergabe Parameters das Bild für den geschlossenen oder geöffneten Block zurück.
     * @param openBlock Übergabe ob der geöffnete oder geschlossene Block zurpc gebgeben werden soll
     * @return Gibt das gewünschte Bild zurück
     */
    public Image getBlock(boolean openBlock){
        if (openBlock) return scaledBlockImages.get(1);
        else return scaledBlockImages.get(0);
    }

    /**
     * @return Gibt den Bild vom Cursor zurück
     */
    public Image getCursor(){
        return cursorImage;
    }

    /**
     * @return Gibt das Bild der Flage zurück
     */
    public Image getFlag(){
        return scaledFlagImage;
    }
}





















