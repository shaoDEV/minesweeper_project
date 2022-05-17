package de.shao.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameBoard extends JPanel {

    private final int FIELD_MEASURE = 48; //Große der Bilder
    private final int FIELD_SIZE = 20;

    private final int BOARD_WIDTH = FIELD_MEASURE * FIELD_SIZE + 1;
    private final int BOARD_HEIGHT = BOARD_WIDTH;

    private Field[][] field;

    GameBoard() {
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(Color.GRAY);
        field = new Field[FIELD_SIZE][FIELD_SIZE];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        init((Graphics2D) g);
    }

    private void init(Graphics2D g2d){
        for (int verticalPosition = 0; verticalPosition < field.length; verticalPosition++){
            for (int horizontalPosition = 0; horizontalPosition < field[verticalPosition].length; horizontalPosition++){
                field[horizontalPosition][verticalPosition] = new Field(horizontalPosition*FIELD_MEASURE, verticalPosition*FIELD_MEASURE, FIELD_MEASURE);
                field[horizontalPosition][verticalPosition].drawField(g2d);
            }
        }
    }
}

