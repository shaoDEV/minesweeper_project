package de.shao.menu;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MainMenuScene extends MenuScenes{

    public MainMenuScene(int profilID){

    }

    @Override
    boolean drawScene(Graphics2D graphics2D) {
        return isSceneActive;
    }

    @Override
    void mouseInteraction(MouseEvent mouseEvent) {

    }

    @Override
    MenuScenes getFollowUpScene() {
        return null;
    }
}
