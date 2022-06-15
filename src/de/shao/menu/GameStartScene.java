package de.shao.menu;

import de.shao.driver.SystemResources;

import java.awt.*;

public class GameStartScene {

    SystemResources systemResources;

    int i = 0;

    public GameStartScene(SystemResources systemResources){
       this.systemResources = systemResources;
    }

    public void drawScene(Graphics2D graphics2D){
        i++;
        System.out.println(i);
        graphics2D.drawImage(systemResources.getSystemImage("gamestart"),i,0,null);
        graphics2D.fillRect(i,700,200,200);
    }

}
