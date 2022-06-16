package de.shao.menu;

import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.MouseEvent;

public class StartFadeOutScene extends MenuScenes {

    SystemResources systemResources;
    int yPosition = 351;

    public StartFadeOutScene(SystemResources systemResources){
       this.systemResources = systemResources;
    }

    @Override
    boolean drawScene(Graphics2D graphics2D) {
        yPosition -= 12;
        graphics2D.drawImage(systemResources.getSystemImage("gamestart_fade"),300,yPosition,null);
        if (yPosition <= -454) isSceneActive = false;
        return isSceneActive;
    }

    @Override
    void mouseInteraction(MouseEvent mouseEvent) {

    }
}
