package de.shao.menu;

import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ProfilScene extends MenuScenes{

    public ProfilScene(SystemResources systemResources){
        this.systemResources = systemResources;
    }
    @Override
    boolean drawScene(Graphics2D graphics2D) {
        graphics2D.drawImage(systemResources.getSystemImage("mainframe_profiles"), 0, 0, null);
        return true;
    }

    @Override
    void mouseInteraction(MouseEvent mouseEvent) {

    }
}
