package de.shao.menu;

import de.shao.game.GameBoard;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame{

    private final int MENU_WIDTH = 700;
    private final int MENU_HEIGHT = 500;

    public MenuFrame() {
        this.add(new MenuPanel(this));
        this.setSize(new Dimension(MENU_WIDTH,MENU_HEIGHT));
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        this.setVisible(true);
    }
}
