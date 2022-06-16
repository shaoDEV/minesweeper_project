package de.shao.menu;

import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.MouseEvent;

public class StartScene extends MenuScenes {

    SystemResources systemResources;

    Rectangle startButtonArea = new Rectangle(300, 351, 675, 198);

    public StartScene(SystemResources systemResources) {
        this.systemResources = systemResources;
    }

    @Override
    boolean drawScene(Graphics2D graphics2D) {
        graphics2D.drawImage(systemResources.getSystemImage("gamestart"), 300, 351, null);
        return isSceneActive;
    }

    @Override
    void mouseInteraction(MouseEvent mouseEvent) {
        if (startButtonArea.contains(mouseEvent.getPoint())) {
            isSceneActive = false;
        }
    }
}
