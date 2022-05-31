package de.shao.game;

import javax.swing.*;
import java.awt.*;

public class PanelHandler extends JLayeredPane {

    private final int BOARD_WIDTH = 612;
    private final int BOARD_HEIGHT = 624;

    public PanelHandler(){
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
    }
}
