package de.shao.menu;

import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class StartScene extends MenuScenes {

    SystemResources systemResources;

    Rectangle startButtonArea = new Rectangle(300, 351, 675, 198);
    boolean startAnimation = false;
    int yPosition = 351;

    public StartScene(SystemResources systemResources) {
        this.systemResources = systemResources;
    }

    @Override
    boolean drawScene(Graphics2D graphics2D) {
        if (!startAnimation) graphics2D.drawImage(systemResources.getSystemImage("gamestart"), 300, 351, null);
        else {
            yPosition -= 12;
            graphics2D.drawImage(systemResources.getSystemImage("gamestart_fade"),300,yPosition,null);
            if (yPosition <= -454) isSceneActive = false;
        }
        return isSceneActive;
    }

    @Override
    void mouseInteraction(MouseEvent mouseEvent) {
        if (startButtonArea.contains(mouseEvent.getPoint())) {
            startAnimation = true;
        }
    }


    @Override
    void keyInteraction(KeyEvent keyEvent) {

    }

    @Override
    MenuScenes getFollowUpScene() {
        return new ProfilScene(systemResources);
    }
}
