package de.shao.gameRefactor;

import de.shao.driver.PictureController;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private int frameWidth = 0;
    private int frameHeight = 0;
    private PictureController pictureController = null;

    public GameFrame(int width, int height, PictureController pictureController){
        frameWidth = width;
        frameHeight = height;

        this.pictureController = pictureController;

        initialize();
    }

    private void initialize(){
        setSize(new Dimension(frameWidth,frameHeight));
        setUndecorated(true);
        setLocationRelativeTo(null);
        setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        setVisible(true);

        add(new GameBoard(frameWidth, frameHeight, pictureController));
    }

}
