package de.shao.gameRefactor;

import de.shao.driver.PictureController;
import de.shao.driver.SystemConstraints;
import de.shao.driver.SystemResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoard extends JPanel {

    //Datenbankinformationen
    private final String DB_URL = "jdbc:mysql://217.160.175.168:3306/minesweeperHS";
    private final String USER = "minesweeperMarvin";
    private final String PASS = "password";

    //PanelWerte
    private int panelWidth = 0;
    private int panelHeight = 0;
    private int fieldSize = 0;
    private int bombCount = 0;
    private PictureController pictureController = null;
    private Boolean activeGame = null;
    private boolean gameWon = false;

    //Das aufrufenden Frame
    private JFrame sirFrameALot = null;

    //Definiert an welcher stelle das Spielffeld auf dem Panel gezeichnet werden soll
    Point initPoint = null;

    //Alle benötigten Scenen
    BackgroundScene backgroundScene = null;
    GameScene gameScene = null;
    EndScene endScene = null;


    /**
     * Custom Konstruktor der den MouseListener hinzufügt und alle Werte in init deklariert.
     * @param frame Das aufrufende Frame.
     * @param width Feldbreite
     * @param height Feldhöhe
     * @param pictureController
     * @param bombCount Anzahl wie viele BOmben platziert werden sollen.
     * @param fieldSize Feldgröße anhand der die Funktionien durchlaufen, und Werte geladen werden.
     */
    public GameBoard(JFrame frame, int width, int height, PictureController pictureController, int bombCount, int fieldSize) {
        sirFrameALot = frame;
        panelWidth = width;
        panelHeight = height;
        this.bombCount = bombCount;
        this.fieldSize = fieldSize;

        this.pictureController = pictureController;

        setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        initialize();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                boardInteraction(e);
                getGameStatus();
                repaint();
            }
        });
    }

    /**
     * Init Methode zum definieren der Werte
     */
    private void initialize() {
        //Starte eine neue Background scene um den Hintergrund dauerhaft zeichnen zu können
        backgroundScene = new BackgroundScene(new Point(0, 0), pictureController, fieldSize);

        //Definiert den Punkt wo das Brett gezeichnet werden soll
        switch (fieldSize) {
            case 10 -> initPoint = SystemConstraints.TEN_TEN.point();
            case 16 -> initPoint = SystemConstraints.SIXTEEN_SIXTEEN.point();
            case 20 -> initPoint = SystemConstraints.TWENTY_TWENTY.point();
        }
        //Erschaffe ein neues Games mit den definierten Parametern.
        gameScene = new GameScene(
                initPoint,
                //Übergibt den rechten unteren Punkt mit indem die Koordinaten vom Bild berechnet werden.
                new Point(pictureController.getSystemResources("background" + fieldSize).getWidth(null), pictureController.getSystemResources("background" + fieldSize).getHeight(null)),
                fieldSize,
                bombCount,
                pictureController);

        //Starte einen Neuen Timer (erzeugt neuen Thread) um das Feld 60 mal die Sekunde neu zu zeichnen damit der Indikator und der Timer aktuell angezeigt werden kann.
        //Der TimerTask führt genau im 17ms die angeben Funktion auf. Schafft er dies nicht zwischen den Intervallen wartet er sollte er Mitten drin fertig werden auf den nächsten Timer Tick
        java.util.Timer menuTimer = new Timer();
        menuTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
            //Period von 17 bedeutet das alle 17 Milisekunden die Funktion neu aufgerufen wird. Dies ergibt einen Framerate von 60 Bildern
        }, 0, 17);

    }

    /**
     * Holt sich den aktuellen Status des Spieles.
     * Zeichnet die Endscene und speichert die gebnötigte Zeit in der Datenbank ab.
     */
    private void getGameStatus() {
        activeGame = gameScene.isActiveGame();
        gameWon = gameScene.isGameWon();

        if (activeGame != null && !activeGame) {
            //Erstellung der Endscene mit der Berechnung wo genau sie gezeichnet werden soll.
            int widthEndSceneItem = pictureController.getSystemResources("startNewGame").getWidth(null); //Breite der MenuObjekte in der Endscene zur Berechnung des Zeichenpunktes
            int widthActiveGameArea = fieldSize * pictureController.getBomb().getWidth(null); //Breite des aktiven Spielfeldes. Wird benötigt um die Endscene in der Mitte des Spielfeldes zu zeichnen
            endScene = new EndScene(pictureController, new Point(initPoint.x + ((widthActiveGameArea / 2) - (widthEndSceneItem / 2)), 200)); //Erstellung der Endscene mit berechnetem Zeichenpunkt
            String sql = "";
            if (fieldSize == 10)
                sql = "INSERT INTO `tenFieldHighscore`(`userID`, `username`, `secToFinish`) VALUES ('" + SystemResources.actualID + "','" + SystemResources.actualUsername + "'," + SystemResources.currentTimeInSec + ")";
            if (fieldSize == 16)
                sql = "INSERT INTO `sixtenFieldHighscore`(`userID`, `username`, `secToFinish`) VALUES ('" + SystemResources.actualID + "','" + SystemResources.actualUsername + "'," + SystemResources.currentTimeInSec + ")";
            if (fieldSize == 25)
                sql = "INSERT INTO `twentyFiveFieldHighscore`(`userID`, `username`, `secToFinish`) VALUES ('" + SystemResources.actualID + "','" + SystemResources.actualUsername + "'," + SystemResources.currentTimeInSec + ")";
            if (gameWon) {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            //endScene = new EndScene(pictureController, new Point(initPoint.x + ((widthActiveGameArea / 2) - (widthEndSceneItem / 2)), 200)); //Erstellung der Endscene mit berechnetem Zeichenpunkt
        }
    }

    /**
     * Ruft die Interaction Methode des Spieles auf und übergibt des auf dem Panel ausgelöäste Mouse Event
     * @param e MouseEvent vom MouseListener
     */
    private void boardInteraction(MouseEvent e) {

        if (activeGame == null || activeGame) gameScene.callSceneInteraction(e);
        else {
            endScene.sceneInteraction(e);
            //Damit das Frame unabhängig vom MenüFrame geschlossen werden kann wird das Übergeordnete Frame des Spieles mit übergeben um dieses Frame zu benenden.
            //Wird aufgerufen wenn das Spiel beendet wurde und es meldet das zum Menü zurück gekehrt werden soll.
            if (endScene.isGoBackToMenu()) sirFrameALot.dispose();
            //Sollte das SPiel den den Status neuesSPiel liefern wird ein neues Spielfeld initialisert und das alte überschrieben
            if (endScene.isStartANewGame()) {
                initialize();
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Zeichne alle Szenen nacheinander
        backgroundScene.drawScene(g2d);
        gameScene.drawScene(g2d);
        //Endescene nur zeichnen wenn das Game nicht mehr aktiv ist.
        if (activeGame != null && !activeGame) {
            endScene.drawScene(g2d);
        }
    }
}
