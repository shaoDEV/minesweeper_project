package de.shao.menu;

import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class MenuScenes {

    SystemResources systemResources = null;
    boolean isSceneActive = true;

    abstract boolean drawScene(Graphics2D graphics2D);

    abstract void mouseInteraction(MouseEvent mouseEvent);

    abstract void keyInteraction(KeyEvent keyEvent);

    abstract MenuScenes getFollowUpScene();
}
