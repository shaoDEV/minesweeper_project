package de.shao.game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    GameFrame(){
        this.add(new GameBoard());
        this.setSize(new Dimension(612,624));
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        this.setVisible(true);


//        //Quelle: http://dev.usw.at/manual/java/javainsel/javainsel_15_032.htm
//        Cursor c = getToolkit().createCustomCursor(
//                Pictures.CARROTCURSOR.image(true),
//                new Point(8,24), "Cursor" );
//        setCursor( c );
//        //Ende
    }
}
