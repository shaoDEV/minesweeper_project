package de.shao.game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    GameFrame(){
        this.add(new GameBoard());
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        //Quelle: http://dev.usw.at/manual/java/javainsel/javainsel_15_032.htm
        Cursor c = getToolkit().createCustomCursor(
                new ImageIcon( "resources/CARROT_CURSOR.png" ).getImage(),
                new Point(10,10), "Cursor" );
        setCursor( c );
        //Ende
    }
}