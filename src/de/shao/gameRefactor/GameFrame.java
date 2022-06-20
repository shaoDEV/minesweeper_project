package de.shao.gameRefactor;

import de.shao.driver.PictureController;
import de.shao.driver.SystemResources;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private int frameWidth = 0;
    private int frameHeight = 0;
    private PictureController pictureController = null;

    public GameFrame(int width, int height, PictureController pictureController, int bombcount, int fieldSize){
        frameWidth = width;
        frameHeight = height;

        this.pictureController = pictureController;

        add(new GameBoard(this, frameWidth, frameHeight, pictureController, bombcount, fieldSize));
        setSize(new Dimension(frameWidth,frameHeight));
        setUndecorated(true);
        setLocationRelativeTo(null);
        setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        //PastaCode
        SystemResources.GameFramePoint = new Point(this.getLocation());
        //Ende vom PastaCode

        setVisible(true);
    }

    private void initialize(){

    }

}
