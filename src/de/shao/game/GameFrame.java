package de.shao.game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private JLayeredPane lPane = new JLayeredPane();
    private JPanel backgroundLayer = new GameBoardBackground();
    private JPanel gameLayer = new GameBoard();

    GameFrame(){
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(612,624));
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        lPane.setBounds(0,0,612,624);
        this.add(lPane, BorderLayout.CENTER);
        lPane.add(backgroundLayer, JLayeredPane.DEFAULT_LAYER, 0);
        lPane.add(gameLayer, JLayeredPane.PALETTE_LAYER, 0);

        this.setVisible(true);


//        //Quelle: http://dev.usw.at/manual/java/javainsel/javainsel_15_032.htm
//        Cursor c = getToolkit().createCustomCursor(
//                Pictures.CARROTCURSOR.image(true),
//                new Point(8,24), "Cursor" );
//        setCursor( c );
//        //Ende
    }
}
