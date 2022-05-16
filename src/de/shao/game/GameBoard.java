package de.shao.game;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {

    private final int FIELD_MEASURE = 48; //Große der Bilder
    private final int FIELD_SIZE = 10;

    private final int BOARD_WIDTH = FIELD_MEASURE * FIELD_SIZE;
    private final int BOARD_HEIGHT = BOARD_WIDTH;

    GameBoard() {
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(Color.GRAY);
    }
}

