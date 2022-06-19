package de.shao.menu;

import de.shao.driver.SystemResources;
import de.shao.game.GameBoard;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame{

    private final int MENU_WIDTH = 1275;
    private final int MENU_HEIGHT = 900;

    public MenuFrame() {
        //Einfluss von Fremdcode -> https://stackoverflow.com/questions/11487369/jpanel-doesnt-response-to-keylistener-event
        MenuPanel menuPanel = new MenuPanel(this);
        menuPanel.addKeyListener(menuPanel); //Füge dem Panel sich selber als KeyListener hinzu
        menuPanel.setFocusable(true); //JPanel ist von natur aus nicht fokusierbar was ein KeyListener aber benötigt.
        this.add(menuPanel);
        //Ende des Fremdcodeeinflussed

        this.setSize(new Dimension(MENU_WIDTH,MENU_HEIGHT));
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);

        //PastaCode
        SystemResources.FramePoint = new Point(this.getLocation());
        System.out.println(SystemResources.FramePoint);
        //Ende vom PastaCode

        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        this.setVisible(true);
    }
}
